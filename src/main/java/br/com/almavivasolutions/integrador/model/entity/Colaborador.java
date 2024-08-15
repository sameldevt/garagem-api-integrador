package br.com.almavivasolutions.integrador.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_COLABORADOR")
public class Colaborador {
	
	@Id
	@Column(name = "PK_COLABORADOR")
	private Long id;
	
	@NotNull
	@Column(name = "NOME")
	private String nome;
	
	@NotNull
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "CARGO_E_NIVEL")
	private String cargoNivel;
	
	@Column(name = "SUPERIOR")
	private String superior;
		
	@Column(name = "DATA_CONTRATACAO")
	private LocalDate dataContratacao;
	
	@Column(name = "DATA_DESLIGAMENTO")
	private LocalDate dataDesligamento;
	
	@Column(name = "MATRICULA")
	private String matricula;
	
	@Column(name = "DEPARTAMENTO")
	private String departamento;
	
	@Column(name = "ATIVO")
	private Boolean ativo;
	
	
	public Colaborador() {
		super();
	}
	
	public String getNome() {
		return nome;
	}


	public String getDepartamento() {
		return departamento;
	}


}
