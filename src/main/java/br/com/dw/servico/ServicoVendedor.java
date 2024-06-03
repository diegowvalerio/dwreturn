package br.com.dw.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.dao.DAOVendedor;
import br.com.dw.entidades.Vendedor;
import br.com.dw.generico.Transacao;

@Dependent
public class ServicoVendedor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOVendedor dao;
	
	@Transacao
	public void salvar(Vendedor clas){
		try {
			if(clas.getId() == null){
				dao.salvar(clas);
			}else{
				dao.alterar(clas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Vendedor> consultar(){
		return dao.consultar();
	}
	
	public List<Vendedor> consultar_existe(String codigoseven) {
		return dao.consultar_existe(codigoseven);
	}
	
}
