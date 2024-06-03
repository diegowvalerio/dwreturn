package br.com.dw.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.DAOUsuario;
import br.com.dw.entidades.Usuario;
import br.com.dw.generico.DAOGenericoHibernate;

@Dependent
public class HibernateUsuario extends DAOGenericoHibernate<Usuario> implements DAOUsuario,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateUsuario(){
		super(Usuario.class);
	}

}
