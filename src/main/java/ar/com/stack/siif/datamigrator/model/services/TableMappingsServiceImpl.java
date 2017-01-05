package ar.com.stack.siif.datamigrator.model.services;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.stack.siif.datamigrator.model.entities.TableMapping;

@Transactional("siifTrxManager")
@Component
public class TableMappingsServiceImpl implements TableMappingsService {

	@PersistenceContext(unitName = "siif-pu")
	private EntityManager siifEntityManager;
	
	public final String DB_NAME_MPF_USERS = "mpfusers";
	public final String DB_NAME_KIWI = "kiwi";
	
	private final String MPF_USERS_ENTITIES_PACKAGE_NAME = "ar.com.stack.siif.datamigrator.model.entities.mpfusers";
	private final String KIWI_ENTITIES_PACKAGE_NAME = "ar.com.stack.siif.datamigrator.model.entities.kiwi";
	
	public Collection<TableMapping> tableMappingExistentes = new ArrayList<TableMapping>();
	public static Map<String, String> mpfUsersEntityMappings;
	public static Map<String, String> kiwiEntityMappings;

	public TableMappingsServiceImpl() {

		initEntityMappings();
	}

	protected EntityManager getSiifEntityManager() {
		return siifEntityManager;
	}

	protected void setSiifEntityManager(EntityManager siifEntityManager) {
		this.siifEntityManager = siifEntityManager;
	}
	
	
	/**
	 * Inicializa los entity maps.
	 */
	private void initEntityMappings() {

		mpfUsersEntityMappings = new HashMap<String, String>();
		kiwiEntityMappings = new HashMap<String, String>();
		
	}
	
	@Override
	public Collection<TableMapping> findAll() {

		System.out.println("Ejecutando el findAll de TableMappingsServiceImpl!!!! ");
		return null;
	}
	
	/**
	 * Genera los mapeos necesarios entre Tablas y Entidades importadas de MPFUSERS y KIWI.
	 */
	@Override
	public void generateTableMappings() {

		// Primero genero los mapeos de MPF_USERS.
		Reflections reff = new Reflections(MPF_USERS_ENTITIES_PACKAGE_NAME);
		System.out.println("Voy generar los mapeos para " + MPF_USERS_ENTITIES_PACKAGE_NAME);
		Set<Class<?>> myEntities = reff.getTypesAnnotatedWith(javax.persistence.Entity.class);

		for (Class<?> entityClass : myEntities) {
			createEntityMappings(DB_NAME_MPF_USERS, entityClass);
		}

		// Primero genero los mapeos de KIWI.
		reff = new Reflections(KIWI_ENTITIES_PACKAGE_NAME);
		System.out.println("Voy generar los mapeos para " + KIWI_ENTITIES_PACKAGE_NAME);
		myEntities = reff.getTypesAnnotatedWith(javax.persistence.Entity.class);

		for (Class<?> entityClass : myEntities) {
			createEntityMappings(DB_NAME_KIWI, entityClass);
		}

		
		persistEntityMappings();
	}
	
	/**
	 * Genera los mapeos según los valores definidos en las Annotations.
	 * @param dbName TODO
	 */
	private void createEntityMappings(String dbName, Class entityClass) {

		try {
			for (Annotation annotation : entityClass.getAnnotations()) {
				Class<? extends Annotation> type = annotation.annotationType();

				if ("javax.persistence.Table".equals(type.getName())) {

					for (Method method : type.getDeclaredMethods()) {

						if ("name".equals(method.getName())) {
							Object value = method.invoke(annotation, (Object[]) null);

							if (DB_NAME_MPF_USERS.equals(dbName)) {
								mpfUsersEntityMappings.put(value.toString(), entityClass.getCanonicalName());
							
							} else {
								kiwiEntityMappings.put(value.toString(), entityClass.getCanonicalName());
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	 * Persiste el mapeo de Tablas/Entities 
	 */
	private void persistEntityMappings() {

		System.out.println("\nMapeo Final... ");
		TableMapping currentMapping;
		
		for (Map.Entry<String, String> entry : mpfUsersEntityMappings.entrySet()) {
			
			System.out.println("\tTable=" + entry.getKey() + ", \tEntity=" + entry.getValue());
			
			currentMapping = getTableMappingByName(DB_NAME_MPF_USERS, entry.getKey(), entry.getValue());
			siifEntityManager.merge(currentMapping);
		}
		
		
		for (Map.Entry<String, String> entry : kiwiEntityMappings.entrySet()) {
			
			System.out.println("\tTable=" + entry.getKey() + ", \tEntity=" + entry.getValue());
			
			currentMapping = getTableMappingByName(DB_NAME_KIWI, entry.getKey(), entry.getValue());
			siifEntityManager.merge(currentMapping);
		}
		
		
	}

	/**
	 * Busca un tableMapping ya existente para actualizar, si no lo encuentra crea uno nuevo. 
	 * @param dbName
	 * @param tableName
	 * @param entityClassName
	 * @return
	 */
	private TableMapping getTableMappingByName(String dbName, String tableName, String entityClassName) {

		TableMapping result = null;
		
		Query query = siifEntityManager.createNamedQuery(TableMapping.findByTableAndDBName);
		query.setParameter("tableName", tableName);
		query.setParameter("dbName", dbName);
		
		try {
			result = (TableMapping) query.getSingleResult();
			System.out.println("Se encontró TableMapping: " + result);
		
		} catch (NoResultException e) {
			System.out.println("No existe tableMapping para tableName:" + tableName + ", dbName:" + dbName);
		}
		
		if (result == null) {
			result = new TableMapping(dbName, tableName, entityClassName);
		}
		
		return result;
	}
}