/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.plugins;

import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;

/**
 *
 * @author PoVALE Team
 */
public abstract class BasePluginDialog extends Dialog {
    
    private GridPane grid;
    private Set<String> pluginSet;
    protected ListView<String> pluginList;
    protected ButtonType actionButtonType;
    protected Label informationMessage;
    private final int ROW_HEIGHT = 24;
    protected ObservableList<String> items;

    public BasePluginDialog(Set<String> pluginSet) {
        this.pluginSet = pluginSet;
        this.pluginList = new ListView<>();
        this.informationMessage = new Label("");
        
        this.setTitle("Plugins");
        this.items =FXCollections.observableArrayList(pluginSet);
        pluginList.setItems(items);
        pluginList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        pluginList.setPrefHeight(items.size() * ROW_HEIGHT + 2);
        
        this.items.addListener(new ListChangeListener() {
        @Override
        public void onChanged(ListChangeListener.Change change) {
            pluginList.setPrefHeight(items.size() * ROW_HEIGHT + 2);
        }
        });
        
        this.grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(this.informationMessage,0,0);
        grid.add(pluginList, 0, 1);
        
        this.getDialogPane().setContent(grid);
        
        Platform.runLater(() -> pluginList.requestFocus());
    }
}
