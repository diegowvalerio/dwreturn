package br.com.dw.relatorios.servico;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.dw.relatorios.dao.DAODefeito_tipo;
import br.com.dw.relatorios.entidades.Defeito_tipo;

@Dependent
@Default
public class ServicoDefeito_tipo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAODefeito_tipo dao;
	

	
	public List<Defeito_tipo> defeito_tipo(int iddefeito,Date data1, Date data2, int responsavel, int tipo){
		return dao.defeito_tipo(iddefeito,data1, data2,responsavel,tipo);
	}
	
}
