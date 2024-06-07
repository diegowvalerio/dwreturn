package br.com.dw.relatorios.servico;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.dw.relatorios.dao.DAODefeito_produto;
import br.com.dw.relatorios.entidades.Defeito_produto;

@Dependent
@Default
public class ServicoDefeito_produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAODefeito_produto dao;
	

	
	public List<Defeito_produto> defeito_produto(int iddefeito,Date data1, Date data2, int responsavel,int tipo){
		return dao.defeito_produto(iddefeito,data1, data2,responsavel,tipo);
	}
	
}
