package utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class GuardarImagen {

	
	public static void leerImagen(File fichero) {

		try {
			byte[] ficheroBytes = Files.readAllBytes(fichero.toPath());
			FileOutputStream write = new FileOutputStream("imagenes/" + fichero.getName());
			write.write(ficheroBytes);
			write.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
