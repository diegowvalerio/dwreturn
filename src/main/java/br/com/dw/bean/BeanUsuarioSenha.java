package br.com.dw.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.dw.entidades.Usuario;
import br.com.dw.servico.ServicoUsuario;
import br.com.dw.util.FacesMessageUtil;

@Named
@ViewScoped
public class BeanUsuarioSenha implements Serializable{
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	@Inject
	private ServicoUsuario servico;
	private List<Usuario> lista;
	
	
	private Date data;
	
	@PostConstruct
	public void carregar(){
		lista = servico.consultar();
		for(Usuario s:lista) {
			if(!s.getLogin().equals(usuarioconectado())) {
				lista.remove(s);
			}
		}
		
	}	
	
	public String salvar(){
		try{
		servico.salvar(usuario);
		}catch(Exception e){
			if(e.getCause().toString().contains("ConstraintViolationException")){
				FacesMessageUtil.addMensagemError("Registro já existente! ");
			}else{
				FacesMessageUtil.addMensagemError(e.getCause().toString());
			}
		}
		lista = servico.consultar();
		return "usuario";
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
	
	
	/* pegar usuario conectado */
	public String usuarioconectado() {
		String nome;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			nome = ((UserDetails) principal).getUsername();
		} else {
			nome = principal.toString();
		}
		// System.out.println(nome);
		return nome;
	}

}