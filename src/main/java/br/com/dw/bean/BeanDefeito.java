package br.com.dw.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dw.entidades.Defeito;
import br.com.dw.servico.ServicoDefeito;
import br.com.dw.util.FacesMessageUtil;

@Named
@ViewScoped
public class BeanDefeito implements Serializable{
	private static final long serialVersionUID = 1L;

	private Defeito defeito = new Defeito();
	@Inject
	private ServicoDefeito servico;
	private List<Defeito> lista = new ArrayList<>();
	
	private Date data;
	
	public BeanDefeito() {
		data = new Date();
	}
	

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.defeito = this.getDefeito();
		
	}	
	
	public String salvar(){
		if(defeito.getId() == null) {
			defeito.setDtcadastro(data);
		}
		
		try{
		servico.salvar(defeito);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-defeito";
	}
	
	public void excluir(){
		try{
		servico.excluir(defeito.getId());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}
	
	public Defeito getDefeito() {
		return defeito;
	}
	public void setDefeito(Defeito efeito) {
		this.defeito = efeito;
	}

	public List<Defeito> getLista() {
		return lista;
	}

	public void setLista(List<Defeito> lista) {
		this.lista = lista;
	}
		
}
