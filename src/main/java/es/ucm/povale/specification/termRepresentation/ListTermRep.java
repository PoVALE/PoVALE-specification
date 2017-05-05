/*
 * The MIT License
 *
 * Copyright 2016 PoVALE Team.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package es.ucm.povale.specification.termRepresentation;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ListTermRep extends TermRep {
    
    private List<TermRep> termReps;
    private int index;

    public ListTermRep(VBox parent) {
        super(parent);
        this.termLbl.setText("LIST OF TERMS:");
        termReps = new ArrayList<>();
        index = 2;
        
        ImageView imgAdd = new ImageView(new Image("file:src/main/resources/correct.png"));
        Button add = new Button();
        imgAdd.setFitHeight(16);
        imgAdd.setFitWidth(16);
        imgAdd.setPreserveRatio(true);
        add.setGraphic(imgAdd);
        
        pane.add(add, 2, 0);
        
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                addTerm();
            }
        });
        
        
        /*
        this.pane.add(this.terms.get(0).getTermLbl(), 0, 3);
        this.pane.add(this.terms.get(0).getTermCombo(), 1, 3);
        
        this.pane.add(this.terms.get(1).getTermLbl(), 0, 4);
        this.pane.add(this.terms.get(1).getTermCombo(), 1, 4);    
        */

    }

    public void deleteTerm(int position){
        this.terms.remove(position);
    }
    
    public void addTerm(){
        this.pane.add(new Label("Termino: "), 0, index);
        ObservableList<String> termObsList = FXCollections.observableArrayList(
            "Function Application",
            "List Term",
            "Integer",
            "String",
            "Variable"
        );
        this.pane.add(new ComboBox(termObsList), 0, index);
        
        

        int position = this.terms.size();
        //this.terms.add(this.addTerm());
        //this.pane.add(this.terms.get(position).getTermLbl(), 0, position+3);
        //this.pane.add(this.terms.get(position).getTermCombo(), 1, position+3);
        //this.pane.r
    }
    
    @Override
    public Element exportTerm(Document document) {
        
        if (this.isValid()){
            Element termElement = document.createElement("listTerm");
            for(BaseTermRep a : this.terms){
               // termElement.appendChild(a.getTerm().exportTerm(document));
            }
            return termElement;
        } else {
            return null;
        }
    }

    @Override
    public Boolean isValid() {
       Boolean valid = true;
        for(BaseTermRep a : this.terms){
           // if(a.getTermCombo().getValue().toString().isEmpty()){
           //     valid = false;
            //}
        }
       return valid;
    }
  
}
