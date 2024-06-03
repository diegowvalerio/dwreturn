package br.com.dw.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dw.entidades.Emissor;
import br.com.dw.servico.ServicoEmissor;
import br.com.dw.util.FacesMessageUtil;

@Named
@ViewScoped
public class BeanEmissor implements Serializable{
	private static final long serialVersionUID = 1L;

	private Emissor emissor = new Emissor();
	@Inject
	private ServicoEmissor servico;
	private List<Emissor> lista = new ArrayList<>();
	
	private Date data;
	
	public BeanEmissor() {
		data = new Date();
	}
	

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.emissor = this.getEmissor();
		
	}	
	
	public String salvar(){
		if(emissor.getId() == null) {
			emissor.setDtcadastro(data);
		}
		
		try{
		servico.salvar(emissor);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-emissor";
	}
	
	public void excluir(){
		try{
		servico.excluir(emissor.getId());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}
	
	public Emissor getEmissor() {
		return emissor;
	}
	public void setEmissor(Emissor emissor) {
		this.emissor = emissor;
	}

	public List<Emissor> getLista() {
		return lista;
	}

	public void setLista(List<Emissor> lista) {
		this.lista = lista;
	}
		
}
