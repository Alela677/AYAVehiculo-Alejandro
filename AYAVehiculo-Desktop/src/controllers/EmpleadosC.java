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

	@FXML
	void vistaCrearEmpleado(MouseEvent event) throws IOException {
		vistaCrearEmpleado();
	}

	@FXML
	void vistaEmpleados(MouseEvent event) throws IOException {
		vistaVerEmpleado();
	}
	
	
	private void vistaVerEmpleado() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneVerEmpleados.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);

	}

	private void vistaCrearEmpleado() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneCrearEmpleado.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);
	}
}
