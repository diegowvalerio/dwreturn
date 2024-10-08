package br.com.dw.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tbproduto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false,columnDefinition="varchar(250)")
	private String referencia;
	@Column(nullable=false,columnDefinition="varchar(250)")
	private String nome;
	@Column(nullable=false) 
	@Temporal(TemporalType.DATE)
	private Date dtcadastro;
	@Column(nullable=false, columnDefinition= "BOOLEAN DEFAULT TRUE")
	private Boolean situacao;
	@Column(nullable=false,columnDefinition="varchar(20)")
	private String codigoseven;		
	
	private Double vlcusto;

	public Produto() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getReferencia() {
		return referencia;
	}


	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getDtcadastro() {
		return dtcadastro;
	}


	public void setDtcadastro(Date dtcadastro) {
		this.dtcadastro = dtcadastro;
	}


	public Boolean getSituacao() {
		return situacao;
	}


	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}


	public String getCodigoseven() {
		return codigoseven;
	}


	public void setCodigoseven(String codigoseven) {
		this.codigoseven = codigoseven;
	}


	public Double getVlcusto() {
		return vlcusto;
	}


	public void setVlcusto(Double vlcusto) {
		this.vlcusto = vlcusto;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}   
	
	
   
}
