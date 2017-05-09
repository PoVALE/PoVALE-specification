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
            case "And":
                return new AndRep(parent, index);
            case "Assert False":
                return new AssertFalseRep(parent, index);
            case "Assert True":
                return new AssertTrueRep(parent, index);
            case "Entails":
                return new EntailRep(parent, index);
            case "Equals":
                return new EqualsRep(parent, index);
            case "Exist One":
                return new ExistOneRep(parent, index);
            case "Exist":
                return new ExistRep(parent, index);
            case "For All":
                return new ForAllRep(parent, index);
            case "Not":
                return new NotRep(parent, index);
            case "Or":
                return new OrRep(parent, index);
            case "Predicate Application":
                return new PredicateApplicationRep(parent, index);    
            default:
                return new AndRep(parent, index);
        }
    }
    
}
