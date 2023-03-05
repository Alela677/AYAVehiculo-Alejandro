package daos;

import java.util.List;

import org.hibernate.Session;

import interfaz.ClienteI;
import models.Cliente;

public class ClienteDAO extends ComunesDAO<Cliente> implements ClienteI {

	private Session sesion;

	public ClienteDAO(Session session) {
		super(session);
		this.sesion = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listaClientes() {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return sesion.createQuery("FROM Cliente").list();
	}

	@Override
	public Cliente buscarPorDni(String dni) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return (Cliente) sesion.createQuery("FROM Cliente c WHERE c.dni = '" + dni + "'").uniqueResult();
	}

	@Override
	public Cliente buscarPorApellidosONombre(String valor) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return (Cliente) sesion
				.createQuery("FROM Cliente c WHERE c.nombre = '" + valor + "' OR c.apellidos LIKE '%" + valor + "%'")
				.uniqueResult();
	}

	@Override
	public Cliente buscarPorTelefono(int telefono) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return (Cliente) sesion.createQuery("FROM Cliente c WHERE c.telefono = '" + telefono + "'").uniqueResult();
	}

}
