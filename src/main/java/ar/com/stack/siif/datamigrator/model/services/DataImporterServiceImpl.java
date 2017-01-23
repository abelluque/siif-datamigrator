package ar.com.stack.siif.datamigrator.model.services;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.stack.siif.datamigrator.model.entities.DataImport;
import ar.com.stack.siif.datamigrator.model.entities.EntityPackageName;
import ar.com.stack.siif.datamigrator.model.entities.TableMapping;

@Transactional("siifTrxManager")
@Component
public class DataImporterServiceImpl implements DataImporterService {

	@PersistenceContext(unitName = "siif-pu")
	private EntityManager siifEntityManager;

	@PersistenceContext(unitName = "mpfUsers-pu")
	private EntityManager mpfUsersEntityManager;

	private ClassPathXmlApplicationContext appContext;
	private TableMappingsService mappingService;

	private List<DataImport> dataToPersist;
	private Type jsonTokenPedidoResultadoMotivos;

	public final String DB_NAME_MPF_USERS = "mpfusers";
	public final String DB_NAME_KIWI = "kiwi";

	private final String MPF_USERS_ENTITIES_PACKAGE_NAME = "ar.com.stack.siif.datamigrator.model.entities.mpfusers";
	private final String KIWI_ENTITIES_PACKAGE_NAME = "ar.com.stack.siif.datamigrator.model.entities.kiwi";

	private Map<String, String> mpfUsersMappedClasses;
	private Map<String, String> kiwiMappedClasses;

	public DataImporterServiceImpl() {

		super();

		//	initMappedClasses();
	}

	/**
	 * Inicializa los Maps que contienen el mapeo de las tablas/classes.
	 */
	private void initMappedClasses() {

		System.out.println("Inicializo el mapeo...");
		
		Collection<TableMapping> mapeos;
		mappingService = getMappingService();

		if (mpfUsersMappedClasses == null) {

			mpfUsersMappedClasses = new HashMap<String, String>();
			System.out.println("\n\n  Obtengo los mapeos de " + EntityPackageName.MPF_USERS.getDbName());

			//		mpfUsersMappedClasses.put("aux_apellidos", "AuxApellido");
			//		mpfUsersMappedClasses.put("aux_nombres_fem", "AuxNombresFem");
			//		mpfUsersMappedClasses.put("cat_value_list", "CatValueList");
			//		mpfUsersMappedClasses.put("avi_servers", "AviServer");
			//		mpfUsersMappedClasses.put("alm_almacenes", "AlmAlmacen");
			mapeos = mappingService.findByDBName(EntityPackageName.MPF_USERS.getDbName());

			for (TableMapping tableMapping : mapeos) {

				mpfUsersMappedClasses.put(tableMapping.getTableName(), tableMapping.getClassName());
				System.out
						.println("\tAgergué mapeo: " + tableMapping.getTableName() + " -> " + tableMapping.getClassName());
			}
		}

		if (kiwiMappedClasses == null) {

			kiwiMappedClasses = new HashMap<String, String>();
			//		kiwiMappedClasses.put("cat_value_list", "CatValueList");

			System.out.println("\n\n  Obtengo los mapeos de " + EntityPackageName.KIWI.getDbName());
			mapeos = null;
			mapeos = mappingService.findByDBName(EntityPackageName.KIWI.getDbName());

			for (TableMapping tableMapping : mapeos) {

				kiwiMappedClasses.put(tableMapping.getTableName(), tableMapping.getClassName());
				System.out
						.println("\tAgergué mapeo: " + tableMapping.getTableName() + " -> " + tableMapping.getClassName());
			}
		}

	}

	private TableMappingsService getMappingService() {

		if (mappingService == null) {

			if (appContext == null) {
				System.out.println("Obtengo el applicationContext!");
				appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
			}

			System.out.println("Inyecto el mappingService!!");
			mappingService = appContext.getBean(TableMappingsService.class);
		}

		return mappingService;
	}

	public List<Object[]> findAllDbTables(String dbName) {
		List<Object[]> tableList = new ArrayList<>();

		if (dbName != null) {
			tableList.addAll(siifEntityManager.createNativeQuery("SHOW FULL TABLES FROM " + dbName).getResultList());

		} else {
			System.err.println("\t -> NO SE ESPECIFICÓ EL NOMBRE DE LA BASE DE DATOS!!");
		}

		return tableList;
	}

	public void importTableData(String dbName, String tableName) {

		initMappedClasses();

		String fullTableName = new String(dbName + "." + tableName);
		System.out.println("Import data de: " + fullTableName);

		// ESTO PARA MAS ADELANTE, IMPORTAR SOLO LO QUE NO SE IMPORTÓ AÚN,
		// BUSCANDO POR ÚLTIMA PK IMPORTADA.
		// Para obtener la PK, (Object[4]): "SHOW KEYS FROM mpfusers.alm_almacenes WHERE Key_name = 'PRIMARY'"

		dataToPersist = new ArrayList<DataImport>();
		List<Object> rows = new ArrayList<Object>();

		// ESTO ES UNA PRUEBA INICIAL!!!
		// Usando NativeQuery, me retorna un Object (si la tabla tiene un solo campo) o Object[] (si la tabla tiene varios campos)
		// Debo reemplazar por ´createQuery´ usando la entidad Mapeada por Hibernate.

		//rows.addAll(em.createNativeQuery("SELECT * FROM " + fullTableName).getResultList());

		try {
			Class entityClass = getClassForTable(dbName, tableName);
			String query = new String("SELECT e FROM " + entityClass.getSimpleName() + " e ");

			if (EntityPackageName.MPF_USERS.getDbName().equals(dbName)) {
				rows.addAll(mpfUsersEntityManager.createQuery(query).getResultList());
				
			} else if (EntityPackageName.KIWI.getDbName().equals(dbName)) {
				
			} else {
				rows.addAll(siifEntityManager.createQuery(query).getResultList());
			}
				
			for (Object object : rows) {
				System.out.println(" Object ==>> " + object);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		// ESTO ES UNA PRUEBA INICIAL!!!		

		int registros = 0;
		for (Object row : rows) {

			// PRUEBA DE JSON
			//			System.out.println("\t => " + row);
			//			Object rowObject = createRowObject(dbName, tableName, row);

			//			DataImport data = new DataImport(dbName, tableName, new Long(1), rowObject);
			DataImport data = new DataImport(dbName, tableName, new Long(1), row);

			// DESEREALIZACION DEL OBJETO JSON.
			// jsonTokenPedidoResultadoMotivos = new TypeToken<AuxApellido>(){}.getType();
			// new Gson().fromJson(filaEjemplo,
			// jsonTokenPedidoResultadoMotivos))

			System.out.println("\t row a persistir =>" + row.toString());
			dataToPersist.add(data);

			// if (row instanceof String) {
			// System.out.println("\t => " + row);
			//
			// DataImport data = new DataImport(dbName,tableName, new Long(1),
			// row.toString());
			// dataToPersist.add(data);
			//
			// } else if (row instanceof Object[]) {
			//
			// StringBuffer rowCSV = new StringBuffer();
			// Object[] rowObject = ((Object[]) row);
			//
			// int rowColumns = rowObject.length;
			//
			// for (int i = 0; i < rowColumns; i++) {
			// rowCSV.append(rowObject[i]).append(",");
			// }
			//
			// System.out.println("\t => " + rowCSV);
			// }

			registros++;
		}

		persistDataImported();

	}

	/**
	 * Crea el objeto que se va a persistir, en formato JSON. Para ello necesita obtener la clase que mapea a la tabla
	 * en cuestion.
	 * 
	 * @param dbName
	 * @param tableName
	 * @param row
	 * @return
	 */
	private Object createRowObject(String dbName, String tableName, Object row) {

		Object objetoCreado = null;

		try {
			Class clazz = getClassForTable(dbName, tableName);

			Constructor[] constructores = clazz.getConstructors();
			for (Constructor constructor : constructores) {

				if (constructor.getParameterCount() == 1) {

					objetoCreado = constructor.newInstance(row);
					System.out.println("\tObjetoCreado = " + objetoCreado.toString());
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (InstantiationException e) {
			e.printStackTrace();

		} catch (IllegalAccessException e) {
			e.printStackTrace();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();

		} catch (InvocationTargetException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objetoCreado;
	}

	/**
	 * Obtiene la clase que mapea la tabla que se va a persistir.
	 * 
	 * @param dbName
	 * @param tableName
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	private Class getClassForTable(String dbName, String tableName) throws Exception {

		if (dbName == null || tableName == null) {
			throw new Exception("Datos obligatorios no válidos: dbName=" + dbName + ", tableName=" + tableName);
		}

		//String packageName = null;
		String className = null;

		if (DB_NAME_MPF_USERS.equals(dbName)) {
			//packageName = MPF_USERS_ENTITIES_PACKAGE_NAME;
			className = mpfUsersMappedClasses.get(tableName);

		} else if (DB_NAME_KIWI.equals(dbName)) {
			//packageName = KIWI_ENTITIES_PACKAGE_NAME;
			className = kiwiMappedClasses.get(tableName);
		}

		//Class clazz = Class.forName(packageName + "." + className);
		System.out.println("Hago el Class.forName para: " +dbName + " / " + tableName + " / "+ className);
		Class clazz = Class.forName(className);
		System.out.println("Se obtuvo la clase:" + clazz.getName());
		return clazz;
	}

	/*
	 * Persiste en la BB.DD ´siif´ los datos recolectados.
	 */
	private void persistDataImported() {

		for (DataImport dataImport : dataToPersist) {
			siifEntityManager.persist(dataImport);

			System.out.println("Persistiendo... " + dataImport);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataImport> findDataImported(String dbName, String tableName) {

		return siifEntityManager.createNamedQuery(DataImport.findAll).getResultList();
	}

}