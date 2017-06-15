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

import es.ucm.povale.specification.assertionRepresentation.BaseAssertionRep;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ListTermRep extends TermRep {
    
    private List<TermRep> termReps;
    private ArrayList<VBox> boxes;
    private int index;
    private VBox box;

    public ListTermRep(VBox parent) {
        super(parent);
        this.termLbl.setText("LISTA DE TÉRMINOS: ");
        termReps = new ArrayList<>();
        this.boxes = new ArrayList<>();
        ImageView imgAdd = new ImageView(new Image("file:src/main/resources/correct.png"));
        Button add = new Button("+");
        imgAdd.setFitHeight(16);
        imgAdd.setFitWidth(16);
        imgAdd.setPreserveRatio(true);
        pane.add(add, 1, 0);
        
        add.setOnAction((ActionEvent event) -> {
            addTerm();
        });
        
        BaseTermRep term = new BaseTermRep(this.observableFunctions);
        terms.add(term);
        termReps.add(term.getTerm());
        this.pane.add(term.getTermBox(),0, 3);
        boxes.add(term.getTermBox());
        GridPane.setColumnSpan(term.getTermBox(), 2);
        term.addRemoveButton();
        
        index = 4;
        
        term.getRemoveBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                terms.remove(term);
                pane.getChildren().remove(term.getTermBox());
                termReps.remove(term.getTerm());
                index--;
            }
        });
    }
    
    public void addTerm(){
        BaseTermRep term = new BaseTermRep(this.observableFunctions);
        terms.add(term);
        boxes.add(term.getTermBox());
        termReps.add(term.getTerm());
        this.pane.add(term.getTermBox(),0, index);
        GridPane.setColumnSpan(term.getTermBox(), 2);
        term.addRemoveButton();
        index++;
        
        term.getRemoveBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                terms.remove(term);
                pane.getChildren().remove(term.getTermBox());
                termReps.remove(term.getTerm());
                index--;
            }
        });
    }
    
    @Override
    public Element exportTerm(Document document) {
    if (this.isValid()){
            Element listTermElement = document.createElement("listTerm");
            for(BaseTermRep a : this.terms){
                listTermElement.appendChild(a.getTerm().exportTerm(document));
            }
            return listTermElement;
            
        } else {
            return null;
        }    
    }

    @Override
    public Boolean isValid() {
       Boolean valid = true;
        for(BaseTermRep a : this.terms){
           if(a.getTermCombo().getValue().toString().isEmpty()){
                return false;
            }
            if(!a.getTerm().isValid()){
                return false;
            }
        }
       return valid && this.termReps.size()>0;
    }

    @Override
    public void setTermValue(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<BaseTermRep> getTerms() {
        return terms;
    }
    
    public ArrayList<VBox> getBoxes() {
        return boxes;
    }

}
