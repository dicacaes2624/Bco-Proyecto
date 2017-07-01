package co.edu.usbcali.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.dao.IUsuariosDAO;
import co.edu.usbcali.demo.modelo.Usuarios;

@Repository
@Scope("singleton")
public class UsuariosDAO implements IUsuariosDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void crear(Usuarios entity) {
		entityManager.persist(entity);
	}

	@Override
	public void modificar(Usuarios entity) {
		entityManager.merge(entity);
	}

	@Override
	public void borrar(Usuarios entity) {
		entityManager.remove(entity);
	}

	@Override
	public Usuarios consultarPorId(long cliId) {
		return entityManager.find(Usuarios.class, cliId);
	}

	@Override
	public List<Usuarios> consultar() {
		String jpql="SELECT usu FROM Usuarios usu";		
		return entityManager.createQuery(jpql).getResultList();
	}
}
