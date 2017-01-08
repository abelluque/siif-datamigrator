package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pay_naciones database table.
 * 
 */
@Entity
@Table(name="pay_naciones")
@NamedQuery(name="PayNacione.findAll", query="SELECT p FROM PayNacione p")
public class PayNacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private String descripcion;

	public PayNacione() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}