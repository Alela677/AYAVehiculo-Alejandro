package application;

import java.io.IOException;

import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {
	/**
	 * Main que ejecuta la aplicacion 
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		// Carga la vista del login
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
		Parent root = loader.load();
		Scene escena = new Scene(root);
		primaryStage.setScene(escena);
		LoginController controlador = loader.getController();// Controlador de la vista del login
		controlador.setStage(primaryStage);
		primaryStage.getIcons().add(new Image("images/logo.PNG"));// Icono de la ventana de la aplicacion
		primaryStage.setTitle("AYA Vehiculos"); // Titulo de la ventana de la aplicacion
		primaryStage.show();// Muestra la vista

	}

	public static void main(String[] args) {
		launch(args);
	}
}
