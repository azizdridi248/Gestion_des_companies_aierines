package Controller;

import BD.DB;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
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

    private Connection conn = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic
    }

    @FXML
    private void add(ActionEvent event) {
        try {
            // Retrieve data from input fields
            int id = Integer.parseInt(idcustomer.getText().trim());
            String name = nomcustomer.getText().trim();
            String email = emailcustomer.getText().trim();
            String phone = telephonecustomer.getText().trim();
            LocalDate birthday = iddata.getValue();
            String cinText = cincustomer.getText().trim();
            String address = addresscustomer.getText().trim();
            String passport = passportcustomer.getText().trim();
            int cin = Integer.parseInt(cinText);

            // Validate inputs
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Name, Email, and Phone are required!");
                return;
            }

            if (!email.matches("\\w+@\\w+\\.\\w+")) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid email format.");
                return;
            }

            insertCustomerIntoDatabase(id, name, email, phone, birthday, cin, address, passport);
             clearFields();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Customer added successfully!");


           

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "ID and CIN must be valid numbers.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Unexpected error: " + e.getMessage());
        }
    }

    private void insertCustomerIntoDatabase(int id, String name, String email, String phone, LocalDate birthday, int cin, String address, String passport) throws SQLException {
        String insertSQL = "INSERT INTO public.customer(id, name, email, telephone, birthday, cin, address, passport_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        conn = DB.connecter(); // Ensure DB.connecter() establishes the connection correctly
        try (PreparedStatement preparedStatement = conn.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setDate(5, java.sql.Date.valueOf(birthday.toString()));
            preparedStatement.setInt(6, cin);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, passport);
            preparedStatement.executeUpdate();
        }
    }

    private void clearFields() {
        idcustomer.clear();
        nomcustomer.clear();
        emailcustomer.clear();
        telephonecustomer.clear();
        cincustomer.clear();
        addresscustomer.clear();
        passportcustomer.clear();
        iddata.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
