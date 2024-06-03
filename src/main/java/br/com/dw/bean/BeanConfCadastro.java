package br.com.dw.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dw.entidades.Vendedor;
import br.com.dw.entidades.seven.SevenVendedor;
import br.com.dw.servico.ServicoVendedor;
import br.com.dw.servico.seven.ServicoSevenVendedor;


@Named
@ViewScoped
public class BeanConfCadastro implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private ServicoVendedor servico_vendedor;
	private List<Vendedor> lista_vendedor = new ArrayList<>();
	private int qtde_vendedor;
	
	@Inject
	private ServicoSevenVendedor servico_seven_vendedor;
	private List<SevenVendedor> lista_sevenvendedor = new ArrayList<>();
	
	@PostConstruct
	public void carregar(){	
		lista_vendedor = servico_vendedor.consultar();
		
		
		calcula_qtde();
	}	
	
	private void calcula_qtde() {
		qtde_vendedor = lista_vendedor.size();
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
