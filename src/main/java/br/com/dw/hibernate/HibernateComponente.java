package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOComponente;
import br.com.dw.entidades.Componente;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateComponente extends DAOGenericoHibernate<Componente> implements DAOComponente,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateComponente(){
		super(Componente.class);
	}


}
