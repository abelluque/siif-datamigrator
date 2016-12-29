package ar.com.stack.siif.datamigrator.model.services;

import java.util.List;

public interface DataImporterService {

	public List<Object[]> findAllDbTables(String dbName);

	public void importTableData(String dbName, String tableName);

}
