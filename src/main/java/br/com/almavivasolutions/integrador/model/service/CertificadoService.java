package br.com.almavivasolutions.integrador.model.service;

import br.com.almavivasolutions.integrador.model.entity.Colaborador;
import br.com.almavivasolutions.integrador.model.entity.Fabricante;
import br.com.almavivasolutions.integrador.model.entity.certificado.Certificado;
import br.com.almavivasolutions.integrador.model.entity.certificado.CertificadoAvaliacao;
import br.com.almavivasolutions.integrador.model.entity.certificado.CertificadoFactory;
import br.com.almavivasolutions.integrador.model.exception.InvalidHeaderException;
import br.com.almavivasolutions.integrador.model.repository.CertificadoRepository;
import br.com.almavivasolutions.integrador.utils.logger.ApiLogger;
import br.com.almavivasolutions.integrador.utils.report.Report;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CertificadoService {

    @Autowired
    private CertificadoRepository certificadoRepository;

    @Autowired
    private FabricanteService fabricanteService;

    @Autowired
    private ColaboradorService colaboradorService;
    
    private Map<String, Colaborador> colaboradores;
    
    private final List<CertificadoAvaliacao> listaParaInserir = new ArrayList<>();
    
    private Report report;
    
    public Report insertUpload(InputStream uploadStream, String fabricante) {
    	initReportAndData();
        readUpload(uploadStream, fabricante, report);
        certificadoRepository.saveAll(listaParaInserir);
        report.endRequest();
        return report;
    }
    
    private void initReportAndData() {
        report = new Report();
        colaboradores = colaboradorService.buscarTodos();
    }

    private void readUpload(InputStream uploadStream, String fabricante, Report report) {
    	report.setNomeFabricante(fabricante);
        Fabricante fabricanteObject = fabricanteService.buscar(fabricante);
        ApiLogger.logRequestDetails("Processando arquivo de upload...");
        int certificateCount = 0;

        try (CSVReader reader = new CSVReader(new InputStreamReader(uploadStream))) {
            String[] uploadHeader = reader.readNext();
            Certificado certificado = CertificadoFactory.get(fabricante);
            checkUpload(certificado, uploadHeader);
            
            String[] line;
            while ((line = reader.readNext()) != null) {
                certificateCount++;
                //line = cleanLine(line);
                CertificadoAvaliacao certificadoAvaliacao = certificado
                        .deserialize(List.of(line))
                        .toCertificadoAvaliacao();
                
                String nomeColaborador = certificado.getCollaboratorName();
                completeCertificadoAvaliacaoConstruction(certificadoAvaliacao, fabricanteObject, nomeColaborador);
            }

        } catch (IOException | CsvValidationException e) {
        	ApiLogger.logRequestError("Algo deu errado durante a leitura do upload!");
            throw new RuntimeException(e);
        }

        report.setTotalCertificates(certificateCount);
    }

    private void checkUpload(Certificado certificado, String[] uploadHeader) {
    	ApiLogger.logRequestDetails("Verificando compatibilidade entre arquivo e fabricante...");
        String fileHeader = String.join(",", uploadHeader);
        String objectHeader = certificado.getHeader();

        if(!objectHeader.equals(fileHeader)){
           throw new InvalidHeaderException("Fabricante e arquivo incompatíveis.");
        }
    }
    
    private String[] cleanLine(String[] oldLine) {
    	return String.join(",", oldLine).replace("\n", "").concat(" ,").split(",");
    }
    
    private void checkColaborador(Colaborador colaborador) {
    	if (colaborador == null) {
            throw new NullPointerException();
        }
    	if(colaborador.getEmail().isBlank()) {
    		throw new NullPointerException();
    	}
    }
    
    private void completeCertificadoAvaliacaoConstruction(
    		CertificadoAvaliacao certificadoAvaliacao, 
    		Fabricante fabricante,
    		String nomeColaborador) {
    	String emailColaborador = "";
    	String nomeCertificado = "";
    	
    	try {
            emailColaborador = certificadoAvaliacao.getEmail();
            nomeCertificado = certificadoAvaliacao.getNome();
            Colaborador colaborador = colaboradores.get(certificadoAvaliacao.getEmail());
            checkColaborador(colaborador);
            certificadoAvaliacao.setColaborador(colaborador);
            certificadoAvaliacao.setFabricante(fabricante);
            listaParaInserir.add(certificadoAvaliacao);
            report.incrementCertificatesInserted();
        } catch (NullPointerException e) {
        	report.addToCertificateNotInsertedList(nomeCertificado, emailColaborador, nomeColaborador);
        }
    }
}
