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

	private Session sesion = HibernateUtil.getSession();
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

	@FXML
	void ordenFecha(MouseEvent event) {
		tablaVentas.getItems().clear();
		listaVentas = gestorVentas.ordenarSoloFecha();
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVentas(ventasLista);
	}

	@FXML
	void ordenAño(MouseEvent event) {
		tablaVentas.getItems().clear();
		listaVentas = gestorVentas.ordenarFechas("YEAR");
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVentas(ventasLista);
	}

	@FXML
	void ordenMes(MouseEvent event) {
		tablaVentas.getItems().clear();
		listaVentas = gestorVentas.ordenarFechas("MONTH");
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVentas(ventasLista);
	}

	@FXML
	void ordenNombreCliente(MouseEvent event) {
		tablaVentas.getItems().clear();
		listaVentas = gestorVentas.ordenarPorCampos("nombreCliente");
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVentas(ventasLista);
	}

	@FXML
	void ordenAlfabetico(MouseEvent event) {
		tablaVentas.getItems().clear();
		listaVentas = gestorVentas.ordenarPorCampos("nombreEmpleado");
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVentas(ventasLista);
	}

	@FXML
	void ordenVenta(MouseEvent event) {
		tablaVentas.getItems().clear();
		listaVentas = gestorVentas.ordenarPorCampos("id");
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVentas(ventasLista);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		listaVentas = gestorVentas.searchAll("Venta");
		ventasLista = FXCollections.observableArrayList(listaVentas);
		añadirFilasVendedor();
		añadirFilasVentas(ventasLista);
		columnasResizable(false);

	}

	private void añadirFilasVendedor() {

		vendedorList = gestorVentas.consultarRankingVendedor();
		listaVendedor = FXCollections.observableArrayList();

		for (Object[] vendedor : vendedorList) {
			listaVendedor.add(new TablaVendedor((int) vendedor[0], (String) vendedor[1], (long) vendedor[2]));

		}

		idVendedor.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombreVendedor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		totalVendedor.setCellValueFactory(new PropertyValueFactory<>("total"));
		tablaVendedor.setItems(listaVendedor);
	}

	private void añadirFilasVentas(ObservableList<Venta> ventasLista) {

		columnIdVenta.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnVehiculoMarca.setCellValueFactory(new PropertyValueFactory<>("marcaVehiculo"));
		columnVehiculoModelo.setCellValueFactory(new PropertyValueFactory<>("modeloVehiculo"));
		columnVendedor.setCellValueFactory(new PropertyValueFactory<>("nombreEmpleado"));
		columCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
		columnFechaCompra.setCellValueFactory(new PropertyValueFactory<>("fechaVenta"));
		tablaVentas.getItems().addAll(ventasLista);
	}

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
