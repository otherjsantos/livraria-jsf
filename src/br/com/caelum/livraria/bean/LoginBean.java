package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.dao.UsuarioDAO;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public RedirectView efetuarLogin() {
		System.out.println("Tentando logar com o usuário: " + usuario.getEmail());

		Usuario usuarioPesquisado = new UsuarioDAO().getByEmail(usuario.getEmail());

		FacesContext context = FacesContext.getCurrentInstance();

		context.getExternalContext().getFlash().setKeepMessages(true);

		if (usuarioPesquisado != null) {
			if (usuario.getSenha().equals(usuarioPesquisado.getSenha())) {
				System.out.println("Logando com o usuário: " + usuario.getEmail());
				context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
				return new RedirectView("livro");
			}
			context.addMessage(null, new FacesMessage("Senha incorreta, tente novamente..."));
			return new RedirectView("login");
		}
		context.addMessage(null, new FacesMessage("O usuário não existe, tente novamente..."));
		return new RedirectView("login");
	}

	public RedirectView efetuarLogout() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		
		return new RedirectView("login");
	}
}
