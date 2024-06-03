package br.com.dw.generico;

import java.util.List;


public interface DAOGenerico<E> {
	public E salvar(E e);
	public E alterar(E e);
	public boolean excluir(Integer id);
	public E consultar(Integer id);
	public List<E> consultar();	
	public List<E> consultar_ativos();	
	
	
	//ws
	public List<E> consultarlogin(String login,String senha);
	
	public List<E> consultar_existe(String codigoseven) ;
}
