package models;

public class TablaVendedor {

	private int id;
	private String nombre;
	private long total;

	public TablaVendedor(int id, String nombre, long total) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Vendedor [id=" + id + ", nombre=" + nombre + ", total=" + total + "]";
	}

}
