package interfaz;

import java.util.List;

import models.Venta;

public interface VentaI {

	List<Venta> ventasPorCliente(String nombreCliente);

	List<Venta> ventaPorEmpleado(String nombreEmpleado);

	List<Venta> ventaPorMarcaOModelo(String valor);

	Venta ventaPorId(int id);

	List<Venta> ventasPorFecha(String fecha);
}
