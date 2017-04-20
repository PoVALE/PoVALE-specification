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
import javafx.scene.control.Label;

/**
 *
 * @author PoVALE Team
 */
public class AddPluginDialog extends BasePluginDialog{

    public AddPluginDialog(Set<String> pluginSet) {
        super(pluginSet);
        this.informationMessage.setText("Añade los plugins que desees:");
        this.actionButtonType = new ButtonType("Añadir", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(actionButtonType, ButtonType.CANCEL);
        
        this.setResultConverter(dialogButton -> {
            if (dialogButton == actionButtonType) {
                return new LinkedList<>( pluginList.getSelectionModel().getSelectedItems());
            }
            return null;
        });
    }
}
