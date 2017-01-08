package ar.com.stack.siif.datamigrator.view.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.stack.siif.datamigrator.model.entities.EntityPackageName;
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

	private List<String> mpfusersTables = new ArrayList<>();
	private List<String> kiwiTables = new ArrayList<>();

	public LoginController() {

		super();
		appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		dataImporter = appContext.getBean(DataImporterService.class);
		mappingService = appContext.getBean(TableMappingsService.class);

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
		importData(EntityPackageName.KIWI.getDbName());
		
		return "succes"; //"succes";
	}

	private void importData(String dataBaseName) {
		String dbName = dataBaseName;
		System.out.println("Obtengo las tablas de '" + dbName + "'...");
		List<Object[]> tablas = dataImporter.findAllDbTables(dbName);

		for (Object[] tablaDesc : tablas) {
			String tableName = (String) tablaDesc[0];
			mpfusersTables.add(tableName);
		}

		System.out.println("Listado de tablas encontradas para " + dbName);
		int i = 0;
		for (String mpfusersTable : mpfusersTables) {
			System.out.println(++i + ") " + mpfusersTable);
		}
		System.out.println("Total:   " + i + " tablas en '" + dbName + "'.");

		for (String tableName : mpfusersTables) {
			dataImporter.importTableData(dbName, tableName);
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