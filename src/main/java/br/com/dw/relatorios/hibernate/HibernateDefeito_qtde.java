package br.com.dw.relatorios.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.generico.DAOGenericoHibernate;
import br.com.dw.relatorios.dao.DAODefeito_qtde;
import br.com.dw.relatorios.entidades.Defeito_qtde;

@Dependent
public class HibernateDefeito_qtde extends DAOGenericoHibernate<Defeito_qtde> implements DAODefeito_qtde,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateDefeito_qtde(){
		super(Defeito_qtde.class);
	}


}
