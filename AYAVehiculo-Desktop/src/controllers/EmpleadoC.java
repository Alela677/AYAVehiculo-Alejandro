package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;

import daos.EmpleadosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.Empleados;
import models.HibernateUtil;

public class EmpleadoC implements Initializable {
	
	// Sesion de hibernate con la base de datos
	private Session sesion = HibernateUtil.getSession();
	// Gestino de lo empleado en la base de datos
	EmpleadosDAO gestorEmpleados = new EmpleadosDAO(sesion);
		
	private FileInputStream input;
	private Empleados empleado = null;

	@FXML
	private Label lblID;

	@FXML
	private ImageView img;

	@FXML
	private Button buttonEliminar;

	@FXML
	private TextField txtApellidos;

	@FXML
	private TextField txtCargo;

	@FXML
	private TextField txtDepartamento;

	@FXML
	private TextField txtFechaAlta;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtDNI;

	@FXML
	private Button buttonEditar;

	@FXML
	private Circle circulo;
	
	/**
	 * Metodo que edita y guarda las modificaciones de los empleados
	 * @param event
	 */
	@FXML
	void editarEmpleado(MouseEvent event) {
		
		//Si el boton tiene el texto editar
		if (buttonEditar.getText().equals("Editar")) {
			buttonEditar.setText("Guardar"); // Cambia el texto a guardar
			txtNombre.setEditable(true); // Los campos de texto se vuelven editables
			txtApellidos.setEditable(true);
			txtDepartamento.setEditable(true);
			txtCargo.setEditable(true);
			txtFechaAlta.setEditable(true);
			txtDNI.setEditable(true);

		} else if (buttonEditar.getText().equals("Guardar")) {
			// Si el boton contiene el texto guardar
			// Recoge el valor de los campos 
			empleado.setNombre(txtNombre.getText()); 
			empleado.setApellidos(txtApellidos.getText());
			empleado.setDepartamento(txtDepartamento.getText());
			empleado.setCargo(txtCargo.getText());
			empleado.setDni(txtDNI.getText());
			empleado.setFechaAlta(txtFechaAlta.getText());
			
			// Actualiza el empleado de la base de datos con los nuevos datos
			gestorEmpleados.update(empleado);
			
			// Cambia el texto a editar y los campos se vuelven no editables
			buttonEditar.setText("Editar");
			txtNombre.setEditable(false);
			txtApellidos.setEditable(false);
			txtDepartamento.setEditable(false);
			txtCargo.setEditable(false);
			txtFechaAlta.setEditable(false);
			txtDNI.setEditable(false);

		}

	}
	
	/**
	 * Metodo que elimina los empledos y sus imagenes menos la de usuario por defecto
	 * y actualiza la pagina
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void eliminaEmpleados(ActionEvent event) throws IOException {
		if (!empleado.getImagenEmpleado().equals("user.jpeg")) {
			File imagenEliminar = new File("imagenes/" + empleado.getImagenEmpleado());
			imagenEliminar.delete();
		}
		gestorEmpleados.delete(empleado);
		actualizarPagina();

	}
	
	/**
	 * Metodo que recibe los empleados de una lista y muestra los valores el los campos 
	 * pertienentes en el pane empleados
	 * @param empleados
	 * @throws IOException
	 */
	public void setDatos(Empleados empleados) throws IOException {
		empleado = empleados;
		lblID.setText(String.valueOf(empleados.getId()));
		txtNombre.setText(empleados.getNombre());
		txtApellidos.setText(empleados.getApellidos());
		txtDNI.setText(empleados.getDni());
		txtDepartamento.setText(empleados.getDepartamento());
		txtCargo.setText(empleados.getCargo());
		txtFechaAlta.setText(String.valueOf(empleados.getFechaAlta()));
		Image imagen = imagenEmpleados(empleados.getImagenEmpleado());
		img.setImage(imagen);
		img.setVisible(false);
		circulo.setFill(new ImagePattern(imagen));
		input.close();
	}
	
	/**
	 * Metodo que incializa la vista con los parametros introducidos
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		txtNombre.setEditable(false);
		txtApellidos.setEditable(false);
		txtDepartamento.setEditable(false);
		txtCargo.setEditable(false);
		txtFechaAlta.setEditable(false);
		txtDNI.setEditable(false);

	}	
	
	/**
	 * Metodo que actualiza pagina
	 * @throws IOException
	 */
	private void actualizarPagina() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SceneVerEmpleados.fxml"));
		AnchorPane root = loader.load();
		LoginController.root.setCenter(root);
	}
	
	/**
	 * Metodo que recoge la imagen del empleado si no contiene imagen 
	 * como imagen podra una por defecto
	 * @param imagen
	 * @return
	 * @throws IOException
	 */
	private Image imagenEmpleados(String imagen) throws IOException {
		Image imagenEmpleado = null;

		try {
			imagenEmpleado = new Image(input = new FileInputStream("imagenes/" + imagen));
		} catch (Exception e) {
			imagenEmpleado = new Image(input = new FileInputStream("imagenes/user.jpeg"));
		}

		return imagenEmpleado;

	}
}
