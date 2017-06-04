/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.termRepresentation;

import es.ucm.povale.specification.assertionRepresentation.*;
import javafx.scene.layout.VBox;

/**
 *
 * @author PoVALE Team
 */
public class TermRepFactory {

    public static TermRep createTermRep(String termRep, VBox parent) {//predicados
        
        switch (termRep) {
            case "Función de aplicación":
                return new FunctionApplicationRep(parent);
            case "Lista de términos":
                return new ListTermRep(parent);
            case "Entero":
                return new LiteralIntegerRep(parent);
            case "Cadena":
                return new LiteralStringRep(parent);
            case "Variable":
                return new VariableRep(parent);
            default:
                return new VariableRep(parent);
        }
    }
    
}
