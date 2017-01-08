package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the gen_funciones database table.
 * 
 */
@Entity
@Table(name="gen_funciones")
@NamedQuery(name="GenFuncione_MPFUsers.findAll", query="SELECT g FROM GenFuncione_MPFUsers g")
public class GenFuncione_MPFUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gfu_code")
	private int gfuCode;

	@Column(name="gfu_funcion")
	private String gfuFuncion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gfu_tmstamp")
	private Date gfuTmstamp;

	public GenFuncione_MPFUsers() {
	}

	public int getGfuCode() {
		return this.gfuCode;
	}

	public void setGfuCode(int gfuCode) {
		this.gfuCode = gfuCode;
	}

	public String getGfuFuncion() {
		return this.gfuFuncion;
	}

	public void setGfuFuncion(String gfuFuncion) {
		this.gfuFuncion = gfuFuncion;
	}

	public Date getGfuTmstamp() {
		return this.gfuTmstamp;
	}

	public void setGfuTmstamp(Date gfuTmstamp) {
		this.gfuTmstamp = gfuTmstamp;
	}

}