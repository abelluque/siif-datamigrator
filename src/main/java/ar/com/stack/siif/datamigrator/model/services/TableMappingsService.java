package ar.com.stack.siif.datamigrator.model.services;

import java.util.Collection;
import java.util.List;

import ar.com.stack.siif.datamigrator.model.entities.TableMapping;

public interface TableMappingsService {
	
	public Collection<TableMapping> findAll();
	
	public List<TableMapping> findByDBName(String dbName);
	
	public void generateTableMappings();

}
