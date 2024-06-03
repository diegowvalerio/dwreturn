package br.com.dw.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dw.entidades.Responsavel;
import br.com.dw.servico.ServicoResponsavel;
import br.com.dw.util.FacesMessageUtil;

@Named
@ViewScoped
public class BeanResponsavel implements Serializable{
	private static final long serialVersionUID = 1L;

	private Responsavel responsavel = new Responsavel();
	@Inject
	private ServicoResponsavel servico;
	private List<Responsavel> lista = new ArrayList<>();
	
	private Date data;
	
	public BeanResponsavel() {
		data = new Date();
	}
	

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.responsavel = this.getResponsavel();
		
	}	
	
	public String salvar(){
		if(responsavel.getId() == null) {
			responsavel.setDtcadastro(data);
		}
		
		try{
		servico.salvar(responsavel);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-responsavel";
	}
	
	public void excluir(){
		try{
		servico.excluir(responsavel.getId());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}
	
	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Responsavel> getLista() {
		return lista;
	}

	public void setLista(List<Responsavel> lista) {
		this.lista = lista;
	}
		
}
