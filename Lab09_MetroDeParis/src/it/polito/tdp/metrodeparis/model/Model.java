package it.polito.tdp.metrodeparis.model;

import java.util.List;

import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {

	public List<Fermata> getAllFermate(){
		MetroDAO dao = new MetroDAO();
    	return dao.getAllFermate();
	}
}
