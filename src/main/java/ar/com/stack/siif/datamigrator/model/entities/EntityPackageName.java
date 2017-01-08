package ar.com.stack.siif.datamigrator.model.entities;

public enum EntityPackageName {

	KIWI("kiwi", "ar.com.stack.siif.datamigrator.model.entities.kiwi"), 
	MPF_USERS("mpfusers", "ar.com.stack.siif.datamigrator.model.entities.mpfusers");

	private final String dbName;
	private final String packageName;

	private EntityPackageName(String dbName, String packageName) {
		this.dbName = dbName;
		this.packageName = packageName;
	}

	public String getDbName() {
		return dbName;
	}

	public String getPackageName() {
		return packageName;
	}

}
