package controllers;

import java.text.DecimalFormat;

import org.hibernate.Session;

import daos.VehiculoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.HibernateUtil;
import models.Vehiculo;

public class VehiculosC {
	Session sesion = HibernateUtil.getSession();
	VehiculoDAO gestorVehiculo = new VehiculoDAO(sesion);

	@FXML
	private ImageView vehiculoImagen;

	@FXML
	private Label vehiculoMarca;

	@FXML
	private Label vehiucloModelo;

	@FXML
	private Label vehiculoColor;

	@FXML
	private Label vehiculoStock;

	@FXML
	private Label vehiculoPrecio;

	public void setDatos(Vehiculo vehiculo) {
		DecimalFormat formatea = new DecimalFormat("###,###.##");
		vehiculoMarca.setText(vehiculo.getMarca());
		vehiucloModelo.setText(vehiculo.getModelo());
		vehiculoColor.setText(vehiculo.getColor());
		vehiculoPrecio.setText(String.valueOf( formatea.format(vehiculo.getPrecio())));
		vehiculoStock.setText(String.valueOf(gestorVehiculo.stockVehiculos(vehiculo.getModelo())));
		Image imagen = new Image(getClass().getResourceAsStream("/images/" + vehiculo.getImagen()));
		vehiculoImagen.setImage(imagen);

	}

}
