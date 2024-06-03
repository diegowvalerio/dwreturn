package br.com.dw.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.dao.DAODefeito;
import br.com.dw.entidades.Defeito;
import br.com.dw.generico.Transacao;

@Dependent
public class ServicoDefeito implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAODefeito dao;
	
	@Transacao
	public void salvar(Defeito clas){
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
	
	public List<Defeito> consultar(){
		return dao.consultar();
	}
	
}
