package br.com.dw.relatorios.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.generico.DAOGenericoHibernate;
import br.com.dw.relatorios.dao.DAODefeito_responsavel;
import br.com.dw.relatorios.entidades.Defeito_responsavel;

@Dependent
public class HibernateDefeito_responsavel extends DAOGenericoHibernate<Defeito_responsavel> implements DAODefeito_responsavel,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateDefeito_responsavel(){
		super(Defeito_responsavel.class);
	}


}
