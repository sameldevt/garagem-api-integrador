package br.com.almavivasolutions.integrador.model.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.almavivasolutions.integrador.model.service.CertificadoService;

@RestController
@RequestMapping("/certificados/enviarArquivo")
public class CertificadoController {
	
	private final String header = ",  ,Accreditation Name_New,Level,Programa,Function,Accreditation Holder: Full Name,Accreditation Holder: Email,Usuário Ativo,Credential Earned Date,Credential Expiration Date,Data Limite para Renovação,Observação";

	@Autowired
	private CertificadoService certificadoService;
	
	@PostMapping
	public ResponseEntity<String> handleFileUpload(
	        @RequestParam("file") MultipartFile file,
	        @RequestParam("fabricante") String fabricante) throws IOException{
		
	    certificadoService.inserirArquivo(file.getInputStream(), fabricante);
	    return ResponseEntity.ok("Arquivo processado com sucesso!");
	}
}
