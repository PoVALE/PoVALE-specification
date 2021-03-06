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

import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author PoVALE Team
 */
public class EntailRep extends AssertionRep {
    
    private BaseAssertionRep assertion1;
    private BaseAssertionRep assertion2;
    private VBox a1;
    private VBox a2;

    public EntailRep(VBox parent, int index) {
        super(parent, index);
        this.parent = parent;
        this.assertionLbl.setText("IMPLICACIÓN:");
        
        this.a1 = new VBox();
        this.assertion1 = this.addAssertion(a1);
        this.a2 = new VBox();
        this.assertion2 = this.addAssertion(a2);
        
        this.pane.add(this.assertion1.getAssertLbl(), 0, 3);
        this.pane.add(this.assertion1.getAssertionCombo(), 1, 3);
        this.pane.add(a1, 1, 4);
        
        
        this.pane.add(this.assertion2.getAssertLbl(), 0, 5);
        this.pane.add(this.assertion2.getAssertionCombo(), 1, 5);
        this.pane.add(a2, 1, 6);
    }

    @Override
    public Element exportAssertion(Document document) {
        if (this.isValid()){
            
            Element assertionElement = document.createElement("entail");
            if(!this.messageTxt.getText().isEmpty()){
                assertionElement.setAttribute("msg", this.messageTxt.getText());
            }
            
            Element lhs = document.createElement("lhs");
            lhs.appendChild(this.assertion1.getAssertion().exportAssertion(document));
             
            Element rhs = document.createElement("rhs");
            rhs.appendChild(this.assertion2.getAssertion().exportAssertion(document));
            
            
            assertionElement.appendChild(lhs);
            assertionElement.appendChild(rhs);
            
            return assertionElement;
            
        } else {
            return null;
        }    
    }

    @Override
    public Boolean isValid() {
        return !this.assertion1.getAssertionCombo().getValue().toString().isEmpty() &&
                !this.assertion2.getAssertionCombo().getValue().toString().isEmpty()
                && this.assertion1.getAssertion().isValid() 
                && this.assertion2.getAssertion().isValid();
    }
    
    @Override
    public String getName() {
        return "Implicación";
    }

    public VBox getABox1() {
        return a1;
    }

    public VBox getABox2() {
        return a2;
    }

}
