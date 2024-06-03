package br.com.dw.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dw.entidades.Componente;
import br.com.dw.servico.ServicoComponente;
import br.com.dw.util.FacesMessageUtil;

@Named
@ViewScoped
public class BeanComponente implements Serializable{
	private static final long serialVersionUID = 1L;

	private Componente componente = new Componente();
	@Inject
	private ServicoComponente servico;
	private List<Componente> lista = new ArrayList<>();
	
	private Date data;
	
	public BeanComponente() {
		data = new Date();
	}
	

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.componente = this.getComponente();
		
	}	
	
	public String salvar(){
		if(componente.getId() == null) {
			componente.setDtcadastro(data);
		}
		
		try{
		servico.salvar(componente);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-componente";
	}
	
	public void excluir(){
		try{
		servico.excluir(componente.getId());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}
	
	public Componente getComponente() {
		return componente;
	}
	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public List<Componente> getLista() {
		return lista;
	}

	public void setLista(List<Componente> lista) {
		this.lista = lista;
	}
		
}
