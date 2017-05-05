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
            case "Function Application":
                return new FunctionApplicationRep(parent);
            case "List Term":
                return new ListTermRep(parent);
            case "Integer":
                return new LiteralIntegerRep(parent);
            case "String":
                return new LiteralStringRep(parent);
            case "Variable":
                return new VariableRep(parent);
            default:
                return new VariableRep(parent);
        }
    }
    
}
