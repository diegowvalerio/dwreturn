package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOModulo;
import br.com.dw.entidades.Modulo;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateModulo extends DAOGenericoHibernate<Modulo> implements DAOModulo,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateModulo(){
		super(Modulo.class);
	}


}
