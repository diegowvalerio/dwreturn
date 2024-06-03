package br.com.dw.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.dao.DAOTipo;
import br.com.dw.entidades.Tipo;
import br.com.dw.generico.Transacao;

@Dependent
public class ServicoTipo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOTipo dao;
	
	@Transacao
	public void salvar(Tipo clas){
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
	
	public List<Tipo> consultar(){
		return dao.consultar();
	}
	
}
