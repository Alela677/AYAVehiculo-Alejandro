package utils;

import javafx.scene.control.Alert;
	
// Clase de alertas para la aplicacion
public class Alertas {
	
	// Alerta error en el login
	public static void alertaErrorLogin() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setContentText("Usuario incorrecto");
		alert.showAndWait();

	}
	
	// Alerta error en el campo nombre y apellido del formulario
	public static void alertaVacio() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("WARNING");
		alert.setContentText("Nombre y apellidos no pueden estar vacios");
		alert.showAndWait();
	}
	
	// Alertas error en el campo de eleccion vacio
	public static void alertaVacioEleccion() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("WARNING");
		alert.setContentText("Los campos de eleccion no pueden estar vacios");
		alert.showAndWait();
	}
	
	// Alerta formato del DNI erroneo
	public static void alertaDni() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("WARNING");
		alert.setContentText("El DNI debe contener 8 digitos y una letra");
		alert.showAndWait();
	}
	
	// Alerta de formato de password erroneo
	public static void alertaPassword() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("WARNING");
		alert.setContentText(
				"La contraseña debe contener:\n1 numero\nUna mayuscula\nUna minuscula\nUn caracter especial");
		alert.showAndWait();
	}
}
