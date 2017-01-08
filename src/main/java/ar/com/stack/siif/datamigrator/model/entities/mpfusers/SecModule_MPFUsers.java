package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sec_modules database table.
 * 
 */
@Entity
@Table(name="sec_modules")
@NamedQuery(name="SecModule_MPFUsers.findAll", query="SELECT s FROM SecModule_MPFUsers s")
public class SecModule_MPFUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="smo_code")
	private int smoCode;

	@Column(name="smo_db_version")
	private String smoDbVersion;

	@Column(name="smo_name")
	private String smoName;

	@Column(name="smo_path")
	private String smoPath;

	@Column(name="smo_status")
	private String smoStatus;

	@Column(name="smo_version")
	private String smoVersion;

	public SecModule_MPFUsers() {
	}

	public int getSmoCode() {
		return this.smoCode;
	}

	public void setSmoCode(int smoCode) {
		this.smoCode = smoCode;
	}

	public String getSmoDbVersion() {
		return this.smoDbVersion;
	}

	public void setSmoDbVersion(String smoDbVersion) {
		this.smoDbVersion = smoDbVersion;
	}

	public String getSmoName() {
		return this.smoName;
	}

	public void setSmoName(String smoName) {
		this.smoName = smoName;
	}

	public String getSmoPath() {
		return this.smoPath;
	}

	public void setSmoPath(String smoPath) {
		this.smoPath = smoPath;
	}

	public String getSmoStatus() {
		return this.smoStatus;
	}

	public void setSmoStatus(String smoStatus) {
		this.smoStatus = smoStatus;
	}

	public String getSmoVersion() {
		return this.smoVersion;
	}

	public void setSmoVersion(String smoVersion) {
		this.smoVersion = smoVersion;
	}

}