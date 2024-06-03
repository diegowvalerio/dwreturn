package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOLancamento;
import br.com.dw.entidades.Lancamento;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateLancamento extends DAOGenericoHibernate<Lancamento> implements DAOLancamento,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateLancamento(){
		super(Lancamento.class);
	}


}
