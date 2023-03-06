package controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;

import daos.EmpleadosDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Empleados;
import models.HibernateUtil;
import utils.Alertas;
import utils.HashPassword;

public class LoginController implements Initializable {
	private Session sesion = HibernateUtil.getSession();
	EmpleadosDAO gestorEmpleados = new EmpleadosDAO(sesion);

	public static BorderPane root;
	public static Stage stage;

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnLogin;

	@FXML
	private ImageView facebook;

	@FXML
	private ImageView instagram;

	@FXML
	private ImageView twitter;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtUser;

	private Empleados comprobar;

	@FXML
	void logeo(MouseEvent event) throws IOException {

		String nombre = txtUser.getText();
		String passwd = txtPassword.getText();
		boolean registrado = false;

		try {
			comprobar = gestorEmpleados.empleadoDepartamentoLogin("JEFE", nombre,HashPassword.convertirSHA256(passwd));
			if (comprobar.getNombre().equalsIgnoreCase(nombre)
					&& comprobar.getContraseña().equals(HashPassword.convertirSHA256(passwd))) {

			registrado = true;

			}

		} catch (NullPointerException e) {
			Alertas.alertaErrorLogin();
		}

		if (registrado == true) {

			FXMLLoader loade = new FXMLLoader(getClass().getResource("/views/PrincipalView.fxml"));
			root = loade.load();
			PrincipalC control = loade.getController();
			Scene escena = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(escena);
			control.init(stage, this, txtUser.getText(), root);
			stage.show();
			this.stage.close();

		} 
	}

	@FXML
	void salir(MouseEvent event) {
		Platform.exit();
	}

	@FXML
	void webFacebook(MouseEvent event) {
		URL url = null;
		try {
			url = new URL("https://es-es.facebook.com/");
			try {
				Desktop.getDesktop().browse(url.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	void webInstagram(MouseEvent event) {
		URL url = null;
		try {
			url = new URL("http://instagram.com/");
			try {
				Desktop.getDesktop().browse(url.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	void webTwitter(MouseEvent event) {
		URL url = null;
		try {
			url = new URL("https://twitter.com/?lang=es");
			try {
				Desktop.getDesktop().browse(url.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}

	

	public void setStage(Stage primaryStage) {
		stage = primaryStage;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtUser.setText("FRANCISCO");
		txtPassword.setText("passFrancisco");

	}

}
