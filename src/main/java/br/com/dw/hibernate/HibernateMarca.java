package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOMarca;
import br.com.dw.entidades.Marca;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateMarca extends DAOGenericoHibernate<Marca> implements DAOMarca,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateMarca(){
		super(Marca.class);
	}


}
