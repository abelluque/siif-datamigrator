package ar.com.stack.siif.datamigrator.model.entities.kiwi;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cat_value database table.
 * 
 */
@Entity
@Table(name="cat_value")
@NamedQuery(name="CatValue_Kiwi.findAll", query="SELECT c FROM CatValue_Kiwi c")
public class CatValue_Kiwi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cas_code")
	private String casCode;

	@Column(name="cas_value")
	private String casValue;

	@Column(name="val_order")
	private int valOrder;

	@Column(name="val_value")
	private String valValue;

	//bi-directional many-to-one association to CatValueList
	@ManyToOne
	@JoinColumn(name="vli_code")
	private CatValueList_Kiwi catValueList;

	public CatValue_Kiwi() {
	}

	public String getCasCode() {
		return this.casCode;
	}

	public void setCasCode(String casCode) {
		this.casCode = casCode;
	}

	public String getCasValue() {
		return this.casValue;
	}

	public void setCasValue(String casValue) {
		this.casValue = casValue;
	}

	public int getValOrder() {
		return this.valOrder;
	}

	public void setValOrder(int valOrder) {
		this.valOrder = valOrder;
	}

	public String getValValue() {
		return this.valValue;
	}

	public void setValValue(String valValue) {
		this.valValue = valValue;
	}

	public CatValueList_Kiwi getCatValueList() {
		return this.catValueList;
	}

	public void setCatValueList(CatValueList_Kiwi catValueList) {
		this.catValueList = catValueList;
	}

}