package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOResponsavel;
import br.com.dw.entidades.Responsavel;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateResponsavel extends DAOGenericoHibernate<Responsavel> implements DAOResponsavel,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateResponsavel(){
		super(Responsavel.class);
	}


}
