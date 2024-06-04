package br.com.dw.generico.seven;

import java.util.List;

import br.com.dw.entidades.seven.SevenCliente;
import br.com.dw.entidades.seven.SevenProduto;
import br.com.dw.entidades.seven.SevenVendedor;


public interface DAOGenericoSeven<E> {

	
	public List<SevenVendedor> sevenvendedor();
	public List<SevenCliente> sevencliente();
	public List<SevenProduto> sevenproduto();
	
}
