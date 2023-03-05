package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controllers.VehiculosC;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Vehiculo;

public class GridpanelVehiculos {

	public GridPane crearGridPane(int columna, int fila, List<AnchorPane> paneles) {

		// Creamos un nuevo gridpane
		GridPane nuevo = new GridPane();
		// Añadimos separacion vertical de los paneles
		nuevo.setHgap(90);
		// Añadimos separacion lateral de los paneles
		nuevo.setVgap(50);
		nuevo.setAlignment(Pos.CENTER);
		// Recorremos la lista de paneles
		for (int i = 0; i < paneles.size(); i++) {

			// Contamos las columnas y la filas y indicamos el nuemo de columnas
			if (columna == 3) {
				columna = 0;
				fila++;
			}

			// Y añadimos los paneles de la lista y le damos una posicion en el gridpane
			nuevo.add(paneles.get(i), columna, fila);
			columna++;
		}

		return nuevo;
	}

	public List<AnchorPane> crearPaneles(List<Vehiculo> vehiculosList) throws IOException {
		List<AnchorPane> paneles = new ArrayList<AnchorPane>();
		// Recorremos la lista de vehiculos
		for (int i = 0; i < vehiculosList.size(); i++) {

			// Cada vuelta del bucle se tiene que generar un panel nuevo para ello cargamos
			// la vista de nuevo todas las veces
			// para que no salte la excepcion de hijos replicados siempres genera uno nuevo
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Vehiculos.fxml"));
			AnchorPane root = loader.load();
			VehiculosC controlador = loader.getController();

			// Enviamos los datos de cada vehiculo al controlador de vehiculos
			controlador.setDatos(vehiculosList.get(i));

			// Añadimos cada panel a una lista de de AnchorPane en nuestro caso siempre de
			// root que hayamos escogido
			paneles.add(root);
		}
		return paneles;
	}

}
