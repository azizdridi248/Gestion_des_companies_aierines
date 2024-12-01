package Controller;

import Model.Schedule;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScheduleController implements Initializable {

    @FXML
    private TableView<Schedule> table;
    @FXML
    private TableColumn<Schedule, Integer> IDFLIGHT;
    @FXML
    private TableColumn<Schedule, String> nomflight;
    @FXML
    private TableColumn<Schedule, String> source;
    @FXML
    private TableColumn<Schedule, String> destination;
    @FXML
    private TableColumn<Schedule, String> depturetime;
    @FXML
    private TableColumn<Schedule, String> arrivaltime;
    @FXML
    private TableColumn<Schedule, String> terminal;
    @FXML
    private TableColumn<Schedule, String> pilote;
    @FXML
    private TextField idfield;
    @FXML
    private TextField nomfield;
    @FXML
    private TextField sourcefield;
    @FXML
    private TextField destinationfield;

    @FXML
    private TextField pilotefield;
    @FXML
    private Button idupdate;
    @FXML
    private ImageView idback;

    private ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();
    @FXML
    private TextField arrivaltimefield;
    @FXML
    private TextField depturetimefield;
    @FXML
    private TextField terminalfield;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize TableView columns
        IDFLIGHT.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getId()));
        nomflight.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        source.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSource()));
        destination.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDestination()));
        depturetime.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getDepartureTime() != null ? cellData.getValue().getDepartureTime().toString() : ""));
        arrivaltime.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getArrivalTime() != null ? cellData.getValue().getArrivalTime().toString() : ""));
        terminal.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTerminal()));

        pilote.setCellValueFactory(cellData -> {
            Schedule.Crew crew = cellData.getValue().new Crew("Default Pilot");
            return new javafx.beans.property.SimpleStringProperty(crew.getPilote());
        });

        loadExampleData();
        table.setItems(scheduleList);

        // Add listener for selection changes
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateFields(newValue);
            }
        });
    }

    private void loadExampleData() {
        scheduleList.add(new Schedule(1, "Flight A", "New York", "Los Angeles",
                LocalDateTime.now(), LocalDateTime.now().plusHours(6), "T1"));
        scheduleList.add(new Schedule(2, "Flight B", "Paris", "Berlin",
                LocalDateTime.now(), LocalDateTime.now().plusHours(2), "T2"));
    }

    private void populateFields(Schedule schedule) {
        idfield.setText(String.valueOf(schedule.getId()));
        nomfield.setText(schedule.getName());
        sourcefield.setText(schedule.getSource());
        destinationfield.setText(schedule.getDestination());
        depturetimefield.setText(schedule.getDepartureTime() != null 
            ? schedule.getDepartureTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) 
            : "");
        arrivaltimefield.setText(schedule.getArrivalTime() != null 
            ? schedule.getArrivalTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) 
            : "");
        terminalfield.setText(schedule.getTerminal());
        pilotefield.setText("Default Pilot"); // Update as per logic
    }
@FXML
private void update(ActionEvent event) {
    try {
        // Parse inputs
        int id = Integer.parseInt(idfield.getText());
        String name = nomfield.getText();
        String source = sourcefield.getText();
        String destination = destinationfield.getText();
        LocalDateTime departureTime = LocalDateTime.parse(depturetimefield.getText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime arrivalTime = LocalDateTime.parse(arrivaltimefield.getText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String terminal = terminalfield.getText();
        String pilote = pilotefield.getText();

        // Verify selection
        Schedule selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            throw new IllegalStateException("No flight is selected in the table.");
        }

        // Update the selected schedule
        selected.setId(id);
        selected.setName(name);
        selected.setSource(source);
        selected.setDestination(destination);
        selected.setDepartureTime(departureTime);
        selected.setArrivalTime(arrivalTime);
        selected.setTerminal(terminal);

        // Update or create the Crew object
        if (selected.getCrew() == null) {
            selected.setCrew(selected.new Crew(pilote));
        } else {
            selected.getCrew().setPilote(pilote);
        }

        // Refresh the table
        table.refresh();
    } catch (Exception e) {
        showAlert("Error", "An unexpected error occurred. Please check your inputs.", Alert.AlertType.ERROR);
        e.printStackTrace();
    }
}



    @FXML
    private void back(MouseEvent event) throws IOException {
                         Parent root = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}