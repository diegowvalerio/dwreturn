package br.com.dw.relatorios.entidades;

import java.io.Serializable;
import java.lang.String;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Defeito_qtde implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private BigInteger total;
	private BigDecimal  percentual;	
	private String nome2;

	public Defeito_qtde() {
		super();
	}

	public String getNome2() {
		return nome2;
	}

	public void setNome2(String nome2) {
		this.nome2 = nome2;
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

	public BigInteger getTotal() {
		return total;
	}

	public void setTotal(BigInteger total) {
		this.total = total;
	}

	public BigDecimal  getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal  percentual) {
		this.percentual = percentual;
	}
}
