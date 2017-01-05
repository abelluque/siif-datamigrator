package ar.com.stack.siif.datamigrator.model.entities.kiwi;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the avi_servers database table.
 * 
 */
@Entity
@Table(name="avi_servers")
@NamedQuery(name="AviServer_Kiwi.findAll", query="SELECT a FROM AviServer_Kiwi a")
public class AviServer_Kiwi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="avs_code")
	private int avsCode;

	@Column(name="avs_account")
	private String avsAccount;

	@Column(name="avs_account_name")
	private String avsAccountName;

	@Column(name="avs_class")
	private String avsClass;

	@Column(name="avs_direction")
	private String avsDirection;

	@Column(name="avs_host")
	private String avsHost;

	@Column(name="avs_password")
	private String avsPassword;

	@Column(name="avs_server_type")
	private String avsServerType;

	@Column(name="avs_status")
	private String avsStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="avs_tstamp")
	private Date avsTstamp;

	@Column(name="avs_user")
	private String avsUser;

	@Column(name="use_code")
	private String useCode;

	public AviServer_Kiwi() {
	}

	public int getAvsCode() {
		return this.avsCode;
	}

	public void setAvsCode(int avsCode) {
		this.avsCode = avsCode;
	}

	public String getAvsAccount() {
		return this.avsAccount;
	}

	public void setAvsAccount(String avsAccount) {
		this.avsAccount = avsAccount;
	}

	public String getAvsAccountName() {
		return this.avsAccountName;
	}

	public void setAvsAccountName(String avsAccountName) {
		this.avsAccountName = avsAccountName;
	}

	public String getAvsClass() {
		return this.avsClass;
	}

	public void setAvsClass(String avsClass) {
		this.avsClass = avsClass;
	}

	public String getAvsDirection() {
		return this.avsDirection;
	}

	public void setAvsDirection(String avsDirection) {
		this.avsDirection = avsDirection;
	}

	public String getAvsHost() {
		return this.avsHost;
	}

	public void setAvsHost(String avsHost) {
		this.avsHost = avsHost;
	}

	public String getAvsPassword() {
		return this.avsPassword;
	}

	public void setAvsPassword(String avsPassword) {
		this.avsPassword = avsPassword;
	}

	public String getAvsServerType() {
		return this.avsServerType;
	}

	public void setAvsServerType(String avsServerType) {
		this.avsServerType = avsServerType;
	}

	public String getAvsStatus() {
		return this.avsStatus;
	}

	public void setAvsStatus(String avsStatus) {
		this.avsStatus = avsStatus;
	}

	public Date getAvsTstamp() {
		return this.avsTstamp;
	}

	public void setAvsTstamp(Date avsTstamp) {
		this.avsTstamp = avsTstamp;
	}

	public String getAvsUser() {
		return this.avsUser;
	}

	public void setAvsUser(String avsUser) {
		this.avsUser = avsUser;
	}

	public String getUseCode() {
		return this.useCode;
	}

	public void setUseCode(String useCode) {
		this.useCode = useCode;
	}

}