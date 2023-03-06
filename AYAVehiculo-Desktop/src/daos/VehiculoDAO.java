package daos;

import java.util.List;

import org.hibernate.Session;

import interfaz.VehiculoI;
import models.HibernateUtil;
import models.Vehiculo;

public class VehiculoDAO extends ComunesDAO<Vehiculo> implements VehiculoI {

	// Session de la base de datos
	private Session sesion;

	public VehiculoDAO(Session session) {
		super(session);
		this.sesion = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehiculo> consultaColumna(String columna) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return sesion.createQuery("SELECT v." + columna + " FROM Vehiculo v").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehiculo> consultarPrecio(String comparacion, int precio) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion
				.createQuery(
						"FROM Vehiculo v WHERE v.precio " + comparacion + " " + precio + " AND v.matricula is null")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehiculo> consultarPrecioEntre(double precio1, double precio2) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery(
				"FROM Vehiculo v WHERE v.precio BETWEEN " + precio1 + " AND " + precio2 + " AND v.matricula is null")
				.list();
	}

	@Override
	public Vehiculo filtrarPorColumnaYValor(String columna, String valor) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return (Vehiculo) sesion.createQuery("FROM Vehiculo v WHERE v." + columna + "= '" + valor + "'").uniqueResult();
	}

	@Override
	public Long stockVehiculos(String modelo) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return (Long) sesion.createQuery("SELECT COUNT(v.modelo) FROM Vehiculo v WHERE v.modelo='" + modelo
				+ "' AND v.matricula is null GROUP BY v.modelo ").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Vehiculo> listaPorColumnaYValorStock(String columna, String valor) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Vehiculo v WHERE v." + columna + "= '" + valor + "' AND v.matricula is null ")
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<String> traerValoresColumnas(String campo) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return sesion.createQuery("SELECT v." + campo + " FROM Vehiculo v WHERE v.matricula is null").list();
	}

	@SuppressWarnings("unchecked")
	public List<Vehiculo> traerVehiculosSinMatricula() {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return sesion.createQuery("FROM Vehiculo v WHERE v.matricula is null").list();
	}

	

}
