package br.com.dw.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dw.entidades.Efeito;
import br.com.dw.servico.ServicoEfeito;
import br.com.dw.util.FacesMessageUtil;

@Named
@ViewScoped
public class BeanEfeito implements Serializable{
	private static final long serialVersionUID = 1L;

	private Efeito efeito = new Efeito();
	@Inject
	private ServicoEfeito servico;
	private List<Efeito> lista = new ArrayList<>();
	
	private Date data;
	
	public BeanEfeito() {
		data = new Date();
	}
	

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.efeito = this.getEfeito();
		
	}	
	
	public String salvar(){
		if(efeito.getId() == null) {
			efeito.setDtcadastro(data);
		}
		
		try{
		servico.salvar(efeito);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-efeito";
	}
	
	public void excluir(){
		try{
		servico.excluir(efeito.getId());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}
	
	public Efeito getEfeito() {
		return efeito;
	}
	public void setEfeito(Efeito efeito) {
		this.efeito = efeito;
	}

	public List<Efeito> getLista() {
		return lista;
	}

	public void setLista(List<Efeito> lista) {
		this.lista = lista;
	}
		
}
