package ar.com.stack.siif.datamigrator.model.services;

import java.util.List;

import ar.com.stack.siif.datamigrator.model.entities.DataImport;

public interface DataImporterService {

	public List<Object[]> findAllDbTables(String dbName);

	public List<DataImport> findDataImported(String dbName, String tableName);
	
	public void importTableData(String dbName, String tableName);
	

}
