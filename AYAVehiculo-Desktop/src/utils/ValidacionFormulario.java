package utils;

import java.util.Iterator;
import java.util.Scanner;

import models.Empleados;

public class ValidacionFormulario {

	final static String REGEX_DNI = "[0-9]{8}[A-Z]{1}";
	final static String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

	public boolean formatoDni(String dni) {
		return dni.matches(REGEX_DNI);
	}

	public boolean formatoPassword(String password) {
		return password.matches(REGEX_PASSWORD);

	}

	private static boolean campoVacioNombreApellido(String nombre, String apellido) {
		if (nombre.isEmpty() || apellido.isEmpty()) {
			return true;
		}
		return false;
	}

	private static boolean camposEleccionVacios(String cargo, String departamento) {
		if (cargo == null || departamento == null) {
			return true;
		}
		return false;
	}

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
