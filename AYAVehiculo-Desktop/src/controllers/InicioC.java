package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class InicioC {

	@FXML
	private Pane ventas;

	@FXML
	private Pane empleados;

	@FXML
	private ImageView mecanicos;

	@FXML
	private ImageView imgVentas;

	@FXML
	private ImageView imgEmpleados;
	
	/**
	 * Metodo que muestra la vista empleados
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void vistaEmpleados(MouseEvent event) throws IOException {
		escenaEmpleados();
	}
	
	/**
	 * Metodo que muestra la vista mecanicos
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void vistaMecanicos(MouseEvent event) throws IOException {
		escenaMecanicos();
	}
	
	/**
	 * Metodo que muestra la vista ventas
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void vistaVentas(MouseEvent event) throws IOException {
		escenaVentas();
	}
	
	/**
	 * Metodo que carga la vista ventas
	 * @throws IOException
	 */
	private void escenaVentas() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneVentas.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);

	}
	
	/**
	 * Metodos que carga la vista empleados
	 * @throws IOException
	 */
	private void escenaEmpleados() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneEmpleados.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);
	}
	
	/**
	 * Metodos que carga la vista mecanicos
	 * @throws IOException
	 */
	private void escenaMecanicos() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneMecanicos.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);
	}

}
