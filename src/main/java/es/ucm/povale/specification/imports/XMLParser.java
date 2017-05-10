/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.imports;

import es.ucm.povale.specification.imports.assertions.AssertImport;
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

import es.ucm.povale.assertion.Assertion;
import es.ucm.povale.environment.Environment;
import es.ucm.povale.plugin.Import;
import es.ucm.povale.specification.imports.terms.TermImport;
import es.ucm.povale.specification.variables.VarRep;
import java.io.InputStream;

/**
 *
 * @author Laura Hernando y Daniel Rossetto
 */
public class XMLParser {

    private List<VarRep> myVars;
    private List<String> myPlugins;
    private String rootFile;
    private List<AssertImport> myAsserts;
    private Map<String, Function<Element, TermImport>> termsMap;
    private Map<String, Function<Element, AssertImport>> assertsMap;
    private AssertImport myRequirements;
    private Map<String, String> defaultMessages;
    private String mySpecName;
    
    public XMLParser() {

        this.myAsserts = new LinkedList();
        this.myPlugins = new LinkedList();
        this.myVars = new LinkedList();
        this.rootFile = "";
        this.mySpecName="";
        this.termsMap = new HashMap<>();
       /* TermParser termParser = new TermParser();
        termsMap.put("variable", termParser::createVariable);
        termsMap.put("literalString", termParser::createLiteralString);
        termsMap.put("literalInteger", termParser::createLiteralInteger);
        termsMap.put("listTerm", termParser::createListTerm);
        termsMap.put("functionApplication", termParser::createFunctionApplication);

        this.assertsMap = new HashMap<>();
        /*AssertParser assertParser = new AssertParser();
        assertsMap.put("assertFalse", assertParser::createAssertFalse);
        assertsMap.put("assertTrue", assertParser::createAssertTrue);
        assertsMap.put("not", assertParser::createNotAssert);
        assertsMap.put("and", assertParser::createAndAssert);
        assertsMap.put("or", assertParser::createOrAssert);
        assertsMap.put("entail", assertParser::createEntailAssert);
        assertsMap.put("equals", assertParser::createEqualsAssert);
        assertsMap.put("exist", assertParser::createExistAssert);
        assertsMap.put("existOne", assertParser::createExistOneAssert);
        assertsMap.put("forAll", assertParser::createForAllAssert);
        assertsMap.put("predicateApplication", assertParser::createPredicateApplication);*/
    }

    public void parseXMLFile(InputStream is) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document dom = db.parse(is);
            Element document = dom.getDocumentElement();
           // readPlugins(document);
            //readRootFile(document);
            //readSpecName(document);
            readVars(document);
            //readRootAssertion(document);
        } catch (ParserConfigurationException | SAXException | IOException pce) {
            pce.printStackTrace();
        }
    }

   /* protected TermImport getTerm(Element element) {
        String term = element.getTagName();
        Function<Element, TermImport> termParserFunction = this.termsMap.get(term);
        TermImport t = termParserFunction.apply(element);
        return t;
    }

    
    protected AssertImport getAssertion(Element element) {
        String assertion = element.getTagName();
        Function<Element, AssertImport> assertParserFunction = this.assertsMap.get(assertion);
        AssertImport a = assertParserFunction.apply(element);
        return a;
    }

    private void readRootAssertion(Element document) {
        Environment envAux = new Environment();
        for (String a : myPlugins) {
            Import plugin = new Import(a, envAux);
        }
        NodeList nl = document.getElementsByTagName("assert");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                NodeList nol = nl.item(i).getChildNodes();
                for (int j = 0; j < nol.getLength(); j++) {
                    if (!nol.item(j).getNodeName().equalsIgnoreCase("#text")) {
                        Element el = (Element) nol.item(j);       
                        AssertImport assertNode = getAssertion(el);
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
*/
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
/*
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
*/
    public List<VarRep> getMyVars() {
        return myVars;
    }
/*
    public List<String> getMyPlugins() {
        return myPlugins;
    }

    public List<AssertImport> getMyAsserts() {
        return myAsserts;
    }
    
    public AssertImport getMyRequirements() {
        return myRequirements;
    }
    
    public String getMySpecName(){
        return this.mySpecName;
    }
    
    public String getRootFile(){
        return this.rootFile;
    }
*/
}
