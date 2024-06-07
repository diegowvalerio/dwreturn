package br.com.dw.relatorios.servico;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.dw.relatorios.dao.DAODefeito_qtde;
import br.com.dw.relatorios.entidades.Defeito_qtde;

@Dependent
@Default
public class ServicoDefeito_qtde implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAODefeito_qtde dao;
	

	
	public List<Defeito_qtde> defeito_qtde(int iddefeito,Date data1, Date data2, int responsavel, int tipo){
		return dao.defeito_qtde(iddefeito,data1, data2,responsavel,tipo);
	}
	
}
