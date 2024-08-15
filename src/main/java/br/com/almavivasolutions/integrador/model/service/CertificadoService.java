package br.com.almavivasolutions.integrador.model.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.almavivasolutions.integrador.model.entity.Colaborador;
import br.com.almavivasolutions.integrador.model.entity.Fabricante;
import br.com.almavivasolutions.integrador.model.entity.certificado.CertificadoAvaliacao;
import br.com.almavivasolutions.integrador.model.entity.certificado.CertificadoFactory;
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
		
		try (Scanner scanner = new Scanner(uploadStream)) {
		        if (scanner.hasNextLine()) {
		            scanner.nextLine(); 
		        }

		        String line;
		        while (scanner.hasNextLine()) {
		            line = scanner.nextLine();
		            CertificadoAvaliacao certificado = CertificadoFactory
		            		.getCertificado(fabricante)
		            		.build(line)
		            		.toCertificadoAvaliacao();
		            
		            certificado.setFabricante(fabricanteObjeto);
		            Colaborador colaborador = colaboradorService.buscar(certificado.getEmail());
		            certificado.setColaborador(colaborador);
		            listaParaInserir.add(certificado);
		        }
		    }
		
		return certificadoRepository.saveAll(listaParaInserir);
	}
	
}
