package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.metrodeparis.model.Connessione;
import it.polito.tdp.metrodeparis.model.Fermata;

public class MetroDAO {

	public List<Fermata> getAllFermate() {

		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		List<Fermata> fermate = new ArrayList<Fermata>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f = new Fermata(rs.getInt("id_Fermata"), rs.getString("nome"), new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				fermate.add(f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return fermate;
	}
	
	public List<Connessione> getConnessioni(){

		final String sql = "SELECT linea.velocita as velocita, connessione.id_linea as linea, connessione.id_stazP as id_f1, connessione.id_stazA as id_f2, f1.nome as nome_f1, f2.nome as nome_f2,"+
							" f1.coordX as coordx_f1, f2.coordX as coordx_f2, f1.coordY as coordy_f1, f2.coordY as coordy_f2"+
							" FROM connessione,fermata f1, fermata f2, linea WHERE connessione.id_linea= linea.id_linea and connessione.id_stazP = f1.id_fermata and connessione.id_stazA = f2.id_fermata";
		List<Connessione> connessioni= new ArrayList<Connessione>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				Fermata f1 = new Fermata(rs.getInt("id_f1"), rs.getString("nome_f1"), new LatLng(rs.getDouble("coordx_f1"), rs.getDouble("coordy_f1")));
				Fermata f2 = new Fermata(rs.getInt("id_f2"), rs.getString("nome_f2"), new LatLng(rs.getDouble("coordx_f2"), rs.getDouble("coordy_f2")));
				
				Connessione c = new Connessione(f1, f2, rs.getDouble("velocita"), rs.getInt("linea"));
				connessioni.add(c);
				
			}
			st.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		return connessioni;

	}
}
