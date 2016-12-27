import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

public class TesterGenerico {

	public static Reflections reff = new Reflections("ar.com.stack.siif.datamigrator.model.entities");
	public static Map<String, String> entityMappings = new HashMap<String, String>();

	public static void main(String[] args) {

		Set<Class<?>> myEntities = reff.getTypesAnnotatedWith(javax.persistence.Entity.class);

		for (Class<?> entityClass : myEntities) {
			createEntityMappings(entityClass);
		}

		System.out.println("\nBinding Final... ");
		for (Map.Entry<String, String> entry : entityMappings.entrySet()) {
			System.out.println("\tTable=" + entry.getKey() + ", \tEntity=" + entry.getValue());
		}

	}

	private static void createEntityMappings(Class entityClass) {

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
