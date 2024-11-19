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
    private Button Cancel;
    @FXML
    private TextField TextUsername;
    @FXML
    private PasswordField TextPassword;
    @FXML
    private Label tryid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LoginButton(ActionEvent event) throws IOException {
            if (!TextUsername.getText().isBlank() && !TextPassword.getText().isBlank()) {
        // Validate login credentials
        if (validateLogin("admin", TextUsername.getText())) {
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
    public boolean validateLogin(String table_name, String name) {
        return true;
    }

    @FXML
    private void CancelButton(ActionEvent event) {
            Stage stage = (Stage) Cancel.getScene().getWindow();
    System.out.println("Cancel button clicked.");
    stage.close();
    }

    @Override
    public boolean authenticate(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean logout() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean resetPassword(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Update(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
