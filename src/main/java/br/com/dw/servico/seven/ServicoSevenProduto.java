package br.com.dw.servico.seven;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.dao.seven.DAOSevenProduto;
import br.com.dw.entidades.seven.SevenProduto;
import br.com.dw.generico.seven.TransacaoSeven;

@Dependent
public class ServicoSevenProduto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOSevenProduto dao;
	
	@TransacaoSeven
	public List<SevenProduto> sevenproduto(){
		return dao.sevenproduto();
	}
	
}
