package ar.com.stack.siif.datamigrator.model.entities;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Representacion del mapeo entre las tablas y las entities correspondientes.
 * 
 * @author c.splata
 *
 */
@Entity
@Table(name = "Tables_mappings", schema = "siif")
@NamedQueries({ @NamedQuery(name = TableMapping.findAll, query = "SELECT e FROM TableMapping e "),
		@NamedQuery(name = TableMapping.findByDBName, query = "SELECT e FROM TableMapping e WHERE e.dbName = :dbName ORDER BY e.tableName"),
		@NamedQuery(name = TableMapping.findByTableAndDBName, query = "SELECT e FROM TableMapping e WHERE e.tableName = :tableName AND e.dbName = :dbName") })
public class TableMapping implements Serializable {

	public static final String findAll = "TableMap.findAll";
	public static final String findByDBName = "TableMap.findByDBName";
	public static final String findByTableAndDBName = "TableMap.findByTableAndDBName";

	/**
	 * Default serial version.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Date creationDate;
	@Column
	private Date modificationDate;

	@Column
	private String dbName;

	@Column
	private String tableName;

	@Column
	private String idColumnName;

	@Column
	private String idColumnType;

	@Column
	private String className;

	public TableMapping() {
	}

	public TableMapping(String dbName, String tableName, Class entityClass) {
		super();
		
		this.creationDate = new Date();
		this.modificationDate = new Date();
		this.dbName = dbName;
		this.tableName = tableName;
		this.className = entityClass.getCanonicalName();

		generateIdColumnNameAndType(entityClass);
	}

	private void generateIdColumnNameAndType(Class entityClass) {
		
		System.out.println("ScanIdColumns for -> " + this.className);

		for (Field field : entityClass.getDeclaredFields()) {
			
			Annotation[] annotations = field.getDeclaredAnnotations();
			
			for (Annotation annotation : annotations) {
				
				Class<? extends Annotation> type = annotation.annotationType();

				if ("javax.persistence.Id".equals(type.getName()) || "javax.persistence.EmbeddedId".equals(type.getName())) {
					
					System.out.println("\t @Id => " + field.getName() + ", type=" + field.getType());
					
					this.idColumnName = field.getName();
					this.idColumnType = field.getType().getName();

					break;
				}
				
//				Class type = field.getType();
//				String name = field.getName();
//				
//				System.out.println("Type=" + type + ", name=" + name );
			}
		}

	
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((dbName == null) ? 0 : dbName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modificationDate == null) ? 0 : modificationDate.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
		result = prime * result + ((idColumnName == null) ? 0 : idColumnName.hashCode());
		result = prime * result + ((idColumnType == null) ? 0 : idColumnType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableMapping other = (TableMapping) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (dbName == null) {
			if (other.dbName != null)
				return false;
		} else if (!dbName.equals(other.dbName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modificationDate == null) {
			if (other.modificationDate != null)
				return false;
		} else if (!modificationDate.equals(other.modificationDate))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		if (idColumnName == null) {
			if (other.idColumnName != null)
				return false;
		} else if (!idColumnName.equals(other.idColumnName))
			return false;
		if (idColumnType == null) {
			if (other.idColumnType != null)
				return false;
		} else if (!idColumnType.equals(other.idColumnType))
			return false;
		return true;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Long getId() {
		return id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public String getIdColumnName() {
		return idColumnName;
	}

	public void setIdColumnName(String idColumnName) {
		this.idColumnName = idColumnName;
	}

	public String getIdColumnType() {
		return idColumnType;
	}

	public void setIdColumnType(String idColumnType) {
		this.idColumnType = idColumnType;
	}

	@Override
	public String toString() {
		return "TableMap [id=" + id + ", creationDate=" + creationDate + ", modificationDate=" + modificationDate
				+ ", dbName=" + dbName + ", tableName=" + tableName + ", idColumnName=" + idColumnName
				+ ", idColumnType=" + idColumnType + ", className=" + className + "]";
	}

}