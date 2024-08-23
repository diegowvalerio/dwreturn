package br.com.dw.relatorios.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.generico.DAOGenericoHibernate;
import br.com.dw.relatorios.dao.DAODefeito_tipo;
import br.com.dw.relatorios.entidades.Defeito_tipo;

@Dependent
public class HibernateDefeito_tipo extends DAOGenericoHibernate<Defeito_tipo> implements DAODefeito_tipo,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateDefeito_tipo(){
		super(Defeito_tipo.class);
	}


}
