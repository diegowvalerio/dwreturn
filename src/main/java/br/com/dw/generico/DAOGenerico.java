package br.com.dw.generico;

import java.util.Date;
import java.util.List;

import br.com.dw.relatorios.entidades.Defeito_componente;
import br.com.dw.relatorios.entidades.Defeito_produto;
import br.com.dw.relatorios.entidades.Defeito_qtde;
import br.com.dw.relatorios.entidades.Defeito_responsavel;


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
	
	//relatorios-graficos
	public List<Defeito_qtde> defeito_qtde(int iddefeito,Date data1,Date data2,int responsavel, int tipo);
	public List<Defeito_componente> defeito_componente(int iddefeito,Date data1,Date data2,int responsavel, int tipo);
	public List<Defeito_responsavel> defeito_responsavel(int iddefeito,Date data1,Date data2, int responsavel, int tipo);
	public List<Defeito_produto> defeito_produto(int iddefeito,Date data1,Date data2, int responsavel, int tipo);
}
