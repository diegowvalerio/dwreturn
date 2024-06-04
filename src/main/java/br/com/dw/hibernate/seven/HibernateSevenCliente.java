package br.com.dw.hibernate.seven;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.seven.DAOSevenCliente;
import br.com.dw.entidades.seven.SevenCliente;
import br.com.dw.generico.seven.DAOGenericoHibernateSeven;


@Dependent
public class HibernateSevenCliente extends DAOGenericoHibernateSeven<SevenCliente> implements DAOSevenCliente,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateSevenCliente(){
		super(SevenCliente.class);
	}


}
