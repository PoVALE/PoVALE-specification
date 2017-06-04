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

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class represents the logical disjunction operation.
 *
 * @author PoVALE Team
 */

public class OrRep extends AssertionRep {

    private ArrayList<VBox> boxes;
    private Button addButton;
    private int index;
    
    public OrRep(VBox parent, int index) {
        super(parent, index);
        this.boxes = new ArrayList<>();
        this.parent = parent;
        this.assertionLbl.setText("DISYUNCIÓN:");
        
        this.addButton = new Button("+");
        this.pane.add(addButton, 1, 0);
        
        this.addButton.setOnAction((ActionEvent event) -> {
            addAssertion();
        });
        
       this.index = 3;
       addAssertion();
       addAssertion();
        
    }
    
     public void addAssertion(){
        VBox a = new VBox();
        boxes.add(a);
        BaseAssertionRep assertion = this.addAssertion(a);
        
        this.pane.add(assertion.getAssertLbl(), 0, index);
        this.pane.add(assertion.getAssertionCombo(), 1, index);
        this.pane.add(assertion.getRemoveBtn(), 2, index);
        this.pane.add(a, 1, index+1);
        
        index+=2;
        
        assertion.getRemoveBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
               int indexOfAssertion = pane.getChildren().indexOf(assertion.getAssertLbl());
               pane.getChildren().remove(indexOfAssertion);
               pane.getChildren().remove(indexOfAssertion);
               pane.getChildren().remove(indexOfAssertion);
               pane.getChildren().remove(indexOfAssertion);
               //index--; 
               assertions.remove(assertion);
            }
        });
    }

    @Override
    public Element exportAssertion(Document document) {
       if (this.isValid()){
            
            Element assertionElement = document.createElement("or");
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
                return false;
            }
            if(!a.getAssertion().isValid()){
                return false;
            }
        }
       return valid;
    }

    @Override
    public String getName() {
        return "Disyunción";
    }
    
    public ArrayList<VBox> getBoxes() {
        return boxes;
    }

}
