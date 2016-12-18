package ar.com.stack.siif.datamigrator.model.entities.mpfusers;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the doc_documents database table.
 * 
 */
@Embeddable
public class DocDocumentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="doc_code")
	private String docCode;

	@Column(name="doc_storage")
	private String docStorage;

	public DocDocumentPK() {
	}
	public String getDocCode() {
		return this.docCode;
	}
	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}
	public String getDocStorage() {
		return this.docStorage;
	}
	public void setDocStorage(String docStorage) {
		this.docStorage = docStorage;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DocDocumentPK)) {
			return false;
		}
		DocDocumentPK castOther = (DocDocumentPK)other;
		return 
			this.docCode.equals(castOther.docCode)
			&& this.docStorage.equals(castOther.docStorage);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.docCode.hashCode();
		hash = hash * prime + this.docStorage.hashCode();
		
		return hash;
	}
}