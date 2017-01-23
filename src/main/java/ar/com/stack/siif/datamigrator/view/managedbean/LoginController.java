package ar.com.stack.siif.datamigrator.view.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.stack.siif.datamigrator.model.entities.EntityPackageName;
import ar.com.stack.siif.datamigrator.model.entities.TableMapping;
import ar.com.stack.siif.datamigrator.model.services.DataImporterService;
import ar.com.stack.siif.datamigrator.model.services.DataImporterServiceImpl;
import ar.com.stack.siif.datamigrator.model.services.TableMappingsService;

/**
 * 
 * @author Abel Luque (a-b)
 *
 */

@ManagedBean
@ViewScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 3973801993975443027L;

	private String userName;
	private String password;

	private ClassPathXmlApplicationContext appContext;
	private DataImporterService dataImporter;
	private TableMappingsService mappingService;

	private List<String> tablesToImport = new ArrayList<>();
	private Collection<TableMapping> mpfusersTMList;

	public LoginController() {

		super();
		
//		System.out.println("Obtengo el applicationContext.xml ...");
//		appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//		System.out.println("Obtengo los services ...");
//		dataImporter = appContext.getBean(DataImporterService.class);
//		mappingService = appContext.getBean(TableMappingsService.class);
//
//		System.out.println("Inicializo mpfusersTMList ...");
//		mpfusersTMList = mappingService.findByDBName(EntityPackageName.MPF_USERS.getDbName());
		
	}
	
	@PostConstruct
    public void init() {
		System.out.println("Ejecuto el PostConstruct [LoginController.init()] ...");
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String goToHome() {
		return "succes";
	}

	public DataImporterService getDataImporter() {
		return dataImporter;
	}

	public void setDataImporterDao(DataImporterServiceImpl dataImporterDao) {
		this.dataImporter = dataImporterDao;
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
		
		System.out.println("Se hará el import de " + tablesToImport.size() + " tablas de '" + dataBaseName+ "'");
		for (String tableName : tablesToImport) {
			dataImporter.importTableData(dataBaseName, tableName);
		}

	}

	/**
	 * Genera el mapeo entre Tablas y Entidades, para persistirlo en SIIF.TABLES_MAPPINGS
	 * 
	 * @return
	 */
	public String generateMappings() {

		System.out.println("Regenerando mapeos tablas/entities...");

		mappingService.generateTableMappings();

		return null;
	}
}