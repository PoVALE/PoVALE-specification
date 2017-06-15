/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.imports;

import es.ucm.povale.specification.assertionRepresentation.AssertionRep;
import es.ucm.povale.specification.termRepresentation.FunctionApplicationRep;
import es.ucm.povale.specification.termRepresentation.ListTermRep;
import es.ucm.povale.specification.termRepresentation.LiteralIntegerRep;
import es.ucm.povale.specification.termRepresentation.LiteralStringRep;
import es.ucm.povale.specification.termRepresentation.TermRep;
import es.ucm.povale.specification.termRepresentation.VariableRep;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.VBox;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Laura Hernando y Daniel Rossetto
 */


public class TermParser {
    
    public VariableRep createVariable(List<Object> list){
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        VariableRep vr = new VariableRep(parent);
        vr.setTermValue(el.getTextContent());
        return vr;
    }
    
    public LiteralStringRep createLiteralString(List<Object> list){
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        LiteralStringRep lsr = new LiteralStringRep(parent);
        lsr.setTermValue(el.getTextContent());
        return lsr;
    }
    
    public LiteralIntegerRep createLiteralInteger(List<Object> list){
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        LiteralIntegerRep lir = new LiteralIntegerRep(parent);
        lir.setTermValue(el.getTextContent());
        return lir;
    }
    
    public ListTermRep createListTerm(List<Object> list){
        int index=0;
        XMLParser parser = new XMLParser();
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        ListTermRep ltr = new ListTermRep(parent);
        NodeList nl = el.getChildNodes();
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                if (!nl.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    Element e = (Element) nl.item(i);
                    if(index > 0){
                        ltr.addTerm();
                    }
                    TermRep child = parser.getTerm(e, ltr.getBoxes().get(index));
                    ltr.getTerms().get(index).setTerm(child);
                    ltr.getTerms().get(index).setTermComboValue(parser.getTermName(e.getTagName()));
                    ltr.getTerms().get(index).getTermBox().getChildren().remove(2);
                    index++;
                }
            }
        }
        return ltr;
    }
    
    public FunctionApplicationRep createFunctionApplication(List<Object> list){
        XMLParser parser = new XMLParser();
        
        Element el = (Element)list.get(0);
        NodeList nl = el.getChildNodes();
        Element function = (Element) nl.item(0);
        Element term = (Element) nl.item(1);
        VBox parent = (VBox)list.get(1);
        
        FunctionApplicationRep far = new FunctionApplicationRep(parent);
        far.setFunctionComboValue(function.getTextContent());
        far.getBaseTerm().setTermComboValue(parser.getTermName(term.getTagName()));
        TermRep child = parser.getTerm(term, far.getBaseTerm().getTermBox());
        far.getBaseTerm().setTerm(child);
        
        far.getBaseTerm().getTermBox().getChildren().remove(1);
        return far;
    }
}
