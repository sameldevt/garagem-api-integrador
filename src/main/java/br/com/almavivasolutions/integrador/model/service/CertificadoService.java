package br.com.almavivasolutions.integrador.model.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.almavivasolutions.integrador.model.entity.Colaborador;
import br.com.almavivasolutions.integrador.model.entity.Fabricante;
import br.com.almavivasolutions.integrador.model.entity.certificado.Certificado;
import br.com.almavivasolutions.integrador.model.entity.certificado.CertificadoAvaliacao;
import br.com.almavivasolutions.integrador.model.entity.certificado.CertificadoFactory;
import br.com.almavivasolutions.integrador.model.exception.ResourceNotFoundException;
import br.com.almavivasolutions.integrador.model.repository.CertificadoRepository;

@Service
public class CertificadoService {

	@Autowired
	private CertificadoRepository certificadoRepository;
	
	@Autowired
	private FabricanteService fabricanteService;
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	public List<CertificadoAvaliacao> inserirArquivo(InputStream uploadStream, String fabricante) {
		Fabricante fabricanteObjeto = fabricanteService.buscar(fabricante);
		List<CertificadoAvaliacao> listaParaInserir = new ArrayList<>();
		Map<String, Colaborador> colaboradores = colaboradorService.buscarTodos();
		
		Scanner scanner = new Scanner(uploadStream);
        if (scanner.hasNextLine()) {
            scanner.nextLine(); 
        }

        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            CertificadoAvaliacao certificado;
            try {
            	certificado = CertificadoFactory
            		.getCertificado(fabricante)
            		.deserialize(line)
            		.toCertificadoAvaliacao();
            } catch (NullPointerException e) {
            	continue;
            }
            
            Colaborador colaborador = colaboradores.get(certificado.getEmail());
            checkColaborador(colaborador, certificado);
            
            certificado.setColaborador(colaborador);
            certificado.setFabricante(fabricanteObjeto);
            listaParaInserir.add(certificado);
        }

        scanner.close();
		return certificadoRepository.saveAll(listaParaInserir);
	}
	
	private void checkColaborador(Colaborador colaborador, CertificadoAvaliacao certificado) {
		if(colaborador == null) {
        	//throw new ResourceNotFoundException("Colaborador " + certificado.getEmail() + " não encontrado.");
        }
	}
}
