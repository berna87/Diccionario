package reto1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ConjuntoNgramas {
	private String texto;
	private List<String> nGramasRepetidos;
	private List<String> nGramasIndividuales;
	/**
	 * 
	 * @param texto texto completo del cual obtendremos los nGramas
	 */
	public ConjuntoNgramas(String texto) {
		this.texto = texto.toLowerCase();
		generarNGramasRepetidos();
		generarNGramasIndividuales();
	}
	
	private void generarNGramasRepetidos(){
		List<String> lista = new ArrayList<String>();
		for (int i = 0; i < texto.length(); i++) {
			if(!texto.substring(i, i+1).equals(" ")){
				lista.add(texto.substring(i, i+1));
			}
			if(i < texto.length()-1){
				lista.add(texto.substring(i, i+2));
			}	
			if(i < texto.length()-2){
				lista.add(texto.substring(i, i+3));
			}	
		}
		nGramasRepetidos = lista;
	}
	
	private void generarNGramasIndividuales(){
		List<String> lista = new ArrayList<String>();
		for (int i = 0; i < texto.length(); i++) {
			if(!texto.substring(i, i+1).equals(" ") && !lista.contains(texto.substring(i, i+1))){
				lista.add(texto.substring(i, i+1));
			}
			if(i < texto.length()-1 && !lista.contains(texto.substring(i, i+2))){
				lista.add(texto.substring(i, i+2));
			}	
			if(i < texto.length()-2 && !lista.contains(texto.substring(i, i+3))){
				lista.add(texto.substring(i, i+3));
			}	
		}
		nGramasIndividuales = lista;
	}
	
	
	private int getOcurrencias(String s){
		int contador = 0;
		for (String string : nGramasRepetidos) {
			if (string.equals(s)) contador++;
		}	
		return contador;
	}
	/**
	 * 
	 * @return los 500 ngramas mas comunes del texto
	 */
	public List<String> nGramasMasUsados(){
		List<NGrama> lista = new ArrayList<NGrama>();
		NGrama n = null;
		for (String s : nGramasIndividuales) {
			n = new NGrama(s);
			n.setNumOcurrencias(getOcurrencias(s));
			lista.add(n);
		}
		Collections.sort(lista);
		List<String> masUsados = new ArrayList<String>();
		for (int i = 0; i < 500; i++) {
			masUsados.add(lista.get(i).getNgrama());
		}
		return masUsados;
	}
	

}
