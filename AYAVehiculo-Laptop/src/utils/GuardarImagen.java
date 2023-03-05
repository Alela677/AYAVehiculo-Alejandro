package utils;


import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;


public class GuardarImagen {

	public static void guardarImagen(File fichero) throws Exception {

		byte[] imagenBytes = Files.readAllBytes(fichero.toPath());
		FileOutputStream output = new FileOutputStream("imagenes/" + fichero.getName());
		output.write(imagenBytes);
		output.close();

	}

}
