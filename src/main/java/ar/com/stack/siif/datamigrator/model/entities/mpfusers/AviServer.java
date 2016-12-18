package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the avi_servers database table.
 * 
 */
@Entity
@Table(name = "avi_servers", schema = "mpfusers")
@NamedQuery(name = "AviServer.findAll", query = "SELECT a FROM AviServer a")
public class AviServer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "avs_code")
	private int avsCode;

	@Column(name = "avs_account")
	private String avsAccount;

	@Column(name = "avs_account_name")
	private String avsAccountName;

	@Column(name = "avs_class")
	private String avsClass;

	@Column(name = "avs_direction")
	private String avsDirection;

	@Column(name = "avs_host")
	private String avsHost;

	@Column(name = "avs_password")
	private String avsPassword;

	@Column(name = "avs_server_type")
	private String avsServerType;

	@Column(name = "avs_status")
	private String avsStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "avs_tstamp")
	private Date avsTstamp;

	@Column(name = "avs_user")
	private String avsUser;

	@Column(name = "use_code")
	private String useCode;

	public AviServer() {
	}

	/**
	 * Constructor a utilizar con lo obtenido de la tabla
	 * �mpfusers.avi_servers�.
	 * 
	 * @param avsCode
	 */
	public AviServer(Object[] dbRow) {
		super();
		this.avsCode = ((int) dbRow[0]);
		this.avsServerType = dbRow[1].toString();
		this.avsHost = dbRow[2].toString();
		this.avsUser = dbRow[3].toString();
		this.avsPassword = dbRow[4].toString();
		this.avsAccount = dbRow[5].toString();
		this.avsDirection = dbRow[6].toString();
		this.useCode = dbRow[7].toString();
		this.avsStatus = dbRow[8].toString();
		this.avsTstamp = ((Date) dbRow[9]);
		this.avsAccountName = dbRow[10].toString();
		this.avsClass = dbRow[11].toString();
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

	@Override
	public String toString() {
		return "AviServer [avsCode=" + avsCode + ", avsAccount=" + avsAccount + ", avsAccountName=" + avsAccountName
				+ ", avsClass=" + avsClass + ", avsDirection=" + avsDirection + ", avsHost=" + avsHost
				+ ", avsPassword=" + avsPassword + ", avsServerType=" + avsServerType + ", avsStatus=" + avsStatus
				+ ", avsTstamp=" + avsTstamp + ", avsUser=" + avsUser + ", useCode=" + useCode + "]";
	}
}