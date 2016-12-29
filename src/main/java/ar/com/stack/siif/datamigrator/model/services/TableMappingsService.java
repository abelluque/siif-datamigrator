package ar.com.stack.siif.datamigrator.model.services;

import java.util.Collection;

import ar.com.stack.siif.datamigrator.model.entities.TableMapping;

public interface TableMappingsService {
	
	public Collection<TableMapping> findAll();
	
	public void generateTableMappings();

}
