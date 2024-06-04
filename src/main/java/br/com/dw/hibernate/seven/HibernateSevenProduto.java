package br.com.dw.hibernate.seven;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dw.dao.seven.DAOSevenProduto;
import br.com.dw.entidades.seven.SevenProduto;
import br.com.dw.generico.seven.DAOGenericoHibernateSeven;


@Dependent
public class HibernateSevenProduto extends DAOGenericoHibernateSeven<SevenProduto> implements DAOSevenProduto,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateSevenProduto(){
		super(SevenProduto.class);
	}


}
