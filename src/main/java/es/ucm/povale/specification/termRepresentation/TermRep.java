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

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * AssertionRep class. 
 * 
 * @author PoVALE Team
 */
public abstract class TermRep {
    
    protected VBox parent;
    protected GridPane pane;
    protected Label termLbl;
    protected List<BaseTermRep> terms;
    protected ObservableList<String> observableFunctions;
    protected Separator line;

    public TermRep(VBox parent) {
        this.parent = parent;
        this.pane = new GridPane();
        this.pane.setPadding(new Insets(0, 10, 10, 10));
        
        observableFunctions = FXCollections.observableArrayList();
        
        this.termLbl = new Label();
        line = new Separator();
        
        this.pane.add(termLbl, 0, 0);
        this.pane.add(line, 0, 1);

        VBox root = new VBox();
        root.getChildren().add(this.pane);
        
        parent.getChildren().add(root);
    }

    public ObservableList<String> getObservableFunctions() {
        return observableFunctions;
    }
    
    public abstract Element exportTerm(Document document);
    
    public abstract Boolean isValid();
    
    public abstract void setTermValue(String value);
}
