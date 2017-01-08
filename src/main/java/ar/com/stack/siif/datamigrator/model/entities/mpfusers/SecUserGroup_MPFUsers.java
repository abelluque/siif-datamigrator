package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sec_user_groups database table.
 * 
 */
@Entity
@Table(name="sec_user_groups")
@NamedQuery(name="SecUserGroup_MPFUsers.findAll", query="SELECT s FROM SecUserGroup_MPFUsers s")
public class SecUserGroup_MPFUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gro_code")
	private String groCode;

	@Column(name="use_code")
	private String useCode;

	public SecUserGroup_MPFUsers() {
	}

	public String getGroCode() {
		return this.groCode;
	}

	public void setGroCode(String groCode) {
		this.groCode = groCode;
	}

	public String getUseCode() {
		return this.useCode;
	}

	public void setUseCode(String useCode) {
		this.useCode = useCode;
	}

}