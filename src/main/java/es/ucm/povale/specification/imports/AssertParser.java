/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.imports;

import es.ucm.povale.specification.assertionRepresentation.AndRep;
import es.ucm.povale.specification.assertionRepresentation.AssertFalseRep;
import es.ucm.povale.specification.assertionRepresentation.AssertTrueRep;
import es.ucm.povale.specification.assertionRepresentation.AssertionRep;
import es.ucm.povale.specification.assertionRepresentation.EntailRep;
import es.ucm.povale.specification.assertionRepresentation.EqualsRep;
import es.ucm.povale.specification.assertionRepresentation.ExistOneRep;
import es.ucm.povale.specification.assertionRepresentation.ExistRep;
import es.ucm.povale.specification.assertionRepresentation.ForAllRep;
import es.ucm.povale.specification.assertionRepresentation.NotRep;
import es.ucm.povale.specification.assertionRepresentation.OrRep;
import es.ucm.povale.specification.assertionRepresentation.PredicateApplicationRep;
import es.ucm.povale.specification.termRepresentation.TermRep;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.VBox;
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
    
    public AssertFalseRep createAssertFalse(List<Object> list) {
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        int index = (int)list.get(2);
        String message = getMessage(el);
        AssertFalseRep af = new AssertFalseRep(parent, index);
        af.setMessage(message);
        return af;
    }

    public AssertTrueRep createAssertTrue(List<Object> list) {
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        int index = (int)list.get(2);
        String message = getMessage(el);
        AssertTrueRep at = new AssertTrueRep(parent, index);
        at.setMessage(message);
        return at;
    }

    public NotRep createNotAssert(List<Object> list) {
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        int index = (int)list.get(2);
        XMLParser parser = new XMLParser();
        NodeList nl = el.getChildNodes();
        String message = getMessage(el);
        NotRep nr = new NotRep(parent, index);
        nr.setMessage(message);
        AssertionRep child;
        
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                if (!nl.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    Element e = (Element)nl.item(i);
                    child = parser.getAssertion(e, nr.getABox1(), 0);
                    nr.getAssertions().get(0).setAssertion(child);
                    nr.getAssertions().get(0).setAssertionComboValue(parser.getAssertionName(e.getTagName()));
                    nr.getABox1().getChildren().remove(0);
                }
            }
        }
        
        return nr;
    }
    
    public EntailRep createEntailAssert(List<Object> list) {
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        int index = (int)list.get(2);
        XMLParser parser = new XMLParser();
        NodeList nodeList = el.getChildNodes();
        String message = getMessage(el);
        EntailRep er = new EntailRep(parent, index);
        er.setMessage(message);
        AssertionRep leftAssert = null, rightAssert = null; 
        
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (!nodeList.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    Element element = (Element) nodeList.item(i);
                    NodeList asserts = element.getChildNodes();
                    if (asserts != null && asserts.getLength() > 0) {
                        for (int j = 0; j < asserts.getLength(); j++) {
                            if (!asserts.item(j).getNodeName().equalsIgnoreCase("#text")) {
                                Element e = (Element) asserts.item(j);
                                if (leftAssert == null) {
                                    leftAssert = parser.getAssertion(e, er.getABox1(), 0);
                                    er.getAssertions().get(0).setAssertion(leftAssert);
                                    er.getAssertions().get(0).setAssertionComboValue(parser.getAssertionName(e.getTagName()));
                                    er.getABox1().getChildren().remove(0);
                                } else {
                                    rightAssert = parser.getAssertion(e, er.getABox2(), 0);
                                    er.getAssertions().get(1).setAssertion(rightAssert);
                                    er.getAssertions().get(1).setAssertionComboValue(parser.getAssertionName(e.getTagName()));
                                    er.getABox2().getChildren().remove(0);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return er;
    }

    public AndRep createAndAssert(List<Object> list) {
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        int index = (int)list.get(2);
        XMLParser parser = new XMLParser();
        NodeList nl = el.getChildNodes();
        String message = getMessage(el);
        AndRep ar = new AndRep(parent, index);
        ar.setMessage(message);
        int andIndex = 0;
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                if (!nl.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    Element e = (Element) nl.item(i);
                    if(andIndex > 1){
                        ar.addAssertion();
                    }
                    AssertionRep child = parser.getAssertion(e, ar.getBoxes().get(andIndex), 0);
                    ar.getAssertions().get(andIndex).setAssertion(child);
                    ar.getAssertions().get(andIndex).setAssertionComboValue(parser.getAssertionName(e.getTagName()));
                    ar.getBoxes().get(andIndex).getChildren().remove(0);
                    andIndex++;
                }
            }
        }
        return ar;
    }

    public OrRep createOrAssert(List<Object> list) {
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        int index = (int)list.get(2);
        XMLParser parser = new XMLParser();
        NodeList nl = el.getChildNodes();
        String message = getMessage(el);
        OrRep or = new OrRep(parent, index);
        or.setMessage(message);
        int andIndex = 0;
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                if (!nl.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    Element e = (Element) nl.item(i);
                    if(andIndex > 1){
                        or.addAssertion();
                    }
                    AssertionRep child = parser.getAssertion(e, or.getBoxes().get(andIndex), 0);
                    or.getAssertions().get(andIndex).setAssertion(child);
                    or.getAssertions().get(andIndex).setAssertionComboValue(parser.getAssertionName(e.getTagName()));
                    or.getBoxes().get(andIndex).getChildren().remove(0);
                    andIndex++;
                }
            }
        }
        return or;
    }



    public EqualsRep createEqualsAssert(List<Object> list) {
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        int index = (int)list.get(2);
        
        XMLParser parser = new XMLParser();
        NodeList nodeList = el.getChildNodes();
        Element element = null;
        TermRep leftTerm = null, rightTerm = null;
        String message = getMessage(el);
        EqualsRep er = new EqualsRep(parent, index);
       
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (!nodeList.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    element = (Element) nodeList.item(i);
                    NodeList terms = element.getChildNodes();
                    if (terms != null && terms.getLength() > 0) {
                        for (int j = 0; j < terms.getLength(); j++) {
                            if (!terms.item(j).getNodeName().equalsIgnoreCase("#text")) {
                                Element e = (Element) terms.item(j);
                                if (leftTerm == null) {
                                    leftTerm = parser.getTerm((Element) terms.item(j), er.getTermReps().get(0).getTermBox());
                                    er.getTermReps().get(0).setTerm(leftTerm);
                                    er.getTermReps().get(0).setTermComboValue(parser.getTermName(e.getTagName()));
                                    er.getTermReps().get(0).getTermBox().getChildren().remove(0);
                                } else {
                                    rightTerm = parser.getTerm((Element) terms.item(j), er.getTermReps().get(1).getTermBox());
                                    er.getTermReps().get(1).setTerm(rightTerm);
                                    er.getTermReps().get(1).setTermComboValue(parser.getTermName(e.getTagName()));
                                    er.getTermReps().get(1).getTermBox().getChildren().remove(0);
                                }
                            }
                        }
                    }
                }
            }
        }
        return er;
    }

    public ExistRep createExistAssert(List<Object> list) {
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        int index = (int)list.get(2);
        XMLParser parser = new XMLParser();
        NodeList nl = el.getChildNodes();
        String message = getMessage(el);
        ExistRep er = new ExistRep(parent, index);
        er.setMessage(message);
        AssertionRep child;
        ArrayList<Integer> existElementPos = new ArrayList<>();
        
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                if (!nl.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    existElementPos.add(i);
                }
            }
            if(existElementPos.size()==3)
            {
                Element vr = (Element)nl.item(existElementPos.get(0));
                er.setVariable(vr.getTextContent());
                
                Element tm = (Element)nl.item(existElementPos.get(1));
                TermRep term = parser.getTerm(tm, er.getTermReps().get(0).getTermBox());
                er.getTermReps().get(0).setTerm(term);
                er.getTermReps().get(0).setTermComboValue(parser.getTermName(tm.getTagName()));
                er.getTermReps().get(0).getTermBox().getChildren().remove(2);
                
                Element e = (Element)nl.item(existElementPos.get(2));
                child = parser.getAssertion(e, er.getABox1(), 0);
                er.getAssertions().get(0).setAssertion(child);
                er.getAssertions().get(0).setAssertionComboValue(parser.getAssertionName(e.getTagName()));
                er.getABox1().getChildren().remove(0);
            }
        }
        return er;
    }
    
    public ExistOneRep createExistOneAssert(List<Object> list) {
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        int index = (int)list.get(2);
        XMLParser parser = new XMLParser();
        NodeList nl = el.getChildNodes();
        String message = getMessage(el);
        ExistOneRep er = new ExistOneRep(parent, index);
        er.setMessage(message);
        AssertionRep child;
        ArrayList<Integer> existElementPos = new ArrayList<>();
        
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                if (!nl.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    existElementPos.add(i);
                }
            }
            if(existElementPos.size()==3)
            {
                Element vr = (Element)nl.item(existElementPos.get(0));
                er.setVariable(vr.getTextContent());
                
                Element tm = (Element)nl.item(existElementPos.get(1));
                TermRep term = parser.getTerm(tm, er.getTermReps().get(0).getTermBox());
                er.getTermReps().get(0).setTerm(term);
                er.getTermReps().get(0).setTermComboValue(parser.getTermName(tm.getTagName()));
                er.getTermReps().get(0).getTermBox().getChildren().remove(2);
                
                Element e = (Element)nl.item(existElementPos.get(2));
                child = parser.getAssertion(e, er.getABox1(), 0);
                er.getAssertions().get(0).setAssertion(child);
                er.getAssertions().get(0).setAssertionComboValue(parser.getAssertionName(e.getTagName()));
                er.getABox1().getChildren().remove(0);
            }
        }
        return er;
    }

    public ForAllRep createForAllAssert(List<Object> list) {
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        int index = (int)list.get(2);
        XMLParser parser = new XMLParser();
        NodeList nl = el.getChildNodes();
        String message = getMessage(el);
        ForAllRep er = new ForAllRep(parent, index);
        er.setMessage(message);
        AssertionRep child;
        ArrayList<Integer> ElementPos = new ArrayList<>();
        
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                if (!nl.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    ElementPos.add(i);
                }
            }
            if(ElementPos.size()==3)
            {
                Element vr = (Element)nl.item(ElementPos.get(0));
                er.setVariable(vr.getTextContent());
                
                Element tm = (Element)nl.item(ElementPos.get(1));
                TermRep term = parser.getTerm(tm, er.getTermReps().get(0).getTermBox());
                er.getTermReps().get(0).setTerm(term);
                er.getTermReps().get(0).setTermComboValue(parser.getTermName(tm.getTagName()));
                er.getTermReps().get(0).getTermBox().getChildren().remove(2);
                
                Element e = (Element)nl.item(ElementPos.get(2));
                child = parser.getAssertion(e, er.getABox1(), 0);
                er.getAssertions().get(0).setAssertion(child);
                er.getAssertions().get(0).setAssertionComboValue(parser.getAssertionName(e.getTagName()));
                er.getABox1().getChildren().remove(0);
            }
        }
        return er;
    }

    public PredicateApplicationRep createPredicateApplication(List<Object> list) {
        Element el = (Element)list.get(0);
        VBox parent = (VBox)list.get(1);
        int index = (int)list.get(2);
        XMLParser parser = new XMLParser();
        NodeList nl = el.getChildNodes();
        String message = getMessage(el);
        PredicateApplicationRep er = new PredicateApplicationRep(parent, index);
        er.setMessage(message);
        AssertionRep child;
        ArrayList<Integer> ElementPos = new ArrayList<>();
        
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                if (!nl.item(i).getNodeName().equalsIgnoreCase("#text")) {
                    ElementPos.add(i);
                }
            }
            if(ElementPos.size()==2)
            {
                Element vr = (Element)nl.item(ElementPos.get(0));
                er.setPredicateComboValue(vr.getTextContent());
                
                Element tm = (Element)nl.item(ElementPos.get(1));
                TermRep term = parser.getTerm(tm, er.getTermReps().get(0).getTermBox());
                er.getTermReps().get(0).setTerm(term);
                er.getTermReps().get(0).setTermComboValue(parser.getTermName(tm.getTagName()));
                er.getTermReps().get(0).getTermBox().getChildren().remove(2);
            }
        }
        return er;
    }

}
