/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.imports;

import es.ucm.povale.specification.FXMLController;
import java.io.InputStream;

/**
 *
 * @author Daniel
 */
public class Import {
    private XMLParser parser;
    private FXMLController controller;
    public Import(FXMLController controller){
        this.controller=controller;
        parser = new XMLParser();
    }
    
    public void importFile(InputStream is){
        parser.parseXMLFile(is);
        controller.importVariables(parser.getMyVars());
        
    }
        
    
}
