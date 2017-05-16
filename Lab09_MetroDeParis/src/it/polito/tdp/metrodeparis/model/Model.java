package it.polito.tdp.metrodeparis.model;


import java.util.ArrayList;
import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.WeightedMultigraph;

import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	
	private List<Fermata> fermate;
	private List<FermataConnessione> fermateC;
	private  WeightedMultigraph<Fermata,DefaultWeightedEdge> graph;
	private double minTime=10000000;
	private DirectedWeightedMultigraph<FermataConnessione, DefaultWeightedEdge> grafo;

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
		creaGrafo();
		List<Fermata> fermatePercorso = new ArrayList<>();
		DijkstraShortestPath<Fermata,DefaultWeightedEdge> percorsoMinimo =new DijkstraShortestPath<Fermata,DefaultWeightedEdge>(graph, f1, f2);
		GraphPath<Fermata, DefaultWeightedEdge> percorso = percorsoMinimo.getPath();
		minTime =  percorso.getWeight();
		for(DefaultWeightedEdge e: percorso.getEdgeList()){
			fermatePercorso.add(graph.getEdgeSource(e));
			minTime += 30/3600;
			
		 }
		fermatePercorso.add(f2);
		return fermatePercorso;
		 		
	}
	
	public String getTempo(){
		int ore = (int) minTime;
		int minuti = (int) ((minTime-ore)*60);
		int secondi = (int) ((((minTime-ore)*60)-minuti)*60);
		String s = ""+ore+":"+minuti+":"+secondi;

		return s;
	}
	
	public DirectedWeightedMultigraph<FermataConnessione, DefaultWeightedEdge> creaGrafoDiretto(){
		if(grafo != null){
			return grafo;
		}
		grafo = new DirectedWeightedMultigraph<FermataConnessione, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		for(FermataConnessione fc: this.getFermateConnesse()){
			for(FermataConnessione fc1: this.getFermateConnesse()){
				//System.out.println("1:"+fc.getIdFermata()+" 2: "+fc1.getIdFermata()+"Linea 1 e 2: "+fc.getIdLinea()+" "+fc1.getIdLinea());
				if(fc.getIdFermata()==fc1.getIdFermata() && fc.getIdLinea()!= fc1.getIdLinea()){
					Graphs.addEdgeWithVertices(grafo, fc, fc1, fc1.getIntervallo());
				}
			}
		}
		MetroDAO dao = new MetroDAO();
		List<ConnessioneConLinea> connessioni = dao.getConnessioniConLinee();
		for(ConnessioneConLinea c : connessioni){
			Graphs.addEdgeWithVertices(grafo, c.getFermata1(), c.getFermata2(), c.getPeso());
		}
		
		return grafo;
	}
	
	public List<FermataConnessione> getFermateConnesse(){
		if(fermateC !=null){
			return fermateC;
		}
		MetroDAO dao = new MetroDAO();
		fermateC =dao.getFermateConnesse();
		return fermateC;
	}
	
	public String trovaPercorsoMinimoConCambi(Fermata f1, Fermata f2){
		GraphPath<FermataConnessione, DefaultWeightedEdge> pminimo = null;
		this.creaGrafoDiretto();
		List<Fermata> fermateIntermedie = new ArrayList<>();
		for(FermataConnessione fc1 : grafo.vertexSet()){
			if(fc1.getIdFermata()==f1.getIdFermata()){
				for(FermataConnessione fc2 : grafo.vertexSet()){
					if(fc2.getIdFermata()==f2.getIdFermata()){
						DijkstraShortestPath<FermataConnessione,DefaultWeightedEdge> percorsoMinimo =new DijkstraShortestPath<FermataConnessione,DefaultWeightedEdge>(grafo, fc1, fc2);
						GraphPath<FermataConnessione, DefaultWeightedEdge> percorso = percorsoMinimo.getPath();
						if(percorso.getWeight()<=minTime){
							pminimo = percorso;
							minTime = percorso.getWeight();
						}
					}
				}
			}
			
		}
		if(pminimo==null){
			return "Nessun percorso trovato";
		}
		String p ="Percorso:\n";
		int idLinea = 0;
		for(DefaultWeightedEdge e: pminimo.getEdgeList()){
			
			if(idLinea !=  grafo.getEdgeSource(e).getIdLinea()){
				if(fermateIntermedie.size()!=0){
					p+="\n"+ fermateIntermedie.toString();
					fermateIntermedie.clear();
				}
				MetroDAO dao = new MetroDAO();
				String nomeLinea = dao.getNomeLinea(grafo.getEdgeSource(e).getIdLinea());
				p+="\nPrendo linea: "+ nomeLinea;
				idLinea =  grafo.getEdgeSource(e).getIdLinea();
				
			}
			fermateIntermedie.add(grafo.getEdgeSource(e));
			
			
		 }
	
		fermateIntermedie.add(f2);
		p+="\n"+ fermateIntermedie.toString();
		
	
		return p;

	}
	
}
