package br.com.dw.generico;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;



public class DAOGenericoHibernate<E> implements DAOGenerico<E>, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected EntityManager manager;
	@SuppressWarnings("rawtypes")
	private Class classeEntidade;
	
	@SuppressWarnings("rawtypes")
	public DAOGenericoHibernate(Class classeEntidade){
		this.classeEntidade = classeEntidade;
	}

	@Override
	public E salvar(E e) {
		manager.persist(e);
		return e;
	}
	
	
	@Override
	public E alterar(E e) {
		return manager.merge(e);
	}

	@Override
	public boolean excluir(Integer id) {
		E e = consultar(id);
		manager.remove(e);
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public E consultar(Integer id) {
		return (E) manager.find(classeEntidade, id);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> consultar() {
		return manager.createQuery("from "+classeEntidade.getSimpleName()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> consultar_existe(String codigoseven) {
		return manager.createQuery("from "+classeEntidade.getSimpleName()+" where codigoseven = '"+codigoseven+"' ").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> consultar_ativos() {		
		return manager.createQuery("from "+classeEntidade.getSimpleName()+" where situacao = true").getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> consultarlogin(String login, String senha){
		return manager.createQuery("from "+classeEntidade.getSimpleName()+" where login = '"+login+"' and senha = '"+senha+"' and situacao = true and vendedor_idcadastrogeral is not null ").getResultList();
	}
	
}
