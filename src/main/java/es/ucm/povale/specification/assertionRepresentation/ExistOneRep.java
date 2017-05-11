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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class ExistOneRep extends AssertionRep {

    private TextField variableTxt;
    private BaseAssertionRep assertion;
    private BaseTermRep baseTerm;

    public ExistOneRep(VBox parent, int index) {
        super(parent, index);
        this.parent = parent;
        this.assertionLbl.setText("EXIST ONE:");
        
        Label variableLbl = new Label("Variable:");
        this.variableTxt = new TextField();
        
        this.pane.add(variableLbl, 0, 3);
        this.pane.add(variableTxt, 1, 3);
        
        this.baseTerm = new BaseTermRep(this.observableFunctions);
        
        this.pane.add(baseTerm.getTermBox(),0, 4);
        GridPane.setColumnSpan(baseTerm.getTermBox(), 2);
        
        VBox a1 = new VBox();
        this.assertion = this.addAssertion(a1);
        
        this.pane.add(this.assertion.getAssertLbl(), 0, 5);
        this.pane.add(this.assertion.getAssertionCombo(), 1, 5);
        
        this.pane.add(a1, 1, 6);
    }

    @Override
    public Element exportAssertion(Document document) {
        if (this.isValid()){
            
            Element assertionElement = document.createElement("existOne");
            if(!this.messageTxt.getText().isEmpty()){
                assertionElement.setAttribute("msg", this.messageTxt.getText());
            }
            
            Element variable = document.createElement("variable");
            variable.appendChild(document.createTextNode(this.variableTxt.getText()));
            assertionElement.appendChild(variable);
             
            Element term = document.createElement("term");
            term.appendChild(this.baseTerm.getTerm().exportTerm(document));
            assertionElement.appendChild(term);
           
            assertionElement.appendChild(this.assertion.getAssertion().exportAssertion(document));
            
            return assertionElement;
            
        } else {
            return null;
        }    
    }
    
    @Override
    public Boolean isValid() {
        return !this.variableTxt.getText().isEmpty() &&
               !this.baseTerm.isValid() &&
               !this.assertion.getAssertionCombo().getValue().toString().isEmpty();
    }
    
    @Override
    public String getName() {
        return "Exist One";
    }
}
