package br.com.dw.relatorios.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.generico.DAOGenericoHibernate;
import br.com.dw.relatorios.dao.DAODefeito_componente;
import br.com.dw.relatorios.entidades.Defeito_componente;

@Dependent
public class HibernateDefeito_componente extends DAOGenericoHibernate<Defeito_componente> implements DAODefeito_componente,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateDefeito_componente(){
		super(Defeito_componente.class);
	}


}
