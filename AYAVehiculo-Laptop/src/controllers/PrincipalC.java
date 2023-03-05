
package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.zip.InflaterInputStream;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalC {

	@SuppressWarnings("unused")
	private Stage stage;
	@SuppressWarnings("unused")
	private LoginController controLogin;
	private BorderPane borderPane;

	@FXML
	private Label nombreUsuario;

	@FXML
	private MenuButton menuVentas;

	@FXML
	private MenuButton menuEmpleados;

	@FXML
	private MenuButton menuMecanicos;

	@FXML
	private Button btnInicio;

	@FXML
	private MenuItem stockMenuButton;

	@FXML
	private MenuItem verResumenVentas;

	@FXML
	private Button buttonApagar;

	@FXML
	void mostrarStock(ActionEvent event) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ScenaStock.fxml"));
		AnchorPane root = loader.load();
		borderPane.setCenter(root);
	}

	public void init(Stage stage, LoginController loginController, String usuario, BorderPane border)
			throws IOException {
		this.stage = stage;
		this.controLogin = loginController;
		nombreUsuario.setText(usuario);
		this.borderPane = border;
		stage.getIcons().add(new Image("images/logo.PNG"));
		stage.setTitle("AYA Vehiculos");
		escenaInicio();
	}

	public void escenaInicio() throws IOException {
		FXMLLoader load = new FXMLLoader(getClass().getResource("/Views/ScenaInicio.fxml"));
		AnchorPane root = load.load();
		borderPane.setCenter(root);

	}

	@FXML
	void vistaInicio(MouseEvent event) throws IOException {
		escenaInicio();
		borderPane.setLeft(null);
	}

	@FXML
	void mostrarVistaVerEmpleados(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneVerEmpleados.fxml"));
		AnchorPane root = loader.load();
		borderPane.setCenter(root);

	}

	@FXML
	void mostrasCrearEmpleado(ActionEvent event) throws IOException {
		escenaCrearEmpledos();
	}

	private void escenaCrearEmpledos() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneCrearEmpleado.fxml"));
		AnchorPane root = loader.load();
		borderPane.setCenter(root);
	}

	private void escenaResumenVentas() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneResumenVentas.fxml"));
		AnchorPane root = loader.load();
		borderPane.setCenter(root);
	}

	@FXML
	void escenaResumenVentas(ActionEvent event) throws IOException {
		escenaResumenVentas();
	}

	@FXML
	void verGanancias(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneGanaciaTotal.fxml"));
		AnchorPane root = loader.load();
		borderPane.setCenter(root);
	}

	@FXML
	void verReparaciones(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneReparaciones.fxml"));
		AnchorPane root = loader.load();
		borderPane.setCenter(root);
	}

}