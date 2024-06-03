package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAODefeito;
import br.com.dw.entidades.Defeito;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateDefeito extends DAOGenericoHibernate<Defeito> implements DAODefeito,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateDefeito(){
		super(Defeito.class);
	}


}
