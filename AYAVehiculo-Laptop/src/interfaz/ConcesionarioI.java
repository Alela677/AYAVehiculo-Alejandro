package interfaz;

import models.Concesionario;

public interface ConcesionarioI {

	Concesionario buscarPorNombre(String nombre);

	Concesionario buscarPorId(int id);

	Concesionario buscarPorLocalidad(String localidad);

	Concesionario buscarPorNif(String nif);

}
