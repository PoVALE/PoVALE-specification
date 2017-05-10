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

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class FunctionApplicationRep extends TermRep {

    private Label functionLbl;
    private ComboBox functionCombo;
    private BaseTermRep baseTerm;
    
    public FunctionApplicationRep(VBox parent) {
        super(parent);
        
        this.termLbl.setText("FUNCTION APPLICATION");
        GridPane.setColumnSpan(this.termLbl,2);
        GridPane.setColumnSpan(this.line,2);
        
        
        this.functionLbl = new Label("Función:      ");
        
        this.functionCombo = new ComboBox();
        functionCombo.setItems(this.observableFunctions);
        
        this.pane.add(this.functionLbl, 0, 3);
        this.pane.add(this.functionCombo, 1, 3);
        
        this.baseTerm = new BaseTermRep(this.observableFunctions);
        
        this.pane.add(baseTerm.getTermBox(),0, 4);
        GridPane.setColumnSpan(baseTerm.getTermBox(), 2);
        
    }

    @Override
    public Element exportTerm(Document document) {
        if (this.isValid()){
            
           Element termElement = document.createElement("functionApplication");
            
           Element function = document.createElement("function");
           function.appendChild(document.createTextNode(this.functionCombo.getValue().toString()));
           termElement.appendChild(function);
           
           Element term = document.createElement("term");
           term.appendChild(this.baseTerm.getTerm().exportTerm(document));
           
           termElement.appendChild(term);
            
           return termElement;
            
        } else {
            return null;
        } 
    }

    @Override
    public Boolean isValid() {
            return this.functionCombo.getValue().toString().isEmpty()
                    && this.baseTerm.isValid() && this.baseTerm.getTerm().isValid();
    }

}
