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

	Session sesion = HibernateUtil.getSession();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		int fila = 0;
		int columna = 0;

		try {
			listaEmpleados = gestorEmpleados.searchAll("Empleados");
			paneles = grid.crearPaneles(listaEmpleados);
			nuevoGrid = grid.crearGridPane(columna, fila, paneles);
			borderPaneEmpleados.setCenter(nuevoGrid);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rellenarCamposDepartamento();
		rellenarCamposCargo();

		Image img = new Image("images/lupa.png");
		ImageView view = new ImageView(img);
		view.setFitHeight(12);
		view.setFitWidth(12);

		Image img2 = new Image("images/lupa.png");
		ImageView view2 = new ImageView(img2);
		view2.setFitHeight(12);
		view2.setFitWidth(12);

		buttonBuqueda.setGraphic(view2);
		buttonBuquedaApellidos.setGraphic(view);
	}

	private void rellenarCamposDepartamento() {
		List<String> departamentos = gestorEmpleados.traerValoresColumnas("departamento");
		departamentos = departamentos.stream().distinct().collect(Collectors.toList());
		ObservableList<String> items = FXCollections.observableArrayList(departamentos);
		comboBoxDepartamentos.setItems(items);
	}

	private void rellenarCamposCargo() {
		List<String> cargos = gestorEmpleados.traerValoresColumnas("cargo");
		cargos = cargos.stream().distinct().collect(Collectors.toList());
		ObservableList<String> items = FXCollections.observableArrayList(cargos);
		comboBoxCargo.setItems(items);
	}

	@FXML
	void buscarEmpleadoNombre(MouseEvent event) throws IOException {
		limpiarListas();
		listaEmpleados = gestorEmpleados.consultarNombreOApellidos(txtBuscarNombre.getText().toUpperCase());
		paneles = grid.crearPaneles(listaEmpleados);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneEmpleados.setCenter(nuevoGrid);
	}

	@FXML
	void buscarEmpleadoApellidos(MouseEvent event) throws IOException {
		limpiarListas();
		listaEmpleados = gestorEmpleados.consultarNombreOApellidos(txtApellidos.getText().toUpperCase());
		paneles = grid.crearPaneles(listaEmpleados);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneEmpleados.setCenter(nuevoGrid);
	}

	@FXML
	void filtrarPorDepartamento(ActionEvent event) throws IOException {
		limpiarListas();
		listaEmpleados = gestorEmpleados.listaEmpleadosPorCulumnaYValor("departamento",
				comboBoxDepartamentos.getValue());
		paneles = grid.crearPaneles(listaEmpleados);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneEmpleados.setCenter(nuevoGrid);

	}

	@FXML
	void filtrarCargo(ActionEvent event) throws IOException {
		limpiarListas();
		listaEmpleados = gestorEmpleados.listaEmpleadosPorCulumnaYValor("cargo", comboBoxCargo.getValue());
		paneles = grid.crearPaneles(listaEmpleados);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneEmpleados.setCenter(nuevoGrid);
	}

	private static void limpiarListas() {
		listaEmpleados.clear();
		paneles.clear();
	}

}
