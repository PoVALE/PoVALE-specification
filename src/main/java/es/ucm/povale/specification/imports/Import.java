/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.imports;

import es.ucm.povale.specification.FXMLController;
import java.io.InputStream;
import javafx.scene.layout.VBox;

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
    
    public void importFile(InputStream is, VBox parent){
        parser.parseXMLFile(is, parent);
        controller.importVariables(parser.getMyVars());
        controller.importPlugins(parser.getMyPlugins());
        controller.importAssertions(parser.getMyAsserts());
    }
        
    
}
