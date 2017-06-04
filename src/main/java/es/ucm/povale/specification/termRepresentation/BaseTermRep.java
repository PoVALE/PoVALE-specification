/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.termRepresentation;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author PoVALE Team
 */
public class BaseTermRep{
    
    protected Label termLbl;
    protected ComboBox termCombo;
    protected ObservableList<String> terms;
    protected TermRep term;
    protected HBox termChooser;
    protected VBox termBox;
    protected ObservableList<String> observableFunctions;
    protected Button removeBtn;

    public BaseTermRep(ObservableList<String> observableFunctions) {
        this.observableFunctions = observableFunctions;
        
        termChooser = new HBox();
        //termChooser.setSpacing(14);
        
        this.termLbl = new Label("Término:     ");
        this.terms = FXCollections.observableArrayList(
            "Función de aplicación",
            "Lista de términos",
            "Entero",
            "Cadena",
            "Variable"
        );
        
        this.termCombo = new ComboBox(terms);
        
        termChooser.getChildren().add(termLbl);
        termChooser.getChildren().add(termCombo);
        
        ImageView imgRemove = new ImageView(new Image("file:src/main/resources/incorrect.png"));
        this.removeBtn = new Button();
        imgRemove.setFitHeight(16);
        imgRemove.setFitWidth(16);
        imgRemove.setPreserveRatio(true);
        removeBtn.setGraphic(imgRemove);
        
        this.termBox = new VBox();
        this.termBox.getChildren().add(termChooser);
        
         this.termCombo.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue observable, String oldValue, String newValue) {
                if(oldValue != null){
                    termBox.getChildren().remove(1);
                }
                term = TermRepFactory.createTermRep(newValue,termBox);
                Bindings.bindContentBidirectional(term.getObservableFunctions(),observableFunctions);
            }  
        });
    }
    
    public void addRemoveButton(){
        termChooser.getChildren().add(removeBtn);
    }
    
    public Button getRemoveBtn() {
        return removeBtn;
    }

    public VBox getTermBox() {
        return termBox;
    }

    public TermRep getTerm() {
        return term;
    }

    public void setTerm(TermRep term) {
        this.term = term;
    }
    
    public boolean isValid(){
        return !this.termCombo.getValue().toString().isEmpty();
    }

    public Label getTermLbl() {
        return termLbl;
    }

    public ComboBox getTermCombo() {
        return termCombo;
    }
    
    public void setTermComboValue(String s){
        this.termCombo.setValue(s);
    }
    
    
}
