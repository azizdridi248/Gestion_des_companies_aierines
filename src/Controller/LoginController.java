/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Login;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CLUB ACRICAIN
 */
public class LoginController implements Initializable,Login {

    @FXML
    private Button Login;
    @FXML
    private TextField TextUsername;
    @FXML
    private PasswordField TextPassword;
    @FXML
    private Label tryid;
    
    HashMap<String,String> users=new HashMap();
    
    Login verif=(username,password) ->
        users.containsKey(username) && password.equals(users.get(username));
    @FXML
    private Button cancelbutton;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        users.put("admin", "123");
        users.put("1", "1");
        users.put("aziz", "123");
    }    


    @Override
    public boolean valid(String username,String Password) {
        return verif.valid(username,Password);
        
    }

    @FXML
    private void loginbutton(ActionEvent event) throws IOException {
            if (!TextUsername.getText().isBlank() && !TextPassword.getText().isBlank()) {
        // Validate login credentials
        String username = TextUsername.getText();
        String password = TextPassword.getText();

        if (valid(username, password)) {
            // Load the next scene if login is successful
            Parent root = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            tryid.setText("Invalid username or password");
        }
    } else {
        // Inform the user if text fields are empty
        tryid.setText("PLEASE FILL IN THE TEXTFIELDS");
    }
}

    @FXML
    private void cancelbutton(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Are you sure you want to cancel?");
    alert.setContentText("Any unsaved changes will be lost.");
    
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        System.out.println("Cancel button confirmed. Closing the window...");
        stage.close();
    } else {
        System.out.println("Cancel action aborted.");
    }
}
    }
    
    





    
   

