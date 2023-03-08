package utils;

import java.util.Iterator;
import java.util.Scanner;

import models.Empleados;

public class ValidacionFormulario {
	
	// Regex de validadcion del DNI Y Password
	final static String REGEX_DNI = "[0-9]{8}[A-Z]{1}";
	final static String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
	
	/**
	 * Metodo que comprueba el formato del DNI
	 * @param dni
	 * @return
	 */
	public boolean formatoDni(String dni) {
		return dni.matches(REGEX_DNI);
	}
	
	/**
	 * Metodo que comprueba el formato del password
	 * @param password
	 * @return
	 */
	public boolean formatoPassword(String password) {
		return password.matches(REGEX_PASSWORD);

	}

	/**
	 * Metodo que comprueba que le nombre y los apellidos no esten vacios
	 * @param nombre
	 * @param apellido
	 * @return
	 */
	private static boolean campoVacioNombreApellido(String nombre, String apellido) {
		if (nombre.isEmpty() || apellido.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo que comprueba que los campos de eleccion no esten vacios
	 * @param cargo
	 * @param departamento
	 * @return
	 */
	private static boolean camposEleccionVacios(String cargo, String departamento) {
		if (cargo == null || departamento == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo que comprueba los campos del usuarios que vamos a crear y valida el formulario
	 * @param empleado
	 * @return
	 */
	public boolean validaFormulario(Empleados empleado) {
		if (formatoDni(empleado.getDni())) {
			if (formatoPassword(empleado.getContraseña())) {
				if (!campoVacioNombreApellido(empleado.getNombre(), empleado.getApellidos())) {
					if (!camposEleccionVacios(empleado.getCargo(), empleado.getDepartamento())) {
						return true;
					} else {
						Alertas.alertaVacioEleccion();
						return false;
					}
				} else {
					Alertas.alertaVacio();
					return false;
				}
			} else {
				Alertas.alertaPassword();
				return false;
			}
		} else {
			Alertas.alertaDni();
			return false;
		}
	}
}
