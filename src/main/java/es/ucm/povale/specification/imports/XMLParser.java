/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.imports;

import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import es.ucm.povale.specification.assertionRepresentation.AssertionRep;
import es.ucm.povale.specification.termRepresentation.TermRep;
import es.ucm.povale.specification.variables.VarRep;
import java.io.InputStream;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Laura Hernando y Daniel Rossetto
 */
public class XMLParser {

    private List<VarRep> myVars;
    private List<String> myPlugins;
    private String rootFile;
    private List<AssertionRep> myAsserts;
    
    //estos maps van a ir en vez de element a su rep, de una lista de objetos
    //formada por: VBox parent, int index y Element el;
    
    private Map<String, Function<List<Object>, TermRep>> termsMap;
    private Map<String, Function<List<Object>, AssertionRep>> assertsMap;
    private Map<String,String> assertionNames;
    private Map<String,String> termNames;
    private String mySpecName;
    
    public XMLParser() {

        this.myAsserts = new LinkedList();
        this.myPlugins = new LinkedList();
        this.myVars = new LinkedList();
        this.rootFile = "";
        this.mySpecName="";
        this.termsMap = new HashMap<>();
        this.assertionNames = new HashMap<>();
        this.termNames = new HashMap<>();
        TermParser termParser = new TermParser();
        termsMap.put("variable", termParser::createVariable);
        termsMap.put("literalString", termParser::createLiteralString);
        termsMap.put("literalInteger", termParser::createLiteralInteger);
        termsMap.put("listTerm", termParser::createListTerm);
        termsMap.put("functionApplication", termParser::createFunctionApplication);
        
        
        termNames.put("variable", "Variable");
        termNames.put("literalString", "String");
        termNames.put("literalInteger", "Integer");
        termNames.put("listTerm", "List Term");
        termNames.put("functionApplication", "Function Application");

        this.assertsMap = new HashMap<>();
        AssertParser assertParser = new AssertParser();
        assertsMap.put("assertFalse", assertParser::createAssertFalse);
        assertsMap.put("assertTrue", assertParser::createAssertTrue);
        assertsMap.put("not", assertParser::createNotAssert);
        assertsMap.put("and", assertParser::createAndAssert);
        assertsMap.put("or", assertParser::createOrAssert);
        assertsMap.put("entail", assertParser::createEntailAssert);
        assertsMap.put("equals", assertParser::createEqualsAssert);
        //assertsMap.put("exist", assertParser::createExistAssert);
        //assertsMap.put("existOne", assertParser::createExistOneAssert);
        //assertsMap.put("forAll", assertParser::createForAllAssert);
        //assertsMap.put("predicateApplication", assertParser::createPredicateApplication);
        assertionNames.put("assertTrue", "Assert True");
        assertionNames.put("assertFalse", "Assert False");
        assertionNames.put("not", "Not");
        assertionNames.put("and", "And");
        assertionNames.put("or", "Or");
        assertionNames.put("entail", "Entails");
        assertionNames.put("exist", "Exist");
        assertionNames.put("existOne", "Exist One");
        assertionNames.put("forAll", "For All");
        assertionNames.put("equals", "Equals");
        assertionNames.put("predicateApplication", "Predicate Application");
        
    }

    public void parseXMLFile(InputStream is, VBox parent) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document dom = db.parse(is);
            Element document = dom.getDocumentElement();
            readPlugins(document);
            readRootFile(document);
            readSpecName(document);
            readVars(document);
            readRootAssertion(document, parent);
        } catch (ParserConfigurationException | SAXException | IOException pce) {
            pce.printStackTrace();
        }
    }

    protected TermRep getTerm(Element element, VBox parent) {
        String term = element.getTagName();
        List<Object> list = new LinkedList();
        list.add(0, element);
        list.add(1, parent);
        Function<List<Object>, TermRep> termParserFunction = this.termsMap.get(term);
        TermRep t = termParserFunction.apply(list);
        return t;
    }

    
    protected AssertionRep getAssertion(Element element, VBox parent, int index) {
        String assertion = element.getTagName();
        Function<List<Object>, AssertionRep> assertParserFunction = this.assertsMap.get(assertion);
        List<Object> list = new LinkedList();
        list.add(0, element);
        list.add(1, parent);
        list.add(2, index);
        
        AssertionRep a = assertParserFunction.apply(list);
        return a;
    }

    private void readRootAssertion(Element document, VBox parent) {
        //int index = 1;
        NodeList nl = document.getElementsByTagName("assert");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                NodeList nol = nl.item(i).getChildNodes();
                for (int j = 0; j < nol.getLength(); j++) {
                    if (!nol.item(j).getNodeName().equalsIgnoreCase("#text")) {
                        Element el = (Element) nol.item(j);
                        Label label = new Label("");
                        parent.getChildren().add(label);
                        int x = parent.getChildren().indexOf(label);
                        AssertionRep assertNode = getAssertion(el, parent, x+1);
                        assertNode.setBaseIndex(x);
                        //index +=assertNode.getOccupiedSpace();
                        myAsserts.add(assertNode);
                    }
                }
            }
        }
    }

    private void readPlugins(Element document) {
        NodeList nl = document.getElementsByTagName("import");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                myPlugins.add(nl.item(i).getTextContent());
            }
        }
    }

    private void readVars(Element document) {
        NodeList nl = document.getElementsByTagName("var");
        ArrayList<Integer> var = new ArrayList<>();
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                NodeList nol = nl.item(i).getChildNodes();
                for (int j = 0; j < nol.getLength(); j++) {
                    if (!nol.item(j).getNodeName().equalsIgnoreCase("#text")) {
                        var.add(j);
                    }
                }
                String id = nol.item(var.get(0)).getTextContent();
                String name = nol.item(var.get(1)).getTextContent();
                String description = nol.item(var.get(2)).getTextContent();
                String entityType = nol.item(var.get(3)).getTextContent();
                myVars.add(new VarRep(id, name, description, entityType));
            }
        }
    }

    private void readRootFile(Element document) {
        NodeList nl = document.getElementsByTagName("rootFile");
        if (nl != null && nl.getLength() > 0) {
            this.rootFile = nl.item(0).getTextContent();
        }
    }
    
    private void readSpecName(Element document) {
        NodeList nl = document.getElementsByTagName("specName");
        if (nl != null && nl.getLength() > 0) {
            this.mySpecName = nl.item(0).getTextContent();
        }
    }

    public List<VarRep> getMyVars() {
        return myVars;
    }

    public List<String> getMyPlugins() {
        return myPlugins;
    }

    public List<AssertionRep> getMyAsserts() {
        return myAsserts;
    }
    
    public String getMySpecName(){
        return this.mySpecName;
    }
    
    public String getRootFile(){
        return this.rootFile;
    }
    
    public String getAssertionName(String a){
        return this.assertionNames.get(a);
    }
    
    public String getTermName(String a){
        return this.termNames.get(a);
    }

}
