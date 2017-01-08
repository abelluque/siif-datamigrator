package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tab_tableros_permisos database table.
 * 
 */
@Entity
@Table(name="tab_tableros_permisos")
@NamedQuery(name="TabTablerosPermiso_MPFUsers.findAll", query="SELECT t FROM TabTablerosPermiso_MPFUsers t")
public class TabTablerosPermiso_MPFUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TabTablerosPermisoPK id;

	public TabTablerosPermiso_MPFUsers() {
	}

	public TabTablerosPermisoPK getId() {
		return this.id;
	}

	public void setId(TabTablerosPermisoPK id) {
		this.id = id;
	}

}