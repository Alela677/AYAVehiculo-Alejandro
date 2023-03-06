package models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Concesionario")
public class Concesionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idConcesionario;
	@Column()
	private String nombre;
	@Column
	private String localidad;
	@Column(unique = true)
	private String nif;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private Set<Empleados> empleados;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private Set<Vehiculo> vehiculos;

	public Concesionario() {
		// TODO Auto-generated constructor stub
	}

	public Concesionario(int idConcesionario, String nombre, String localidad, String nif) {
		super();
		this.idConcesionario = idConcesionario;
		this.nombre = nombre;
		this.localidad = localidad;
		this.nif = nif;
	}

	public Set<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Set<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public int getIdConcesionario() {
		return idConcesionario;
	}

	public void setIdConcesionario(int idConcesionario) {
		this.idConcesionario = idConcesionario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Empleados> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Set<Empleados> empleados) {
		this.empleados = empleados;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	@Override
	public String toString() {
		return "Concesionario [idConcesionario=" + idConcesionario + ", nombre=" + nombre + ", localidad=" + localidad
				+ "]";
	}

}
