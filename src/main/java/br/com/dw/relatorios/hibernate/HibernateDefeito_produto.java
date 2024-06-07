package br.com.dw.relatorios.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.generico.DAOGenericoHibernate;
import br.com.dw.relatorios.dao.DAODefeito_produto;
import br.com.dw.relatorios.entidades.Defeito_produto;

@Dependent
public class HibernateDefeito_produto extends DAOGenericoHibernate<Defeito_produto> implements DAODefeito_produto,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateDefeito_produto(){
		super(Defeito_produto.class);
	}


}
