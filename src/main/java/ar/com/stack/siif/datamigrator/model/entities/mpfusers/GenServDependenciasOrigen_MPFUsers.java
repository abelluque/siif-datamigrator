package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gen_serv_dependencias_origen database table.
 * 
 */
@Entity
@Table(name="gen_serv_dependencias_origen")
@NamedQuery(name="GenServDependenciasOrigen_MPFUsers.findAll", query="SELECT g FROM GenServDependenciasOrigen_MPFUsers g")
public class GenServDependenciasOrigen_MPFUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenServDependenciasOrigenPK id;

	public GenServDependenciasOrigen_MPFUsers() {
	}

	public GenServDependenciasOrigenPK getId() {
		return this.id;
	}

	public void setId(GenServDependenciasOrigenPK id) {
		this.id = id;
	}

}