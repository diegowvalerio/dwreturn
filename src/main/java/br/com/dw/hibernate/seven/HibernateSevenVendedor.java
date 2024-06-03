package br.com.dw.hibernate.seven;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.seven.DAOSevenVendedor;
import br.com.dw.entidades.seven.SevenVendedor;
import br.com.dw.generico.seven.DAOGenericoHibernateSeven;


@Dependent
public class HibernateSevenVendedor extends DAOGenericoHibernateSeven<SevenVendedor> implements DAOSevenVendedor,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateSevenVendedor(){
		super(SevenVendedor.class);
	}


}
