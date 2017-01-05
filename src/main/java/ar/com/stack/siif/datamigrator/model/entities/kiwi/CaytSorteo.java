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
 * The persistent class for the cayt_sorteos database table.
 * 
 */
@Entity
@Table(name="cayt_sorteos")
@NamedQuery(name="CaytSorteo.findAll", query="SELECT c FROM CaytSorteo c")
public class CaytSorteo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sor_agrupamiento")
	private String sorAgrupamiento;

	@Column(name="sor_equipofiscal")
	private int sorEquipofiscal;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sor_tstamp")
	private Date sorTstamp;

	@Column(name="sor_uco")
	private int sorUco;

	public CaytSorteo() {
	}

	public String getSorAgrupamiento() {
		return this.sorAgrupamiento;
	}

	public void setSorAgrupamiento(String sorAgrupamiento) {
		this.sorAgrupamiento = sorAgrupamiento;
	}

	public int getSorEquipofiscal() {
		return this.sorEquipofiscal;
	}

	public void setSorEquipofiscal(int sorEquipofiscal) {
		this.sorEquipofiscal = sorEquipofiscal;
	}

	public Date getSorTstamp() {
		return this.sorTstamp;
	}

	public void setSorTstamp(Date sorTstamp) {
		this.sorTstamp = sorTstamp;
	}

	public int getSorUco() {
		return this.sorUco;
	}

	public void setSorUco(int sorUco) {
		this.sorUco = sorUco;
	}

}