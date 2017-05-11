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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author PoVALE Team
 */
public class EqualsRep extends AssertionRep {
    
    private BaseTermRep term1;
    private BaseTermRep term2;

    public EqualsRep(VBox parent, int index) {
        super(parent, index);
        this.parent = parent;
        this.assertionLbl.setText("EQUALS:");
        
        this.term1 = new BaseTermRep(this.observableFunctions);
        this.term2 = new BaseTermRep(this.observableFunctions);
        this.termReps.add(term1);
        this.termReps.add(term2);
        
        this.pane.add(term1.getTermBox(),0, 3);
        GridPane.setColumnSpan(term1.getTermBox(), 2);
        
        this.pane.add(term2.getTermBox(),0, 4);
        GridPane.setColumnSpan(term2.getTermBox(), 2);
    }

    @Override
    public Element exportAssertion(Document document) {
        if (this.isValid()){
            
            Element assertionElement = document.createElement("equals");
            if(!this.messageTxt.getText().isEmpty()){
                assertionElement.setAttribute("msg", this.messageTxt.getText());
            }
            
            Element lhs = document.createElement("lhs");
            lhs.appendChild(this.term1.getTerm().exportTerm(document));
             
            Element rhs = document.createElement("rhs");
            lhs.appendChild(this.term2.getTerm().exportTerm(document));
            
            assertionElement.appendChild(lhs);
            assertionElement.appendChild(rhs);
            
            return assertionElement;
            
        } else {
            return null;
        }    
    }

    @Override
    public Boolean isValid() {
        return this.term1.isValid() && this.term1.getTerm().isValid()
                && this.term2.isValid() && this.term2.getTerm().isValid();
    }
    
    @Override
    public String getName() {
        return "Equals";
    }

}
