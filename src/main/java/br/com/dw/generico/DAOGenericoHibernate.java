package br.com.dw.generico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.dw.relatorios.entidades.Defeito_componente;
import br.com.dw.relatorios.entidades.Defeito_marca;
import br.com.dw.relatorios.entidades.Defeito_produto;
import br.com.dw.relatorios.entidades.Defeito_qtde;
import br.com.dw.relatorios.entidades.Defeito_responsavel;
import br.com.dw.relatorios.entidades.Defeito_tipo;



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
	
	public List<Defeito_produto> defeito_produto(int iddefeito,Date data1,Date data2, int responsavel, int tipo){
		List<Defeito_produto> list = new ArrayList<>();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data1);
		String dataFormatada2 = formato.format(data2);
		
		String sql = ""
				+ " select  "
				+ " d.id , "
				+ " d.referencia , "
				+ " sum(t.qtde) total , "
				+ " trunc((sum(cast(t.qtde as decimal)) / g.total_geral)*100,2) percentual, "
				+ " ''''||d.referencia||'''' nome2,"
				+ " d.nome ,"
				+ " d.codigoseven "
				+ " from tblancamento l "
				+ " inner join tbitem t ON t.lancamento_id = l.id  "
				+ " inner join tbproduto d on d.id = t.produto_id "
				+ " left join( "
				+ " select  "
				+ " to_char(l.dtanalise,'YYYY') ano, "
				+ " to_char(l.dtanalise,'MM') mes, "
				+ " sum(t.qtde) total_geral  "
				+ " from tblancamento l "
				+ " inner join tbitem t ON t.lancamento_id = l.id  "
				+ " where l.dtanalise between '"+dataFormatada+"' and '"+dataFormatada2+"' "
				+ " and (t.defeito_id = "+iddefeito+" or -1 = "+iddefeito+" ) "
				+ " and (t.tipo_id = "+tipo+" or -1 = "+tipo+" ) "
				+ " and (t.responsavel_id = "+responsavel+" or -1 = "+responsavel+" ) "
				+ " group by to_char(l.dtanalise,'YYYY'),to_char(l.dtanalise,'MM') "
				+ " )g on g.ano = to_char(l.dtanalise,'YYYY') and g.mes = to_char(l.dtanalise,'MM') "
				+ " where l.dtanalise between '"+dataFormatada+"' and '"+dataFormatada2+"' "
				+ " and (t.defeito_id = "+iddefeito+" or -1 = "+iddefeito+" ) "
				+ " and (t.tipo_id = "+tipo+" or -1 = "+tipo+" ) "
				+ " and (t.responsavel_id = "+responsavel+" or -1 = "+responsavel+" ) "
				+ " group by d.id ,d.codigoseven,d.referencia ,g.total_geral, d.nome "
				+ " order by sum(t.qtde) desc ";
		
		javax.persistence.Query query = (javax.persistence.Query) manager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();

		for (Object[] row : lista) {
			Defeito_produto f = new Defeito_produto();
			
			f.setId((Integer) row[0]);
			f.setNome((String)row[1]);
			f.setTotal((BigInteger)row[2]);
			f.setPercentual((BigDecimal)row[3]);
			f.setNome2((String)row[4]);
			f.setNome_produto((String)row[5]);
			f.setIdseven((String)row[6]);
			
			list.add(f);
		}
		return list;		
	}
	
	public List<Defeito_responsavel> defeito_responsavel(int iddefeito,Date data1,Date data2, int responsavel,int tipo){
		List<Defeito_responsavel> list = new ArrayList<>();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data1);
		String dataFormatada2 = formato.format(data2);
		
		String sql = ""
				+ " select  "
				+ " d.id , "
				+ " d.nome , "
				+ " sum(t.qtde) total , "
				+ " trunc((sum(cast(t.qtde as decimal)) / g.total_geral)*100,2) percentual, "
				+ " ''''||d.nome||'''' nome2 "
				+ " from tblancamento l "
				+ " inner join tbitem t ON t.lancamento_id = l.id  "
				+ " inner join tbfornecedor d on d.id = t.responsavel_id "
				+ " left join( "
				+ " select  "
				+ " to_char(l.dtanalise,'YYYY') ano, "
				+ " to_char(l.dtanalise,'MM') mes, "
				+ " sum(t.qtde) total_geral  "
				+ " from tblancamento l "
				+ " inner join tbitem t ON t.lancamento_id = l.id  "
				+ " inner join tbfornecedor d on d.id = t.responsavel_id "
				+ " where l.dtanalise between '"+dataFormatada+"' and '"+dataFormatada2+"' "
				+ " and (t.defeito_id = "+iddefeito+" or -1 = "+iddefeito+" ) "
				+ " and (t.responsavel_id = "+responsavel+" or -1 = "+responsavel+" ) "
				+ " and (t.tipo_id = "+tipo+" or -1 = "+tipo+" ) "
				+ " group by to_char(l.dtanalise,'YYYY'),to_char(l.dtanalise,'MM') "
				+ " )g on g.ano = to_char(l.dtanalise,'YYYY') and g.mes = to_char(l.dtanalise,'MM') "
				+ " where l.dtanalise between '"+dataFormatada+"' and '"+dataFormatada2+"' "
				+ " and (t.defeito_id = "+iddefeito+" or -1 = "+iddefeito+" ) "
				+ " and (t.responsavel_id = "+responsavel+" or -1 = "+responsavel+" ) "
				+ " and (t.tipo_id = "+tipo+" or -1 = "+tipo+" ) "
				+ " group by d.id ,d.nome ,g.total_geral "
				+ " order by sum(t.qtde) desc ";
		
		javax.persistence.Query query = (javax.persistence.Query) manager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();

		for (Object[] row : lista) {
			Defeito_responsavel f = new Defeito_responsavel();
			
			f.setId((Integer) row[0]);
			f.setNome((String)row[1]);
			f.setTotal((BigInteger)row[2]);
			f.setPercentual((BigDecimal)row[3]);
			f.setNome2((String)row[4]);
			
			list.add(f);
		}
		return list;		
	}
	
	public List<Defeito_qtde> defeito_qtde(int iddefeito,Date data1,Date data2, int responsavel,int tipo){
		List<Defeito_qtde> list = new ArrayList<>();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data1);
		String dataFormatada2 = formato.format(data2);
		
		String sql = ""
				+ " select  "
				+ " d.id , "
				+ " d.nome , "
				+ " sum(t.qtde) total , "
				+ " trunc((sum(cast(t.qtde as decimal)) / g.total_geral)*100,2) percentual, "
				+ " ''''||d.nome||'''' nome2 "
				+ " from tblancamento l "
				+ " inner join tbitem t ON t.lancamento_id = l.id  "
				+ " inner join tbdefeito d on d.id = t.defeito_id "
				+ " left join( "
				+ " select  "
				+ " to_char(l.dtanalise,'YYYY') ano, "
				+ " to_char(l.dtanalise,'MM') mes, "
				+ " sum(t.qtde) total_geral  "
				+ " from tblancamento l "
				+ " inner join tbitem t ON t.lancamento_id = l.id  "
				+ " inner join tbdefeito d on d.id = t.defeito_id "
				+ " where l.dtanalise between '"+dataFormatada+"' and '"+dataFormatada2+"' "
				+ " and (d.id = "+iddefeito+" or -1 = "+iddefeito+" ) "
				+ " and (t.responsavel_id = "+responsavel+" or -1 = "+responsavel+" ) "
				+ " and (t.tipo_id = "+tipo+" or -1 = "+tipo+" ) "
				+ " group by to_char(l.dtanalise,'YYYY'),to_char(l.dtanalise,'MM') "
				+ " )g on g.ano = to_char(l.dtanalise,'YYYY') and g.mes = to_char(l.dtanalise,'MM') "
				+ " where l.dtanalise between '"+dataFormatada+"' and '"+dataFormatada2+"' "
				+ " and (d.id = "+iddefeito+" or -1 = "+iddefeito+" ) "
				+ " and (t.responsavel_id = "+responsavel+" or -1 = "+responsavel+" ) "
				+ " and (t.tipo_id = "+tipo+" or -1 = "+tipo+" ) "
				+ " group by d.id ,d.nome ,g.total_geral "
				+ " order by sum(t.qtde) desc ";
		
		javax.persistence.Query query = (javax.persistence.Query) manager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();

		for (Object[] row : lista) {
			Defeito_qtde f = new Defeito_qtde();
			
			f.setId((Integer) row[0]);
			f.setNome((String)row[1]);
			f.setTotal((BigInteger)row[2]);
			f.setPercentual((BigDecimal)row[3]);
			f.setNome2((String)row[4]);
			
			list.add(f);
		}
		return list;
	}
	
	public List<Defeito_componente> defeito_componente(int iddefeito,Date data1,Date data2, int responsavel,int tipo){
	List<Defeito_componente> list = new ArrayList<>();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data1);
		String dataFormatada2 = formato.format(data2);
		
		String sql = ""
				+ " select  "
				+ " c.id , "
				+ " c.nome , "
				+ " sum(t.qtde) total , "
				+ " trunc((sum(cast(t.qtde as decimal)) / g.total_geral)*100,2) percentual, "
				+ " ''''||c.nome||'''' nome2 "
				+ " from tblancamento l "
				+ " inner join tbitem t ON t.lancamento_id = l.id  "
				+ " inner join tbdefeito d on d.id = t.defeito_id "
				+ " inner join tbcomponente c on c.id = t.componente_id  "
				+ " left join( "
				+ " select "
				+ " to_char(l.dtanalise,'YYYY') ano, "
				+ " to_char(l.dtanalise,'MM') mes, "
				+ " sum(t.qtde) total_geral  "
				+ " from tblancamento l "
				+ " inner join tbitem t ON t.lancamento_id = l.id  "
				+ " inner join tbdefeito d on d.id = t.defeito_id "
				+ " inner join tbcomponente c on c.id = t.componente_id  "
				+ " where l.dtanalise between '"+dataFormatada+"' and '"+dataFormatada2+"' "
				+ " and (d.id = "+iddefeito+" or -1 = "+iddefeito+" ) "
				+ " and (t.responsavel_id = "+responsavel+" or -1 = "+responsavel+" ) "
				+ " and (t.tipo_id = "+tipo+" or -1 = "+tipo+" ) "
				+ " group by to_char(l.dtanalise,'YYYY'),to_char(l.dtanalise,'MM') "
				+ " )g on g.ano = to_char(l.dtanalise,'YYYY') and g.mes = to_char(l.dtanalise,'MM')   "
				+ " where l.dtanalise between '"+dataFormatada+"' and '"+dataFormatada2+"' "
				+ " and (d.id = "+iddefeito+" or -1 = "+iddefeito+" )"
				+ " and (t.responsavel_id = "+responsavel+" or -1 = "+responsavel+" ) "
				+ " and (t.tipo_id = "+tipo+" or -1 = "+tipo+" ) "
				+ " group by c.id ,c.nome,g.total_geral "
				+ " order by sum(t.qtde) desc ";
		
		javax.persistence.Query query = (javax.persistence.Query) manager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();

		for (Object[] row : lista) {
			Defeito_componente f = new Defeito_componente();
			
			
			f.setId((Integer) row[0]);
			f.setNome((String)row[1]);
			f.setTotal((BigInteger)row[2]);
			f.setPercentual((BigDecimal)row[3]);
			f.setNome2((String)row[4]);
			
			list.add(f);
		}
		return list;
	}
	
	public List<Defeito_tipo> defeito_tipo(int iddefeito,Date data1,Date data2, int responsavel,int tipo){
		List<Defeito_tipo> list = new ArrayList<>();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data1);
		String dataFormatada2 = formato.format(data2);
		
		String sql = ""
				+ " select  "
				+ " d.id , "
				+ " d.nome , "
				+ " sum(t.qtde) total , "
				+ " trunc((sum(cast(t.qtde as decimal)) / g.total_geral)*100,2) percentual, "
				+ " ''''||d.nome||'''' nome2 "
				+ " from tblancamento l "
				+ " inner join tbitem t ON t.lancamento_id = l.id  "
				+ " inner join tbtipo d on d.id = t.tipo_id "
				+ " left join( "
				+ " select  "
				+ " to_char(l.dtanalise,'YYYY') ano, "
				+ " to_char(l.dtanalise,'MM') mes, "
				+ " sum(t.qtde) total_geral  "
				+ " from tblancamento l "
				+ " inner join tbitem t ON t.lancamento_id = l.id  "
				+ " inner join tbtipo d on d.id = t.tipo_id "
				+ " where l.dtanalise between '"+dataFormatada+"' and '"+dataFormatada2+"' "
				+ " and (t.defeito_id = "+iddefeito+" or -1 = "+iddefeito+" ) "
				+ " and (t.responsavel_id = "+responsavel+" or -1 = "+responsavel+" ) "
				+ " and (t.tipo_id = "+tipo+" or -1 = "+tipo+" ) "
				+ " group by to_char(l.dtanalise,'YYYY'),to_char(l.dtanalise,'MM') "
				+ " )g on g.ano = to_char(l.dtanalise,'YYYY') and g.mes = to_char(l.dtanalise,'MM') "
				+ " where l.dtanalise between '"+dataFormatada+"' and '"+dataFormatada2+"' "
				+ " and (t.defeito_id = "+iddefeito+" or -1 = "+iddefeito+" ) "
				+ " and (t.responsavel_id = "+responsavel+" or -1 = "+responsavel+" ) "
				+ " and (t.tipo_id = "+tipo+" or -1 = "+tipo+" ) "
				+ " group by d.id ,d.nome ,g.total_geral "
				+ " order by sum(t.qtde) desc ";
		
		javax.persistence.Query query = (javax.persistence.Query) manager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();

		for (Object[] row : lista) {
			Defeito_tipo f = new Defeito_tipo();
			
			f.setId((Integer) row[0]);
			f.setNome((String)row[1]);
			f.setTotal((BigInteger)row[2]);
			f.setPercentual((BigDecimal)row[3]);
			f.setNome2((String)row[4]);
			
			list.add(f);
		}
		return list;
	}
	
	public List<Defeito_marca> defeito_marca(int iddefeito,Date data1,Date data2, int responsavel,int tipo){
		List<Defeito_marca> list = new ArrayList<>();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data1);
		String dataFormatada2 = formato.format(data2);
		
		String sql = ""
				+ " select  "
				+ " d.id , "
				+ " d.nome , "
				+ " sum(t.qtde) total , "
				+ " trunc((sum(cast(t.qtde as decimal)) / g.total_geral)*100,2) percentual, "
				+ " ''''||d.nome||'''' nome2 "
				+ " from tblancamento l "
				+ " inner join tbitem t ON t.lancamento_id = l.id  "
				+ " inner join tbmarca d on d.id = t.tipo_id "
				+ " left join( "
				+ " select  "
				+ " to_char(l.dtanalise,'YYYY') ano, "
				+ " to_char(l.dtanalise,'MM') mes, "
				+ " sum(t.qtde) total_geral  "
				+ " from tblancamento l "
				+ " inner join tbitem t ON t.lancamento_id = l.id  "
				+ " inner join tbmarca d on d.id = t.tipo_id "
				+ " where l.dtanalise between '"+dataFormatada+"' and '"+dataFormatada2+"' "
				+ " and (t.defeito_id = "+iddefeito+" or -1 = "+iddefeito+" ) "
				+ " and (t.responsavel_id = "+responsavel+" or -1 = "+responsavel+" ) "
				+ " and (t.tipo_id = "+tipo+" or -1 = "+tipo+" ) "
				+ " group by to_char(l.dtanalise,'YYYY'),to_char(l.dtanalise,'MM') "
				+ " )g on g.ano = to_char(l.dtanalise,'YYYY') and g.mes = to_char(l.dtanalise,'MM') "
				+ " where l.dtanalise between '"+dataFormatada+"' and '"+dataFormatada2+"' "
				+ " and (t.defeito_id = "+iddefeito+" or -1 = "+iddefeito+" ) "
				+ " and (t.responsavel_id = "+responsavel+" or -1 = "+responsavel+" ) "
				+ " and (t.tipo_id = "+tipo+" or -1 = "+tipo+" ) "
				+ " group by d.id ,d.nome ,g.total_geral "
				+ " order by sum(t.qtde) desc ";
		
		javax.persistence.Query query = (javax.persistence.Query) manager.createNativeQuery(sql);
		
		List<Object[]> lista = query.getResultList();

		for (Object[] row : lista) {
			Defeito_marca f = new Defeito_marca();
			
			f.setId((Integer) row[0]);
			f.setNome((String)row[1]);
			f.setTotal((BigInteger)row[2]);
			f.setPercentual((BigDecimal)row[3]);
			f.setNome2((String)row[4]);
			
			list.add(f);
		}
		return list;
	}	
	
}
