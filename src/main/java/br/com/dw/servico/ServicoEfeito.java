package br.com.dw.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.dao.DAOEfeito;
import br.com.dw.entidades.Efeito;
import br.com.dw.generico.Transacao;

@Dependent
public class ServicoEfeito implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOEfeito dao;
	
	@Transacao
	public void salvar(Efeito clas){
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
	
	public List<Efeito> consultar(){
		return dao.consultar();
	}
	
}
