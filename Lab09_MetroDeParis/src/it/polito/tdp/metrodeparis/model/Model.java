package it.polito.tdp.metrodeparis.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	
	private List<Fermata> fermate;
	private  WeightedMultigraph<Fermata,DefaultWeightedEdge> graph;
	private double minTime;

	public List<Fermata> getAllFermate(){
		if(fermate==null){
		MetroDAO dao = new MetroDAO();
		fermate = dao.getAllFermate();
 
		}
		return fermate;
	}
	public WeightedMultigraph<Fermata,DefaultWeightedEdge> creaGrafo(){
		if(graph!= null){
			return graph;
		}
		graph = new WeightedMultigraph<Fermata,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		MetroDAO dao = new MetroDAO();
		List<Connessione> connessioni = dao.getConnessioni();
		//Graphs.addAllVertices(graph, getAllFermate());
		
		for(Connessione c : connessioni){
			Graphs.addEdgeWithVertices(graph, c.getFermata1(), c.getFermata2(), c.getPeso());
		}
		return graph;
	}
	
	public List<Fermata> trovaPercorsoMinimo(Fermata f1, Fermata f2){
		List<Fermata> fermatePercorso = new ArrayList<>();
		DijkstraShortestPath<Fermata,DefaultWeightedEdge> percorsoMinimo =new DijkstraShortestPath<Fermata,DefaultWeightedEdge>(graph, f1, f2);
		//percorsoMinimo.
		GraphPath<Fermata, DefaultWeightedEdge> percorso = percorsoMinimo.getPath();
		minTime =  percorso.getWeight();
		for(DefaultWeightedEdge e: percorso.getEdgeList()){
			fermatePercorso.add(graph.getEdgeSource(e));
			minTime += 30/3600;
			
		 }
		fermatePercorso.add(f2);
		return fermatePercorso;
		 		
	}
	
	public double getTempo(){
		
		return minTime;
	}
	
	
}
