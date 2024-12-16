/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import BD.DB;
import static Controller.CustomerController.customerList;
import Model.Customer;
import Model.Flight;
import Model.FlightState;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private TableColumn<Flight, LocalDate> DATE;
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
    private Button Buttonsearch;
    @FXML
    private Button idadd;
    @FXML
    private Button idupdate;
    @FXML
    private Button iddelete;
    @FXML
    private Button idback;
            String query=null;
    Connection conn=null;
    PreparedStatement preparedstatement=null;
    ResultSet rs=null; 
    


    // Observable list for table data
    public static ObservableList<Flight> flightList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize Table Columns
        loaddataflight();
 
     

    }
    public void loaddataflight(){
    
           conn = DB.connecter();
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        NOM.setCellValueFactory(new PropertyValueFactory<>("name"));
        SOURCE.setCellValueFactory(new PropertyValueFactory<>("source"));
        DESTINATION.setCellValueFactory(new PropertyValueFactory<>("destination"));
        DATE.setCellValueFactory(new PropertyValueFactory<>("date"));
        ECOSEAT.setCellValueFactory(new PropertyValueFactory<>("ecoSeat"));
        CLASSSEAT.setCellValueFactory(new PropertyValueFactory<>("classSeat"));
        BUISSNESSCLASS.setCellValueFactory(new PropertyValueFactory<>("businessSeat"));
        STATE.setCellValueFactory(new PropertyValueFactory<>("state"));
        flightList.clear();
            query = "SELECT id, name, source, destination, date, eco_seat, business_seat, class_seat, state" +
"	FROM public.flight;"; 
      try {
          preparedstatement = conn.prepareStatement(query);
      } catch (SQLException ex) {
          Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
      }
      try {
          rs = preparedstatement.executeQuery();
      } catch (SQLException ex) {
          Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
      }

      try {
          // Iterate over the ResultSet and populate the ObservableList
          while (rs.next()) {
              Flight flight;
              try {
                  flight = new Flight(
                          rs.getInt("id"),
                          rs.getString("name"),
                          rs.getString("source"),
                          rs.getString("destination"),
                          rs.getDate("date").toLocalDate(), // Convert SQL date to LocalDate
                          rs.getString("eco_seat"),
                          rs.getString("business_seat"),
                          rs.getString("class_seat"),
                          FlightState.valueOf(rs.getString("state").toUpperCase()) // Assuming FlightState is an enum
                  );
              flightList.add(flight); 
              TABLE.setItems(flightList);
              
              } catch (SQLException ex) {
                      Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              
              // Add the flight to the list
          } } catch (SQLException ex) {
          Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
      }
    
        
    }



    @FXML
    private void search(ActionEvent event) {
                    String searchText = labelsearch.getText().toLowerCase();

    if (searchText == null || searchText.isEmpty()) {
        TABLE.setItems(flightList); // Reset to full list if search is empty
        return;
    }

    ObservableList<Flight> filteredList = FXCollections.observableArrayList(
        flightList.stream()
            .filter(flight -> flight.getName().toLowerCase().contains(searchText) ||
                              flight.getSource().toLowerCase().contains(searchText) ||
                              flight.getDestination().toLowerCase().contains(searchText))
            .toList()
    );

    TABLE.setItems(filteredList);
    }

    @FXML
    private void ADD(ActionEvent event) throws IOException {
        
                 Parent root = FXMLLoader.load(getClass().getResource("/View/Flightadd.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void delete(ActionEvent event) {
              // Get selected flight
        Flight selectedFlight = TABLE.getSelectionModel().getSelectedItem();
        if (selectedFlight != null) {
            flightList.remove(selectedFlight);
            TABLE.refresh();
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        
        // Logic to go back or navigate to a different scene
         Parent root = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
