/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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
}

