package br.com.dw.hibernate.seven;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.seven.DAOFormaPag;
import br.com.dw.entidades.seven.FormaPag;
import br.com.dw.generico.seven.DAOGenericoHibernateSeven;


@Dependent
public class HibernateFormaPag extends DAOGenericoHibernateSeven<FormaPag> implements DAOFormaPag,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateFormaPag(){
		super(FormaPag.class);
	}


}
