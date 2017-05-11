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
 * This class represents the negation operation.
 *
 * @author PoVALE Team
 */
public class NotRep extends AssertionRep {
    
    private BaseAssertionRep assertion;
    private VBox a1;

    public NotRep(VBox parent, int index) {
        super(parent, index);
        this.assertionLbl.setText("NOT:");
        
       a1 = new VBox();
        this.assertion = this.addAssertion(a1);
        
        this.pane.add(this.assertion.getAssertLbl(), 0, 3);
        this.pane.add(this.assertion.getAssertionCombo(), 1, 3);
        
        this.pane.add(a1, 1, 4);
    }
    
    @Override
    public Element exportAssertion(Document document) {
        if (this.isValid()){
            
            Element assertionElement = document.createElement("not");
            if(!this.messageTxt.getText().isEmpty()){
                assertionElement.setAttribute("msg", this.messageTxt.getText());
            }
            
            assertionElement.appendChild(this.assertion.getAssertion().exportAssertion(document));
            
            return assertionElement;
            
        } else {
            return null;
        }    
    }

    @Override
    public Boolean isValid() {
        return this.assertion.getAssertionCombo().getValue().toString().isEmpty();
    }
    
    @Override
    public String getName() {
        return "Not";
    }
}
