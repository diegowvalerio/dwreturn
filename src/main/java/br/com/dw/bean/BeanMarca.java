package br.com.dw.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dw.entidades.Marca;
import br.com.dw.servico.ServicoMarca;
import br.com.dw.util.FacesMessageUtil;

@Named
@ViewScoped
public class BeanMarca implements Serializable{
	private static final long serialVersionUID = 1L;

	private Marca marca = new Marca();
	@Inject
	private ServicoMarca servico;
	private List<Marca> lista = new ArrayList<>();
	
	private Date data;
	
	public BeanMarca() {
		data = new Date();
	}
	

	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		
		this.marca = this.getMarca();
		
	}	
	
	public String salvar(){
		if(marca.getId() == null) {
			marca.setDtcadastro(data);
		}
		
		try{
		servico.salvar(marca);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente!");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "lista-marca";
	}
	
	public void excluir(){
		try{
		servico.excluir(marca.getId());
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro utilizado em outro local! Não foi possível realizar a operação.");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
		}
	}
		lista = servico.consultar();
	}
	
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Marca> getLista() {
		return lista;
	}

	public void setLista(List<Marca> lista) {
		this.lista = lista;
	}
		
}
