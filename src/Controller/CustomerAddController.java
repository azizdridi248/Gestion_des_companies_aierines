/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class CustomerAddController implements Initializable {

    @FXML
    private Button idadd;
    @FXML
    private ImageView IDBACK;
    @FXML
    private TextField idcustomer;
    @FXML
    private TextField nomcustomer;
    @FXML
    private TextField emailcustomer;
    @FXML
    private TextField telephonecustomer;
    @FXML
    private TextField cincustomer;
    @FXML
    private TextField addresscustomer;
    @FXML
    private TextField passportcustomer;
    @FXML
    private DatePicker iddata;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

@FXML
private void add(ActionEvent event) {
    try {
        // Retrieve data from input fields
        String name = nomcustomer.getText();
        String email = emailcustomer.getText();
        String phone = telephonecustomer.getText();
        LocalDate birthday = iddata.getValue(); // Directly use the LocalDate from iddata.getdata()
        String cinText = cincustomer.getText();
        String address = addresscustomer.getText();
        String passport = passportcustomer.getText();

        // Validate inputs
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            System.out.println("Name, Email, and Phone are required!");
            return;
        }
        if (!email.matches("\\w+@\\w+\\.\\w+")) {
            System.out.println("Invalid email format.");
            return;
        }
        if (birthday == null) {
            System.out.println("Birthday is required!");
            return;
        }

        // Parse CIN
        int cin = Integer.parseInt(cinText); // Ensure cinText contains valid numbers

        // Create a new Customer object
        Customer newCustomer = new Customer(
            CustomerController.customerList.size() + 1, // Assign ID based on list size
            name,
            email,
            phone,
            birthday, // Directly use the LocalDate
            cin,
            address,
            passport
        );

        // Add the new customer to the list
        CustomerController.customerList.add(newCustomer);
        System.out.println("Customer added successfully: " + newCustomer);

        // Optionally, clear input fields
        clearFields();

    } catch (NumberFormatException e) {
        System.out.println("CIN must be a number.");
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

// Helper method to clear form fields
private void clearFields() {
    idcustomer.clear();
    nomcustomer.clear();
    emailcustomer.clear();
    telephonecustomer.clear();
    cincustomer.clear();
    addresscustomer.clear();
    passportcustomer.clear();
}


    @FXML
    private void back(MouseEvent event) throws IOException {
                
                 Parent root = FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }


    
}
