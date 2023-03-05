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

	private Session sesion = HibernateUtil.getSession();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		listaReparaciones = gestorReparacion.searchAll("Reparacion");
		ObservableList<Reparacion> reparaciones = FXCollections.observableArrayList(listaReparaciones);

		añadirFilasReparaciones(reparaciones);
		añadirFilasRanking();
		columnasResizable(false);
	}

	private void añadirFilasReparaciones(ObservableList<Reparacion> ventasLista) {
		coID.setCellValueFactory(new PropertyValueFactory<>("idReparacion"));
		colMecanico.setCellValueFactory(new PropertyValueFactory<>("nombreMecanico"));
		colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
		colPieza.setCellValueFactory(new PropertyValueFactory<>("pieza"));
		colCoste.setCellValueFactory(new PropertyValueFactory<>("coste"));
		colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		tablaReparaciones.getItems().addAll(ventasLista);
	}

	private void añadirFilasRanking() {

		listaRanking = gestorReparacion.consultarRankingReparacion();
		rankingMecanico = FXCollections.observableArrayList();

		for (Object[] objects : listaRanking) {
			rankingMecanico.add(new TablaRankingMecanico((int) objects[0], (String) objects[1], (long) objects[2]));
		}

		colIdMencanico.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNombreMecanico.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colReparacinoesMecanicos.setCellValueFactory(new PropertyValueFactory<>("totalReparacion"));
		tablaRanking.getItems().addAll(rankingMecanico);
	}

	private void columnasResizable(boolean opc) {
		colCoste.setResizable(opc);
		coID.setResizable(opc);
		colFecha.setResizable(opc);
		colMatricula.setResizable(opc);
		colMecanico.setResizable(opc);
		colPieza.setResizable(opc);

	}

	@FXML
	void ordenAlfabetico(MouseEvent event) {
		tablaReparaciones.getItems().clear();
		listaReparaciones = gestorReparacion.ordenarPorCampos("nombreMecanico");
		reparaciones = FXCollections.observableArrayList(listaReparaciones);
		añadirFilasReparaciones(reparaciones);
		
	}

	@FXML
	void ordenAño(MouseEvent event) {
		tablaReparaciones.getItems().clear();
		listaReparaciones = gestorReparacion.ordenarFechas("YEAR");
		reparaciones = FXCollections.observableArrayList(listaReparaciones);
		añadirFilasReparaciones(reparaciones);
	}

	@FXML
	void ordenFecha(MouseEvent event) {
		tablaReparaciones.getItems().clear();
		listaReparaciones = gestorReparacion.ordenarSoloFecha();
		reparaciones = FXCollections.observableArrayList(listaReparaciones);
		añadirFilasReparaciones(reparaciones);
	}

	@FXML
	void ordenMes(MouseEvent event) {
		tablaReparaciones.getItems().clear();
		listaReparaciones = gestorReparacion.ordenarFechas("MONTH");
		reparaciones = FXCollections.observableArrayList(listaReparaciones);
		añadirFilasReparaciones(reparaciones);
	}

	@FXML
	void ordenPiezas(MouseEvent event) {
		tablaReparaciones.getItems().clear();
		listaReparaciones = gestorReparacion.ordenarPorCampos("pieza");
		reparaciones = FXCollections.observableArrayList(listaReparaciones);
		añadirFilasReparaciones(reparaciones);
	}

	@FXML
	void ordenReparacion(MouseEvent event) {
		tablaReparaciones.getItems().clear();
		listaReparaciones = gestorReparacion.ordenarPorCampos("idReparacion");
		reparaciones = FXCollections.observableArrayList(listaReparaciones);
		añadirFilasReparaciones(reparaciones);
	}
}
