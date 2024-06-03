package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOProduto;
import br.com.dw.entidades.Produto;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateProduto extends DAOGenericoHibernate<Produto> implements DAOProduto,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateProduto(){
		super(Produto.class);
	}


}
