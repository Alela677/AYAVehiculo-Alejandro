package daos;

import java.util.List;

import org.hibernate.Session;

import interfaz.EmpleadosI;
import javafx.css.PseudoClass;
import models.Empleados;
import models.HibernateUtil;

public class EmpleadosDAO extends ComunesDAO<Empleados> implements EmpleadosI {

	private Session sesion;

	public EmpleadosDAO(Session session) {
		super(session);
		this.sesion = session;
	}

	@Override
	public Empleados empleadoDepartamentoLogin(String departamento, String nombre, String password) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		System.out.println(departamento + "|" + nombre + "|" + password);
		return (Empleados) sesion.createQuery(
				"FROM Empleados e WHERE e.departamento = '" + departamento + "' AND e.contraseña = '" + password + "'")
				.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empleados> consultarNombreOApellidos(String valor) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion
				.createQuery("FROM Empleados e WHERE e.nombre = '" + valor + "' OR e.apellidos LIKE '%" + valor + "%'")
				.list();
	}

	@Override
	public Empleados buscarUnEmpleadoPorColumnaYValor(String columna, String valor) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return (Empleados) sesion.createQuery("FROM Empleados e WHERE e." + columna + " = '" + valor + "' ")
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empleados> listarEmpleadoPorColumna(String columna) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Empleados e WHERE e." + columna + "").list();
	}

	@SuppressWarnings("unchecked")
	public List<String> traerValoresColumnasCargos() {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return sesion.createQuery("SELECT e.cargo FROM Empleados e").list();
	}

	@SuppressWarnings("unchecked")
	public List<Empleados> listaEmpleados() {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return sesion.createQuery("FROM Empleados e").list();
	}

	@SuppressWarnings("unchecked")
	public List<String> traerValoresColumnas(String columna) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return sesion.createQuery("SELECT e." + columna + " FROM Empleados e").list();
	}

	@SuppressWarnings("unchecked")
	public List<Empleados> listaEmpleadosPorCulumnaYValor(String columna, String valor) {
		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}
		return sesion.createQuery("FROM Empleados e WHERE e." + columna + " = '" + valor + "'").list();
	}

}
