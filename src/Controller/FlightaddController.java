/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import BD.DB;
import Model.Flight;
import Model.FlightState;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CLUB ACRICAIN
 */
public class FlightaddController implements Initializable {

    @FXML
    private Button addBtn;
    @FXML
    private ImageView back;
    @FXML
    private TextField idFld;
    @FXML
    private TextField nomFld;
    @FXML
    private TextField sourceFld;
    @FXML
    private TextField destinationFld;
    @FXML
    private DatePicker dateFld;
    @FXML
    private TextField ecoFld;
    @FXML
    private TextField classFld;
    @FXML
    private TextField buissnesFld;
    @FXML
    private TextField stateFld;
                String query=null;
    Connection conn=null;
    PreparedStatement preparedstatement=null;
    ResultSet rs=null; 
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
@FXML
  private void addFlight() {
        // Check if any fields are empty
        if (idFld.getText().isEmpty() || nomFld.getText().isEmpty() || sourceFld.getText().isEmpty() ||
            destinationFld.getText().isEmpty() || ecoFld.getText().isEmpty() || classFld.getText().isEmpty() ||
            buissnesFld.getText().isEmpty() || stateFld.getText().isEmpty() || dateFld.getValue() == null) {
            showError("All fields must be filled out.");
            return;
        }

        // Try to add the flight to the database
        try {
            FlightState flightState = FlightState.valueOf(stateFld.getText().toUpperCase());
            insertFlightIntoDatabase(
                Integer.parseInt(idFld.getText()),
                nomFld.getText(),
                sourceFld.getText(),
                destinationFld.getText(),
                dateFld.getValue().toString(),
                Integer.parseInt(ecoFld.getText()),
                Integer.parseInt(buissnesFld.getText()),
                Integer.parseInt(classFld.getText()),
                flightState.name()
            );
            clean(); // Clear the fields after adding
        } catch (NumberFormatException e) {
            showError("Invalid input. Please enter numeric values for ID, economy seats, business seats, and class seats.");
        } catch (IllegalArgumentException e) {
            showError("Invalid flight state entered.");
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        }
    }

private void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    

    @FXML
    private void back(MouseEvent event) throws IOException {
                 Parent root = FXMLLoader.load(getClass().getResource("/View/Flight.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void clean(){
               idFld.clear();
        nomFld.clear();
        sourceFld.clear();
        destinationFld.clear();
        dateFld.setValue(null);
        ecoFld.clear();
        classFld.clear();
        buissnesFld.clear();
        stateFld.clear();
        
    }
    
    private void insertFlightIntoDatabase(int id, String name, String source, String destination, String date,
                                          int ecoSeats, int businessSeats, int classSeats, String state) throws SQLException {
        conn = DB.connecter();
        String sql = "INSERT INTO public.flight(id, name, source, destination, date, eco_seat, business_seat, class_seat, state) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (
                
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, source);
            preparedStatement.setString(4, destination);
           preparedStatement.setDate(5, java.sql.Date.valueOf(date)); 
            preparedStatement.setInt(6, ecoSeats);
            preparedStatement.setInt(7, businessSeats);
            preparedStatement.setInt(8, classSeats);
            preparedStatement.setString(9, state);

            preparedStatement.executeUpdate();
            
        }
    }
    
}
