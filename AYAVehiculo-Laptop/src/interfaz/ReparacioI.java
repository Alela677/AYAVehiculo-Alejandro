package interfaz;

import java.util.List;

import models.Cliente;
import models.Empleados;
import models.Reparacion;
import models.Vehiculo;

public interface ReparacioI {

	Empleados buscaMecanico(String nombre);

	Vehiculo buscarVehiculo(String matricula);

	Cliente buscarCliente(String dni);

	String fechaReparacion(String fecha);
	
	List<Reparacion> listaReparaciones();
}
