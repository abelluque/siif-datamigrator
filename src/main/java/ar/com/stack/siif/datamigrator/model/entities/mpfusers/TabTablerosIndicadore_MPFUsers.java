package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tab_tableros_indicadores database table.
 * 
 */
@Entity
@Table(name="tab_tableros_indicadores")
@NamedQuery(name="TabTablerosIndicadore_MPFUsers.findAll", query="SELECT t FROM TabTablerosIndicadore_MPFUsers t")
public class TabTablerosIndicadore_MPFUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TabTablerosIndicadorePK id;

	public TabTablerosIndicadore_MPFUsers() {
	}

	public TabTablerosIndicadorePK getId() {
		return this.id;
	}

	public void setId(TabTablerosIndicadorePK id) {
		this.id = id;
	}

}