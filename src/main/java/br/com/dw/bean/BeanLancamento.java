package br.com.dw.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.dw.entidades.Cliente;
import br.com.dw.entidades.Componente;
import br.com.dw.entidades.Defeito;
import br.com.dw.entidades.Efeito;
import br.com.dw.entidades.Emissor;
import br.com.dw.entidades.Item;
import br.com.dw.entidades.Lancamento;
import br.com.dw.entidades.Marca;
import br.com.dw.entidades.Produto;
import br.com.dw.entidades.Responsavel;
import br.com.dw.entidades.Tipo;
import br.com.dw.entidades.Vendedor;
import br.com.dw.servico.ServicoCliente;
import br.com.dw.servico.ServicoComponente;
import br.com.dw.servico.ServicoDefeito;
import br.com.dw.servico.ServicoEfeito;
import br.com.dw.servico.ServicoEmissor;
import br.com.dw.servico.ServicoLancamento;
import br.com.dw.servico.ServicoMarca;
import br.com.dw.servico.ServicoProduto;
import br.com.dw.servico.ServicoResponsavel;
import br.com.dw.servico.ServicoTipo;
import br.com.dw.servico.ServicoVendedor;
import br.com.dw.util.FacesMessageUtil;

@Named
@ViewScoped
public class BeanLancamento implements Serializable{
	private static final long serialVersionUID = 1L;

	private Lancamento lancamento = new Lancamento();
	private Item item = new Item();
	@Inject
	private ServicoLancamento servico;
	private List<Lancamento> lista = new ArrayList<>();
	private List<Item> lista_items = new ArrayList<>();
	
	//opções
	@Inject
	private ServicoEmissor servico_emissor;
	private List<Emissor> lista_emissor = new ArrayList<>();
	
	@Inject
	private ServicoCliente servico_cliente;
	private List<Cliente> lista_cliente = new ArrayList<>();
	
	@Inject
	private ServicoComponente servico_componente;
	private List<Componente> lista_componente = new ArrayList<>();
	
	@Inject
	private ServicoDefeito servico_defeito;
	private List<Defeito> lista_defeito = new ArrayList<>();
	
	@Inject
	private ServicoEfeito servico_efeito;
	private List<Efeito> lista_efeito = new ArrayList<>();
	
	@Inject
	private ServicoMarca servico_marca;
	private List<Marca> lista_marca = new ArrayList<>();
	
	@Inject
	private ServicoProduto servico_produto;
	private List<Produto> lista_produto = new ArrayList<>();
	
	@Inject
	private ServicoResponsavel servico_responsavel;
	private List<Responsavel> lista_responsavel = new ArrayList<>();
	
	@Inject
	private ServicoTipo servico_tipo;
	private List<Tipo> lista_tipo = new ArrayList<>();
	
	@Inject
	private ServicoVendedor servico_vendedor;
	private List<Vendedor> lista_vendedor = new ArrayList<>();

	private Date data;
	
	public BeanLancamento() {
		data = new Date();		
	}
	

	@PostConstruct
	public void carregar(){
		
		lista = servico.consultar();
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession();
		this.lancamento = (Lancamento) session.getAttribute("lancamentoAux");
		
		if(this.lancamento == null) {
			this.lancamento = new Lancamento();
			this.lancamento = getLancamento();
			this.lancamento.setDtanalise(data);
		}
		this.lista_items = this.lancamento.getItems();
		
		
		lista_cliente = servico_cliente.consultar();
		lista_componente = servico_componente.consultar();
		lista_defeito = servico_defeito.consultar();
		lista_emissor = servico_emissor.consultar();
		lista_marca = servico_marca.consultar();
		lista_produto = servico_produto.consultar();
		lista_responsavel = servico_responsavel.consultar();
		lista_tipo = servico_tipo.consultar();
		lista_vendedor = servico_vendedor.consultar();
		lista_efeito = servico_efeito.consultar();		
		
		session.removeAttribute("lancamentoAux");
	}	
	
	public String salvar(){
		try{
			lancamento.setItems(lista_items);
			servico.salvar(lancamento);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
			
		return "lista-lancamento";
	}
	
	public String excluir(){
		try{
			servico.excluir(lancamento.getId());
			}catch(Exception e){
				if(e.getCause().toString().contains("ConstraintViolationException")){
					FacesMessageUtil.addMensagemError("Registro utilizado em outro local!");
				}else{
					FacesMessageUtil.addMensagemError(e.getCause().toString());
				}
			}
			lista = servico.consultar();

		return "lista-lancamento";	
	}
	
	/* editar */
	public String encaminha() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("lancamentoAux", this.lancamento);

		return "edita-lancamento";
	}
	
	//items
	public void addNovoItem() {
		if (this.lancamento.getCliente() == null || this.lancamento.getVendedor() == null || this.lancamento.getEmissor() == null) {
			FacesMessageUtil.addMensagemError("O cabeçalho do Lancamento precisa estar preenchido para continuar !");
		} else {
			item = new Item();
			item.setLancamento(lancamento);
			item.setDtcadastro(data);
		}
	}
	
	public void removerItem() {
		int index = lista_items.indexOf(item);
		if (index > -1) {
			this.lista_items.remove(index);
		}
	}
	
	public void editarsalvarItem() {

		if (item.getCausa() == null || item.getDescricao() == null ) {
			FacesMessageUtil.addMensagemError("Preencha os dados corretamente");
		} else {
			try {
				int index = lista_items.indexOf(item);
				if (index > -1) {
					lista_items.remove(index);
					item.setLancamento(lancamento);
					lista_items.add(index, item);
				} else {
					item.setLancamento(lancamento);
					lista_items.add(item);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			item = new Item();
		}
	}	
	
	public String cancelar(){
		return "lista-lancamento";
	}

//geters
	public Lancamento getLancamento() {
		return lancamento;
	}


	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public List<Lancamento> getLista() {
		return lista;
	}


	public void setLista(List<Lancamento> lista) {
		this.lista = lista;
	}


	public List<Item> getLista_items() {
		return lista_items;
	}


	public void setLista_items(List<Item> lista_items) {
		this.lista_items = lista_items;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public List<Emissor> getLista_emissor() {
		return lista_emissor;
	}


	public void setLista_emissor(List<Emissor> lista_emissor) {
		this.lista_emissor = lista_emissor;
	}


	public List<Cliente> getLista_cliente() {
		return lista_cliente;
	}


	public void setLista_cliente(List<Cliente> lista_cliente) {
		this.lista_cliente = lista_cliente;
	}


	public List<Componente> getLista_componente() {
		return lista_componente;
	}


	public void setLista_componente(List<Componente> lista_componente) {
		this.lista_componente = lista_componente;
	}


	public List<Defeito> getLista_defeito() {
		return lista_defeito;
	}


	public void setLista_defeito(List<Defeito> lista_defeito) {
		this.lista_defeito = lista_defeito;
	}


	public List<Efeito> getLista_efeito() {
		return lista_efeito;
	}


	public void setLista_efeito(List<Efeito> lista_efeito) {
		this.lista_efeito = lista_efeito;
	}


	public List<Marca> getLista_marca() {
		return lista_marca;
	}


	public void setLista_marca(List<Marca> lista_marca) {
		this.lista_marca = lista_marca;
	}


	public List<Produto> getLista_produto() {
		return lista_produto;
	}


	public void setLista_produto(List<Produto> lista_produto) {
		this.lista_produto = lista_produto;
	}


	public List<Responsavel> getLista_responsavel() {
		return lista_responsavel;
	}


	public void setLista_responsavel(List<Responsavel> lista_responsavel) {
		this.lista_responsavel = lista_responsavel;
	}


	public List<Tipo> getLista_tipo() {
		return lista_tipo;
	}


	public void setLista_tipo(List<Tipo> lista_tipo) {
		this.lista_tipo = lista_tipo;
	}


	public List<Vendedor> getLista_vendedor() {
		return lista_vendedor;
	}


	public void setLista_vendedor(List<Vendedor> lista_vendedor) {
		this.lista_vendedor = lista_vendedor;
	}
	
	
		
}
