package controllers;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;

import org.hibernate.Session;

import daos.EmpleadosDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import models.Empleados;
import models.HibernateUtil;
import utils.Alertas;
import utils.GuardarImagen;
import utils.HashPassword;
import utils.ValidacionFormulario;

// Vista que contiene el formulario para crear usuario a los empleados
public class CrearEmpleadoC implements Initializable {
	
	// Sesion de hibernate con la base de datos
	private Session sesion = HibernateUtil.getSession();
	// Gestion de los empleado con la base de datos
	EmpleadosDAO gestorEmpleados = new EmpleadosDAO(sesion);

	@FXML
	private Button buscarImagen;

	private Component nombreImagen;

	@FXML
	private TextField txtArchivoImg;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtApellidos;

	@FXML
	private TextField txtDni;

	@FXML
	private TextField txtContraseña;

	@FXML
	private DatePicker boxFechaAlta;

	@FXML
	private ComboBox<String> comboDepartamento;

	@FXML
	private ComboBox<String> comboCargo;
	
	/**
	 * Metodo que contiene un filechooser para buscar imagenes en nuestro equipo
	 * solo admite archivos de imagen png,jpg y jpeg
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void buscarImagen(MouseEvent event) throws Exception {

		FileChooser filechooser = new FileChooser();
		
		// Fichero seleccionado
		File fichero = filechooser.showOpenDialog(LoginController.stage); // Guardamos en la variable fichero el archivo
		
		// Si el fichero no es uno de estos formatos no lo guardara
		if (fichero.getName().contains(".jpg") || fichero.getName().contains(".png")
				|| fichero.getName().contains(".jpeg")) {
			txtArchivoImg.setText(fichero.getName());
			GuardarImagen.guardarImagen(fichero);// Guarda la imagen en la carpeta
		}
		

	}
	
	// Metodo que inicializa la vista con lo indicado en el metodo
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Imagen de lupa del boton que inicializa el filechoose
		Image img = new Image("images/lupa.png");
		ImageView view = new ImageView(img);
		view.setFitHeight(12);
		view.setFitWidth(12);
		buscarImagen.setGraphic(view);
		
		// Rellena el combobox con los cargos disponibles y los deparatamentos
		rellenarCamposCargo();
		rellenarCamposDepartamento();
	}
	
	/**
	 * Metodo que reinicia los campos del formulario 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void borrarCamposFormulario(MouseEvent event) throws IOException {
		actualizarPagina();

	}
	
	/**
	 * Metodo que comprueba el formulario si pasa las validaciones del formulario creara 
	 * una cuenta de usuario a un empleado nuevo
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void crearEmpleado(MouseEvent event) throws Exception {
		ValidacionFormulario valida = new ValidacionFormulario();
		
		// Campos del formulario
		String nombre = txtNombre.getText();
		String apellidos = txtApellidos.getText();
		String dni = txtDni.getText();
		String departamento = comboDepartamento.getValue();
		String cargo = comboCargo.getValue();
		String fechaAlta = String.valueOf(boxFechaAlta.getValue());
		String imagen = txtArchivoImg.getText();
		String contraseña = txtContraseña.getText();
		
		// Si la imagen es vacia pone una imagen por defecto
		if (imagen.isEmpty()) {
			imagen = "user.jpeg";
		}
		// Nueva cuenta que se va a crear
		Empleados nuevoEmpleado = new Empleados(nombre, apellidos, dni, departamento, cargo, fechaAlta, imagen,
				contraseña);
		// Si el formulario pasa las validaciones creara un nuevo usuario y actualiza la vista 
		if (valida.validaFormulario(nuevoEmpleado)) {
			nuevoEmpleado.setContraseña(HashPassword.convertirSHA256(contraseña));
			gestorEmpleados.insert(nuevoEmpleado);
			actualizarPagina();
		}
	}
	
	/**
	 * Metodo que rellena los campos de cargo
	 */
	private void rellenarCamposCargo() {
		List<String> cargos = gestorEmpleados.traerValoresColumnasCargos();
		cargos = cargos.stream().distinct().collect(Collectors.toList());
		ObservableList<String> items = FXCollections.observableArrayList(cargos);
		comboCargo.setItems(items);
	}
	
	/**
	 * Metodo que rellena los campos departamentos
	 */
	private void rellenarCamposDepartamento() {
		ObservableList<String> items = FXCollections.observableArrayList();
		items.add("VENTAS");
		items.add("MECANICOS");
		comboDepartamento.setItems(items);
	}
	
	/**
	 * Metodo que actualiza la vista 
	 * @throws IOException
	 */
	private void actualizarPagina() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneCrearEmpleado.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);
	}
}
