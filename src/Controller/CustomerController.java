package Controller;

import BD.DB;
import Model.Booking;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
        String query=null;
    Connection conn=null;
    PreparedStatement preparedstatement=null;
    ResultSet rs=null; 
    Customer customer=null;


    // Observable list to hold customer data
    public static ObservableList<Customer> customerList = FXCollections.observableArrayList();
    @FXML
    private ImageView idback;
    @FXML
    private TableColumn<Customer, Void> actionCol;
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configure TableView columns
        try{
            conn = DB.connecter();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        idpassport.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));
        
        addButtonToTable();
        customerList.clear();
            
    query = "SELECT * FROM public.customer"; 
    preparedstatement = conn.prepareStatement(query);
    rs = preparedstatement.executeQuery();

    // Iterate over the ResultSet and populate the ObservableList
    while (rs.next()) {
    customerList.add(new Customer(
    rs.getInt("id"),
    rs.getString("name"),
    rs.getString("email"),
    rs.getString("telephone"),
    rs.getDate("birthday").toLocalDate(), // Convert SQL date to LocalDate
    rs.getInt("cin"),
    rs.getString("address"),
    rs.getString("passport_number")
));
    table.setItems(customerList);
    }

    
    


        
        
        }catch (NullPointerException e) {
        System.err.println("A component is null. Check your FXML file: " + e.getMessage());
    }       catch (SQLException ex) {      
                Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void addButtonToTable() {
    actionCol.setCellFactory(column -> new javafx.scene.control.TableCell<>() {
        private Button button = new Button("Send Email");

   {
            button.setOnAction(event -> {
                Customer selected = getTableView().getItems().get(getIndex());
                if (selected != null) {
                    // Create an anonymous subclass of Booking
                    Customer customBooking = new Customer(
                        selected.getId(),
                        selected.getName(),
                        selected.getEmail(),
                        selected.getTelephone(),
                        selected.getBirthday(),
                        selected.getCin(),
                            selected.getAddress(),
                            selected.getPassportNumber()
                            
                    ) {
                        @Override
                        public void message() {
                            try {
                                // Navigate to the Web.fxml scene
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Web.fxml"));
                                Parent root = loader.load();

                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                                System.out.println("Button clicked. Booking details printed.");
                            } catch (IOException ex) {
                                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };

                    // Call the overridden imprimer method
                    customBooking.message();
                } else {
                    System.out.println("No booking selected.");
                }
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
            }
        }
    });

    // Add the action column to the TableView if not already added
    if (!table.getColumns().contains(actionCol)) {
        table.getColumns().add(actionCol);
    }
    }
}
