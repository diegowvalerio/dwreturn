package br.com.dw.fabrica;

import java.io.Serializable;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;


@ApplicationScoped
public class EntityManagerProducer implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceUnit(unitName = "dw")
	private EntityManagerFactory factory;
	
	@Produces 
	@RequestScoped
	@Default
	public EntityManager get(){
		return factory.createEntityManager();
	}
	
	/*
	public void close(@Disposes EntityManager manager){
		manager.close();
	}
	*/

}
