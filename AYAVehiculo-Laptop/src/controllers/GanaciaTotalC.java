package controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;

import daos.ReparacionDAO;
import daos.VentaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.HibernateUtil;
import models.Reparacion;
import models.TablaGananciaRepara;
import models.TablaGanciaVenta;
import models.Venta;

public class GanaciaTotalC implements Initializable {

	private Session sesion = HibernateUtil.getSession();

	static List<Object[]> listaVentas = null;
	static List<Object[]> listaReparacion = null;

	ObservableList<TablaGanciaVenta> ventasO = null;
	ObservableList<TablaGananciaRepara> reparacionO = null;

	VentaDAO gestorVenta = new VentaDAO(sesion);
	ReparacionDAO gestorReparacion = new ReparacionDAO(sesion);

	@FXML
	private TableView<TablaGanciaVenta> tablaVentaGanacias;

	@FXML
	private TableColumn<TablaGanciaVenta, Integer> colIdV;

	@FXML
	private TableColumn<TablaGanciaVenta, String> colMarcaV;

	@FXML
	private TableColumn<Venta, Double> colPrecioV;

	@FXML
	private TableView<TablaGananciaRepara> tablaReparaGanancia;

	@FXML
	private TableColumn<TablaGananciaRepara, Integer> colIdR;

	@FXML
	private TableColumn<TablaGananciaRepara, String> colFechaR;

	@FXML
	private TableColumn<TablaGananciaRepara, Double> colPrecioR;

	@FXML
	private Label labelTotalVenta;

	@FXML
	private Label labelTotalReparaciones;

	@FXML
	private Label labelGananciaTotal;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		DecimalFormat formatea = new DecimalFormat("###,###.##");
		cargarTablaVenta();
		cargarTablaReparaciones();
		labelTotalVenta.setText(String.valueOf(formatea.format(totalVentas())));
		labelTotalReparaciones.setText(String.valueOf(formatea.format(totalReparaciones())));
		labelGananciaTotal.setText(String.valueOf(formatea.format(gananciaTotal())));
	}

	private void cargarTablaVenta() {

		listaVentas = gestorVenta.consultarGanaciaVenta();
		ventasO = FXCollections.observableArrayList();

		for (Object[] objects : listaVentas) {
			ventasO.add(new TablaGanciaVenta((int) objects[0], (String) objects[1], (double) objects[2]));
		}

		colIdV.setCellValueFactory(new PropertyValueFactory<>("id"));
		colMarcaV.setCellValueFactory(new PropertyValueFactory<>("marca"));
		colPrecioV.setCellValueFactory(new PropertyValueFactory<>("precio"));
		tablaVentaGanacias.getItems().addAll(ventasO);
	}

	private void cargarTablaReparaciones() {
		listaReparacion = gestorReparacion.consultarGanaciaReparacion();
		reparacionO = FXCollections.observableArrayList();

		for (Object[] objects : listaReparacion) {
			reparacionO.add(new TablaGananciaRepara((int) objects[0], (String) objects[1], (double) objects[2]));
		}
		colIdR.setCellValueFactory(new PropertyValueFactory<>("id"));
		colFechaR.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		colPrecioR.setCellValueFactory(new PropertyValueFactory<>("precio"));
		tablaReparaGanancia.getItems().addAll(reparacionO);
	}

	private double totalVentas() {
		List<Venta> calculo = gestorVenta.searchAll("Venta");
		double resultado = 0.0;
		for (Venta venta : calculo) {
			resultado += venta.getPrecio();
		}
		return resultado;
	}

	private double totalReparaciones() {
		List<Reparacion> calculo = gestorReparacion.searchAll("Reparacion");
		double resultado = 0.0;
		for (Reparacion reparacion : calculo) {
			resultado += reparacion.getCoste();
		}
		return resultado;
	}

	private double gananciaTotal() {
		double resultado = 0.0;
		resultado = totalVentas() + totalReparaciones();
		return resultado;
	}
}
