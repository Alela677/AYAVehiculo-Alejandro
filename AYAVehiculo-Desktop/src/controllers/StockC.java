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
	
	// Sesion de hibernate con la base de datos
	private Session sesion = HibernateUtil.getSession();
	// Gestion de los vehiculos con la base de datos
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
	
	/**
	 * Metodo que busca los vehiculos por color
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void buscarPorColor(ActionEvent event) throws IOException {
		limpiarLista();
		vehiculos = gestorVehiculo.listaPorColumnaYValorStock("color", comboColor.getValue());
		paneles = grid.crearPaneles(vehiculos);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneStock.setCenter(nuevoGrid);

	}
	
	/**
	 * Metodo que busca lso vehiculos por marca
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void buscarPorMarca(ActionEvent event) throws IOException {
		limpiarLista();
		vehiculos = gestorVehiculo.listaPorColumnaYValorStock("marca", comboMarca.getValue());
		paneles = grid.crearPaneles(vehiculos);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneStock.setCenter(nuevoGrid);
	}
	
	/**
	 * Metodo que busca vehiculos por modelo
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void buscarporModelo(ActionEvent event) throws IOException {
		limpiarLista();
		vehiculos = gestorVehiculo.listaPorColumnaYValorStock("modelo", comboModelo.getValue());
		paneles = grid.crearPaneles(vehiculos);
		nuevoGrid = grid.crearGridPane(columna, fila, paneles);
		borderPaneStock.setCenter(nuevoGrid);
	}
	
	/**
	 * Metodo que busca vehiculos por precio 
	 * @param event
	 * @throws IOException
	 */
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
	
	/**
	 * Metodo que busca vehiculos por precio mayor de 50K
	 * @param event
	 * @throws IOException
	 */
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
	
	/**
	 * Metodo que busca vehiculos menor de 20K
	 * @param event
	 * @throws IOException
	 */
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
	
	/**
	 * Metodo que rellena el combobox con las marcas
	 */
	private void rellenarCamposMarca() {
		List<String> cargos = gestorVehiculo.traerValoresColumnas("marca");
		cargos = cargos.stream().distinct().collect(Collectors.toList());
		ObservableList<String> items = FXCollections.observableArrayList(cargos);
		comboMarca.setItems(items);
	}
	
	/**
	 * Metodo que rellena el combobox con los modelos
	 */
	private void rellenarCamposModelo() {
		List<String> modelo = gestorVehiculo.traerValoresColumnas("modelo");
		modelo = modelo.stream().distinct().collect(Collectors.toList());
		ObservableList<String> items = FXCollections.observableArrayList(modelo);
		comboModelo.setItems(items);
	}
	
	/**
	 * Metodo que rellena el combobox con el color
	 */
	private void rellenarCamposColor() {
		List<String> colores = gestorVehiculo.traerValoresColumnas("color");
		colores = colores.stream().distinct().collect(Collectors.toList());
		ObservableList<String> items = FXCollections.observableArrayList(colores);
		comboColor.setItems(items);
	}
	
	/**
	 * Metodo que limpia la listas 
	 */
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
