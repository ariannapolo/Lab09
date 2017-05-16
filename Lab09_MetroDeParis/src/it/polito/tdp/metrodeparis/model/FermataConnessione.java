package it.polito.tdp.metrodeparis.model;

import com.javadocmd.simplelatlng.LatLng;

public class FermataConnessione extends Fermata {
	private int idLinea;
	private int intervallo;

	public FermataConnessione(int idFermata, int idLinea, int intervallo) {
		super(idFermata);
		this.idLinea = idLinea;
		this.intervallo = intervallo;
	}

	public FermataConnessione(int idFermata, String nome, LatLng coords, int idLinea) {
		super(idFermata,nome,coords);
		this.idLinea = idLinea;
			
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
	 * @return the intervallo
	 */
	public int getIntervallo() {
		return intervallo;
	}

	/**
	 * @param intervallo the intervallo to set
	 */
	public void setIntervallo(int intervallo) {
		this.intervallo = intervallo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FermataConnessione other = (FermataConnessione) obj;
		if (idLinea != other.idLinea)
			return false;
		return true;
	}
	

}
