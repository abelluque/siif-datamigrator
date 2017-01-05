package ar.com.stack.siif.datamigrator.model.entities.kiwi;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cat_value_list database table.
 * 
 */
@Entity
@Table(name="cat_value_list", schema="kiwi")
@NamedQuery(name="CatValueList_Kiwi.findAll", query="SELECT c FROM CatValueList_Kiwi c")
public class CatValueList_Kiwi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="vli_code")
	private String vliCode;

	@Column(name="vli_name")
	private String vliName;

	//bi-directional many-to-one association to CatValue
	@OneToMany(mappedBy="catValueList")
	private List<CatValue_Kiwi> catValues;

	public CatValueList_Kiwi() {
	}
	
	/**
	 * Constructor a utilizar con lo obtenido de la DB Kiwi.cat_value_list.
	 * @param vliCode
	 * @param vliName
	 * @param catValues
	 */
	public CatValueList_Kiwi(String dbRow) {
		super();
		this.vliCode = dbRow.substring(0, 1);
		this.vliName = dbRow.substring(2);
	}



	public String getVliCode() {
		return this.vliCode;
	}

	public void setVliCode(String vliCode) {
		this.vliCode = vliCode;
	}

	public String getVliName() {
		return this.vliName;
	}

	public void setVliName(String vliName) {
		this.vliName = vliName;
	}

	public List<CatValue_Kiwi> getCatValues() {
		return this.catValues;
	}

	public void setCatValues(List<CatValue_Kiwi> catValues) {
		this.catValues = catValues;
	}

	public CatValue_Kiwi addCatValue(CatValue_Kiwi catValue) {
		getCatValues().add(catValue);
		catValue.setCatValueList(this);

		return catValue;
	}

	public CatValue_Kiwi removeCatValue(CatValue_Kiwi catValue) {
		getCatValues().remove(catValue);
		catValue.setCatValueList(null);

		return catValue;
	}

}