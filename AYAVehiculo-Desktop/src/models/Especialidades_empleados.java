package models;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "especialidades_empleados")
public class Especialidades_empleados {

	@EmbeddedId
	private EspecialidadesID idEspecialidad;

	
	public Especialidades_empleados() {

	}

	public EspecialidadesID getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(EspecialidadesID idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEspecialidad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especialidades_empleados other = (Especialidades_empleados) obj;
		return Objects.equals(idEspecialidad, other.idEspecialidad);
	}

}
