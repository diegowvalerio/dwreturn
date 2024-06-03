package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOUsuarioModulo;
import br.com.dw.entidades.UsuarioModulo;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateUsuarioModulo extends DAOGenericoHibernate<UsuarioModulo> implements DAOUsuarioModulo,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateUsuarioModulo(){
		super(UsuarioModulo.class);
	}


}
