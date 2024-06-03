package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOEfeito;
import br.com.dw.entidades.Efeito;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateEfeito extends DAOGenericoHibernate<Efeito> implements DAOEfeito,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateEfeito(){
		super(Efeito.class);
	}


}
