package models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Venta")
public class Venta implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String fechaVenta;

	@ManyToOne
	@JoinColumn(name = "vendedor")
	private Empleados empleado;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "vehiculo")
	private Vehiculo vehiculo;

	@Column
	private String nombreEmpleado;
	@Column
	private String marcaVehiculo;
	@Column
	private String modeloVehiculo;
	@Column
	private double precio;
	@Column
	private String nombreCliente;

	public Venta() {
		// TODO Auto-generated constructor stub
	}

	public Venta(String fechaVenta, String nombreEmpleado, String marcaVehiculo, String modeloVehiculo, double precio,
			String nombreCliente) {
		super();

		this.fechaVenta = fechaVenta;
		this.nombreEmpleado = nombreEmpleado;
		this.marcaVehiculo = marcaVehiculo;
		this.modeloVehiculo = modeloVehiculo;
		this.precio = precio;
		this.nombreCliente = nombreCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Empleados getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleados empleado) {
		this.empleado = empleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getMarcaVehiculo() {
		return marcaVehiculo;
	}

	public void setMarcaVehiculo(String marcaVehiculo) {
		this.marcaVehiculo = marcaVehiculo;
	}

	public String getModeloVehiculo() {
		return modeloVehiculo;
	}

	public void setModeloVehiculo(String modeloVehiculo) {
		this.modeloVehiculo = modeloVehiculo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Venta [id=" + id + ", fechaVenta=" + fechaVenta + ", nombreEmpleado=" + nombreEmpleado
				+ ", marcaVehiculo=" + marcaVehiculo + ", modeloVehiculo=" + modeloVehiculo + ", precio=" + precio
				+ ", nombreCliente=" + nombreCliente + "]";
	}
	
	
}
