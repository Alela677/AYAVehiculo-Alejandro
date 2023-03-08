package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;

import daos.ReparacionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.HibernateUtil;
import models.Reparacion;
import models.TablaRankingMecanico;

public class ReparacionesC implements Initializable {
	
	// Sesion de hibernate con la base de datos
	private Session sesion = HibernateUtil.getSession();
	// Gestion de la reparacion con la base de datos
	ReparacionDAO gestorReparacion = new ReparacionDAO(sesion);

	List<Reparacion> listaReparaciones = new ArrayList<Reparacion>();
	static ObservableList<Reparacion> reparaciones = null;

	List<Object[]> listaRanking = null;
	static ObservableList<TablaRankingMecanico> rankingMecanico = null;

	@FXML
	private TableView<Reparacion> tablaReparaciones;

	@FXML
	private TableColumn<Reparacion, Integer> coID;

	@FXML
	private TableColumn<Reparacion, String> colMecanico;

	@FXML
	private TableColumn<Reparacion, String> colMatricula;

	@FXML
	private TableColumn<Reparacion, String> colPieza;

	@FXML
	private TableColumn<Reparacion, String> colFecha;

	@FXML
	private TableColumn<Reparacion, Double> colCoste;

	@FXML
	private TableView<TablaRankingMecanico> tablaRanking;

	@FXML
	private TableColumn<TablaRankingMecanico, Integer> colIdMencanico;

	@FXML
	private TableColumn<TablaRankingMecanico, String> colNombreMecanico;

	@FXML
	private TableColumn<TablaRankingMecanico, Integer> colReparacinoesMecanicos;
	
	/**
	 * Metodo que incializa la vista con los parametros introducidos
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		listaReparaciones = gestorReparacion.searchAll("Reparacion");
		ObservableList<Reparacion> reparaciones = FXCollections.observableArrayList(listaReparaciones);

		añadirFilasReparaciones(reparaciones);
		añadirFilasRanking();
		columnasResizable(false);
	}
	
	/**
	 * Metodo que añade los datos de las ventas a la tabla de ventas
	 * @param ventasLista
	 */
	private void añadirFilasReparaciones(ObservableList<Reparacion> ventasLista) {
		coID.setCellValueFactory(new PropertyValueFactory<>("idReparacion"));
		colMecanico.setCellValueFactory(new PropertyValueFactory<>("nombreMecanico"));
		colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
		colPieza.setCellValueFactory(new PropertyValueFactory<>("pieza"));
		colCoste.setCellValueFactory(new PropertyValueFactory<>("coste"));
		colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		tablaReparaciones.getItems().addAll(ventasLista); // Inserta lo valores de las ventas en la tabla
	}
	
	/**
	 * Metodo que añade los valores del ranking a la tabla
	 */
	private void añadirFilasRanking() {
		// Recoge los valores de las ventas de cada empleado
		listaRanking = gestorReparacion.consultarRankingReparacion();
		rankingMecanico = FXCollections.observableArrayList();
		
		// Crea objeto para la tabla
		for (Object[] objects : listaRanking) {
			rankingMecanico.add(new TablaRankingMecanico((int) objects[0], (String) objects[1], (long) objects[2]));
		}

		colIdMencanico.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNombreMecanico.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colReparacinoesMecanicos.setCellValueFactory(new PropertyValueFactory<>("totalReparacion"));
		tablaRanking.getItems().addAll(rankingMecanico); // Muestra los valores de lo objeto en la tabla
	}
	
	/**
	 * Metodo que actuliza el estado de resizable o no 
	 * @param opc Opcion true o false
	 */
	private void columnasResizable(boolean opc) {
		colCoste.setResizable(opc);
		coID.setResizable(opc);
		colFecha.setResizable(opc);
		colMatricula.setResizable(opc);
		colMecanico.setResizable(opc);
		colPieza.setResizable(opc);

	}
	
	/**
	 * Metodo que ordena las reparaciones por años
	 * @param event
	 */
	@FXML
	void ordenAño(MouseEvent event) {
		tablaReparaciones.getItems().clear();
		listaReparaciones = gestorReparacion.ordenarFechas("YEAR");
		reparaciones = FXCollections.observableArrayList(listaReparaciones);
		añadirFilasReparaciones(reparaciones);
	}
	
	/**
	 * Metodo que ordena las reparaciones por fecha
	 * @param event
	 */
	@FXML
	void ordenFechas(MouseEvent event) {
		tablaReparaciones.getItems().clear();
		listaReparaciones = gestorReparacion.ordenarSoloFecha();
		reparaciones = FXCollections.observableArrayList(listaReparaciones);
		añadirFilasReparaciones(reparaciones);
	}
	
	/**
	 * Metodo que ordena las reparaciones por id de reparacion
	 * @param event
	 */
	@FXML
	void ordenId(MouseEvent event) {
		tablaReparaciones.getItems().clear();
		listaReparaciones = gestorReparacion.ordenarPorCampos("idReparacion");
		reparaciones = FXCollections.observableArrayList(listaReparaciones);
		añadirFilasReparaciones(reparaciones);
	}
	
	/**
	 * Metodo que ordena las reparaciones por nombre mecanico
	 * @param event
	 */
	@FXML
	void ordenMecanico(MouseEvent event) {
		tablaReparaciones.getItems().clear();
		listaReparaciones = gestorReparacion.ordenarPorCampos("nombreMecanico");
		reparaciones = FXCollections.observableArrayList(listaReparaciones);
		añadirFilasReparaciones(reparaciones);
	}
	
	/**
	 * Metodo que ordena las reparaciones por el mes
	 * @param event
	 */
	@FXML
	void ordenMes(MouseEvent event) {
		tablaReparaciones.getItems().clear();
		listaReparaciones = gestorReparacion.ordenarFechas("MONTH");
		reparaciones = FXCollections.observableArrayList(listaReparaciones);
		añadirFilasReparaciones(reparaciones);
	}
	
	/**
	 * Metodo que ordena alfabeticamente las piezas
	 * @param event
	 */
	@FXML
	void ordenPieza(MouseEvent event) {
		tablaReparaciones.getItems().clear();
		listaReparaciones = gestorReparacion.ordenarPorCampos("pieza");
		reparaciones = FXCollections.observableArrayList(listaReparaciones);
		añadirFilasReparaciones(reparaciones);
	}
}
