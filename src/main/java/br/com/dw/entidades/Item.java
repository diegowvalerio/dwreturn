package br.com.dw.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tbitem")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Lancamento lancamento;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Componente componente;
	
	@ManyToOne
	private Tipo tipo;
	
	@ManyToOne
	private Defeito defeito;
	
	@ManyToOne
	private Efeito efeito;
	
	@ManyToOne
	private Responsavel responsavel;
	
	@ManyToOne
	private Marca marca;
		
	@Column(nullable=false,columnDefinition="varchar(50)")
	private String descricao;
	
	@Column(nullable=false,columnDefinition="varchar(50)")
	private String causa;
	
	@Column(nullable=false,columnDefinition="varchar(50)")
	private String componente_defeituoso;
	
	@Column(nullable=false,columnDefinition="varchar(250)")
	private String observacao;
	
	@Column(nullable=false) 
	@Temporal(TemporalType.DATE)
	private Date dtcadastro;
			

	public Item() {
		super();
	}


	public Lancamento getLancamento() {
		return lancamento;
	}


	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Componente getComponente() {
		return componente;
	}


	public void setComponente(Componente componente) {
		this.componente = componente;
	}


	public Tipo getTipo() {
		return tipo;
	}


	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


	public Defeito getDefeito() {
		return defeito;
	}


	public void setDefeito(Defeito defeito) {
		this.defeito = defeito;
	}


	public Efeito getEfeito() {
		return efeito;
	}


	public void setEfeito(Efeito efeito) {
		this.efeito = efeito;
	}


	public Responsavel getResponsavel() {
		return responsavel;
	}


	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}


	public Marca getMarca() {
		return marca;
	}


	public void setMarca(Marca marca) {
		this.marca = marca;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getCausa() {
		return causa;
	}


	public void setCausa(String causa) {
		this.causa = causa;
	}


	public String getComponente_defeituoso() {
		return componente_defeituoso;
	}


	public void setComponente_defeituoso(String componente_defeituoso) {
		this.componente_defeituoso = componente_defeituoso;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public Date getDtcadastro() {
		return dtcadastro;
	}


	public void setDtcadastro(Date dtcadastro) {
		this.dtcadastro = dtcadastro;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lancamento == null) ? 0 : lancamento.hashCode());
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
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lancamento == null) {
			if (other.lancamento != null)
				return false;
		} else if (!lancamento.equals(other.lancamento))
			return false;
		return true;
	}
	
	

}
