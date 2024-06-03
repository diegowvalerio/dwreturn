package br.com.dw.servico.seven;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.dao.seven.DAOCondPgto;
import br.com.dw.entidades.seven.CondPgto;
import br.com.dw.generico.seven.TransacaoSeven;

@Dependent
public class ServicoCondPgto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOCondPgto dao;
	
	@TransacaoSeven
	public List<CondPgto> condPgto(String login){
		return dao.condPgto(login);
	}
	
}
