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

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    
    public PredicateApplicationRep(VBox parent) {
        super(parent);
        this.parent = parent;
        this.assertionLbl.setText("PREDICATE APPLICATION:");
        
        predicate = new Label("Predicate : ");
        this.predicateCombo = new ComboBox();
        predicateCombo.setItems(this.observablePredicates);
        
        this.pane.add(this.predicate, 0, 3);
        this.pane.add(this.predicateCombo, 1, 3);
        this.pane.add(this.termLbl, 0, 4);
        this.pane.add(this.termCombo, 1, 4);
        
    }

    @Override
    public Element exportAssertion(Document document) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isValid() {
        return !this.termCombo.getValue().toString().isEmpty() &&
                !this.predicateCombo.getValue().toString().isEmpty();
                
    }

}
