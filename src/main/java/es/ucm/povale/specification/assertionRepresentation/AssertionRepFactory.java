/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.assertionRepresentation;

import javafx.scene.layout.VBox;

/**
 *
 * @author PoVALE Team
 */
public class AssertionRepFactory {
    
    public static AssertionRep createAssertionRep(String assertionRep, VBox parent, int index) {//predicados
        
        switch (assertionRep) {
            case "Conjunción":
                return new AndRep(parent, index);
            case "Falsedad":
                return new AssertFalseRep(parent, index);
            case "Verdad":
                return new AssertTrueRep(parent, index);
            case "Implicación":
                return new EntailRep(parent, index);
            case "Igualdad":
                return new EqualsRep(parent, index);
            case "Existe un":
                return new ExistOneRep(parent, index);
            case "Existe":
                return new ExistRep(parent, index);
            case "Para todo":
                return new ForAllRep(parent, index);
            case "Negación":
                return new NotRep(parent, index);
            case "Disyunción":
                return new OrRep(parent, index);
            case "Aplicación de predicado":
                return new PredicateApplicationRep(parent, index);    
            default:
                return new AndRep(parent, index);
        }
    }
    
}
