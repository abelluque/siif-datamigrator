package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the pm_importa_grupos_incidentes database table.
 * 
 */
@Entity
@Table(name="pm_importa_grupos_incidentes")
@NamedQuery(name="PmImportaGruposIncidente.findAll", query="SELECT p FROM PmImportaGruposIncidente p")
public class PmImportaGruposIncidente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	private String codigoerr;

	private String descripcion;

	private Timestamp tmstamp;

	private String valor;

	public PmImportaGruposIncidente() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoerr() {
		return this.codigoerr;
	}

	public void setCodigoerr(String codigoerr) {
		this.codigoerr = codigoerr;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getTmstamp() {
		return this.tmstamp;
	}

	public void setTmstamp(Timestamp tmstamp) {
		this.tmstamp = tmstamp;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}