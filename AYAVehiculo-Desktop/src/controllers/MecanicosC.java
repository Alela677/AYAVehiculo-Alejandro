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
	private Pane paneGanancia;
	
	/**
	 * Metodo que muestra la vista ganacia total
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void vistaGanancias(MouseEvent event) throws IOException {
		escenaGananciaTotal();
	}
	
	/**
	 * Metodo que muestra la vista reparaciones
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void verReaparaciones(MouseEvent event) throws IOException {
		escenaReparaciones();
	}
	
	/**
	 * Metodo que carga la vista reparaciones
	 * @throws IOException
	 */
	private void escenaReparaciones() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneReparaciones.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);
	}
	
	/**
	 * Metodo que carga la vista ganacia total
	 * @throws IOException
	 */
	private void escenaGananciaTotal() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneGanaciaTotal.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);

	}
}
