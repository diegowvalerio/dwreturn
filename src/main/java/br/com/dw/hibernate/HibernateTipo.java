package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOTipo;
import br.com.dw.entidades.Tipo;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateTipo extends DAOGenericoHibernate<Tipo> implements DAOTipo,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateTipo(){
		super(Tipo.class);
	}


}
