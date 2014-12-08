/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;
import org.insa.model.Model;
import org.insa.model.Scenario;
import org.insa.model.beans.Consumer;
import org.insa.model.beans.Distribution;
import org.insa.model.beans.KPI;
import org.insa.model.beans.Task;
import org.insa.model.beans.Producer;
import org.insa.model.data.Data;
import org.insa.model.parser.XmlParser;
import org.insa.tasks.ScenarioService;

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

    /* The pages */
    private VBox esbPage;
    private VBox scenarioPage;
    private VBox createScenarioPage;
    private VBox emulationPage;
    private VBox resultPage;
    private ComboBox<String> esbChoiceBox;
    private Hyperlink loadScenario;
    private ComboBox<Scenario> selectPredScenario;
    private Hyperlink createScenario;
    private Label labelScenario;
    private Hyperlink create;
    private Hyperlink createAndNew;
    private ComboBox<Consumer> consumerBox;
    private ComboBox<Producer> producerBox;
    private Label labelCreateScenario;

    private TextField requestSize;
    private TextField responseSize;
    private TextField processingTime;
    private TextField frequency;

    private Hyperlink emuEsb;
    private Hyperlink emuScenario;
    private Hyperlink emuStart;
    private Label emuStatus;
    private BorderPane emuBorderPane;

    private ProgressIndicator progress;

    /* ------------- result page ------------------*/
    private ComboBox<String> inputSensitivity;
    private ComboBox<String> outputSensitivity;
    private BorderPane sensitivityZone;

    private TabPane tabPane;

    /* Stats */
    private Label producerCount;
    private Label consumerCount;
    private Label taskCount;
    private Label avgReqTime;
    private Label stdevReqTime;
    private Label avgRespTime;
    private Label stdevRespTime;
    private Label avgRTT;
    private Label stdevRTT;

    private Label resultScenario;
    private BorderPane chartZone;
    private ComboBox<String> chartType;
    private ComboBox<String> kpiBox;

    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    LineChart<Number, Number> sensitivityChart = new LineChart<>(xAxis, yAxis);
    final CategoryAxis KPIxAxis = new CategoryAxis();
    final NumberAxis KPIyAxis = new NumberAxis();
    final BarChart<String, Number> kpiPerTaskChart = new BarChart<>(KPIxAxis, KPIyAxis);

    private Model model;
    private Data data = new Data();
    private ObservableList<String> availableESB = FXCollections.observableArrayList();
    private ObservableList<Scenario> predifinedScenario = FXCollections.observableArrayList();
    private ObservableList<Producer> producers = FXCollections.observableArrayList();
    private ObservableList<Consumer> consumers = FXCollections.observableArrayList();
    private ObservableList<String> inputs = FXCollections.observableArrayList();
    private ObservableList<String> outputs = FXCollections.observableArrayList();
    private ObservableList<String> chartTypeData = FXCollections.observableArrayList();

    private ScenarioService service;
    private boolean isRunning = false;

    public MainController() {

        try {
            esbPage = FXMLLoader.load(getClass().getResource("/org/insa/view/EsbSelection.fxml"));
            scenarioPage = FXMLLoader.load(getClass().getResource("/org/insa/view/Scenario.fxml"));
            createScenarioPage = FXMLLoader.load(getClass().getResource("/org/insa/view/CreateScenario.fxml"));
            emulationPage = FXMLLoader.load(getClass().getResource("/org/insa/view/Emulation.fxml"));
            resultPage = FXMLLoader.load(getClass().getResource("/org/insa/view/Result.fxml"));

            esbChoiceBox = (ComboBox<String>) esbPage.lookup("#esbChoiceBox");

            loadScenario = (Hyperlink) scenarioPage.lookup("#loadScenario");
            selectPredScenario = (ComboBox<Scenario>) scenarioPage.lookup("#selectPredScenario");
            createScenario = (Hyperlink) scenarioPage.lookup("#createScenario");
            labelScenario = (Label) scenarioPage.lookup("#notify");

            create = (Hyperlink) createScenarioPage.lookup("#saveLink");
            createAndNew = (Hyperlink) createScenarioPage.lookup("#saveLinkAndNew");
            consumerBox = (ComboBox<Consumer>) createScenarioPage.lookup("#consumerBox");
            producerBox = (ComboBox<Producer>) createScenarioPage.lookup("#producerBox");
            labelCreateScenario = (Label) createScenarioPage.lookup("#notify");

            requestSize = (TextField) createScenarioPage.lookup("#requestSize");
            responseSize = (TextField) createScenarioPage.lookup("#responseSize");
            processingTime = (TextField) createScenarioPage.lookup("#processingTime");
            frequency = (TextField) createScenarioPage.lookup("#frequency");

            emuEsb = (Hyperlink) emulationPage.lookup("#emuEsb");
            emuScenario = (Hyperlink) emulationPage.lookup("#emuScenario");
            emuStart = (Hyperlink) emulationPage.lookup("#emuStart");
            emuStatus = (Label) emulationPage.lookup("#emuStatus");
            emuBorderPane = (BorderPane) emulationPage.lookup("#emuBorderPane");

            progress = new ProgressIndicator();
            progress.setMaxHeight(60);
            progress.setMaxWidth(60);
            progress.setId("emuProgress");

            tabPane = (TabPane) resultPage.lookup("#tabPane");

            tabPane.getTabs().stream().forEach((t) -> {
                Node n = t.getContent();
                switch (t.getId()) {
                    case "summary":
                        /* Stats */
                        producerCount = (Label) n.lookup("#producerCount");
                        consumerCount = (Label) n.lookup("#consumerCount");
                        taskCount = (Label) n.lookup("#taskCount");
                        avgReqTime = (Label) n.lookup("#avgReqTime");
                        stdevReqTime = (Label) n.lookup("#stdevReqTime");
                        avgRespTime = (Label) n.lookup("#avgRespTime");
                        stdevRespTime = (Label) n.lookup("#stdevRespTime");
                        avgRTT = (Label) n.lookup("#avgRTT");
                        stdevRTT = (Label) n.lookup("#stdevRTT");

                        resultScenario = (Label) n.lookup("#resultScenario");
                        chartZone = (BorderPane) n.lookup("#chartZone");
                        chartType = (ComboBox<String>) n.lookup("#chartType");
                        kpiBox = (ComboBox<String>) n.lookup("#kpiBox");
                        break;
                    case "sensitivity":
                        inputSensitivity = (ComboBox<String>) n.lookup("#inputSensitivity");
                        outputSensitivity = (ComboBox<String>) n.lookup("#outputSensitivity");
                        sensitivityZone = (BorderPane) n.lookup("#sensitivityZone");
                        break;
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new Model();
        availableESB.setAll(data.getEsbList());
        inputs.setAll("Request size", "Response size", "Request frequency", "Processing time");
        outputs.setAll("Request time", "Response time", "RTT", "Loss");
        chartTypeData.setAll("Repartition of the losses", "Results per task");

        predifinedScenario.setAll(
                data.dataSizeScenario(500, 600),
                data.processingTimeScenario(600),
                data.requestFrequencyScenario(20));
        producers.setAll(data.getProducers());
        consumers.setAll(data.getConsumers());
        model.getScenario().setConsumers(data.getConsumers());
        model.getScenario().setProducers(data.getProducers());

        initializeEsbPage();
        initializeScenarioPage();
        initializeCreateScenario();
        initializeEmulationPage();
        initializeResultPage();

        initializeNavigation();

    }

    /**
     * Show the Esb selection page
     */
    private void showEsbPage() {
        contentBox.getChildren().setAll(esbPage);
    }

    /**
     * Show the scenario home page
     */
    private void showScenarioPage() {
        contentBox.getChildren().setAll(scenarioPage);
    }

    /**
     * Show the scenario creation page
     */
    private void showCreateScenarioPage() {
        contentBox.getChildren().setAll(createScenarioPage);
    }

    /**
     * Show the emulation page
     */
    private void showEmulationPage() {
        contentBox.getChildren().setAll(emulationPage);
        String selectEsb = model.getSelectedEsb();
        if (selectEsb == null) {
            model.setSelectedEsb(data.getEsbList().get(0));
        }
        emuEsb.setText(model.getSelectedEsb());

        Scenario scenario = model.getScenario();
        //System.out.println(scenario.getName());

        emuScenario.setText(model.getScenario().getName());
    }

    /**
     * Show the result page
     */
    private void showResultPage() {
        getDummyResults();
        
        contentBox.getChildren().setAll(resultPage);

        //display stats
        resultScenario.setText(model.getScenario().getName());
        producerCount.setText(model.getScenario().getProducers().size()+"");
        consumerCount.setText(model.getScenario().getConsumers().size()+"");
        taskCount.setText(model.getScenario().getTasks().size()+"");
        avgReqTime.setText(format(model.getScenario().getMeanRequestTime()));
        stdevReqTime.setText(format(model.getScenario().getStdevRequestTime()));
        avgRespTime.setText(format(model.getScenario().getMeanResponseTime()));
        stdevRespTime.setText(format(model.getScenario().getStdevResponseTime()));
        avgRTT.setText(format(model.getScenario().getMeanRTT()));
        stdevRTT.setText(format(model.getScenario().getStdevRTT()));

        //draw the pie chart
        drawStatsChart();

        //draw the line chart for the sensitivities chosen
        drawSensitivityChart();

    }

    /**
     * Initialize navigation links and exit button
     */
    private void initializeNavigation() {
        exitLink.setOnAction(event -> {
            Platform.exit();
        });
        esbLink.setOnAction(event -> {
            showEsbPage();
        });
        scenarioLink.setOnAction(event -> {
            showScenarioPage();
        });
        emulationLink.setOnAction(event -> {
            showEmulationPage();
        });
        createScenario.setOnAction(event -> {
            showCreateScenarioPage();
        });
        resultsLink.setOnAction(event -> {
            showResultPage();
        });

    }

    /**
     * Load the content of the page
     */
    private void loadContent(String fileName) throws IOException {
        VBox page = FXMLLoader.load(getClass().getResource("/org/insa/view/" + fileName));
        contentBox.getChildren().setAll(page.getChildren());
    }

    /**
     * Initialize the Esb selection page
     */
    private void initializeEsbPage() {
        esbChoiceBox.setItems(availableESB);
        //select the first Esb
        esbChoiceBox.getSelectionModel().select(0);
        //Handle the change of the selected ESB
        esbChoiceBox.valueProperty().addListener(event -> {
            model.setSelectedEsb(esbChoiceBox.getSelectionModel().getSelectedItem());
        });
    }

    /**
     * Initialize the scenario page
     */
    private void initializeScenarioPage() {
        //default scenario
        model.setScenario(data.processingTimeScenario(1000));

        /*
         * When the user clicks on the load scenario link then open the file chooser to
         * select an xml file describing the scenario, parse the loaded file
         * and store it in the model
         */
        loadScenario.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (.xml)", "*.xml"));
            File chosen = chooser.showOpenDialog(null);
            if (chosen != null) {
                //parse the file
                try {
                    Scenario s = XmlParser.readScenarioFile(chosen);
                    model.setScenario(s);
                    //System.out.println(model.getScenario());
                    notify(labelScenario, "The scenario has been added succesfully", "success");
                    Notifications.create().title("Scenario").text("The scenario has been added succesfully").showInformation();
                } catch (Exception ex) {
                    notify(labelScenario, "The scenario description is not valid", "error");
                }
            } else {
                //Display error message
                notify(labelScenario, "File not uploaded", "warning");
            }
        });

        /*
         * The predifined scenarii
         */
        selectPredScenario.setItems(predifinedScenario);
        selectPredScenario.setOnAction(event -> {
            Scenario scenario = selectPredScenario.getSelectionModel().getSelectedItem();
            model.setScenario(scenario);
            notify(labelScenario, "Scenario " + scenario + " is selected!", "success");
        });

        emuEsb.setOnAction(e -> {
            showEsbPage();
        });

        emuScenario.setOnAction(e -> {
            showScenarioPage();
        });
    }

    /**
     * Initialize the create scenario page
     */
    private void initializeCreateScenario() {
        //init the comboboxes
        consumerBox.setItems(consumers);
        producerBox.setItems(producers);

        /*
         * Add task and go back to the scenario page
         */
        create.setOnAction(e -> {

            if (addTask(consumerBox, producerBox)) {
                showScenarioPage();
                notify(labelScenario, "Task added successfuly", "success");
                //System.out.println(model.getScenario().printScenario());
            } else {
                notify(labelScenario, "And error occured when adding the task", "error");
            };

        });

        /*
         * Add task and offer to add a new one
         */
        createAndNew.setOnAction(e -> {
            if (addTask(consumerBox, producerBox)) {
                notify(labelCreateScenario, "Task added successfuly", "success");
            } else {
                notify(labelCreateScenario, "And error occured when adding the task", "error");
            };
        });
    }

    private void notify(Label errorZone, String msg, String type) {
        errorZone.setText(msg);
        errorZone.getStyleClass().clear();
        errorZone.getStyleClass().add(type);
    }

    private boolean addTask(ComboBox<Consumer> consumerBox, ComboBox<Producer> producerBox) {

        String errorMsg = null;
        //validate
        if (consumerBox.getSelectionModel().getSelectedItem() == null) {
            errorMsg = "Please select a consumer";
        }
        if (producerBox.getSelectionModel().getSelectedItem() == null) {
            errorMsg = "Please select a producer";
        }
        if (requestSize.getText() == null || requestSize.getText().trim().isEmpty()) {
            errorMsg = "Please fill the request size field";
        }
        if (responseSize.getText() == null || responseSize.getText().isEmpty()) {
            errorMsg = "Please fill the response size field";
        }
        if (processingTime.getText() == null || processingTime.getText().isEmpty()) {
            errorMsg = "Please fill the processing time field";
        }
        if (frequency.getText() == null || frequency.getText().isEmpty()) {
            errorMsg = "Please fill the number of requests per second field";
        }
        Task p = new Task(producerBox.getSelectionModel().getSelectedItem(),
                consumerBox.getSelectionModel().getSelectedItem(),
                Integer.parseInt(requestSize.getText()), Integer.parseInt(responseSize.getText()),
                Integer.parseInt(frequency.getText()), Integer.parseInt(processingTime.getText()));
        model.getScenario().addTask(p);
        return errorMsg == null;
    }

    private void initializeEmulationPage() {
        emuStart.setOnAction(event -> {
            isRunning = false; //TODO  change this later
            if (!isRunning) {
                emuStatus.setText("Starting the scenario ...");
                emuBorderPane.setCenter(progress);
                Notifications.create().text("Emulation has been started").title("Emulation").showInformation();

                //start the scenario service
                service = new ScenarioService(model);
                service.start();

                service.setOnSucceeded(e -> {
                    progress.setVisible(false);
                    notify(emuStatus, "Scenario has been completed successfully! "
                            + "Go to the result section to view the results", "success");
                    Notifications.create().text("Scenario has been completed successfully!\n"
                            + "Go to the result section to view the results").title("Emulation").showInformation();
                });

                service.setOnFailed(e -> {
                    progress.setVisible(false);
                    notify(emuStatus, "Failed to run the scenario! ", "error");
                    Notifications.create().text("Failed to run the scenario! ").title("Emulation").showError();
                    System.err.println(service.getException());
                });

            }
            isRunning = true;
        });

    }

    private void initializeResultPage() {
        inputSensitivity.getItems().setAll(inputs);
        inputSensitivity.getSelectionModel().select(0);
        outputSensitivity.getItems().setAll(outputs);
        outputSensitivity.getSelectionModel().select(0);

        kpiBox.getItems().setAll(outputs);
        kpiBox.getSelectionModel().select(0);

        chartType.getItems().setAll(chartTypeData);
        chartType.getSelectionModel().select(0);

        chartType.valueProperty().addListener(e -> {
            drawStatsChart();
        });

        inputSensitivity.valueProperty().addListener(e -> {
            drawSensitivityChart();
        });

        outputSensitivity.valueProperty().addListener(e -> {
            drawSensitivityChart();
        });

        kpiBox.valueProperty().addListener(e -> {
            drawStatsChart();
        });

        kpiPerTaskChart.setTitle("KPI per task");
        kpiPerTaskChart.setLegendVisible(false);
        KPIxAxis.setLabel("Task number");
        KPIyAxis.setLabel("KPI");

        sensitivityChart.setLegendVisible(false);

    }

    private void getDummyResults() {

        //for test purpose
        model.getScenario().getTasks().stream().forEach((t) -> {
            KPI kpi = t.getResult();
            kpi.setRequestTimeDist(new Distribution(0, 2, 1 + Math.random() * 20, 2));
            kpi.setResponseTimeDist(new Distribution(0, 2, 1 + Math.random() * 20, 2));
            kpi.setRttDist(new Distribution(0, 2, 1 + Math.random() * 20, 2));
            kpi.setNumberOfLoss(1);
            kpi.setNumberOfNonLoss(10);
        });
    }

    private void drawStatsChart() {
        switch (chartType.getSelectionModel().getSelectedIndex()) {
            //Pie chart showing the losses repartition
            case 0:

                PieChart pieChart = new PieChart();
                pieChart.getData().setAll(new PieChart.Data("Loss", model.getScenario().getNumberOfLoss()),
                        new PieChart.Data("Non loss", model.getScenario().getNumberOfNonLoss()));
                chartZone.setCenter(pieChart);

                kpiBox.setVisible(false);
                break;

            //bar chart showing the results per task
            case 1:

                kpiPerTaskChart.getData().setAll(getKpiPerTaskSeries().get(kpiBox.getSelectionModel().getSelectedIndex()));
                chartZone.setCenter(kpiPerTaskChart);

                kpiBox.setVisible(true);
                break;
        }
    }

    private void drawSensitivityChart() {
        String selectedInput = inputSensitivity.getSelectionModel().getSelectedItem();
        String selectedOutput = outputSensitivity.getSelectionModel().getSelectedItem();

        sensitivityChart.setTitle("Sensitivity of the " + selectedInput + " to the " + selectedOutput);

        xAxis.setLabel(selectedInput);
        yAxis.setLabel(selectedOutput);

        int input = inputSensitivity.getSelectionModel().getSelectedIndex();
        int output = outputSensitivity.getSelectionModel().getSelectedIndex();
        sensitivityChart.getData().setAll(getSensitivitySeries(input).get(output));
        sensitivityZone.setCenter(sensitivityChart);

    }

    private ArrayList<XYChart.Series> getKpiPerTaskSeries() {
        ArrayList<XYChart.Series> list = new ArrayList<>();

        XYChart.Series requestsSerie = new XYChart.Series();
        requestsSerie.setName("Request time");

        XYChart.Series responseSerie = new XYChart.Series();
        responseSerie.setName("Response time");

        XYChart.Series rttSerie = new XYChart.Series();
        rttSerie.setName("RTT time");

        XYChart.Series lossSerie = new XYChart.Series();
        lossSerie.setName("Losses");

        int i = 1;
        for (Task t : model.getScenario().getTasks()) {
            requestsSerie.getData().add(new XYChart.Data<>(i + "", t.getResult().getRequestTimeDist().getAverage()));
            responseSerie.getData().add(new XYChart.Data<>(i + "", t.getResult().getResponseTimeDist().getAverage()));
            rttSerie.getData().add(new XYChart.Data<>(i + "", t.getResult().getRttDist().getAverage()));
            lossSerie.getData().add(new XYChart.Data<>(i + "", t.getResult().getNumberOfLoss()));
            i++;
        }
        list.add(requestsSerie);
        list.add(responseSerie);
        list.add(rttSerie);
        list.add(lossSerie);

        return list;
    }

    private ArrayList<XYChart.Series<Number, Number>> getSensitivitySeries(int input) {
        ArrayList<XYChart.Series<Number, Number>> list = new ArrayList<>();

        XYChart.Series<Number, Number> requestsSerie = new XYChart.Series<>();
        requestsSerie.setName("Request time");

        XYChart.Series<Number, Number> responseSerie = new XYChart.Series<>();
        responseSerie.setName("Response time");

        XYChart.Series<Number, Number> rttSerie = new XYChart.Series<>();
        rttSerie.setName("RTT time");

        XYChart.Series<Number, Number> lossSerie = new XYChart.Series<>();
        lossSerie.setName("Losses");

        //getDummyResults();
        //Average     
        switch (input) {
            //Request size
            case 0:
                model.getScenario().getTasks().stream().forEach((t) -> {
                    int size = t.getRequestSize();
                    requestsSerie.getData().add(new XYChart.Data(size, t.getResult().getRequestTimeDist().getAverage()));
                    responseSerie.getData().add(new XYChart.Data(size, t.getResult().getResponseTimeDist().getAverage()));
                    rttSerie.getData().add(new XYChart.Data(size, t.getResult().getRttDist().getAverage()));
                    lossSerie.getData().add(new XYChart.Data(size, t.getResult().getNumberOfLoss()));
                });
                break;

            //Response time
            case 1:
                model.getScenario().getTasks().stream().forEach((t) -> {
                    int size = t.getResponseSize();
                    requestsSerie.getData().add(new XYChart.Data(size, t.getResult().getRequestTimeDist().getAverage()));
                    responseSerie.getData().add(new XYChart.Data(size, t.getResult().getResponseTimeDist().getAverage()));
                    rttSerie.getData().add(new XYChart.Data(size, t.getResult().getRttDist().getAverage()));
                    lossSerie.getData().add(new XYChart.Data(size, t.getResult().getNumberOfLoss()));
                });
                break;
            //Request frequency
            case 2:
                model.getScenario().getTasks().stream().forEach((t) -> {
                    int size = t.getRequestFrequency();
                    requestsSerie.getData().add(new XYChart.Data(size, t.getResult().getRequestTimeDist().getAverage()));
                    responseSerie.getData().add(new XYChart.Data(size, t.getResult().getResponseTimeDist().getAverage()));
                    rttSerie.getData().add(new XYChart.Data(size, t.getResult().getRttDist().getAverage()));
                    lossSerie.getData().add(new XYChart.Data(size, t.getResult().getNumberOfLoss()));
                });
                break;
            //Processing time
            case 3:
                model.getScenario().getTasks().stream().forEach((t) -> {
                    int size = t.getProcessingTime();
                    requestsSerie.getData().add(new XYChart.Data(size, t.getResult().getRequestTimeDist().getAverage()));
                    responseSerie.getData().add(new XYChart.Data(size, t.getResult().getResponseTimeDist().getAverage()));
                    rttSerie.getData().add(new XYChart.Data(size, t.getResult().getRttDist().getAverage()));
                    lossSerie.getData().add(new XYChart.Data(size, t.getResult().getNumberOfLoss()));
                });
                break;
        }

        list.add(requestsSerie);
        list.add(responseSerie);
        list.add(rttSerie);
        list.add(lossSerie);

        return list;
    }

    private String format(double nb) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(nb);
    }
}
