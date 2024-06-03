package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOCliente;
import br.com.dw.entidades.Cliente;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateCliente extends DAOGenericoHibernate<Cliente> implements DAOCliente,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCliente(){
		super(Cliente.class);
	}


}
