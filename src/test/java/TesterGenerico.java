import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

public class TesterGenerico {

	public static Reflections reff = new Reflections("ar.com.stack.siif.datamigrator.model.entities");
	public static Map<String, String> entityMappings = new HashMap<String, String>();

	public static void main(String[] args) {
		
		Date fInicio = new Date();
		Date fFin = new Date();
		
		System.out.println("F_INICIO: " + fInicio + ", F_FIN: " + fFin);
		
		//testScanIdColumns();

	}

	private static void testScanIdColumns() {
		Set<Class<?>> myEntities = reff.getTypesAnnotatedWith(javax.persistence.Entity.class);

		for (Class<?> entityClass : myEntities) {
			//			createEntityMappings(entityClass);

			scanIdColumns(entityClass);
		}

		//		System.out.println("\nBinding Final... ");
		//		for (Map.Entry<String, String> entry : entityMappings.entrySet()) {
		//			System.out.println("\tTable=" + entry.getKey() + ", \tEntity=" + entry.getValue());
		//		}
	}

	private static void scanIdColumns(Class entityClass) {

		System.out.println("ScanIdColumns for -> " + entityClass);

		for (Field field : entityClass.getDeclaredFields()) {
			
			Annotation[] annotations = field.getDeclaredAnnotations();
			
			for (Annotation annotation : annotations) {
				
				Class<? extends Annotation> type = annotation.annotationType();

				if ("javax.persistence.Id".equals(type.getName()) || "javax.persistence.EmbeddedId".equals(type.getName())) {
					
					System.out.println("\t @Id => " + field.getName() + ", type=" + field.getType());
					break;
				}
				
//				Class type = field.getType();
//				String name = field.getName();
//				
//				System.out.println("Type=" + type + ", name=" + name );
			}
		}

	}

	private static void createEntityMappings(Class entityClass) {

		System.out.println("entityClass-> " + entityClass);
		try {
			for (Annotation annotation : entityClass.getAnnotations()) {
				Class<? extends Annotation> type = annotation.annotationType();

				if ("javax.persistence.Table".equals(type.getName())) {

					for (Method method : type.getDeclaredMethods()) {

						if ("name".equals(method.getName())) {
							Object value = method.invoke(annotation, (Object[]) null);

							entityMappings.put(value.toString(), entityClass.getCanonicalName());
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
