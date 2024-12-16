/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import BD.DB;
import Model.Plane;
import Model.PlaneEtat;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CLUB ACRICAIN
 */
public class PlaneController implements Initializable {

@FXML
private TableView<Plane> table;
@FXML
private TableColumn<Plane, Integer> id;
@FXML
private TableColumn<Plane, String> nom;
@FXML
private TableColumn<Plane, Float> distance;

    @FXML
    private TextField searchFld;


    
    @FXML
    private TableColumn<Plane, PlaneEtat> etat;
    public static ObservableList<Plane> planeList = FXCollections.observableArrayList(
      
    );
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private TableColumn<Plane, Integer> nbseat;
                String query=null;
    Connection conn=null;
    PreparedStatement preparedstatement=null;
    ResultSet rs=null; 

  
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize Table Columns
        conn = DB.connecter();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("type"));
        distance.setCellValueFactory(new PropertyValueFactory<>("maxDistance"));
        nbseat.setCellValueFactory(new PropertyValueFactory<>("nbSeats"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        planeList.clear();

     
    query = "SELECT id, type, max_distance, nb_seats, etat FROM public.plane;"; 
    
    try {
        preparedstatement = conn.prepareStatement(query);
        rs = preparedstatement.executeQuery();

        // Iterate over the ResultSet and populate the ObservableList
        while (rs.next()) {
            Plane plane = new Plane(
                rs.getInt("id"),
                rs.getString("type"),
                rs.getFloat("max_distance"),
                rs.getInt("nb_seats"),
                PlaneEtat.valueOf(rs.getString("etat").toUpperCase()) // assuming PlaneEtat is an enum
            );
            planeList.add(plane); 
            table.setItems(planeList);
        }

        
        

    } catch (SQLException ex) {
        Logger.getLogger(PlaneController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    


 




    // Helper methods
    private void clearFields() {

    }

    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
                
                 Parent root = FXMLLoader.load(getClass().getResource("/View/Planeadd.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void delete(ActionEvent event) {
                // Get the selected plane
        Plane selectedPlane = table.getSelectionModel().getSelectedItem();
        if (selectedPlane != null) {
            planeList.remove(selectedPlane);
            clearFields();
            showAlert("Success", "Plane deleted successfully!", AlertType.INFORMATION);
        } else {
            showAlert("Error", "No plane selected!", AlertType.ERROR);
        }
    }

    @FXML
    private void search(ActionEvent event) {
            String query = searchFld.getText().toLowerCase();

    // Réinitialiser la table si la requête est vide
    if (query.isEmpty()) {
        table.setItems(planeList);
        return;
    }

    // Utiliser un Stream pour filtrer les résultats
    ObservableList<Plane> filteredList = FXCollections.observableArrayList(
        planeList.stream()
            .filter(plane -> plane.getType().toLowerCase().contains(query) ||
                             String.valueOf(plane.getId()).contains(query))
            .toList()
    );

    // Mettre à jour la table avec les résultats filtrés
    table.setItems(filteredList);

 
    if (filteredList.isEmpty()) {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Recherche");
        alert.setHeaderText("Aucun résultat trouvé");
        alert.setContentText("Aucun avion ne correspond à la requête : \"" + query + "\"");
        alert.showAndWait();
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