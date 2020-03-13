package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {

		FacesContext context = event.getFacesContext();
		String viewId = context.getViewRoot().getViewId();

		System.out.println(viewId);

		if ("/login.xhtml".equals(viewId)) {
			return;
		}

		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		if (usuario != null) {
			return;
		}

		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		navHandler.handleNavigation(context, null, "/login?faces-redirect=true");
		context.renderResponse();

	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
