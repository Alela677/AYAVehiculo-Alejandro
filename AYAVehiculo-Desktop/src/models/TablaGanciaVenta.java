package models;

public class TablaGanciaVenta {

	private int id;
	private String marca;
	private double precio;

	public TablaGanciaVenta(int id, String marca, double precio) {
		super();
		this.id = id;
		this.marca = marca;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "TablaGanciaVenta [id=" + id + ", marca=" + marca + ", precio=" + precio + "]";
	}

}
