package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class EmpleadosC {

	@FXML
	private Pane paneEmpleados;

	@FXML
	private Pane paneCrear;
	
	/**
	 * Metodo que muestra la vista Crear empleados
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void vistaCrearEmpleado(MouseEvent event) throws IOException {
		vistaCrearEmpleado();
	}
	
	/**
	 * Metodo que muestra la vista Ver empleados
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void vistaEmpleados(MouseEvent event) throws IOException {
		vistaVerEmpleado();
	}
	
	/**
	 * Metodo que carga la vista ver empleados
	 * @throws IOException
	 */
	private void vistaVerEmpleado() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneVerEmpleados.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);

	}
	
	/**
	 * Metodo que carga la vista crear empleados
	 * @throws IOException
	 */
	private void vistaCrearEmpleado() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneCrearEmpleado.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);
	}
}
