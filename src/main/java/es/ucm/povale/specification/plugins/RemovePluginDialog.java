/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.plugins;

import java.util.LinkedList;
import java.util.Set;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author PoVALE Team
 */
public class RemovePluginDialog extends BasePluginDialog{
    
    public RemovePluginDialog(Set<String> pluginSet) {
        super(pluginSet);
        this.informationMessage.setText("Elimina los plugins que desees:");
        this.actionButtonType = new ButtonType("Eliminar", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(actionButtonType, ButtonType.CANCEL);
        
        this.setResultConverter(dialogButton -> {
            if (dialogButton == actionButtonType) {
                return new LinkedList<>( pluginList.getSelectionModel().getSelectedItems());
            }
            return null;
        });
    }
    
}
