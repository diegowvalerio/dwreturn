package br.com.dw.entidades.seven;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import com.google.gson.annotations.Expose;


public class SevenCliente implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	private Integer id;
	private String nome;
	private String situacao;
	
	public SevenCliente() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	
   
}
