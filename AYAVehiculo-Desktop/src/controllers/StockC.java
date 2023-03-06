package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.hibernate.Session;

import daos.EmpleadosDAO;
import daos.VehiculoDAO;
import daos.VentaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.HibernateUtil;
import models.Vehiculo;
import utils.GridpanelVehiculos;

public class StockC implements Initializable {

	private Session sesion = HibernateUtil.getSession();
	VehiculoDAO gestorVehiculo = new VehiculoDAO(sesion);

	@SuppressWarnings("unused")
	private VehiculosC controlador;
	private GridPane nuevoGrid;
	private GridpanelVehiculos grid = new GridpanelVehiculos();

	int columna = 0;
	int fila = 0;
	// Lista de vehiculos
	private static List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

	// Lista de paneles
	private static List<AnchorPane> paneles = new ArrayList<AnchorPane>();

	@FXML
	private BorderPane borderPaneStock;

	@FXML
	private ScrollPane scrollVehiculos;

	@FXML
	private GridPane gridVehiculo;

	@FXML
	private ComboBox<String> comboMarca;

	@FXML
	private ComboBox<String> comboModelo;

	@FXML
	private ComboBox<String> comboColor;

	@FXML
	void buscarPorColor(ActionEvent event) throws IOException {
		limpiarLista();
		vehiculos = gestorVehiculo.listaPorColumnaYValorStock("color", comboColor.getValue());
		paneles = grid.crearPaneles(vehiculos);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneStock.setCenter(nuevoGrid);

	}

	@FXML
	void buscarPorMarca(ActionEvent event) throws IOException {
		limpiarLista();
		vehiculos = gestorVehiculo.listaPorColumnaYValorStock("marca", comboMarca.getValue());
		paneles = grid.crearPaneles(vehiculos);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneStock.setCenter(nuevoGrid);
	}

	@FXML
	void buscarporModelo(ActionEvent event) throws IOException {
		limpiarLista();
		vehiculos = gestorVehiculo.listaPorColumnaYValorStock("modelo", comboModelo.getValue());
		paneles = grid.crearPaneles(vehiculos);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneStock.setCenter(nuevoGrid);
	}

	@FXML
	void verVehiculosEntre(ActionEvent event) throws IOException {
		// Antes de generar los nuevos vehiculos hay que limpiar ambas listas
		limpiarLista();

		// Repetimos el proceso cuando pulsamos el boton
		vehiculos = gestorVehiculo.consultarPrecioEntre(20000, 50000);
		paneles = grid.crearPaneles(vehiculos);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneStock.setCenter(nuevoGrid);
	}

	@FXML
	void verVehiculosMasCincuenta(ActionEvent event) throws IOException {
		// Antes de generar los nuevos vehiculos hay que limpiar ambas listas
		limpiarLista();

		// Repetimos el proceso cuando pulsamos el boton

		vehiculos = gestorVehiculo.consultarPrecio(">", 50000);
		paneles = grid.crearPaneles(vehiculos);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneStock.setCenter(nuevoGrid);
	}

	@FXML
	void verVehiculosMenosVeinte(ActionEvent event) throws IOException {
		// Antes de generar los nuevos vehiculos hay que limpiar ambas listas
		limpiarLista();
		// Repetimos el proceso cuando pulsamos el boton

		vehiculos = gestorVehiculo.consultarPrecio("<", 20000);
		paneles = grid.crearPaneles(vehiculos);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneStock.setCenter(nuevoGrid);
	}

	private void rellenarCamposMarca() {
		List<String> cargos = gestorVehiculo.traerValoresColumnas("marca");
		cargos = cargos.stream().distinct().collect(Collectors.toList());
		ObservableList<String> items = FXCollections.observableArrayList(cargos);
		comboMarca.setItems(items);
	}

	private void rellenarCamposModelo() {
		List<String> modelo = gestorVehiculo.traerValoresColumnas("modelo");
		modelo = modelo.stream().distinct().collect(Collectors.toList());
		ObservableList<String> items = FXCollections.observableArrayList(modelo);
		comboModelo.setItems(items);
	}

	private void rellenarCamposColor() {
		List<String> colores = gestorVehiculo.traerValoresColumnas("color");
		colores = colores.stream().distinct().collect(Collectors.toList());
		ObservableList<String> items = FXCollections.observableArrayList(colores);
		comboColor.setItems(items);
	}

	private void limpiarLista() {
		paneles.clear();
		vehiculos.clear();
	}

	/**
	 * Antes de nada dentro del scrollpane añadimos un borderpane donde vamos a
	 * setear en gridpane en el centro Inicializamos la vista con un GridPane
	 * creandolo desde 0 dentro del metodo
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Contadores para la posicion dentro del Gridpane
		int columna = 0;
		int fila = 0;

		try {
			vehiculos = gestorVehiculo.traerVehiculosSinMatricula();
			paneles = grid.crearPaneles(vehiculos);
			nuevoGrid = grid.crearGridPane(columna, fila, paneles);
			borderPaneStock.setCenter(nuevoGrid);
		} catch (IOException e) {
			System.out.println("ERROR: NO SE PUDO INICIALIZAR EL GRIDPANE");
		}
		rellenarCamposModelo();
		rellenarCamposMarca();
		rellenarCamposColor();
	}

}
