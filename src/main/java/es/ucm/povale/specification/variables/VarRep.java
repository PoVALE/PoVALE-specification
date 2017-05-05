/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.variables;

import es.ucm.povale.specification.FXMLController;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author PoVALE Team
 */
public class VarRep {
    
    private TextField idTxt;
    private TextField nameTxt;
    private TextField descTxt;
    private GridPane pane;
    private ComboBox typeCombo;
    private ObservableList<String> possibleEntities;
    private FXMLController controller;
    private VarRep that;

    public VarRep(FXMLController controller) {
        this.pane = new GridPane();
        this.controller = controller;
        that = this;

        pane.setPadding(new Insets(0, 10, 10, 10));
        
        Label idLbl = new Label("Identificador:");
        pane.add(idLbl, 0, 1);
        this.idTxt = new TextField();
        pane.add(idTxt, 1, 1);

        Label nameLbl = new Label("Nombre:");
        pane.add(nameLbl, 0, 2);
        this.nameTxt = new TextField();
        pane.add(nameTxt, 1, 2);
        
        Label descLbl = new Label("Descripción:");
        pane.add(descLbl, 0, 3);
        this.descTxt = new TextField();
        pane.add(descTxt, 1, 3);
        
        Label typeLbl = new Label("Tipo:");
        pane.add(typeLbl, 0, 4);
        possibleEntities = FXCollections.observableArrayList();
        this.typeCombo = new ComboBox(possibleEntities);
        pane.add(typeCombo, 1, 4);
        
        ImageView imgRemove = new ImageView(new Image("file:src/main/resources/incorrect.png"));
        Button remove = new Button();
        imgRemove.setFitHeight(16);
        imgRemove.setFitWidth(16);
        imgRemove.setPreserveRatio(true);
        remove.setGraphic(imgRemove);
        pane.add(remove, 2, 1);
        
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                controller.handleRemoveVariable(that);
            }
        });
    }

    public ObservableList<String> getPossibleEntities() {
        return possibleEntities;
    }
    
    public Pane getPane() {
        return this.pane;
    }
    
    public Element exportVariable(Document document){
        
        if (this.isValid()){
            
            Element variable = document.createElement("var");
            
            Element label = document.createElement("label");
            label.appendChild(document.createTextNode(this.idTxt.getText()));
                
            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(this.nameTxt.getText()));
                
            Element desc = document.createElement("desc");
            desc.appendChild(document.createTextNode(this.descTxt.getText()));
            
            Element type = document.createElement("type");
            type.appendChild(document.createTextNode(this.typeCombo.getValue().toString()));
            
            variable.appendChild(label);
            variable.appendChild(name);
            variable.appendChild(desc);
            variable.appendChild(type);
            
            return variable;
            
        } else {
            return null;
        }    
    }
    
    public boolean isValid() {
        return !this.idTxt.getText().isEmpty() &&
            !this.nameTxt.getText().isEmpty() &&
            !this.descTxt.getText().isEmpty() &&
            !this.typeCombo.getSelectionModel().isEmpty();
    }

}
