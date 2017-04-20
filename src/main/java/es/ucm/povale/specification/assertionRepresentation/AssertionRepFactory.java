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
    
    public static AssertionRep createAssertionRep(String assertionRep, VBox parent) {
        
        switch (assertionRep) {
            case "And":
                return new AndRep(parent);
            case "Assert False":
                return new AssertFalseRep(parent);
            case "Assert True":
                return new AssertTrueRep(parent);
            case "Entails":
                return new EntailRep(parent);
            case "Equals":
                return new EqualsRep(parent);
            case "Exist One":
                return new ExistOneRep(parent);
            case "Exist":
                return new ExistRep(parent);
            case "For All":
                return new ForAllRep(parent);
            case "Not":
                return new NotRep(parent);
            case "Or":
                return new OrRep(parent);
            case "Predicate Application":
                return new PredicateApplicationRep(parent);    
            default:
                return new AndRep(parent);
        }
    }
    
}
