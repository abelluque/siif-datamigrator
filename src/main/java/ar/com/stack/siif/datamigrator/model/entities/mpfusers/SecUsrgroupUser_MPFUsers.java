package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sec_usrgroup_users database table.
 * 
 */
@Entity
@Table(name="sec_usrgroup_users")
@NamedQuery(name="SecUsrgroupUser_MPFUsers.findAll", query="SELECT s FROM SecUsrgroupUser_MPFUsers s")
public class SecUsrgroupUser_MPFUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SecUsrgroupUserPK id;

	public SecUsrgroupUser_MPFUsers() {
	}

	public SecUsrgroupUserPK getId() {
		return this.id;
	}

	public void setId(SecUsrgroupUserPK id) {
		this.id = id;
	}

}