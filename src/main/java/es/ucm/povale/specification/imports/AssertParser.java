/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.imports;

import es.ucm.povale.specification.assertionRepresentation.AssertionRep;
import es.ucm.povale.specification.termRepresentation.TermRep;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 *
 * @author Laura Hernando y Daniel Rossetto
 */
public class AssertParser {

    public String getMessage(Element el){
        return el.getAttribute("msg");
    }
    
    public AssertionRep createAssertFalse(Element el) {
        String message = getMessage(el);
        return null;
        //return new AssertFalseImport(message);
    }

    public AssertionRep createAssertTrue(Element el) {
        String message = getMessage(el);

        return null;
        //return new AssertTrueImport(message);
    }

    public AssertionRep createNotAssert(Element el) {
        XMLParser parser = new XMLParser();
        NodeList nl = el.getChildNodes();
        String message = getMessage(el);
/*
        AssertImport child; 
        AssertImport notNode = null;
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                if (!nl.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    child = parser.getAssertion((Element) nl.item(i));
                    notNode = new NotImport(child ,message);
                    
                }
            }
        }
        
        return notNode;
*/
return null;
        //
    }

    public AssertionRep createAndAssert(Element el) {
        /*XMLParser parser = new XMLParser();
        NodeList nl = el.getChildNodes();
        String message = getMessage(el);
        AndImport andNode = new AndImport(message);
        AssertionRep child; 
        
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                if (!nl.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    Element e = (Element) nl.item(i);
                    child = parser.getAssertion(e);
                    andNode.addChild(child);
                }
            }
        }
        return andNode;
        */
        return null;
        //
    }

    public AssertionRep createOrAssert(Element el) {

      /*  XMLParser parser = new XMLParser();
        NodeList nl = el.getChildNodes();
        String message = getMessage(el);
        OrRep orNode = new OrImport(message);
        AssertionRep child; 
        
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                if (!nl.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    Element e = (Element) nl.item(i);
                    child = parser.getAssertion(e);
                    orNode.addChild(child);
                }
            }
        }
        return orNode;*/
      return null;
        //
    }

    public AssertionRep createEntailAssert(Element el) {
        /*XMLParser parser = new XMLParser();
        NodeList nodeList = el.getChildNodes();
        Element element = null;
        String message = getMessage(el);
        AssertionRep leftAssert = null, rightAssert = null; 
        
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (!nodeList.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    element = (Element) nodeList.item(i);
                    NodeList asserts = element.getChildNodes();
                    if (asserts != null && asserts.getLength() > 0) {
                        for (int j = 0; j < asserts.getLength(); j++) {
                            if (!asserts.item(j).getNodeName().equalsIgnoreCase("#text")) {
                                if (leftAssert == null) {
                                    leftAssert = parser.getAssertion((Element) asserts.item(j));
                                } else {
                                    rightAssert = parser.getAssertion((Element) asserts.item(j));
                                }
                            }
                        }
                    }
                }
            }
        }
        
        EntailImport entailNode = new EntailImport(leftAssert, rightAssert ,message);
        return entailNode;
*/
        return null;
        //
    }

    public AssertionRep createEqualsAssert(Element el) {
        /*
        XMLParser parser = new XMLParser();
        NodeList nodeList = el.getChildNodes();
        Element element = null;
        TermRep leftTerm = null, rightTerm = null;
        String message = getMessage(el);
        
        AssertionRep equalsAssertion;
       
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (!nodeList.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    element = (Element) nodeList.item(i);
                    NodeList terms = element.getChildNodes();
                    if (terms != null && terms.getLength() > 0) {
                        for (int j = 0; j < terms.getLength(); j++) {
                            if (!terms.item(j).getNodeName().equalsIgnoreCase("#text")) {
                                if (leftTerm == null) {
                                    leftTerm = parser.getTerm((Element) terms.item(j));
                                } else {
                                    rightTerm = parser.getTerm((Element) terms.item(j));
                                }
                            }
                        }
                    }
                }
            }
        }
        
        EqualsImport equalsNode = new EqualsImport(leftTerm, rightTerm, message); 
        return equalsNode;
*/
        return null;
        //
    }

    public AssertionRep createExistAssert(Element el) {
        /*
        XMLParser parser = new XMLParser();
        NodeList existElements = el.getChildNodes();
        String variable = null;
        TermRep term = null;
        AssertionRep assertion = null;
        ArrayList<Integer> existPos = new ArrayList<>();
        String message = getMessage(el);
        AssertImport existNode = new AssertImport(null,message);
        AssertionRep child; 
        Assertion existAssertion;
        
        if (existElements != null && existElements.getLength() > 0) {
            for (int i = 0; i < existElements.getLength(); i++) {
                if (!existElements.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    existPos.add(i);
                }
            }
            variable = existElements.item(existPos.get(0)).getTextContent();
            term = parser.getTerm((Element) existElements.item(existPos.get(1)));
            child = parser.getAssertion((Element) existElements.item(existPos.get(2)), env);
            assertion = child.getAssertion();
            existNode.addChild(child);
        }
        
        existAssertion = new Exist(variable, term, assertion, message);
        existNode.setAssertion(existAssertion);
        if(message == null){
            String stringTerm = getStringTerm(term, env);
            existNode.setMessage("Existe un elemento " + variable + " en " + stringTerm +
                " tal que cumple: ");
        }
        return existNode;
*/
        return null;
        //
    }

    public AssertionRep createExistOneAssert(Element el) {
        /*
        XMLParser parser = new XMLParser();
        NodeList existElements = el.getChildNodes();
        String variable = null;
        TermRep term = null;
        Assertion assertion = null;
        ArrayList<Integer> existPos = new ArrayList<>();
        String message = getMessage(el);
        AssertImport existOneNode = new AssertImport(null,message);
        AssertImport child; 
        Assertion existOneAssertion;
        
        if (existElements != null && existElements.getLength() > 0) {
            for (int i = 0; i < existElements.getLength(); i++) {
                if (!existElements.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    existPos.add(i);
                }
            }
            variable = existElements.item(existPos.get(0)).getTextContent();
            term = parser.getTerm((Element) existElements.item(existPos.get(1)));
            child = parser.getAssertion((Element) existElements.item(existPos.get(2)), env);
            assertion = child.getAssertion();
            existOneNode.addChild(child);
        }
        existOneAssertion = new ExistOne(variable, term, assertion, message);
        existOneNode.setAssertion(existOneAssertion);
        
        if(message == null){
            String stringTerm = getStringTerm(term, env);
            existOneNode.setMessage("Existe solo un elemento " + variable + " en " + stringTerm +
                " tal que cumple: ");
        }
        return existOneNode;
*/
        return null;
        //
    }

    public AssertionRep createForAllAssert(Element el) {
        /*
        XMLParser parser = new XMLParser();
        NodeList forAllElements = el.getChildNodes();
        String variable = null;
        Term term = null;
        Assertion assertion = null;
        ArrayList<Integer> existPos = new ArrayList<>();
        String message = getMessage(el);
        AssertImport forAllNode = new AssertImport(null,message);
        AssertImport child; 
        Assertion forAllAssertion;
        
        if (forAllElements != null && forAllElements.getLength() > 0) {
            for (int j = 0; j < forAllElements.getLength(); j++) {
                if (!forAllElements.item(j).getNodeName().equalsIgnoreCase("#text")) {
                    existPos.add(j);
                }
            }
            variable = forAllElements.item(existPos.get(0)).getTextContent();
            term = parser.getTerm((Element) forAllElements.item(existPos.get(1)));
            child = parser.getAssertion((Element) forAllElements.item(existPos.get(2)), env);
            assertion = child.getAssertion();
            forAllNode.addChild(child);
        }
        
        forAllAssertion = new ForAll(variable, term, assertion, message);
        forAllNode.setAssertion(forAllAssertion);

        if(message == null){
            String stringTerm = getStringTerm(term, env);
            forAllNode.setMessage("Para todo elemento " + variable + " en " + stringTerm +
                " cumple: ");
        }
        return forAllNode;
*/
        return null;
        //
    }

    public AssertionRep createPredicateApplication(Element el) {
/*
        XMLParser parser = new XMLParser();
        String predicate = null;
        List<TermRep> predicateTerms = new LinkedList();
        NodeList nodeList = el.getChildNodes();
        String message = getMessage(el);
        AssertImport predicateApplicationNode = new AssertImport(null,message);
        Assertion predicateApplicationAssertion;
        
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (!nodeList.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    if (predicate == null) {
                        predicate = nodeList.item(i).getTextContent();
                    } else {
                        NodeList termList = nodeList.item(i).getChildNodes();
                        if (termList != null && termList.getLength() > 0) {
                            for (int j = 0; j < termList.getLength(); j++) {
                                if (!termList.item(j).getNodeName().equalsIgnoreCase("#text")) {
                                    predicateTerms.add(parser.getTerm((Element) termList.item(j)));
                                }
                            }
                        }
                    }
                }
            }
        }
        
        predicateApplicationAssertion = new PredicateApplication(predicate, predicateTerms, message);
        predicateApplicationNode.setAssertion(predicateApplicationAssertion);
        if(message == null){
            Predicate p = env.getPredicate(predicate);
            message="";
            for(Term t: predicateTerms){
                if(predicateTerms.size()==1){
                    message+=predicateTerms.get(0).toString()+" ";
                }
                message+=t.toString()+", ";
            }
            String aux[] = p.getParamDescriptions();
            for(String s: aux){
                if(s != null){
                    message+=s;
                }
            }
        }
        predicateApplicationNode.setMessage(message);
        return predicateApplicationNode;
*/
return null;
        //
    }
}