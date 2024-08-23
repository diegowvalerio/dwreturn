package br.com.dw.relatorios.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.generico.DAOGenericoHibernate;
import br.com.dw.relatorios.dao.DAODefeito_marca;
import br.com.dw.relatorios.entidades.Defeito_marca;

@Dependent
public class HibernateDefeito_marca extends DAOGenericoHibernate<Defeito_marca> implements DAODefeito_marca,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateDefeito_marca(){
		super(Defeito_marca.class);
	}


}
