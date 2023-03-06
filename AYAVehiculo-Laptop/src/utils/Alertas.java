package utils;

import javafx.scene.control.Alert;

public class Alertas {

	public static void alertaErrorLogin() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setContentText("Usuario incorrecto");
		alert.showAndWait();

	}

	public static void alertaVacio() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("WARNING");
		alert.setContentText("Nombre y apellidos no pueden estar vacios");
		alert.showAndWait();
	}

	public static void alertaVacioEleccion() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("WARNING");
		alert.setContentText("Los campos de eleccion no pueden estar vacios");
		alert.showAndWait();
	}

	public static void alertaDni() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("WARNING");
		alert.setContentText("El DNI debe contener 8 digitos y una letra");
		alert.showAndWait();
	}

	public static void alertaPassword() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("WARNING");
		alert.setContentText(
				"La contraseña debe contener:\n1 numero\nUna mayuscula\nUna minuscula\nUn caracter especial");
		alert.showAndWait();
	}
}
