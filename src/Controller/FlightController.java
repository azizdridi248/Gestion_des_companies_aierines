/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Flight;
import Model.FlightState;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author CLUB ACRICAIN
 */
public class FlightController implements Initializable {

  @FXML
    private TableView<Flight> TABLE;
    @FXML
    private TableColumn<Flight, Integer> ID;
    @FXML
    private TableColumn<Flight, String> NOM;
    @FXML
    private TableColumn<Flight, String> SOURCE;
    @FXML
    private TableColumn<Flight, String> DESTINATION;
    @FXML
    private TableColumn<Flight, Date> DATE;
    @FXML
    private TableColumn<Flight, String> ECOSEAT;
    @FXML
    private TableColumn<Flight, String> CLASSSEAT;
    @FXML
    private TableColumn<Flight, String> BUISSNESSCLASS;
    @FXML
    private TableColumn<Flight, FlightState> STATE;
    @FXML
    private TextField labelsearch;
    @FXML
    private ImageView Buttonsearch;
    @FXML
    private ImageView idadd;
    @FXML
    private ImageView idupdate;
    @FXML
    private ImageView iddelete;
    @FXML
    private ImageView idback;

    // Observable list for table data
    private ObservableList<Flight> flightList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize Table Columns
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        NOM.setCellValueFactory(new PropertyValueFactory<>("name"));
        SOURCE.setCellValueFactory(new PropertyValueFactory<>("source"));
        DESTINATION.setCellValueFactory(new PropertyValueFactory<>("destination"));
        DATE.setCellValueFactory(new PropertyValueFactory<>("date"));
        ECOSEAT.setCellValueFactory(new PropertyValueFactory<>("ecoSeat"));
        CLASSSEAT.setCellValueFactory(new PropertyValueFactory<>("classSeat"));
        BUISSNESSCLASS.setCellValueFactory(new PropertyValueFactory<>("businessSeat"));
        STATE.setCellValueFactory(new PropertyValueFactory<>("state"));

        // Custom cell factory for the DATE column to format the date
        DATE.setCellFactory(column -> new TableCell<Flight, Date>() {
            private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(format.format(item));
                }
            }
        });

        // Load initial data into the table
        loadFlights();
    }

    /**
     * Load flight data into the TableView.
     */
    private void loadFlights() {
        // Add mock data or fetch from database
        flightList.add(new Flight(1, "Flight A", "New York", "Los Angeles",
                new Date(), "50", "20", "30", FlightState.SCHEDULED));
        flightList.add(new Flight(2, "Flight B", "Paris", "London",
                new Date(), "40", "15", "25", FlightState.DELAYED));

        TABLE.setItems(flightList);
    }

    @FXML
    private void search(MouseEvent event) {
        String searchText = labelsearch.getText().toLowerCase();
        if (searchText.isEmpty()) {
            TABLE.setItems(flightList); // Reset to full list if search is empty
            return;
        }

        ObservableList<Flight> filteredList = FXCollections.observableArrayList();
        for (Flight flight : flightList) {
            if (flight.getName().toLowerCase().contains(searchText) ||
                    flight.getSource().toLowerCase().contains(searchText) ||
                    flight.getDestination().toLowerCase().contains(searchText)) {
                filteredList.add(flight);
            }
        }
        TABLE.setItems(filteredList);
    }

    @FXML
    private void add(MouseEvent event) {
        // Logic to add a new flight
        Flight newFlight = new Flight(flightList.size() + 1, "New Flight", "Source", "Destination",
                new Date(), "50", "20", "30", FlightState.SCHEDULED);
        flightList.add(newFlight);
        TABLE.refresh();
    }

    @FXML
    private void delete(MouseEvent event) {
        // Get selected flight
        Flight selectedFlight = TABLE.getSelectionModel().getSelectedItem();
        if (selectedFlight != null) {
            flightList.remove(selectedFlight);
            TABLE.refresh();
        }
    }

    @FXML
    private void back(MouseEvent event) {
        // Logic to go back or navigate to a different scene
        System.out.println("Back button clicked");
    }
}
