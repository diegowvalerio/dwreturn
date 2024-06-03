package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOVendedor;
import br.com.dw.entidades.Vendedor;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateVendedor extends DAOGenericoHibernate<Vendedor> implements DAOVendedor,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateVendedor(){
		super(Vendedor.class);
	}


}
