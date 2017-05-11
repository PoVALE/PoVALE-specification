/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.imports;

import es.ucm.povale.specification.termRepresentation.LiteralIntegerRep;
import es.ucm.povale.specification.termRepresentation.LiteralStringRep;
import es.ucm.povale.specification.termRepresentation.TermRep;
import es.ucm.povale.specification.termRepresentation.VariableRep;
import java.util.List;
import javafx.scene.layout.VBox;
import org.w3c.dom.Element;

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
    
    public TermRep createListTerm(List<Object> list){
        /*XMLParser parser = new XMLParser();
        List<TermRep> terms = new LinkedList();
        NodeList nl = el.getChildNodes();
        if(nl != null && nl.getLength() > 0) 
            for(int i = 0 ; i < nl.getLength(); i++) 
                if(!nl.item(i).getNodeName().equalsIgnoreCase("#text")){
                    Element e = (Element)nl.item(i);
                    terms.add(parser.getTerm(e));
                }*/
        return null;
        //return new ListTermRep(terms);
    }
    
    public TermRep createFunctionApplication(List<Object> list){
        /*XMLParser parser = new XMLParser();
        String function = null;
        List<TermRep> terms = new LinkedList();
        NodeList nodeList = el.getChildNodes();
        if(nodeList != null && nodeList.getLength()>0)
            for(int i = 0; i < nodeList.getLength(); i++)
                if(!nodeList.item(i).getNodeName().equalsIgnoreCase("#text"))
                    if(function == null)
                        function = nodeList.item(i).getTextContent();
                    else{
                        NodeList termList = nodeList.item(i).getChildNodes();
                        if(termList != null && termList.getLength()>0)
                            for(int j = 0; j < termList.getLength(); j++)
                                if(!termList.item(j).getNodeName().equalsIgnoreCase("#text"))
                                    terms.add(parser.getTerm((Element)termList.item(j)));
                    }*/
        return null;
        //return new FunctionApplicationRep(function, terms);
    }
}
