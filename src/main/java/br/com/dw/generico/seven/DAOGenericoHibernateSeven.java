package br.com.dw.generico.seven;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.dw.entidades.seven.SevenCliente;
import br.com.dw.entidades.seven.SevenProduto;
import br.com.dw.entidades.seven.SevenVendedor;
import br.com.dw.fabrica.EntityManagerProducerSeven.Corporativo;

@SuppressWarnings("unchecked")
public class DAOGenericoHibernateSeven<E> implements DAOGenericoSeven<E>, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	@Corporativo
	protected EntityManager manager;
	
	@SuppressWarnings("rawtypes")
	private Class classeEntidade;
	
	@SuppressWarnings("rawtypes")
	public DAOGenericoHibernateSeven(Class classeEntidade){
		this.classeEntidade = classeEntidade;
	}
	
	
	
	public List<SevenVendedor> sevenvendedor(){
		List<SevenVendedor> list = new ArrayList<>();
		
		String sql= ""
				+ " select "
				+ " cast(v.cadcftvid as integer) cadcftvid, "
				+ " c.nome_cadcftv nome, "
				+ " c.ativo_cadcftv ativo "
				+ " from vendedor v "
				+ " inner join cadcftv c on c.cadcftvid = v.cadcftvid "
				+ " where c.funcao_principal_cadcftv = 'VENDEDOR' ";
		
		javax.persistence.Query query = (javax.persistence.Query) manager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();

		for (Object[] row : lista) {
			SevenVendedor f = new SevenVendedor();
			
			f.setId((Integer) row[0]);
			f.setNome((String)row[1]);
			f.setSituacao((String)row[2]);
			
			list.add(f);
		}
		return list;
	}
	
	public List<SevenCliente> sevencliente(){
		List<SevenCliente> list = new ArrayList<>();
		
		String sql= ""
				+ " select  "
				+ " CAST(C.cadcftvid as integer) cadcftvid , "
				+ " cc.nome_cadcftv nome, "
				+ " cc.ativo_cadcftv  ativo "
				+ " from cliente c "
				+ " inner join cadcftv cc on cc.cadcftvid = c.cadcftvid  "
				+ " where cc.funcao_principal_cadcftv = 'CLIENTE' ";
		
		javax.persistence.Query query = (javax.persistence.Query) manager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();

		for (Object[] row : lista) {
			SevenCliente f = new SevenCliente();
			
			f.setId((Integer) row[0]);
			f.setNome((String)row[1]);
			f.setSituacao((String)row[2]);
			
			list.add(f);
		}
		return list;
	}	
	
	public List<SevenProduto> sevenproduto(){
		List<SevenProduto> list = new ArrayList<>();
		
		String sql= ""
				+ " select  "
				+ " cast(p.produtoid as integer) produtoid, "
				+ " p.nome_produto , "
				+ " p.referencia_produto , "
				+ " p.status_produto,  "
				+ " cast(p.vl_custo_produto as float) vlcusto "
				+ " from produto p "
				+ " where p.tp_produto = 'ACABADO' ";
		
		javax.persistence.Query query = (javax.persistence.Query) manager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();

		for (Object[] row : lista) {
			SevenProduto f = new SevenProduto();
			
			f.setId((Integer) row[0]);
			f.setNome((String)row[1]);
			f.setReferencia((String)row[2]);
			f.setSituacao((String)row[3]);
			f.setVlcusto((Double)row[4]);
			
			list.add(f);
		}
		return list;
	}	

}
