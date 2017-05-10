package it.polito.tdp.metrodeparis.model;

import com.javadocmd.simplelatlng.*;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Connessione {
	private Fermata fermata1;
	private Fermata fermata2;
	private double velocita;
	private int idLinea;
	private double peso;
	
	public Connessione(Fermata fermata1, Fermata fermata2, double velocita, int idLinea) {
		this.fermata1 = fermata1;
		this.fermata2 = fermata2;
		this.velocita = velocita;
		this.idLinea = idLinea;
		peso = (LatLngTool.distance(fermata1.getCoords(), fermata2.getCoords(), LengthUnit.KILOMETER))/velocita;
		
	}
	
	public Fermata getFermata1() {
		return fermata1;
	}
	public void setFermata1(Fermata fermata1) {
		this.fermata1 = fermata1;
	}
	public Fermata getFermata2() {
		return fermata2;
	}
	public void setFermata2(Fermata fermata2) {
		this.fermata2 = fermata2;
	}
	public double getVelocita() {
		return velocita;
	}
	public void setVelocita(double velocita) {
		this.velocita = velocita;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fermata1 == null) ? 0 : fermata1.hashCode());
		result = prime * result + ((fermata2 == null) ? 0 : fermata2.hashCode());
		result = prime * result + idLinea;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connessione other = (Connessione) obj;
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

	@Override
	public String toString() {
		return "Da: "+fermata1+" A: "+fermata2+" peso: "+peso;
	}

	public double getPeso(){
		return peso;
	}

	
	
	
	

}
