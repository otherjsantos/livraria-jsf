package br.com.caelum.livraria.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	private Integer autorId;

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	private List<Autor> autores = new ArrayList<Autor>();

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Autor> getAutores() {
		this.autores = new DAO<Autor>(Autor.class).listaTodos();
		return autores;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}

		this.autor = new Autor();
	}

	public void removerAutor(Autor autor) {
		new DAO<Autor>(Autor.class).remove(autor);
	}

	public void carregarAutorPeloId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
		if(this.autor == null) {
			this.autor = new Autor();
		}
	}
}
