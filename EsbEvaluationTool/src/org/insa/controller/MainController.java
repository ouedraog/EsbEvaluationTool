/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;
import org.insa.model.Model;
import org.insa.model.Scenario;
import org.insa.model.beans.Consumer;
import org.insa.model.beans.Task;
import org.insa.model.beans.Producer;
import org.insa.model.data.Data;
import org.insa.model.parser.XmlParser;

/**
 * FXML Controller class
 *
 * @author JusteAbel
 */
public class MainController implements Initializable {

    @FXML
    private VBox root;
    /**
     * The exit button
     */
    @FXML
    private Hyperlink exitLink;
    /**
     * Menu ESB
     */
    @FXML
    private Hyperlink esbLink;
    /**
     * Menu scenario
     */
    @FXML
    private Hyperlink scenarioLink;
    /**
     * Menu Emulation
     */
    @FXML
    private Hyperlink emulationLink;
    /**
     * Menu results
     */
    @FXML
    private Hyperlink resultsLink;
    /**
     * Content wrapper
     */
    @FXML
    private VBox contentBox;

    private Model model = new Model();
    private Data data = new Data();
    private ObservableList<String> availableESB = FXCollections.observableArrayList();
    private ObservableList<Scenario> predifinedScenario = FXCollections.observableArrayList();
    private ObservableList<Producer> producers = FXCollections.observableArrayList();
    private ObservableList<Consumer> consumers = FXCollections.observableArrayList();
    private boolean isRunning = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        availableESB.setAll(data.getEsbList());
        predifinedScenario.setAll(
                data.dataSizeScenario(500, 600), 
                data.processingTimeScenario(600),
                data.requestFrequencyScenario(20));
        producers.setAll(data.getProducers());
        consumers.setAll(data.getConsumers());
        model.getScenario().setConsumers(data.getConsumers());
        model.getScenario().setProducers(data.getProducers());
        initNavigation();
    }

    /**
     * Initialize navigation links and exit button
     */
    private void initNavigation() {
        exitLink.setOnAction(event -> {
            Platform.exit();
        });
        esbLink.setOnAction(event -> {
            try {
                loadContent("EsbSelection.fxml");
                initEsbPage();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        scenarioLink.setOnAction(event -> {
            try {
                loadContent("Scenario.fxml");
                initScenarioPage();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        emulationLink.setOnAction(event -> {
            try {
                loadContent("Emulation.fxml");
                initEmulationPage();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * Load the content of the page
     */
    private void loadContent(String fileName) throws IOException {
        VBox page = FXMLLoader.load(getClass().getResource("/org/insa/view/" + fileName));
        contentBox.getChildren().setAll(page.getChildren());
    }

    private void initEsbPage() {
        ComboBox<String> esbChoiceBox = (ComboBox<String>) root.getScene().lookup("#esbChoiceBox");
        if (esbChoiceBox != null) {
            esbChoiceBox.setItems(availableESB);
            esbChoiceBox.getSelectionModel().select(0);
            esbChoiceBox.valueProperty().addListener(event->{
                model.setSelectedEsb(esbChoiceBox.getSelectionModel().getSelectedItem());
            });
        }
    }

    private void initScenarioPage() {
        Hyperlink loadScenario = (Hyperlink) root.getScene().lookup("#loadScenario");
        ComboBox<Scenario> selectPredScenario = (ComboBox<Scenario>) root.getScene().lookup("#selectPredScenario");
        Hyperlink createScenario = (Hyperlink) root.getScene().lookup("#createScenario");

        loadScenario.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (.xml)", "*.xml"));
            File chosen = chooser.showOpenDialog(null);
            Label label = (Label) root.getScene().lookup("#notify");
            if (chosen != null) {
                //parse the file
                Scenario s = new Scenario();
                try {
                    s = XmlParser.readScenarioFile(chosen);
                    model.setScenario(s);
                    //System.out.println(model.getScenario());
                    notify(label, "The scenario has been added succesfully", "success");
                    Notifications.create().title("Scenario").text("The scenario has been added succesfully").showInformation();
                } catch (Exception ex) {
                   notify(label, "The scenario description is not valid", "error");
                }
            } else {
                //Display error message
                notify(label, "File not uploaded", "warning");
            }
        });

        createScenario.setOnAction(event -> {
            try {
                loadContent("CreateScenario.fxml");
                Hyperlink create = (Hyperlink) root.getScene().lookup("#saveLink");
                Hyperlink createAndNew = (Hyperlink) root.getScene().lookup("#saveLinkAndNew");
                ComboBox<Consumer> consumerBox = (ComboBox<Consumer>)root.getScene().lookup("#consumerBox");
                ComboBox<Producer> producerBox = (ComboBox<Producer>)root.getScene().lookup("#producerBox");
                
                //init the comboboxes
                consumerBox.setItems(consumers);
                producerBox.setItems(producers);
                
                create.setOnAction(e -> {
                    try {
                        if(addLink(consumerBox, producerBox)){
                            loadContent("Scenario.fxml");
                            initScenarioPage();
                            Label label = (Label) root.getScene().lookup("#notify");
                            notify(label, "Link added successfuly", "success");  
                            
                            System.out.println(model.getScenario().printScenario());
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                //create and new
                createAndNew.setOnAction(e -> {
                    Label label = (Label) root.getScene().lookup("#notify");
                    addLink(consumerBox, producerBox);
                });
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        selectPredScenario.setItems(predifinedScenario);
        selectPredScenario.setOnAction(event->{
            Label label = (Label) root.getScene().lookup("#notify");
            Scenario scenario = selectPredScenario.getSelectionModel().getSelectedItem();
            model.setScenario(scenario);
            notify(label, "Scenario "+scenario+" is selected!", "success");
        });
    }
    /*private void loadScenarioPage() throws IOException{
        loadContent("Scenario.fxml");
        initScenarioPage();
    }
    private void loadCreateScenarioPage() throws IOException{
        loadContent("CreateScenario.fxml");
        initScenarioPage();
    }*/
    private void notify(Label errorZone, String msg, String type) {
        errorZone.setText(msg);
        errorZone.getStyleClass().clear();
        errorZone.getStyleClass().add(type);
    }
    private boolean addLink(ComboBox<Consumer> consumerBox, ComboBox<Producer> producerBox){
        //Get the form
        TextField requestSize = (TextField) root.getScene().lookup("#requestSize");
        TextField responseSize = (TextField)root.getScene().lookup("#responseSize"); 
        TextField processingTime = (TextField)root.getScene().lookup("#processingTime"); 
        TextField frequency = (TextField)root.getScene().lookup("#frequency"); 
        
        String errorMsg = null;
        //validate
        if(consumerBox.getSelectionModel().getSelectedItem() == null){
            errorMsg = "Please select a consumer";
        }
        if(producerBox.getSelectionModel().getSelectedItem() == null){
            errorMsg = "Please select a producer";
        }
        if(requestSize.getText()==null || requestSize.getText().trim().isEmpty()){
            errorMsg = "Please fill the request size field";
        }
        if(responseSize.getText()==null || responseSize.getText().isEmpty()){
            errorMsg = "Please fill the response size field";
        }
        if(processingTime.getText()==null || processingTime.getText().isEmpty()){
            errorMsg = "Please fill the processing time field";
        }
        if(frequency.getText()==null || frequency.getText().isEmpty()){
            errorMsg = "Please fill the number of requests per second field";
        }
        Label label = (Label) root.getScene().lookup("#notify");
        if(errorMsg != null){
            notify(label, errorMsg, "error");
        }
        else{
            Task p = new Task(producerBox.getSelectionModel().getSelectedItem(), 
                    consumerBox.getSelectionModel().getSelectedItem(), 
                    Integer.parseInt(requestSize.getText()), Integer.parseInt(responseSize.getText()),
                    Integer.parseInt(frequency.getText()), Integer.parseInt(processingTime.getText()));
            model.getScenario().addTask(p);
            notify(label, "Link added successfully", "success");
        }
        return errorMsg == null;
    }

    private void initEmulationPage() {
        Hyperlink emuEsb = (Hyperlink)root.getScene().lookup("#emuEsb");
        Hyperlink emuScenario = (Hyperlink)root.getScene().lookup("#emuScenario");
        Hyperlink emuStart = (Hyperlink)root.getScene().lookup("#emuStart");
        Label emuStatus = (Label)root.getScene().lookup("#emuStatus");
        BorderPane emuBorderPane = (BorderPane)root.getScene().lookup("#emuBorderPane");
        
        String selectEsb = model.getSelectedEsb();
        if(selectEsb == null){
            model.setSelectedEsb(data.getEsbList().get(0));
        }
        emuEsb.setText(model.getSelectedEsb());
        
        Scenario scenario = model.getScenario();
        if(scenario.getLinks().isEmpty()){
            model.setScenario(data.processingTimeScenario(10));
        }
        emuScenario.setText(model.getScenario().getName());
        
        emuStart.setOnAction(event->{
            isRunning = false; //TODO  change this later
            if(!isRunning){
                startEmulation();
                emuStatus.setText("Starting the scenario ...");
                ProgressIndicator progress = new ProgressIndicator();
                progress.setMaxHeight(60);
                progress.setMaxWidth(60);
                progress.setId("emuProgress");
                emuBorderPane.setCenter(progress);
                
                Notifications.create().text("Emulation has been started").title("Emulation").showInformation();
            }
            isRunning = true;
        });
    }

    private void startEmulation() {
    }
}
