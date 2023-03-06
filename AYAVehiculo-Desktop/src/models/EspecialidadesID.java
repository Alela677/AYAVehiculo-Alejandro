package models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EspecialidadesID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "empleados_id")
	private Empleados empleado;
	@ManyToOne
	@JoinColumn(name = "especialidades_id")
	private Especialidades especialidad;

	public EspecialidadesID() {

	}
	
	
	public EspecialidadesID(Empleados empleado, Especialidades especialidad) {
		super();
		this.empleado = empleado;
		this.especialidad = especialidad;
	}


	public Empleados getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleados empleado) {
		this.empleado = empleado;
	}

	public Especialidades getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidades especialidad) {
		this.especialidad = especialidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(empleado, especialidad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EspecialidadesID other = (EspecialidadesID) obj;
		return Objects.equals(empleado, other.empleado) && Objects.equals(especialidad, other.especialidad);
	}

	@Override
	public String toString() {
		return "EspecialidadesID [empleado=" + empleado + ", especialidad=" + especialidad + "]";
	}

}
