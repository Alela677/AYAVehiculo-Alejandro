package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reparacion")
public class Reparacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idReparacion;

	@Column
	private String pieza;

	@Column
	private double coste;

	@Column
	private String nombreMecanico;

	@Column
	private String matricula;

	@ManyToOne()
	@JoinColumn(name = "empleado")
	private Empleados empleado;

	@OneToOne
	@JoinColumn
	private Vehiculo vehiculo;

	@Column
	private String fecha;

	public Reparacion() {

	}

	public Reparacion(String pieza, double coste, String nombreMecanico, String matricula, String fecha) {
		super();

		this.pieza = pieza;
		this.coste = coste;
		this.nombreMecanico = nombreMecanico;
		this.matricula = matricula;
		this.fecha = fecha;
	}

	public int getIdReparacion() {
		return idReparacion;
	}

	public void setIdReparacion(int idReparacion) {
		this.idReparacion = idReparacion;
	}

	public String getPieza() {
		return pieza;
	}

	public void setPieza(String pieza) {
		this.pieza = pieza;
	}

	public double getCoste() {
		return coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}

	public String getNombreMecanico() {
		return nombreMecanico;
	}

	public void setNombreMecanico(String nombreMecanico) {
		this.nombreMecanico = nombreMecanico;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Empleados getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleados empleado) {
		this.empleado = empleado;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Reparacion [idReparacion=" + idReparacion + ", pieza=" + pieza + ", coste=" + coste
				+ ", nombreMecanico=" + nombreMecanico + ", matricula=" + matricula + ", fecha=" + fecha + "]";
	}

}
