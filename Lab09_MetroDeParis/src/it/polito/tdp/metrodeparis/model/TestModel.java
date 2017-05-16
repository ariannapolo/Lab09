package it.polito.tdp.metrodeparis.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		//System.out.println("Grafo creato: ");
//		model.creaGrafo();
		Fermata f1 = model.getAllFermate().get(14);
		Fermata f2 = model.getAllFermate().get(19); 
		System.out.println("Percorso minimo da "+f1.getNome()+" a "+f2.getNome());
//		
//		System.out.println(model.trovaPercorsoMinimo(f1, new Fermata(5)));
//		System.out.println(model.getTempo());
//		
		System.out.println(model.creaGrafoDiretto());
		System.out.println(model.trovaPercorsoMinimoConCambi(f1, f2));
	}

}
