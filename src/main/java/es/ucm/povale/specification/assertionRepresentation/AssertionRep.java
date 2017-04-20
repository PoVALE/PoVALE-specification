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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * AssertionRep class. 
 * 
 * @author PoVALE Team
 */
public abstract class AssertionRep {
    
    protected VBox parent;
    protected GridPane pane;
    protected Label assertionLbl;
    protected final Label termLbl;
    protected final Label assertLbl;
    private final Label mensajeLbl;
    protected TextField mensajeTxt;
    protected ComboBox termCombo;
    protected ComboBox assertionCombo;
    protected ObservableList<String> terms;
    protected ObservableList<String> assertions;

    public AssertionRep(VBox parent) {
        this.parent = parent;
        this.pane = new GridPane();
        this.pane.setPadding(new Insets(0, 10, 10, 10));
        
        this.assertionLbl = new Label();
        Separator line = new Separator();
        
        this.pane.add(assertionLbl, 0, 0);
        this.pane.add(line, 0, 1);
        
        this.mensajeLbl = new Label("Mensaje: ");
        this.mensajeTxt = new TextField();
        this.mensajeTxt.setPromptText("Inserta aquí tu mensaje");
        
        this.pane.add(mensajeLbl, 0, 2);
        this.pane.add(mensajeTxt, 1, 2);
        
        
        this.termLbl = new Label("Término: ");
        this.assertLbl = new Label("Aserto: ");
        
        this.terms = FXCollections.observableArrayList(
            "Function Application",
            "List Term",
            "Integer",
            "String",
            "Variable"
        );
        
        this.assertions = FXCollections.observableArrayList(
            "And",
            "Assert False",
            "Assert True",
            "Entails",
            "Equals",
            "Exist One",
            "Exist",
            "For All",
            "Not",
            "Or",
            "Predicate Application"
        );
        
        this.termCombo = new ComboBox(terms);
        
        this.assertionCombo = new ComboBox(assertions);
        
        assertionCombo.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue observable, String oldValue, String newValue) {
                createAssertionRep(newValue);
            }    
        });
        
        VBox root = new VBox();
        root.getChildren().add(this.pane);
        parent.getChildren().add(root);
    }
    
    private void createAssertionRep(String assertion){
        VBox root = new VBox();
        root.getChildren().add(this.pane);
        AssertionRepFactory.createAssertionRep(assertion,root);
    }
    
    public abstract Element exportAssertion(Document document);

}
