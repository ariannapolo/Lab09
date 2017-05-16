/**
 * Sample Skeleton for 'MetroDeParis.fxml' Controller Class
 */

package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.dao.MetroDAO;
import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbPartenza"
    private ComboBox<Fermata> cmbPartenza; // Value injected by FXMLLoader

    @FXML // fx:id="cmbArrivo"
    private ComboBox<Fermata> cmbArrivo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	txtResult.setText(model.trovaPercorsoMinimoConCambi(cmbPartenza.getValue(), cmbArrivo.getValue()).toString());
    	//txtResult.setText(model.trovaPercorsoMinimo(cmbPartenza.getValue(), cmbArrivo.getValue()).toString());
    	txtResult.appendText("\nTempo di percorrenza stimato: "+model.getTempo());
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbPartenza != null : "fx:id=\"cmbPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert cmbArrivo != null : "fx:id=\"cmbArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MetroDeParis.fxml'.";

    }
    
    public void setModel(Model model){
    	this.model = model;
    	List<Fermata> fermate = model.getAllFermate();
    	cmbPartenza.getItems().addAll(fermate);
    	cmbArrivo.getItems().addAll(fermate);
    }
}
