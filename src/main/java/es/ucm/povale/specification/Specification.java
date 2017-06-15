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
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PoVALE Team
 */
public class Specification{
    
    private List<AssertionRep> assertions;
    private List<VarRep> variables;
    private Map<String, PluginInfo> plugins;
    private Map<String, Function> functions;
    private Map<String, Predicate> predicates;
    private Set<String> entities;
    private ObservableList<String> observableEntities;
    private ObservableList<String> observablePredicates;
    private ObservableList<String> observableFunctions;
    
    public Specification() {
        assertions = new LinkedList();
        variables = new LinkedList();
        plugins = new HashMap<>();
        functions = new HashMap<>();
        predicates = new HashMap<>();
        entities = new HashSet<>();
        observableEntities = FXCollections.observableArrayList(entities);
        observablePredicates = FXCollections.observableArrayList(predicates.keySet());
        observableFunctions = FXCollections.observableArrayList(functions.keySet());
    }

    public ObservableList<String> getObservableEntities() {
        return observableEntities;
    }

    public ObservableList<String> getObservablePredicates() {
        return observablePredicates;
    }

    public ObservableList<String> getObservableFunctions() {
        return observableFunctions;
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
    
    public void removeVariable(VarRep variable){
        this.variables.remove(variable);
    }
    
    public void addEntities(List<Class<?>> ents) {
        for(Class<?> e: ents){
            this.entities.add(e.getSimpleName());
            if(!observableEntities.contains(e.getSimpleName())){
                observableEntities.add(e.getSimpleName());
            }
        }
    }
    
    public void removeEntities(List<Class<?>> ents) {
        for(Class<?> e: ents){
            if(this.entities.contains(e.getSimpleName())){
                this.entities.remove(e.getSimpleName());
                observableEntities.remove(e.getSimpleName());
            }
        }
    }
     
    public void addFunctions(List<Function> functions) {

        functions.stream().forEach((f) -> {
            this.functions.put(f.getName(), f);
            observableFunctions.add(f.getName());
        });
    }
    
    public void addPredicates(List<Predicate> predicates) {

        predicates.stream().forEach((p) -> {
            this.predicates.put(p.getName(), p);
            observablePredicates.add(p.getName());
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
    
    public final Map<String,String> getCurrentPlugins(){
        Map<String,String> currentPlugins = new HashMap<>();
        
        for(PluginInfo p: plugins.values()){
            int index = p.getIdPlugin().lastIndexOf(".");
            String key = p.getIdPlugin().substring(index+1,p.getIdPlugin().length());
            currentPlugins.put(key, p.getIdPlugin());
        }
        return currentPlugins;
        
    }
    
    public void addAssertion(AssertionRep assertion) {
        this.assertions.add(assertion);
    }

    public List<AssertionRep> getAssertions() {
        return assertions;
    }

    void removeAssertion(AssertionRep assertion) {
        this.assertions.remove(assertion);
    }

}
