package interfaz;

import java.util.List;

import models.Vehiculo;

public interface VehiculoI {

	List<Vehiculo> consultaColumna(String columna);

	List<Vehiculo> consultarPrecio(String comparacion, int precio);

	List<Vehiculo> consultarPrecioEntre(double precio1, double precio2);

	Vehiculo filtrarPorColumnaYValor(String columna, String valor);

	Long stockVehiculos(String modelo);
}
