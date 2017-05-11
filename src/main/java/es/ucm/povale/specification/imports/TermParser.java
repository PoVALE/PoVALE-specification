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
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Laura Hernando y Daniel Rossetto
 */



public class TermParser {
    
    public VariableRep createVariable(Element el){
        return null;
        //return new VariableRep(el.getTextContent());
    }
    
    public LiteralStringRep createLiteralString(Element el){
        String s = el.getTextContent();
        return null;
        //return new LiteralStringRep(s);
    }
    
    public LiteralIntegerRep createLiteralInteger(Element el){
        String integer = el.getTextContent();
        return null;
        //return new LiteralIntegerRep(integer);
    }
    
    public TermRep createListTerm(Element el){
        XMLParser parser = new XMLParser();
        List<TermRep> terms = new LinkedList();
        NodeList nl = el.getChildNodes();
        if(nl != null && nl.getLength() > 0) 
            for(int i = 0 ; i < nl.getLength(); i++) 
                if(!nl.item(i).getNodeName().equalsIgnoreCase("#text")){
                    Element e = (Element)nl.item(i);
                    terms.add(parser.getTerm(e));
                }
        return null;
        //return new ListTermRep(terms);
    }
    
    public TermRep createFunctionApplication(Element el){
        XMLParser parser = new XMLParser();
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
                    }
        return null;
        //return new FunctionApplicationRep(function, terms);
    }
}
