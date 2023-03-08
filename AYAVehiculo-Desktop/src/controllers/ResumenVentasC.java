package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;

import daos.VentaDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.HibernateUtil;
import models.TablaVendedor;
import models.Venta;

public class ResumenVentasC implements Initializable {
	// Sesion de hibernate con la base de datos
	private Session sesion = HibernateUtil.getSession();
	// Gestion de ventas con la base de datos
	VentaDAO gestorVentas = new VentaDAO(sesion);

	private static List<Venta> listaVentas = new ArrayList<Venta>();
	private List<Object[]> vendedorList = new ArrayList<Object[]>();
	private static ObservableList<Venta> ventasLista = null;
	private ObservableList<TablaVendedor> listaVendedor = null;

	@FXML
	private TableView<Venta> tablaVentas;

	@FXML
	private TableColumn<Venta, Integer> columnIdVenta;

	@FXML
	private TableColumn<Venta, Integer> columnVehiculoMarca;

	@FXML
	private TableColumn<Venta, Integer> columnVehiculoModelo;

	@FXML
	private TableColumn<Venta, String> columnVendedor;

	@FXML
	private TableColumn<Venta, String> columCliente;

	@FXML
	private TableColumn<Venta, String> columnFechaCompra;

	@FXML
	private TableView<TablaVendedor> tablaVendedor;

	@FXML
	private TableColumn<TablaVendedor, Integer> idVendedor;

	@FXML
	private TableColumn<TablaVendedor, String> nombreVendedor;

	@FXML
	private TableColumn<TablaVendedor, Long> totalVendedor;
	
	/**
	 * Metodo que ordena la tabla por fechas
	 * @param event
	 */
	@FXML
	void ordenFecha(MouseEvent event) {
		tablaVentas.getItems().clear();
		listaVentas = gestorVentas.ordenarSoloFecha();
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVentas(ventasLista);
	}
	
	/**
	 * Metodo que ordena la tabla por año
	 * @param event
	 */
	@FXML
	void ordenAño(MouseEvent event) {
		tablaVentas.getItems().clear();
		listaVentas = gestorVentas.ordenarFechas("YEAR");
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVentas(ventasLista);
	}
	
	/**
	 * Metodoq que ordena la tabla por mes
	 * @param event
	 */
	@FXML
	void ordenMes(MouseEvent event) {
		tablaVentas.getItems().clear();
		listaVentas = gestorVentas.ordenarFechas("MONTH");
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVentas(ventasLista);
	}
	
	/**
	 * Metodo que ordena alfabeticamente los nombre de los clientes
	 */
	
	@FXML
	void ordenNombreCliente(MouseEvent event) {
		tablaVentas.getItems().clear();
		listaVentas = gestorVentas.ordenarPorCampos("nombreCliente");
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVentas(ventasLista);
	}
	
	/**
	 * Metodo que ordena alfabeticamente los nombre de los empleados
	 * @param event
	 */
	@FXML
	void ordenAlfabetico(MouseEvent event) {
		tablaVentas.getItems().clear();
		listaVentas = gestorVentas.ordenarPorCampos("nombreEmpleado");
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVentas(ventasLista);
	}
	
	/**
	 * Metodo que ordena por id de ventas
	 * @param event
	 */
	@FXML
	void ordenVenta(MouseEvent event) {
		tablaVentas.getItems().clear();
		listaVentas = gestorVentas.ordenarPorCampos("id");
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVentas(ventasLista);
	}
	
	/**
	 * Metodo que incializa la vista con los parametro introducidos
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		listaVentas = gestorVentas.searchAll("Venta");
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVendedor();
		añadirFilasVentas(ventasLista);
		columnasResizable(false);

	}
	
	/**
	 * Metodo que muestra los datos en la vista ranking vendedor
	 */
	private void añadirFilasVendedor() {
		
		// Recoge los datos de la base de datos para el ranking de vendedor
		vendedorList = gestorVentas.consultarRankingVendedor();
		listaVendedor = FXCollections.observableArrayList();
		
		// Crea objeto para la tabla del ranking
		for (Object[] vendedor : vendedorList) {
			listaVendedor.add(new TablaVendedor((int) vendedor[0], (String) vendedor[1], (long) vendedor[2]));

		}

		idVendedor.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombreVendedor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		totalVendedor.setCellValueFactory(new PropertyValueFactory<>("total"));
		tablaVendedor.setItems(listaVendedor); // Muestra los valores de los objetos en la tabla
	}
	
	/**
	 * Metodo que carga los datos de la ventas en la tabla
	 * @param ventasLista
	 */
	private void añadirFilasVentas(ObservableList<Venta> ventasLista) {

		columnIdVenta.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnVehiculoMarca.setCellValueFactory(new PropertyValueFactory<>("marcaVehiculo"));
		columnVehiculoModelo.setCellValueFactory(new PropertyValueFactory<>("modeloVehiculo"));
		columnVendedor.setCellValueFactory(new PropertyValueFactory<>("nombreEmpleado"));
		columCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
		columnFechaCompra.setCellValueFactory(new PropertyValueFactory<>("fechaVenta"));
		tablaVentas.getItems().addAll(ventasLista);
	}
	
	/**
	 * Metodod que vulve las columnas resizable segun la opcion
	 * @param opc Opcion true o false
	 */
	private void columnasResizable(boolean opc) {

		columCliente.setResizable(opc);
		columnFechaCompra.setResizable(opc);
		columnVehiculoModelo.setResizable(opc);
		columnVehiculoMarca.setResizable(opc);
		columnIdVenta.setResizable(opc);
		columnVendedor.setResizable(opc);
		idVendedor.setResizable(opc);
		nombreVendedor.setResizable(opc);
		totalVendedor.setResizable(opc);

	}

}
