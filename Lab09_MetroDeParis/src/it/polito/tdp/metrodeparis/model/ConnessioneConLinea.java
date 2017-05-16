package it.polito.tdp.metrodeparis.model;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class ConnessioneConLinea {
	private FermataConnessione fermata1;
	private FermataConnessione fermata2;
	private double velocita;
	private int idLinea;
	private double peso;
	
	public ConnessioneConLinea(FermataConnessione fermata1, FermataConnessione fermata2, double velocita, int idLinea) {
		this.fermata1 = fermata1;
		this.fermata2 = fermata2;
		this.velocita = velocita;
		this.idLinea = idLinea;
		peso = (LatLngTool.distance(fermata1.getCoords(), fermata2.getCoords(), LengthUnit.KILOMETER))/velocita;
		
	}

	/**
	 * @return the fermata1
	 */
	public FermataConnessione getFermata1() {
		return fermata1;
	}

	/**
	 * @param fermata1 the fermata1 to set
	 */
	public void setFermata1(FermataConnessione fermata1) {
		this.fermata1 = fermata1;
	}

	/**
	 * @return the fermata2
	 */
	public FermataConnessione getFermata2() {
		return fermata2;
	}

	/**
	 * @param fermata2 the fermata2 to set
	 */
	public void setFermata2(FermataConnessione fermata2) {
		this.fermata2 = fermata2;
	}

	/**
	 * @return the velocita
	 */
	public double getVelocita() {
		return velocita;
	}

	/**
	 * @param velocita the velocita to set
	 */
	public void setVelocita(double velocita) {
		this.velocita = velocita;
	}

	/**
	 * @return the idLinea
	 */
	public int getIdLinea() {
		return idLinea;
	}

	/**
	 * @param idLinea the idLinea to set
	 */
	public void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}

	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fermata1 == null) ? 0 : fermata1.hashCode());
		result = prime * result + ((fermata2 == null) ? 0 : fermata2.hashCode());
		result = prime * result + idLinea;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConnessioneConLinea other = (ConnessioneConLinea) obj;
		if (fermata1 == null) {
			if (other.fermata1 != null)
				return false;
		} else if (!fermata1.equals(other.fermata1))
			return false;
		if (fermata2 == null) {
			if (other.fermata2 != null)
				return false;
		} else if (!fermata2.equals(other.fermata2))
			return false;
		if (idLinea != other.idLinea)
			return false;
		return true;
	}
	
	
}
