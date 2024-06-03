package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOEmissor;
import br.com.dw.entidades.Emissor;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateEmissor extends DAOGenericoHibernate<Emissor> implements DAOEmissor,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateEmissor(){
		super(Emissor.class);
	}


}
