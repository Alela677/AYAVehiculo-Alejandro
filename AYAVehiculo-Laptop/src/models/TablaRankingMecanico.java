package models;

public class TablaRankingMecanico {

	private int id;

	private String nombre;

	private long totalReparacion;

	public TablaRankingMecanico(int id, String nombre, long totalReparacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.totalReparacion = totalReparacion;
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

	public long getTotalReparacion() {
		return totalReparacion;
	}

	public void setTotalReparacion(long totalReparacion) {
		this.totalReparacion = totalReparacion;
	}

	@Override
	public String toString() {
		return "TablaRankingMecanico [id=" + id + ", nombre=" + nombre + ", totalReparacion=" + totalReparacion + "]";
	}

}
