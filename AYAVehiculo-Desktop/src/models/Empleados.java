package models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Empleados")
public class Empleados implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellidos")
	private String apellidos;
	@Column(name = "dni", unique = true)
	private String dni;
	@Column(name = "departamento")
	private String departamento;
	@Column(name = "cargo")
	private String cargo;
	@Column(name = "fechaAlta")
	private String fechaAlta;

	@Column(name = "imagen")
	private String imagenEmpleado;
	@Column(name = "contraseña")
	private String contraseña;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "concesionario")
	private Concesionario concesionario;

	@OneToMany(mappedBy = "id", cascade = { CascadeType.ALL })
	private Set<Venta> ventas;

	@OneToMany(mappedBy = "idReparacion", cascade = { CascadeType.ALL })
	private Set<Reparacion> reparaciones;

	@OneToMany(mappedBy = "idPropuesta", cascade = { CascadeType.ALL })
	private Set<Propuesta> propuestas;

	public Empleados() {

	}

	public Empleados(String nombre, String apellidos, String dni, String departamento, String cargo, String fechaAlta,
			String imagenEmpleado, String contraseña) {
		super();

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.departamento = departamento;
		this.cargo = cargo;
		this.fechaAlta = fechaAlta;
		this.imagenEmpleado = imagenEmpleado;
		this.contraseña = contraseña;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getImagenEmpleado() {
		return imagenEmpleado;
	}

	public void setImagenEmpleado(String imagenEmpleado) {
		this.imagenEmpleado = imagenEmpleado;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Concesionario getConcesionario() {
		return concesionario;
	}

	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public Set<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(Set<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}

	public Set<Propuesta> getPropuestas() {
		return propuestas;
	}

	public void setPropuestas(Set<Propuesta> propuestas) {
		this.propuestas = propuestas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Empleados [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni
				+ ", departamento=" + departamento + ", cargo=" + cargo + ", fechaAlta=" + fechaAlta
				+ ", imagenEmpleado=" + imagenEmpleado + ", contraseña=" + contraseña + "]";
	}

}
