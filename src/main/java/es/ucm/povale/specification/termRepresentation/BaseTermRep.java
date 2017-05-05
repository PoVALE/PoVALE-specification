/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.termRepresentation;

import es.ucm.povale.specification.assertionRepresentation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author PoVALE Team
 */
public class BaseTermRep{
    
    protected Label termLbl;
    protected ComboBox termCombo;
    protected ObservableList<String> termObsList;
    protected TermRep term;

    public BaseTermRep() {
        this.termLbl = new Label("Termino: ");
        
        this.termObsList = FXCollections.observableArrayList(
            "Function Application",
            "List Term",
            "Integer",
            "String",
            "Variable"
        );
        
        this.termCombo.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue observable, String oldValue, String newValue) {
                //TermRep term = TermRepFactory.createTermRep(newValue,termBox);
                //Bindings.bindContentBidirectional(term.getObservableFunctions(),this.observableFunctions);
            }    
        });
        
        this.termCombo = new ComboBox(termObsList);
    }


    
}
