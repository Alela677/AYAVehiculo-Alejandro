package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.hibernate.Session;

import daos.EmpleadosDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.Empleados;
import models.HibernateUtil;
import utils.GridpanelEmpleados;

public class VerEmpleadosC implements Initializable {
	
	// Sesion de hibernate conla base de datos
	Session sesion = HibernateUtil.getSession();
	// Gestion de los empleados con la base de datos
	EmpleadosDAO gestorEmpleados = new EmpleadosDAO(sesion);

	private GridpanelEmpleados grid = new GridpanelEmpleados();

	static List<Empleados> listaEmpleados = new ArrayList<Empleados>();

	static List<AnchorPane> paneles = new ArrayList<AnchorPane>();

	private GridPane nuevoGrid;

	private int columna = 0;
	private int fila = 0;
	@FXML
	private ComboBox<String> comboBoxDepartamentos;

	@FXML
	private BorderPane borderPaneEmpleados;

	@FXML
	private ComboBox<String> comboBoxCargo;

	@FXML
	private Button buttonBuqueda;

	@FXML
	private TextField txtBuscarNombre;

	@FXML
	private TextField txtApellidos;

	@FXML
	private Button buttonBuquedaApellidos;
	
	/**
	 * Metodo que inicializa la vista con los parametros introducidos
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		int fila = 0;
		int columna = 0;
		
		// Recoge los datos de los empleados en la base de datos y los muestra en la tabla
		try {
			listaEmpleados = gestorEmpleados.searchAll("Empleados");
			paneles = grid.crearPaneles(listaEmpleados);
			nuevoGrid = grid.crearGridPane(columna, fila, paneles);
			borderPaneEmpleados.setCenter(nuevoGrid);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Rellena los campos en el combobox cargos y departamentos
		rellenarCamposDepartamento();
		rellenarCamposCargo();

		Image img = new Image("images/lupa.png");
		ImageView view = new ImageView(img);
		view.setFitHeight(18);
		view.setFitWidth(18);

		Image img2 = new Image("images/lupa.png");
		ImageView view2 = new ImageView(img2);
		view2.setFitHeight(18);
		view2.setFitWidth(18);

		buttonBuqueda.setGraphic(view2);
		buttonBuquedaApellidos.setGraphic(view);
	}
	
	/**
	 * Metodo que rellena los campos departaamento con lo que tenga disponibles en la base de datos
	 */
	private void rellenarCamposDepartamento() {
		List<String> departamentos = gestorEmpleados.traerValoresColumnas("departamento");
		departamentos = departamentos.stream().distinct().collect(Collectors.toList());
		ObservableList<String> items = FXCollections.observableArrayList(departamentos);
		comboBoxDepartamentos.setItems(items);
	}
	
	/**
	 * Metodo que rellena los cargos con lo que tengas disponibles en la base de datos
	 */
	private void rellenarCamposCargo() {
		List<String> cargos = gestorEmpleados.traerValoresColumnas("cargo");
		cargos = cargos.stream().distinct().collect(Collectors.toList());
		ObservableList<String> items = FXCollections.observableArrayList(cargos);
		comboBoxCargo.setItems(items);
	}
	
	/**
	 * Metodo que busca empleado por nombre y los muestra
	 * @throws IOException
	 */
	@FXML
	void buscarEmpleadoNombre(MouseEvent event) throws IOException {
		limpiarListas();
		listaEmpleados = gestorEmpleados.consultarNombreOApellidos(txtBuscarNombre.getText().toUpperCase());
		paneles = grid.crearPaneles(listaEmpleados);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneEmpleados.setCenter(nuevoGrid);
	}
	
	/**
	 * Metodo que busca empleado por apellidos y los muestra
	 * @throws IOException
	 */
	@FXML
	void buscarEmpleadoApellidos(MouseEvent event) throws IOException {
		limpiarListas();
		listaEmpleados = gestorEmpleados.consultarNombreOApellidos(txtApellidos.getText().toUpperCase());
		paneles = grid.crearPaneles(listaEmpleados);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneEmpleados.setCenter(nuevoGrid);
	}
	
	/**
	 * Metodo que filtra los empleados por deparamento
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void filtrarPorDepartamento(ActionEvent event) throws IOException {
		limpiarListas();
		listaEmpleados = gestorEmpleados.listaEmpleadosPorCulumnaYValor("departamento",
				comboBoxDepartamentos.getValue());
		paneles = grid.crearPaneles(listaEmpleados);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneEmpleados.setCenter(nuevoGrid);

	}
	
	/**
	 * Metodo que filtra los empleados por cargo
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void filtrarCargo(ActionEvent event) throws IOException {
		limpiarListas();
		listaEmpleados = gestorEmpleados.listaEmpleadosPorCulumnaYValor("cargo", comboBoxCargo.getValue());
		paneles = grid.crearPaneles(listaEmpleados);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneEmpleados.setCenter(nuevoGrid);
	}
	
	/**
	 * Metodo que limpia las listas 
	 */
	private static void limpiarListas() {
		listaEmpleados.clear();
		paneles.clear();
	}

}
