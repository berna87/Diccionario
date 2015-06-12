package reto1;


import java.util.ArrayList;


public class Diccionario {
	ArrayList<String> listadoPalabras;
	
	public Diccionario (ArrayList<String> lista){
		listadoPalabras = lista;	
	}
	/**
	 * 
	 * @param palabra palabra a comprobar
	 * @return true si la palabra pasada como parametros está en el diccionario
	 */
	public boolean exist (String palabra){
		return listadoPalabras.contains(palabra);	
	}
	
	/**
	 * 
	 * @param s string a comprobar
	 * @return listado con las coincidencias de palabras que empiezan por el string pasado como parametros
	 */
	public ArrayList<String> comienzaPor(String s){
		ArrayList<String> lista = new ArrayList<String>();
		String aux = s.toLowerCase();
		for (String string : listadoPalabras) {
			if(string.toLowerCase().startsWith(aux)) lista.add(string);
		}
		return lista;
	}
	
	/**
	 * 
	 * @param d diccionario con el que queremos comparar
	 * @return listado con las coincidencias entre ambos diccionarios
	 */
	
	public ArrayList<String> repetidos(Diccionario d){
		ArrayList<String> lista = new ArrayList<String>();
		for (String string : listadoPalabras) {
			if(d.exist(string)) lista.add(string);
		}
		return lista;
	}
	
	/**
	 * 
	 * @param s frase a comprobar en el diccionario
	 * @return porcentaje de palabras encontradas en el diccionario
	 */
	
	public int similitud (String s){
		Tokenize t = new Tokenize(s);
		double coincidencias = 0;
		
		for (int i = 0; i < t.getPalabras().length; i++) {
			if(this.exist(t.getPalabras()[i])) coincidencias++;
		}
		
		return (int)(coincidencias / t.getPalabras().length * 100);
		
		
		
		
	}
	
	
}
