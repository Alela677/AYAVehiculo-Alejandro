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
	
	//Sesion de hibernate con la base de datos
	private Session sesion = HibernateUtil.getSession();
	
	static List<Object[]> listaVentas = null;
	static List<Object[]> listaReparacion = null;
	
	ObservableList<TablaGanciaVenta> ventasO = null;
	ObservableList<TablaGananciaRepara> reparacionO = null;
	
	// Gestion de las ventas con la base de datos
	VentaDAO gestorVenta = new VentaDAO(sesion);
	// Gestion de las reparacinoes con la base de datos
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
	
	/**
	 * Metodo que incializa la vista con los parametros introducidos
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		DecimalFormat formatea = new DecimalFormat("###,###.##");
		cargarTablaVenta();
		cargarTablaReparaciones();
		labelTotalVenta.setText(String.valueOf(formatea.format(totalVentas())));
		labelTotalReparaciones.setText(String.valueOf(formatea.format(totalReparaciones())));
		labelGananciaTotal.setText(String.valueOf(formatea.format(gananciaTotal())));
	}
	
	/**
	 * Metodo que carga lso datos en la tabla ventas
	 */
	private void cargarTablaVenta() {
		
		// Lista que recoge la consuta a la base de datos
		listaVentas = gestorVenta.consultarGanaciaVenta();
		ventasO = FXCollections.observableArrayList();
		
		//Crea objetos para mostra enla tabla
		for (Object[] objects : listaVentas) {
			ventasO.add(new TablaGanciaVenta((int) objects[0], (String) objects[1], (double) objects[2]));
		}

		colIdV.setCellValueFactory(new PropertyValueFactory<>("id"));
		colMarcaV.setCellValueFactory(new PropertyValueFactory<>("marca"));
		colPrecioV.setCellValueFactory(new PropertyValueFactory<>("precio"));
		tablaVentaGanacias.getItems().addAll(ventasO);// Muestra todos los objetos de la tabla creados
	}
	
	/**
	 * Metodo que carga los datos en la tabla reparaciones
	 */
	private void cargarTablaReparaciones() {
		
		//Lista que recoge los datos en la base de datos
		listaReparacion = gestorReparacion.consultarGanaciaReparacion();
		reparacionO = FXCollections.observableArrayList();
		
		// Crear objetos para la tabla reparaciones
		for (Object[] objects : listaReparacion) {
			reparacionO.add(new TablaGananciaRepara((int) objects[0], (String) objects[1], (double) objects[2]));
		}
		colIdR.setCellValueFactory(new PropertyValueFactory<>("id"));
		colFechaR.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		colPrecioR.setCellValueFactory(new PropertyValueFactory<>("precio"));
		tablaReparaGanancia.getItems().addAll(reparacionO); // Muestra todo os objeto creados de la tabla
	}
	
	/**
	 * Metodo que calcula el total de la ventas
	 * @return
	 */
	private double totalVentas() {
		List<Venta> calculo = gestorVenta.searchAll("Venta");
		double resultado = 0.0;
		for (Venta venta : calculo) {
			resultado += venta.getPrecio();
		}
		return resultado;
	}
	
	/**
	 * Metodo que calcula el total de las reparaciones
	 * @return
	 */
	private double totalReparaciones() {
		List<Reparacion> calculo = gestorReparacion.searchAll("Reparacion");
		double resultado = 0.0;
		for (Reparacion reparacion : calculo) {
			resultado += reparacion.getCoste();
		}
		return resultado;
	}
	
	// Metodo que calcula el tota de todo
	private double gananciaTotal() {
		double resultado = 0.0;
		resultado = totalVentas() + totalReparaciones();
		return resultado;
	}
}
