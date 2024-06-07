package br.com.dw.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.dw.entidades.Usuario;
import br.com.dw.entidades.UsuarioModulo;
import br.com.dw.servico.ServicoUsuario;


@ManagedBean
@SessionScoped
public class BeanSession implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	@Inject
	private ServicoUsuario servico;
	private List<Usuario> lista = new ArrayList<>();
	

	
	@PostConstruct
	public void init() { 
		lista = servico.consultar();
		for (Usuario acesso : lista) {
		if (acesso.getLogin().equals(usuarioconectado())){
			usuario = acesso;
			//System.out.println(usuario.getLogin());
		}	
	}
		
	}
	
	public boolean acesso(String tipo){
		boolean t = false;
		if (!usuario.getLogin().equals("admin")) {
			for (UsuarioModulo modulo : usuario.getUsuariomodulos()) {
				if (modulo.getModulo().getIdentificacao().equals(tipo)) {
					t = true;
				}
			}
		} else {
			t = true;
		}
		return t;
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
		return nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
