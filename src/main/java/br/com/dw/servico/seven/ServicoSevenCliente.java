package br.com.dw.servico.seven;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.dao.seven.DAOSevenCliente;
import br.com.dw.entidades.seven.SevenCliente;
import br.com.dw.generico.seven.TransacaoSeven;

@Dependent
public class ServicoSevenCliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOSevenCliente dao;
	
	@TransacaoSeven
	public List<SevenCliente> sevencliente(){
		return dao.sevencliente();
	}
	
}
