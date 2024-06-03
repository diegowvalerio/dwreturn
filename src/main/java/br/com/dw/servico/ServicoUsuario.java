package br.com.dw.servico;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.dw.dao.DAOUsuario;
import br.com.dw.entidades.Usuario;
import br.com.dw.generico.Transacao;

@Dependent
public class ServicoUsuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAOUsuario dao;
	
	@Transacao
	public void salvar(Usuario usuario){
		try {
			if(usuario.getIdusuario() == null){
				dao.salvar(usuario);
			}else{
				dao.alterar(usuario);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Transacao
	public boolean excluir(Integer id){
		return dao.excluir(id);
	}
	
	public List<Usuario> consultar(){
		return dao.consultar();
	}
	
	public Usuario consultar(Integer id) {
		return dao.consultar(id);
	}
	
	public List<Usuario> consultarlogin(String login, String senha){
		return dao.consultarlogin(login, senha);
	}
}
