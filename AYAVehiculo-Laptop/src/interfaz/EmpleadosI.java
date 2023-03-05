package interfaz;

import java.util.List;

import models.Empleados;

public interface EmpleadosI {

	

	public List<Empleados> consultarNombreOApellidos(String valor);

	Empleados buscarUnEmpleadoPorColumnaYValor(String columna, String valor);

	List<Empleados> listarEmpleadoPorColumna(String columna);

	Empleados empleadoDepartamentoLogin(String departamento, String nombre, String password);
}
