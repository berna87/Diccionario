package reto1;


public class NGrama implements Comparable<NGrama>{
	private String ngrama;
	private int numOcurrencias;
	
	public NGrama(String ngrama) {
		this.ngrama = ngrama;
	}

	public void setNumOcurrencias(int numOcurrencias) {
		this.numOcurrencias = numOcurrencias;
	}
	
	
	public String getNgrama() {
		return ngrama;
	}
	
	public void setNgrama(String ngrama) {
		this.ngrama = ngrama;
	}

	@Override
	public int compareTo(NGrama n) {
		return n.numOcurrencias-this.numOcurrencias;
	}

	@Override
	public String toString() {
		return "NGrama [ngrama=" + ngrama + ", numOcurrencias="
				+ numOcurrencias + "]";
	}
	


}
