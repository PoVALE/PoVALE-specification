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
package es.ucm.povale.specification.assertionRepresentation;

import es.ucm.povale.specification.termRepresentation.BaseTermRep;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class represents the predicate application operation.
 *
 * @author PoVALE Team
 */

public class PredicateApplicationRep extends AssertionRep {

    private Label predicate;
    private ComboBox predicateCombo;
    private BaseTermRep baseTerm;
    
    public PredicateApplicationRep(VBox parent, int index) {
        super(parent, index);
        this.assertionLbl.setText("APLICACIÓN DE PREDICADO:");
        
        predicate = new Label("Predicado: ");
        this.predicateCombo = new ComboBox();
        predicateCombo.setItems(this.observablePredicates);
        
        this.pane.add(this.predicate, 0, 3);
        GridPane.setColumnSpan(this.assertionLbl,2);
        GridPane.setColumnSpan(this.line,2);
        
        this.pane.add(this.predicateCombo, 1, 3);
        
        this.baseTerm = new BaseTermRep(this.observableFunctions);
        this.termReps.add(baseTerm);
        this.pane.add(baseTerm.getTermBox(),0, 4);
        GridPane.setColumnSpan(baseTerm.getTermBox(), 2);
        
    }

    @Override
    public Element exportAssertion(Document document) {
        if (this.isValid()){
            
            Element assertionElement = document.createElement("predicateApplication");
            if(!this.messageTxt.getText().isEmpty()){
                assertionElement.setAttribute("msg", this.messageTxt.getText());
            }
            
           Element pred = document.createElement("predicate");
           pred.appendChild(document.createTextNode(this.predicateCombo.getValue().toString()));
           assertionElement.appendChild(pred);
           
           assertionElement.appendChild(this.baseTerm.getTerm().exportTerm(document));
            
           return assertionElement;
            
        } else {
            return null;
        }    

    }

    @Override
    public Boolean isValid() {
        return  this.baseTerm.isValid() &&
                !this.predicateCombo.getValue().toString().isEmpty();
                
    }
    
    @Override
    public String getName() {
        return "Aplicación de predicado";
    }

    public void setPredicateComboValue(String value) {
        this.predicateCombo.setValue(value);
    }
    

}
