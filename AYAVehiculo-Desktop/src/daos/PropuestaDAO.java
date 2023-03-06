package daos;

import java.util.List;

import org.hibernate.Session;

import interfaz.PropuestaI;
import models.Propuesta;

public class PropuestaDAO extends ComunesDAO<Propuesta> implements PropuestaI {

	private Session sesion;

	public PropuestaDAO(Session session) {
		super(session);
		this.sesion = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propuesta> propuestasPorNombreCliente(String nombre) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Propuesta p WHERE p.nombreCliente = '" + nombre + "'").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propuesta> propuestasPorNombreEmpleado(String nombre) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return sesion.createQuery("FROM Propuesta p WHERE p.nombreEmpleado = '" + nombre + "'").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propuesta> propuestasPorMarcaOModelo(String valor) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return sesion
				.createQuery(
						"FROM Propuesta p WHERE p.marcaVehiculo = '" + valor + "' OR p.modeloVehiculo= '" + valor + "'")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propuesta> propuestasPorEstado(String valor) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Propuesta p WHERE p.estado = '" + valor + "'").list();
	}

	@Override
	public Propuesta propuestaPorId(int id) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return (Propuesta) sesion.createQuery("FROM Propuesta p WHERE p.idPropuesta = " + id + "").uniqueResult();
	}

}
