package ar.com.stack.siif.datamigrator.security.crypto;

public class AppUtilidades {

	public static void main(String[] args) throws Exception {

		String texto = "Esto es una prueba";
		System.out.println("APPUtilidades: voy a encriptar el texto '" + texto + "'");
		
		String encriptado = Utilidades.Encriptar(texto);
		System.out.println("\t encriptado: " + encriptado);
		
		String desencriptado = Utilidades.Desencriptar(encriptado);
		System.out.println("\t desencriptado: '" + desencriptado + "'");

	}

}
