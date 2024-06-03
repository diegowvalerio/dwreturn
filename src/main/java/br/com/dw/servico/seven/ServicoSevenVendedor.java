package br.com.dw.servico.seven;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.dao.seven.DAOSevenVendedor;
import br.com.dw.entidades.seven.SevenVendedor;
import br.com.dw.generico.seven.TransacaoSeven;

@Dependent
public class ServicoSevenVendedor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOSevenVendedor dao;
	
	@TransacaoSeven
	public List<SevenVendedor> sevenvendedor(){
		return dao.sevenvendedor();
	}
	
}
