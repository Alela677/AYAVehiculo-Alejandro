package interfaz;

import java.util.List;

import models.Propuesta;

public interface PropuestaI {

	List<Propuesta> propuestasPorNombreCliente(String nombre);

	List<Propuesta> propuestasPorNombreEmpleado(String nombre);

	List<Propuesta> propuestasPorMarcaOModelo(String valor);

	List<Propuesta> propuestasPorEstado(String valor);

	Propuesta propuestaPorId(int id);

}
