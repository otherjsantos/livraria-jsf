package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDAO {

	public Usuario getByEmail(String email) {
		
		Usuario usuario = null;
		EntityManager em = new JPAUtil().getEntityManager();
        TypedQuery<Usuario> query = 
        		em.createQuery
        		("select u from Usuario u where u.email = :pEmail", Usuario.class);
        
        query.setParameter("pEmail", email);
        
        try {
        	usuario = query.getSingleResult();
        }catch (NoResultException e) {
			return usuario;
		}
		return usuario;
	}
}
