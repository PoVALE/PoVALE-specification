/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.assertionRepresentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author PoVALE Team
 */
public class BaseAssertionRep {
    
    protected Label assertLbl;
    protected ComboBox assertionCombo;
    protected ObservableList<String> assertionObsList;
    protected AssertionRep assertion;
    protected Button removeBtn;
    protected HBox box;

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
        
        ImageView imgRemove = new ImageView(new Image("file:src/main/resources/incorrect.png"));
        this.removeBtn = new Button();
        imgRemove.setFitHeight(16);
        imgRemove.setFitWidth(16);
        imgRemove.setPreserveRatio(true);
        removeBtn.setGraphic(imgRemove);
        
        this.box = new HBox();
        this.box.getChildren().add(assertionCombo);
        box.getChildren().add(removeBtn);
    }
    
    public HBox getHPane(){
        return this.box;
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
    
    public Button getRemoveBtn() {
        return removeBtn;
    }
}
