package daos;

import java.util.List;

import org.hibernate.Session;

import interfaz.VentaI;
import models.HibernateUtil;
import models.Venta;

public class VentaDAO extends ComunesDAO<Venta> implements VentaI {

	private Session sesion;

	public VentaDAO(Session session) {
		super(session);
		this.sesion = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Venta> ventasPorCliente(String nombreCliente) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Venta v WHERE v.nombreCliente = '" + nombreCliente + "'").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Venta> ventaPorEmpleado(String nombreEmpleado) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Venta v WHERE v.nombreEmpleado = '" + nombreEmpleado + "'").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Venta> ventaPorMarcaOModelo(String valor) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion
				.createQuery(
						"FROM Venta v WHERE v.marcaVehiculo = '" + valor + "' OR v.modeloVehiculo= '" + valor + "' ")
				.list();
	}

	@Override
	public Venta ventaPorId(int id) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return (Venta) sesion.createQuery("FROM Venta v WHERE v.id = " + id + "").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Venta> ventasPorFecha(String fecha) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Venta v WHERE v.fechaVenta = '" + fecha + "'").list();
	}

	@SuppressWarnings("unchecked")
	public List<Venta> ordenarPorCampos(String campo) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Venta v ORDER BY v." + campo + "").list();
	}

	@SuppressWarnings("unchecked")
	public List<Venta> ordenarFechas(String tipo) {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Venta v ORDER BY " + tipo + "(v.fechaVenta)").list();
	}

	@SuppressWarnings("unchecked")
	public List<Venta> ordenarSoloFecha() {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("FROM Venta v ORDER BY v.fechaVenta").list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consultarRankingVendedor() {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery(
				"SELECT e.id, v.nombreEmpleado, COUNT(v.nombreEmpleado) FROM Venta v INNER JOIN Empleados e ON v.nombreEmpleado = e.nombre  GROUP BY(v.nombreEmpleado)")
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> consultarGanaciaVenta() {

		if (!sesion.getTransaction().isActive()) {
			sesion.beginTransaction();
		}

		return sesion.createQuery("SELECT v.id, v.marcaVehiculo , v.precio  FROM Venta v").list();
	}

	public static void main(String[] args) {
		Session sesion = HibernateUtil.getSession();
		VentaDAO vv = new VentaDAO(sesion);

		List<Object[]> l = vv.consultarGanaciaVenta();
		for (Object[] objects : l) {
			System.out.println((int) objects[0] + (String) objects[1] + (double) objects[2]);
		}
	}

}
