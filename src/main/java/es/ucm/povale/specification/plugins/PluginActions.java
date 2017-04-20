/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification.plugins;

import es.ucm.povale.plugin.Import;
import es.ucm.povale.plugin.PluginInfo;
import es.ucm.povale.specification.Specification;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author PoVALE Team
 */
public class PluginActions {
    
    private final Specification specification;
    private Map<String,String> availablePlugins;
    private Map<String,String> currentPlugins;
    private BasePluginDialog dialogAddPlugin;

    public PluginActions(Specification specification) {
        
        this.specification = specification;
        this.availablePlugins = new HashMap<>();
        this.currentPlugins = new HashMap<>();
        
        //esto debería cargarse con los plugins disponibles DUDA y metodorecargar
        availablePlugins.put("CorePlugin", "es.ucm.povale.plugin.CorePlugin");
        availablePlugins.put("FilesPlugin", "es.ucm.povaleFiles.FilesPlugin");
        
        this.reloadCurrentPlugins();
    }

    private void reloadCurrentPlugins() {
        this.currentPlugins = specification.getCurrentPlugins();
    }
    
    private void reloadAvailablePlugins(){
        //cargar todos los plugins
        this.reloadCurrentPlugins();
        this.currentPlugins.forEach((k,v)->this.availablePlugins.remove(k, v));
    }
    
    public Boolean showAddPluginsDialog(Specification specification){
        
        this.reloadAvailablePlugins();
        this.dialogAddPlugin = new AddPluginDialog(availablePlugins.keySet());
        
        Optional<List<String>> result = dialogAddPlugin.showAndWait();
        if(result.isPresent()){
            result.ifPresent(list -> {
                for(String s:list){
                    if(availablePlugins.containsKey(s)){
                        this.addPlugin(this.availablePlugins.get(s));
                    }
                }
            });
            return true;
        }
        else{
            return false;
        }
    }
    
    public Boolean showRemovePluginsDialog(Specification specification){
        
        this.reloadCurrentPlugins();
        this.dialogAddPlugin = new RemovePluginDialog(this.currentPlugins.keySet());
        
        Optional<List<String>> result = dialogAddPlugin.showAndWait();
        if(result.isPresent()){
            result.ifPresent(list -> {
                for(String s:list){
                    if(this.specification.getCurrentPlugins().containsKey(s)){
                        this.removePlugin(this.specification.getCurrentPlugins().get(s));
                    }
                }
            });
            return true;
        }
        else{
            return false;
        }
    }
    
    public Boolean showViewPluginsDialog(Specification specification){
        this.reloadCurrentPlugins();
        this.dialogAddPlugin = new ViewPluginDialog(this.currentPlugins.keySet());
        dialogAddPlugin.showAndWait();
        return true;
        
    }
    

    public void addPlugin(String plugin){
        
        try {
            Class<?> cl = Class.forName(plugin);
            PluginInfo pi = (PluginInfo) cl.newInstance();
                
            specification.addPlugin(plugin, pi);
            specification.addFunctions(pi.getFunctions());
            specification.addPredicates(pi.getPredicates());
            specification.addEntities(pi.getEntities());
          
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Import.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void removePlugin(String plugin){
        
        try {
            Class<?> cl = Class.forName(plugin);
            PluginInfo pi = (PluginInfo) cl.newInstance();
                
            specification.removePlugin(plugin, pi);
            specification.removeFunctions(pi.getFunctions());
            specification.removePredicates(pi.getPredicates());
            //specification.removeEntities(pi.getEntities());
          
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Import.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<Element> exportPlugins(Document document){
        
        List<Element> plugins = new LinkedList<>();
        
        if (!this.currentPlugins.isEmpty()){
            
            for(String s: this.currentPlugins.values()){
                Element plugin = document.createElement("import");
                plugin.appendChild(document.createTextNode(s));
                plugins.add(plugin);
            }
            return plugins;
        } else {
            return null;
        }    
    }

};
        
    
