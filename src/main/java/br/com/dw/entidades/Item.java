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
	
	@Column(nullable=false)
	private int qtde;
	
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


	public int getQtde() {
		return qtde;
	}


	public void setQtde(int qtde) {
		this.qtde = qtde;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((causa == null) ? 0 : causa.hashCode());
		result = prime * result + ((componente == null) ? 0 : componente.hashCode());
		result = prime * result + ((componente_defeituoso == null) ? 0 : componente_defeituoso.hashCode());
		result = prime * result + ((defeito == null) ? 0 : defeito.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((dtcadastro == null) ? 0 : dtcadastro.hashCode());
		result = prime * result + ((efeito == null) ? 0 : efeito.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lancamento == null) ? 0 : lancamento.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + qtde;
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		if (causa == null) {
			if (other.causa != null)
				return false;
		} else if (!causa.equals(other.causa))
			return false;
		if (componente == null) {
			if (other.componente != null)
				return false;
		} else if (!componente.equals(other.componente))
			return false;
		if (componente_defeituoso == null) {
			if (other.componente_defeituoso != null)
				return false;
		} else if (!componente_defeituoso.equals(other.componente_defeituoso))
			return false;
		if (defeito == null) {
			if (other.defeito != null)
				return false;
		} else if (!defeito.equals(other.defeito))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (dtcadastro == null) {
			if (other.dtcadastro != null)
				return false;
		} else if (!dtcadastro.equals(other.dtcadastro))
			return false;
		if (efeito == null) {
			if (other.efeito != null)
				return false;
		} else if (!efeito.equals(other.efeito))
			return false;
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
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (qtde != other.qtde)
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}


	
	

}
