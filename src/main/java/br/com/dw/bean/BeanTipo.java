package br.com.dw.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dw.entidades.Tipo;
import br.com.dw.servico.ServicoTipo;
import br.com.dw.util.FacesMessageUtil;

@Named
@ViewScoped
public class BeanTipo implements Serializable{
	private static final long serialVersionUID = 1L;

	private Tipo tipo = new Tipo();
	@Inject
	private ServicoTipo servico;
	private List<Tipo> lista = new ArrayList<>();
	
	private Date data;
	
	public BeanTipo() {
		data = new Date();
	}
	

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.tipo = this.getTipo();
		
	}	
	
	public String salvar(){
		if(tipo.getId() == null) {
			tipo.setDtcadastro(data);
		}
		
		try{
		servico.salvar(tipo);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-tipo";
	}
	
	public void excluir(){
		try{
		servico.excluir(tipo.getId());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Tipo> getLista() {
		return lista;
	}

	public void setLista(List<Tipo> lista) {
		this.lista = lista;
	}
		
}
