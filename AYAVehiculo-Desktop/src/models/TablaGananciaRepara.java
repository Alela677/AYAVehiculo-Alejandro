package models;

public class TablaGananciaRepara {
	
	
	private int id;

	private String fecha;

	private double precio;

	public TablaGananciaRepara(int id, String fecha, double precio) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "tablaGananciaRepara [id=" + id + ", fecha=" + fecha + ", precio=" + precio + "]";
	}

}
