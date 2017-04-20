/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.povale.specification;

import es.ucm.povale.specification.variables.VarRep;
import java.util.List;
import es.ucm.povale.specification.assertionRepresentation.AssertionRep;
import es.ucm.povale.function.Function;
import es.ucm.povale.plugin.PluginInfo;
import es.ucm.povale.predicate.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

/**
 *
 * @author PoVALE Team
 */
public class Specification extends Observable{
    
    private List<AssertionRep> assertions;
    private List<VarRep> variables;
    private Map<String, PluginInfo> plugins;
    private Map<String, Function> functions;
    private Map<String, Predicate> predicates;
    private Set<String> entities;
    

    public Specification() {
        assertions = new LinkedList();
        variables = new LinkedList();
        plugins = new HashMap<>();
        functions = new HashMap<>();
        predicates = new HashMap<>();
        entities = new HashSet<>();
        
    }
    
    public List<String> getEntities(){
        return new ArrayList<>(this.entities);
    }

    public List<VarRep> getVariables() {
        return variables;
    }
    
    public void addVariable(VarRep variable) {
        this.variables.add(variable);
    }
    
    public void deleteVariable(VarRep variable){
        this.variables.remove(variable);
    }
    
    public void addEntities(List<Class<?>> ents) {
        for(Class<?> e: ents){
            this.entities.add(e.getSimpleName());
        }
    }
     
    public void addFunctions(List<Function> functions) {

        functions.stream().forEach((f) -> {
            this.functions.put(f.getName(), f);
        });
    }
    
    public void addPredicates(List<Predicate> predicates) {

        predicates.stream().forEach((p) -> {
            this.predicates.put(p.getName(), p);
        });
    }
    
    public void addPlugin(String initializerClass, PluginInfo plugin) {
        plugins.put(initializerClass, plugin);
    }

    public void removePlugin(String plugin, PluginInfo pi) {
        if(plugins.containsKey(plugin)){
            plugins.remove(plugin);
        }
    }

    public void removeFunctions(List<Function> funct){
        for(Function f: funct){
            if(this.functions.containsValue(f)){
                this.functions.remove(f.getName(), f);
            }
        }
    }

    public void removePredicates(List<Predicate> pred) {
        for(Predicate p: pred){
            if(this.predicates.containsValue(p)){
                this.predicates.remove(p.getName(), p);
            }
        }
    }
    
    public Map<String,String> getCurrentPlugins(){
        Map<String,String> currentPlugins = new HashMap<>();
        
        for(PluginInfo p: plugins.values()){
            int index = p.getIdPlugin().lastIndexOf(".");
            String key = p.getIdPlugin().substring(index+1,p.getIdPlugin().length());
            currentPlugins.put(key, p.getIdPlugin());
        }
        return currentPlugins;
    }
     
}
