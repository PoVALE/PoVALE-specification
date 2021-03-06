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

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author PoVALE Team
 */

public class VariableRep extends TermRep {
	
    private Label variableLbl;
    private TextField variableTxt;
    public VariableRep(VBox parent) {
        super(parent);
        this.termLbl.setText("VARIABLE");
        
        variableLbl = new Label("Variable:");
        this.variableTxt = new TextField();
        this.variableTxt.setMaxWidth(115);
        this.variableTxt.setMinWidth(115);
        
        this.pane.add(variableLbl, 0, 3);
        this.pane.add(variableTxt, 1, 3);
        
        
    }

    @Override
    public Element exportTerm(Document document) {
        if (this.isValid()){
             Element variableElement = document.createElement("variable");
             variableElement.appendChild(document.createTextNode(this.variableTxt.getText()));
             return variableElement;
        } else {
            return null;
        } 
    }

    @Override
    public Boolean isValid() {
        return !this.variableTxt.getText().isEmpty();
    }

    @Override
    public void setTermValue(String value) {
        this.variableTxt.setText(value);
    }
   
}
