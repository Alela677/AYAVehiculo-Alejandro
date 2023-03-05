package daos;

import java.util.List;

import org.hibernate.Session;

import interfaz.ReparacioI;

import models.Cliente;
import models.Empleados;
import models.Reparacion;
import models.Vehiculo;
import models.Venta;

public class ReparacionDAO extends ComunesDAO<Reparacion> implements ReparacioI {
	private Session sesion;

	public ReparacionDAO(Session sesion) {
		super(session);
		this.sesion = sesion;
	}

	@Override
	public Empleados buscaMecanico(String nombre) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return (Empleados) sesion
				.createQuery("SELECT r.empleado.nombre FROM Reparacion r WHERE r.empleado.nombre = '" + nombre + "'")
				.uniqueResult();
	}

	@Override
	public Vehiculo buscarVehiculo(String matricula) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return (Vehiculo) sesion.createQuery("FROM Vehiculo v WHERE v.matricula = '" + matricula + "'").uniqueResult();
	}

	@Override
	public Cliente buscarCliente(String dni) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return (Cliente) sesion.createQuery(" FROM Cliente c WHERE c.dni = '" + dni + "'").uniqueResult();
	}

	@Override
	public String fechaReparacion(String fecha) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return (String) sesion.createQuery(" FROM Reparacion r WHERE r.fecha = '" + fecha + "'").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reparacion> listaReparaciones() {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return sesion.createQuery("FROM Reparacion r").list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consultarRankingReparacion() {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return sesion.createQuery(
				"SELECT e.id, e.nombre, COUNT(r.nombreMecanico) FROM Reparacion r INNER JOIN Empleados e ON r.nombreMecanico = e.nombre GROUP BY r.nombreMecanico")
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> consultarGanaciaReparacion() {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("SELECT r.idReparacion, r.fecha , r.coste  FROM Reparacion r").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Reparacion> ordenarPorCampos(String campo) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Reparacion r ORDER BY r." + campo + "").list();
	}

	@SuppressWarnings("unchecked")
	public List<Reparacion> ordenarFechas(String tipo) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Reparacion r ORDER BY " + tipo + "(r.fecha)").list();
	}

	@SuppressWarnings("unchecked")
	public List<Reparacion> ordenarSoloFecha() {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Reparacion r ORDER BY r.fecha").list();
	}

}
