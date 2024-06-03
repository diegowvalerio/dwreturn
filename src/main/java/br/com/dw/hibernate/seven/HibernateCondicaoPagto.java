package br.com.dw.hibernate.seven;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.seven.DAOCondPgto;
import br.com.dw.entidades.seven.CondPgto;
import br.com.dw.generico.seven.DAOGenericoHibernateSeven;


@Dependent
public class HibernateCondicaoPagto extends DAOGenericoHibernateSeven<CondPgto> implements DAOCondPgto,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCondicaoPagto(){
		super(CondPgto.class);
	}


}
