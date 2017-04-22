package es.ucm.povale.specification;

import es.ucm.povale.specification.assertionRepresentation.AssertionRep;
import es.ucm.povale.specification.assertionRepresentation.AssertionRepFactory;
import es.ucm.povale.specification.variables.VarRep;
import es.ucm.povale.specification.plugins.PluginActions;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FXMLController implements Initializable {
    
    private Specification specification;
    private Boolean incomplete;
    private String path;
    private PluginActions pluginActions;
    
    @FXML
    private VBox variables;
    @FXML
    private VBox assertions;
    
    private Stage stage;
    
    //private String path;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.specification = new Specification();
        this.incomplete = false;
        this.pluginActions = new PluginActions(this.specification);
        this.path = null;
    }    
    
    @FXML
    private void handleOnAddVariable(ActionEvent event) {
        VarRep variable = new VarRep(this.specification.getEntities());
        this.specification.addVariable(variable);
        this.specification.addObserver(variable);
        this.variables.getChildren().add(variable.getPane());
    }
    
    @FXML
    private void handleOnAddAssertion(ActionEvent event) {
        ObservableList<String> options = 
        FXCollections.observableArrayList(
            "And",
            "Assert False",
            "Assert True",
            "Entails",
            "Equals",
            "Exist One",
            "Exist",
            "For All",
            "Not",
            "Or",
            "Predicate Application"
        );
        ComboBox assertionscb = new ComboBox(options);
        this.assertions.getChildren().add(assertionscb);
        
        assertionscb.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue observable, String oldValue, String newValue) {
                specification.addAssertion(AssertionRepFactory.createAssertionRep(assertionscb.getValue().toString(), assertions));
            }    
        });
        
        
    }
    
    @FXML
    private void handleOnViewPlugins(ActionEvent event) {
        this.pluginActions.showViewPluginsDialog(specification);
    }
    
    @FXML
    private void handleOnAddPlugins(ActionEvent event) {
        if(this.pluginActions.showAddPluginsDialog(specification)){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("Plugin(s) añadido(s) con éxito");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleOnRemovePlugins(ActionEvent event) {
        if(this.pluginActions.showRemovePluginsDialog(specification)){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("Plugin(s) eliminado(s) con éxito");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleOnSaveFile(ActionEvent event) throws TransformerException {
        if(this.path == null){
            this.path = getPath();
        }
        saveFile(this.path);
    }
    
    
    @FXML
    private void handleOnSaveAsFile(ActionEvent event) throws TransformerException{
        
            this.path = getPath();
            saveFile(this.path);
        
    }
    private void saveFile(String path)  throws TransformerConfigurationException, TransformerException{
        try {
            if(this.path == null){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("¡Se debe seleccionar donde guardar el fichero!");
                alert.showAndWait();
            }
            else{
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document document = dBuilder.newDocument();

                Element rootElement = document.createElement("spec");
                document.appendChild(rootElement);

                this.savePlugins(document, rootElement);
                this.saveVariables(document, rootElement);
                this.saveAssertions(document, rootElement);


                //cambiar o por fichero actual save si ya existe
                //o por nuevo save as?
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult result =  new StreamResult(new File(path+".xml"));
                transformer.transform(source, result);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Operacion realizada con exito");
                alert.setHeaderText(null);
                alert.setContentText("¡Fichero guardado!");
                alert.showAndWait();
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getPath(){
        String path=null;
        FileChooser fileChooser = new FileChooser();
        File selectedDirectory = 
            fileChooser.showSaveDialog(stage);

        if(selectedDirectory == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Directorio no existente, la entrega no fue enviada");
            alert.showAndWait();
        }
        else{
                path = selectedDirectory.getPath();
        }
        return path;
    }
    
    
    private void saveVariables(Document document, Element rootElement){
        for(VarRep var: this.specification.getVariables()){
                if(!var.isValid()){
                    this.incomplete = true;
                }
            }
            if(incomplete){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("¡Se deben rellenar todos los campos de las variables!");
                alert.showAndWait();
            }
            else{
                for(VarRep var: this.specification.getVariables()){
                    rootElement.appendChild(var.exportVariable(document));
                }
            }
            this.incomplete = false;
    }
    
    private void savePlugins(Document document, Element rootElement){
        List<Element> plugins = this.pluginActions.exportPlugins(document);
            if(plugins != null){
                for(Element e: plugins){
                    rootElement.appendChild(e);
                }
            }
    }
    
    private void saveAssertions(Document document, Element rootElement){
        for(AssertionRep assertion: this.specification.getAssertions()){
                if(!assertion.isValid()){
                    this.incomplete = true;
                }
            }
            if(incomplete){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("¡Se deben rellenar todos los campos de los asertos!");
                alert.showAndWait();
            }
            else{
                if(!this.specification.getAssertions().isEmpty()){
                    Element assertionElement = document.createElement("assertion");
                    rootElement.appendChild(assertionElement);

                    for(AssertionRep assertion: this.specification.getAssertions()){
                        Element assertElem = document.createElement("assert");
                        assertElem.appendChild(assertion.exportAssertion(document));
                        assertionElement.appendChild(assertElem);
                    }
                }
            }
            this.incomplete = false;
    }

    void setStage(Stage mainStage) {
        this.stage = mainStage;
    }
    
}


