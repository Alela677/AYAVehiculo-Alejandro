package controllers;

import java.io.IOException;

import javax.persistence.GeneratedValue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MecanicosC {

	@FXML
	private ImageView paneReparaciones;

	@FXML
	private Pane paneGanancia;

	@FXML
	void vistaGanancias(MouseEvent event) throws IOException {
		escenaGananciaTotal();
	}

	@FXML
	void vistaReparaciones(MouseEvent event) throws IOException {
		escenaReparaciones();
	}

	private void escenaReparaciones() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneReparaciones.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);
	}

	private void escenaGananciaTotal() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneGanaciaTotal.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);

	}
}
