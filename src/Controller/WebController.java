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
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CLUB ACRICAIN
 */
public class WebController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private TextField TextField;
    @FXML
    private Button LoadButton;
    
    private WebEngine engine;
    @FXML
    private Button LoadButton1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        engine = webView.getEngine();
        
    }

@FXML
private void loadPage(ActionEvent event) {
    if (TextField != null && TextField.getText() != null && !TextField.getText().trim().isEmpty()) {
        String url = TextField.getText().trim(); // Remove extra spaces
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url; // Add "https://" if not included
        }
        engine.load(url); // Load the cleaned URL
    } else {
        System.out.println("TextField is empty or not initialized.");
    }
}

    @FXML
    private void back(ActionEvent event) throws IOException {
                        Parent root = FXMLLoader.load(getClass().getResource("/View/Booking.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

