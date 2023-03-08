package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;

public class VentaC {

	@FXML
	private Pane paneStock;

	@FXML
	private Pane paneResumen;
	
	/**
	 * Metod que muestra la vista resumen ventas
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void vistaResumen(MouseEvent event) throws IOException {
		escenaResumenVentas();
	}
	
	/**
	 * Metodo que muestra la vista stock
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void vistaStockVehiculos(MouseEvent event) throws IOException {
		escenaStock();

	}
	
	/**
	 * Metodo que carga la vista stock
	 * @throws IOException
	 */
	private void escenaStock() throws IOException {
		FXMLLoader loade = new FXMLLoader(getClass().getResource("/Views/ScenaStock.fxml"));
		AnchorPane root = loade.load();
		LoginController.root.setCenter(root);

	}
	
	/**
	 * Metodo que carga la vista resumen ventas
	 * @throws IOException
	 */
	private void escenaResumenVentas() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneResumenVentas.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);
	}

}
