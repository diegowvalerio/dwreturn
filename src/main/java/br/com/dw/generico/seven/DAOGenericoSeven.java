package br.com.dw.generico.seven;

import java.util.List;

import br.com.dw.entidades.seven.CondPgto;
import br.com.dw.entidades.seven.FormaPag;
import br.com.dw.entidades.seven.SevenVendedor;


public interface DAOGenericoSeven<E> {

	public List<CondPgto> condPgto(String login);
	public List<FormaPag> formapag();
	
	public List<SevenVendedor> sevenvendedor();
	
}
