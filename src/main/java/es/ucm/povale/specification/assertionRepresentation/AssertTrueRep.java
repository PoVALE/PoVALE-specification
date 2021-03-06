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
 * This class represents the logical true
 * @author PoVALE Team
 */
public class AssertTrueRep extends AssertionRep {

    public AssertTrueRep(VBox parent, int index) {
        super(parent, index);
        this.parent = parent;
        this.assertionLbl.setText("VERDAD:");
    }

    public AssertTrueRep(String message) {
        super();
        this.assertionLbl.setText("VERDAD:");
        this.messageTxt.setText(message);
    }

    @Override
    public Element exportAssertion(Document document) {
        Element assertionElement = document.createElement("assertTrue");
            if(!this.messageTxt.getText().isEmpty()){
                assertionElement.setAttribute("msg", this.messageTxt.getText());
            }
        return assertionElement;
    }

    @Override
    public Boolean isValid() {
       return true;
    }
    
    @Override
    public String getName() {
        return "Verdad";
    }

}
