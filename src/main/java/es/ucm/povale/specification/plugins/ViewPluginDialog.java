/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.plugins;

import java.util.Set;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author PoVALE Team
 */
public class ViewPluginDialog extends BasePluginDialog{
    
    public ViewPluginDialog(Set<String> pluginSet) {
        super(pluginSet);
        this.informationMessage.setText("Aquí puedes ver los plugins ya añadidos:");
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
      
        this.getDialogPane().getButtonTypes().addAll(okButton);
        
        this.setResultConverter(dialogButton -> {
            return null;
        });
    }
    
}
