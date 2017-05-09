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

import es.ucm.povale.specification.termRepresentation.TermRep;
import es.ucm.povale.specification.termRepresentation.TermRepFactory;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    protected VBox child;
    protected GridPane pane;
    protected Label assertionLbl;
    private final Label messageLbl;
    protected TextField messageTxt;
    protected ObservableList<String> terms;
    protected ObservableList<String> observableFunctions;
    protected ObservableList<String> observablePredicates;
    protected List<BaseAssertionRep> assertions;
    protected List<TermRep> termReps;
    protected int index;
    protected Separator line;
   

    public AssertionRep(VBox parent, int index) {
        this.parent = parent;
        this.pane = new GridPane();
        this.pane.setPadding(new Insets(0, 10, 10, 10));
        this.index = index;
        
        this.assertionLbl = new Label();
        line = new Separator();
        
        this.pane.add(assertionLbl, 0, 0);
        this.pane.add(line, 0, 1);
        
        this.messageLbl = new Label("Mensaje: ");
        this.messageTxt = new TextField();
        this.messageTxt.setPromptText("Inserta aquí tu mensaje");
        this.messageTxt.setMaxWidth(180);
        
        this.pane.add(messageLbl, 0, 2);
        this.pane.add(messageTxt, 1, 2);
        
        this.assertions = new LinkedList<>();
        
        observableFunctions = FXCollections.observableArrayList();
        observablePredicates = FXCollections.observableArrayList();
        
        child = new VBox();
        child.getChildren().add(this.pane);
        
        if(index == -1){
            parent.getChildren().add(child);
        }
        else{
            parent.getChildren().add(index, child);
        }
        
        
        
    }

    public ObservableList<String> getObservableFunctions() {
        return observableFunctions;
    }

    public ObservableList<String> getObservablePredicates() {
        return observablePredicates;
    }
    
    protected final BaseAssertionRep addAssertion(VBox a){
        BaseAssertionRep assertion = new BaseAssertionRep();
        this.assertions.add(assertion);
        int index = this.assertions.indexOf(assertion);
        
        assertion.getAssertionCombo().valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue observable, String oldValue, String newValue) {
                if(oldValue != null){
                    if(a == child){
                        a.getChildren().remove(a.getChildren().size()-1);
                    }
                    else{
                        a.getChildren().remove(0);
                    }
                }
                AssertionRep assertion;
                if(a == child){
                     assertion = AssertionRepFactory.createAssertionRep(newValue,a, -1);
                }
                else{
                    assertion = AssertionRepFactory.createAssertionRep(newValue,a,0);
                }
                
                Bindings.bindContentBidirectional(assertion.getObservableFunctions(),observableFunctions);
                Bindings.bindContentBidirectional(assertion.getObservablePredicates(),observablePredicates);
                assertions.get(index).setAssertion(assertion);
            }    
        });
        
        return assertion;
    }
    
    public abstract Element exportAssertion(Document document);
    
    public abstract Boolean isValid();
    
    
}
