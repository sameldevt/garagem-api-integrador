package br.com.almavivasolutions.integrador.model.service;

import br.com.almavivasolutions.integrador.model.entity.Colaborador;
import br.com.almavivasolutions.integrador.model.entity.Fabricante;
import br.com.almavivasolutions.integrador.model.entity.certificado.Certificado;
import br.com.almavivasolutions.integrador.model.entity.certificado.CertificadoAvaliacao;
import br.com.almavivasolutions.integrador.model.entity.certificado.CertificadoFactory;
import br.com.almavivasolutions.integrador.model.exception.InvalidHeaderException;
import br.com.almavivasolutions.integrador.model.repository.CertificadoRepository;
import br.com.almavivasolutions.integrador.util.Report;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    public Report inserirUpload(InputStream uploadStream, String fabricante) {
        Report report = new Report();
        List<CertificadoAvaliacao> listaParaInserir = readUpload(uploadStream, fabricante, report);
        //certificadoRepository.saveAll(listaParaInserir);
        report.endRequest();
        return report;
    }

    private List<CertificadoAvaliacao> readUpload(InputStream uploadStream, String fabricante, Report report) {
        List<CertificadoAvaliacao> listaParaInserir = new ArrayList<>();
        Map<String, Colaborador> colaboradores = colaboradorService.buscarTodos();
        Fabricante fabricanteObjeto = fabricanteService.buscar(fabricante);
        int certificateCount = 0;
        try (CSVReader reader = new CSVReader(new InputStreamReader(uploadStream))) {
            String[] header = reader.readNext();
            Certificado certificado = CertificadoFactory.get(fabricante);

            checkUpload(certificado, header);

            String[] line;
            while ((line = reader.readNext()) != null) {
                String emailColaborador = "";
                String nomeCertificado = "";

                CertificadoAvaliacao certificadoAvaliacao = certificado.deserialize(List.of(line))
                        .toCertificadoAvaliacao();
                try {
                    emailColaborador = certificadoAvaliacao.getEmail();
                    nomeCertificado = certificadoAvaliacao.getNome();
                    Colaborador colaborador = colaboradores.get(certificadoAvaliacao.getEmail());
                    if (colaborador == null) {
                        throw new NullPointerException();
                    }
                    certificadoAvaliacao.setColaborador(colaborador);
                    certificadoAvaliacao.setFabricante(fabricanteObjeto);
                    listaParaInserir.add(certificadoAvaliacao);
                    report.incrementCertificatesInserted();
                } catch (NullPointerException e) {
                    report.addCollaboratorNotFound(emailColaborador);
                    report.addCertificateNotInserted(nomeCertificado);
                }
            }

            certificateCount = (int) reader.getLinesRead() - 1;
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        report.setTotalCertificates(certificateCount);
        return listaParaInserir;
    }

    private void checkUpload(Certificado certificado, String[] uploadHeader) {
        String fileHeader = String.join(",", uploadHeader);
        String objectHeader = certificado.getHeader();

        System.out.println(fileHeader);
        System.out.println(objectHeader);
        if(!objectHeader.equals(fileHeader)){
           throw new InvalidHeaderException("fabricante e arquivo divergem");
        }
    }
}
