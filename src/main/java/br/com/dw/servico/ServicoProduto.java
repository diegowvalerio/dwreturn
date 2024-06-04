package br.com.dw.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.dao.DAOProduto;
import br.com.dw.entidades.Produto;
import br.com.dw.generico.Transacao;

@Dependent
public class ServicoProduto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOProduto dao;
	
	@Transacao
	public void salvar(Produto clas){
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
	
	public List<Produto> consultar(){
		return dao.consultar();
	}
	
	public List<Produto> consultar_existe(String codigoseven) {
		return dao.consultar_existe(codigoseven);
	}
	
}
