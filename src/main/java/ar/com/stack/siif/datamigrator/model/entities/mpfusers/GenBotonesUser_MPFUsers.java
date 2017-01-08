package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the gen_botones_user database table.
 * 
 */
@Entity
@Table(name="gen_botones_user")
@NamedQuery(name="GenBotonesUser_MPFUsers.findAll", query="SELECT g FROM GenBotonesUser_MPFUsers g")
public class GenBotonesUser_MPFUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	private String botonera;

	@Id
	@Column(name="gba_id")
	private int gbaId;

	private int orden;

	@Column(name="use_code")
	private String useCode;

	public GenBotonesUser_MPFUsers() {
	}

	public String getBotonera() {
		return this.botonera;
	}

	public void setBotonera(String botonera) {
		this.botonera = botonera;
	}

	public int getGbaId() {
		return this.gbaId;
	}

	public void setGbaId(int gbaId) {
		this.gbaId = gbaId;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getUseCode() {
		return this.useCode;
	}

	public void setUseCode(String useCode) {
		this.useCode = useCode;
	}

}