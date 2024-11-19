/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CLUB ACRICAIN
 */
public class MenuController implements Initializable {

    @FXML
    private Button idflight;
    @FXML
    private Button idbooking;
    @FXML
    private Button idcustomers;
    @FXML
    private Button idplane;
    @FXML
    private Button idschedule;
    @FXML
    private Button idexit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void FlightButton(ActionEvent event) throws IOException {
                  Parent root = FXMLLoader.load(getClass().getResource("/View/Flight.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void BookingButton(ActionEvent event) {
    }

    @FXML
    private void CustomerButton(ActionEvent event) {
    }

    @FXML
    private void PlaneButton(ActionEvent event) {
    }

    @FXML
    private void ScheduleButton(ActionEvent event) {
    }

    @FXML
    private void ExitButton(ActionEvent event) {
    }
    
}
