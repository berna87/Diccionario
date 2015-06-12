package reto1;

public class Tokenize {
	private String [] palabras;
	
	/**
	 * 
	 * @param texto Texto que se dividirá en un array de Strings
	 */
	
	public Tokenize(String texto){
		palabras = texto.replaceAll("[\" \".,:¿?¡!;]+", ";").split(";"); 
	}
	//convierto todos los simbolos (uno o mas) en un solo ";"
	//por ultimo uso como delimitador ";"
	/**
	 * 
	 * @return Array con las palabras
	 */
	public String[] getPalabras() {
		return palabras;
	}
	

}
