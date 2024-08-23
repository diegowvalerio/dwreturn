package br.com.dw.relatorios.servico;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.dw.relatorios.dao.DAODefeito_marca;
import br.com.dw.relatorios.entidades.Defeito_marca;

@Dependent
@Default
public class ServicoDefeito_marca implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAODefeito_marca dao;
	

	
	public List<Defeito_marca> defeito_marca(int iddefeito,Date data1, Date data2, int responsavel, int tipo){
		return dao.defeito_marca(iddefeito,data1, data2,responsavel,tipo);
	}
	
}
