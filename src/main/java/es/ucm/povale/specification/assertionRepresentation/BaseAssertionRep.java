/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.assertionRepresentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author PoVALE Team
 */
public class BaseAssertionRep {
    
    protected Label assertLbl;
    protected ComboBox assertionCombo;
    protected ObservableList<String> assertionObsList;
    protected AssertionRep assertion;

    public BaseAssertionRep() {
        this.assertLbl = new Label("Aserto: ");
        
        this.assertionObsList = FXCollections.observableArrayList(
            "And",
            "Assert False",
            "Assert True",
            "Entails",
            "Equals",
            "Exist One",
            "Exist",
            "For All",
            "Not",
            "Or",
            "Predicate Application"
        );
        
        this.assertionCombo = new ComboBox(assertionObsList);
    }

    public Label getAssertLbl() {
        return assertLbl;
    }

    public ComboBox getAssertionCombo() {
        return assertionCombo;
    }

    public AssertionRep getAssertion() {
        return assertion;
    }

    public void setAssertion(AssertionRep assertion) {
        this.assertion = assertion;
    }
    
}
