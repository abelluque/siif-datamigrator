package ar.com.stack.siif.datamigrator.view.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.stack.siif.datamigrator.model.entities.DataImport;
import ar.com.stack.siif.datamigrator.model.entities.EntityPackageName;
import ar.com.stack.siif.datamigrator.model.entities.TableMapping;
import ar.com.stack.siif.datamigrator.model.services.DataImporterService;
import ar.com.stack.siif.datamigrator.model.services.TableMappingsService;

/**
 * 
 * ManagedBean for home page
 * 
 * @author a-b
 *
 */
@ManagedBean
@ViewScoped
public class HomeImporterManagedBean implements Serializable {

	private List<String> fullTableList;
	private List<String> fullDBList;
	private Collection<TableMapping> mpfusersTMList;
	private Collection<DataImport> dataImported;

	private static final long serialVersionUID = 1L;
	private ClassPathXmlApplicationContext appContext;
	private List<String> tablesToImport;

	@ManagedProperty("#{dataImporterService}")
	private DataImporterService dataImporterService;

	@ManagedProperty("#{mappingService}")
	private TableMappingsService mappingService;

	@PostConstruct
	public void init() {
		System.out.println("HomeImporterManagedBean.init(): inicializo mpfusersTMList ... ");

		if (appContext == null) {
			System.out.println("HomeImporterManagedBean - Obtengo el applicationContext.xml ...");
			appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		} else {
			System.out.println("HomeImporterManagedBean - Ya existe applicationContext.xml !!");
		}

		dataImporterService = appContext.getBean(DataImporterService.class);
		mappingService = appContext.getBean(TableMappingsService.class);

		mpfusersTMList = mappingService.findByDBName(EntityPackageName.MPF_USERS.getDbName());

		tablesToImport = new ArrayList<>();
		dataImported = dataImporterService.findDataImported(EntityPackageName.MPF_USERS.getDbName(), null);
		
		fullDBList = new ArrayList<String>();
		fullTableList = new ArrayList<String>();

		for (DataImport dataImport : dataImported) {
			fullTableList.add(dataImport.getTableName());
			
			String dbName = dataImport.getDbName();
			if (!fullDBList.contains(dbName)) {
				fullDBList.add(dbName);
			}
		}
		
	}

	/**
	 * Genera el mapeo entre Tablas y Entidades, para persistirlo en SIIF.TABLES_MAPPINGS
	 * 
	 * @return
	 */
	public String generateMappings() {

		System.out.println("HomeImporterManagedBean.generateMappings(): Regenerando mapeos tablas/entities...");

		mappingService.generateTableMappings();

		return null;
	}

	public Collection<TableMapping> getMpfusersTMList() {
		return mpfusersTMList;
	}

	public void setMpfusersTMList(Collection<TableMapping> mpfusersTMList) {
		this.mpfusersTMList = mpfusersTMList;
	}

	public Collection<DataImport> getDataImported() {
		return dataImported;
	}

	public void setDataImported(Collection<DataImport> dataImported) {
		this.dataImported = dataImported;
	}

	public DataImporterService getDataImporterService() {
		return dataImporterService;
	}

	public void setDataImporterService(DataImporterService service) {
		this.dataImporterService = service;
	}

	public TableMappingsService getMappingService() {
		return mappingService;
	}

	public void setMappingService(TableMappingsService mappingService) {
		this.mappingService = mappingService;
	}

	/**
	 * Se conecta a la BB.DD 'SIIF' y levanta la data de la tabla de prueba 'Person'.
	 */
	public String importDbInfo() {

		importData(EntityPackageName.MPF_USERS.getDbName());
		//importData(EntityPackageName.KIWI.getDbName());

		return null; // "succes";
	}

	private void importData(String dataBaseName) {

		System.out.println("Obtengo las tablas de '" + dataBaseName + "'...");
		//List<Object[]> tablas = dataImporter.findAllDbTables(dbName);

		//		for (Object[] tablaDesc : tablas) {
		//			String tableName = (String) tablaDesc[0];
		//			mpfusersTables.add(tableName);
		//		}

		Collection<TableMapping> mapeos = mappingService.findByDBName(dataBaseName);

		int i = 0;
		for (TableMapping tableMapping : mapeos) {

			String tableName = tableMapping.getTableName();

			System.out.println("\t tablesToImport.add: " + tableName);
			tablesToImport.add(tableName);

			System.out.println(++i + ") " + tableName);
		}

		System.out.println("Se hará el import de " + tablesToImport.size() + " tablas de '" + dataBaseName + "'");
		for (String tableName : tablesToImport) {
			dataImporterService.importTableData(dataBaseName, tableName);
		}

	}
	
	public List<String> getFullTableList(){
		return fullTableList;
	}
	
	public List<String> getFullDBList(){
		return fullDBList;
	}
}
