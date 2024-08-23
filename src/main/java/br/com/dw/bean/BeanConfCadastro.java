package br.com.dw.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dw.entidades.Cliente;
import br.com.dw.entidades.Produto;
import br.com.dw.entidades.Vendedor;
import br.com.dw.entidades.seven.SevenCliente;
import br.com.dw.entidades.seven.SevenProduto;
import br.com.dw.entidades.seven.SevenVendedor;
import br.com.dw.servico.ServicoCliente;
import br.com.dw.servico.ServicoProduto;
import br.com.dw.servico.ServicoVendedor;
import br.com.dw.servico.seven.ServicoSevenCliente;
import br.com.dw.servico.seven.ServicoSevenProduto;
import br.com.dw.servico.seven.ServicoSevenVendedor;


@Named
@ViewScoped
public class BeanConfCadastro implements Serializable{
	private static final long serialVersionUID = 1L;
//vendedor
	@Inject
	private ServicoVendedor servico_vendedor;
	private List<Vendedor> lista_vendedor = new ArrayList<>();
	private int qtde_vendedor;
	
	@Inject
	private ServicoSevenVendedor servico_seven_vendedor;
	private List<SevenVendedor> lista_sevenvendedor = new ArrayList<>();
	
//cliente	
	@Inject
	private ServicoCliente servico_cliente;
	private List<Cliente> lista_cliente = new ArrayList<>();
	private int qtde_cliente;
	
	@Inject
	private ServicoSevenCliente servico_seven_cliente;
	private List<SevenCliente> lista_sevencliente = new ArrayList<>();
	
//produto	
	@Inject
	private ServicoProduto servico_produto;
	private List<Produto> lista_produto = new ArrayList<>();
	private int qtde_produto;
	
	@Inject
	private ServicoSevenProduto servico_seven_produto;
	private List<SevenProduto> lista_sevenproduto = new ArrayList<>();
	
	
	@PostConstruct
	public void carregar(){	
		lista_vendedor = servico_vendedor.consultar();
		lista_cliente = servico_cliente.consultar();
		lista_produto = servico_produto.consultar();
		
		calcula_qtde();
	}	
	
	private void calcula_qtde() {
		qtde_vendedor = lista_vendedor.size();
		qtde_cliente = lista_cliente.size();
		qtde_produto = lista_produto.size();
	}
	
	public String atualiza_vendedor() {
		lista_sevenvendedor = servico_seven_vendedor.sevenvendedor();
		if(lista_sevenvendedor.size()>0) {
			for(SevenVendedor s:lista_sevenvendedor) {
				List<Vendedor> vs = servico_vendedor.consultar_existe(s.getId().toString());
				Vendedor v = new Vendedor();
				if(vs.size()>0) {
					v = vs.get(0);
				}				 
				if(v.getId() != null) {
					//altera
					v.setNome(s.getNome());
					if(s.getSituacao().equals("SIM")) {
						v.setSituacao(true);
					}else {
						v.setSituacao(false);
					}
					servico_vendedor.salvar(v);
				}else {
					//salva
					v.setCodigoseven(s.getId().toString());
					v.setNome(s.getNome());
					if(s.getSituacao().equals("SIM")) {
						v.setSituacao(true);
					}else {
						v.setSituacao(false);
					}
					v.setDtcadastro(new Date());
					servico_vendedor.salvar(v);
				}
			} 
		}
		return "confcadastro";
	}
	
	
	public String atualiza_cliente() {
		lista_sevencliente = servico_seven_cliente.sevencliente();
		if(lista_sevencliente.size()>0) {
			for(SevenCliente s:lista_sevencliente) {
				List<Cliente> vs = servico_cliente.consultar_existe(s.getId().toString());
				Cliente v = new Cliente();
				if(vs.size()>0) {
					v = vs.get(0);
				}				 
				if(v.getId() != null) {
					//altera
					v.setNome(s.getNome());
					if(s.getSituacao().equals("SIM")) {
						v.setSituacao(true);
					}else {
						v.setSituacao(false);
					}
					servico_cliente.salvar(v);
				}else {
					//salva
					v.setCodigoseven(s.getId().toString());
					v.setNome(s.getNome());
					if(s.getSituacao().equals("SIM")) {
						v.setSituacao(true);
					}else {
						v.setSituacao(false);
					}
					v.setDtcadastro(new Date());
					servico_cliente.salvar(v);
				}
			} 
		}
		return "confcadastro";
	}
	
	public String atualiza_produto() {
		lista_sevenproduto = servico_seven_produto.sevenproduto();
		if(lista_sevenproduto.size()>0) {
			for(SevenProduto s:lista_sevenproduto) {
				List<Produto> vs = servico_produto.consultar_existe(s.getId().toString());
				Produto v = new Produto();
				if(vs.size()>0) {
					v = vs.get(0);
				}				 
				if(v.getId() != null) {
					//altera
					v.setNome(s.getNome());
					v.setReferencia(s.getReferencia());
					v.setVlcusto(s.getVlcusto());
					if(s.getSituacao().equals("ATIVO")) {
						v.setSituacao(true);
					}else {
						v.setSituacao(false);
					}
					servico_produto.salvar(v);
				}else {
					//salva
					v.setCodigoseven(s.getId().toString());
					v.setNome(s.getNome());
					v.setReferencia(s.getReferencia());
					v.setVlcusto(s.getVlcusto());
					if(s.getSituacao().equals("ATIVO")) {
						v.setSituacao(true);
					}else {
						v.setSituacao(false);
					}
					v.setDtcadastro(new Date());
					servico_produto.salvar(v);
				}
			} 
		}
		return "confcadastro";
	}
	public List<Produto> getLista_produto() {
		return lista_produto;
	}

	public void setLista_produto(List<Produto> lista_produto) {
		this.lista_produto = lista_produto;
	}

	public int getQtde_produto() {
		return qtde_produto;
	}

	public void setQtde_produto(int qtde_produto) {
		this.qtde_produto = qtde_produto;
	}

	public List<SevenProduto> getLista_sevenproduto() {
		return lista_sevenproduto;
	}

	public void setLista_sevenproduto(List<SevenProduto> lista_sevenproduto) {
		this.lista_sevenproduto = lista_sevenproduto;
	}

	public List<SevenCliente> getLista_sevencliente() {
		return lista_sevencliente;
	}

	public void setLista_sevencliente(List<SevenCliente> lista_sevencliente) {
		this.lista_sevencliente = lista_sevencliente;
	}

	public List<Cliente> getLista_cliente() {
		return lista_cliente;
	}

	public void setLista_cliente(List<Cliente> lista_cliente) {
		this.lista_cliente = lista_cliente;
	}

	public int getQtde_cliente() {
		return qtde_cliente;
	}

	public void setQtde_cliente(int qtde_cliente) {
		this.qtde_cliente = qtde_cliente;
	}

	public List<Vendedor> getLista_vendedor() {
		return lista_vendedor;
	}

	public void setLista_vendedor(List<Vendedor> lista_vendedor) {
		this.lista_vendedor = lista_vendedor;
	}

	public int getQtde_vendedor() {
		return qtde_vendedor;
	}

	public void setQtde_vendedor(int qtde_vendedor) {
		this.qtde_vendedor = qtde_vendedor;
	}

	public List<SevenVendedor> getLista_sevenvendedor() {
		return lista_sevenvendedor;
	}

	public void setLista_sevenvendedor(List<SevenVendedor> lista_sevenvendedor) {
		this.lista_sevenvendedor = lista_sevenvendedor;
	}
	
	
	
}
