package br.com.almavivasolutions.integrador.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_FABRICANTE")
public class Fabricante {

	@Id
	@Column(name = "PK_FABRICANTE")
	private Long id;

	@NotNull
	@Column(name = "NOME")
	private String nome;

	public Fabricante() {
		super();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}