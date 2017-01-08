package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sec_user_pass database table.
 * 
 */
@Entity
@Table(name="sec_user_pass")
@NamedQuery(name="SecUserPass_MPFUsers.findAll", query="SELECT s FROM SecUserPass_MPFUsers s")
public class SecUserPass_MPFUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="use_code")
	private String useCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="use_passact")
	private Date usePassact;

	@Column(name="use_password")
	private String usePassword;

	public SecUserPass_MPFUsers() {
	}

	public String getUseCode() {
		return this.useCode;
	}

	public void setUseCode(String useCode) {
		this.useCode = useCode;
	}

	public Date getUsePassact() {
		return this.usePassact;
	}

	public void setUsePassact(Date usePassact) {
		this.usePassact = usePassact;
	}

	public String getUsePassword() {
		return this.usePassword;
	}

	public void setUsePassword(String usePassword) {
		this.usePassword = usePassword;
	}

}