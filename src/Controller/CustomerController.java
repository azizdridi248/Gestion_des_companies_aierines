package Controller;

import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class CustomerController implements Initializable {
        @FXML
    private Button idadd;
    @FXML
    private Button iddelete;
    @FXML
    private Button idsearch;


    @FXML
    private TextField searchFld;
    @FXML
    private TableView<Customer> table; // Properly annotated TableView
    @FXML
    private TableColumn<Customer, Integer> id;
    @FXML
    private TableColumn<Customer, Integer> cin;
    @FXML
    private TableColumn<Customer, String> nom;
    @FXML
    private TableColumn<Customer, String> telephone;
    @FXML
    private TableColumn<Customer, LocalDate> birthday;
    @FXML
    private TableColumn<Customer, String> email;
    @FXML
    private TableColumn<Customer, String> address;
    @FXML
    private TableColumn<Customer, String> idpassport;


    // Observable list to hold customer data
    public static ObservableList<Customer> customerList = FXCollections.observableArrayList(
        new Customer(1, "Alice", "alice@example.com", "123456789", LocalDate.of(1990, 1, 15), 123456, "123 Main St", "P987654"),
        new Customer(2, "Bob", "bob@example.com", "987654321", LocalDate.of(1985, 5, 10), 654321, "456 Elm St", "P123456")
    );
    @FXML
    private ImageView idback;
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configure TableView columns
        try{
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        idpassport.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));


        // Bind data to TableView
        table.setItems(customerList);
        }catch (NullPointerException e) {
        System.err.println("A component is null. Check your FXML file: " + e.getMessage());
    }
    }

 




    // Utility method to display alerts
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void idsearch(ActionEvent event) {
              // Get the search query
        String searchQuery = searchFld.getText() != null ? searchFld.getText().toLowerCase() : "";

        // Reset the table if the search field is empty
        if (searchQuery.isEmpty()) {
            table.setItems(customerList);
            return;
        }

        // Filter the customers using Stream API
        ObservableList<Customer> filteredList = FXCollections.observableArrayList(
            customerList.stream()
                .filter(customer -> customer.getName().toLowerCase().contains(searchQuery) ||
                                    customer.getEmail().toLowerCase().contains(searchQuery) ||
                                    customer.getTelephone().contains(searchQuery))
                .toList()
        );

        // Update the TableView with filtered data
        table.setItems(filteredList);

        // Show an alert if no results are found
        if (filteredList.isEmpty()) {
            showAlert("Recherche", "Aucun résultat trouvé", "Aucun client ne correspond à la recherche : \"" + searchQuery + "\".");
        }
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
                // Add a new customer
        
                 Parent root = FXMLLoader.load(getClass().getResource("/View/CustomerAdd.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }

    @FXML
    private void delete(ActionEvent event) {
               Customer selectedCustomer = table.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            customerList.remove(selectedCustomer);
            table.refresh(); // Refresh TableView after deletion
        } else {
            // Show an alert if no customer is selected
            showAlert("Suppression", "Aucun client sélectionné", "Veuillez sélectionner un client pour le supprimer.");
        }
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
                         Parent root = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
