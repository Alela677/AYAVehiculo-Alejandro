
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
	@Override
	public void start(Stage primaryStage) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
		Parent root = loader.load();
		Scene escena = new Scene(root);
		primaryStage.setScene(escena);
		LoginController controlador = loader.getController();
		controlador.setStage(primaryStage);
		primaryStage.setMaximized(true);
		primaryStage.getIcons().add(new Image("images/logo.PNG"));
		primaryStage.setTitle("AYA Vehiculos");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
