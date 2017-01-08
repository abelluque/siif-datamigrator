package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the aux_nombres_fem database table.
 * 
 */
@Entity
@Table(name="aux_nombres_fem")
@NamedQuery(name = "AuxNombresFem.findAll", query = "SELECT a FROM AuxNombresFem a")
public class AuxNombresFem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nombre;

	public AuxNombresFem() {
	}
	
	public AuxNombresFem(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}