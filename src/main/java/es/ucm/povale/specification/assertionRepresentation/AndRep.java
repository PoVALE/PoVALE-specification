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

import java.util.LinkedList;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author PoVALE Team
 */
public class AndRep extends AssertionRep {

    public AndRep(VBox parent) {
        super(parent);
        this.parent = parent;
        this.assertionLbl.setText("AND:");
        
        this.assertions.add(this.addAssertion());
        this.assertions.add(this.addAssertion());
        
        this.pane.add(this.assertions.get(0).getAssertLbl(), 0, 3);
        this.pane.add(this.assertions.get(0).getAssertionCombo(), 1, 3);
        
        this.pane.add(this.assertions.get(1).getAssertLbl(), 0, 4);
        this.pane.add(this.assertions.get(1).getAssertionCombo(), 1, 4);
        
    }

    @Override
    public Element exportAssertion(Document document) {
       if (this.isValid()){
            
            Element assertionElement = document.createElement("and");
            if(!this.messageTxt.getText().isEmpty()){
                assertionElement.setAttribute("msg", this.messageTxt.getText());
            }
            
            for(BaseAssertionRep a : this.assertions){
                assertionElement.appendChild(a.getAssertion().exportAssertion(document));
            }
            
            return assertionElement;
            
        } else {
            return null;
        }    
    }

    @Override
    public Boolean isValid() {
        Boolean valid = true;
        for(BaseAssertionRep a : this.assertions){
            if(a.getAssertionCombo().getValue().toString().isEmpty()){
                valid = false;
            }
        }
       return valid;
    }

}
