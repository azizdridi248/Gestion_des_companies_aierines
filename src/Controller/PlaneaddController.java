/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Plane;
import Model.PlaneEtat;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CLUB ACRICAIN
 */
public class PlaneaddController implements Initializable {

    @FXML
    private Button addBtn;
    @FXML
    private ImageView back;
    @FXML
    private TextField idFld;
    @FXML
    private TextField nomFld;
    @FXML
    private TextField ecoFld;
    @FXML
    private TextField classFld;
    @FXML
    private TextField buissnesFld;
    @FXML
    private TextField etatFld;
    @FXML
    private TextField distanceFld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void back(MouseEvent event) throws IOException {
                 Parent root = FXMLLoader.load(getClass().getResource("/View/Plane.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    // Méthode pour ajouter un avion
    @FXML
    private void addPlane(ActionEvent event) {
        // Vérification que tous les champs sont remplis
        if (idFld.getText().isEmpty() || nomFld.getText().isEmpty() || distanceFld.getText().isEmpty() ||
            ecoFld.getText().isEmpty() || classFld.getText().isEmpty() || buissnesFld.getText().isEmpty() ||
            etatFld.getText().isEmpty()) {
            showError("Tous les champs doivent être remplis.");
            return;
        }

        try {
            // Récupération des données des champs
            int id = Integer.parseInt(idFld.getText());
            String type = nomFld.getText();
            float distance = Float.parseFloat(distanceFld.getText());
            int ecoSeats = Integer.parseInt(ecoFld.getText());
            int businessSeats = Integer.parseInt(buissnesFld.getText());
            int firstClassSeats = Integer.parseInt(classFld.getText());
            PlaneEtat etat = PlaneEtat.valueOf(etatFld.getText().toUpperCase());

            // Créer et ajouter l'avion à la liste
            Plane newPlane = new Plane(id, type, distance, firstClassSeats, businessSeats, ecoSeats, etat);
            PlaneController.planeList.add(newPlane);

            // Nettoyer les champs après ajout
            clearFields();
            showSuccess("Avion ajouté avec succès !");

        } catch (NumberFormatException e) {
            showError("L'ID, la distance et le nombre de sièges doivent être des nombres valides.");
        } catch (IllegalArgumentException e) {
            showError("L'état de l'avion est invalide. Utilisez 'BON_ETAT' ou 'EN_PANNE'.");
        }
    }

    // Méthode pour afficher un message d'erreur
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Méthode pour afficher un message de succès
    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Méthode pour nettoyer les champs de texte après l'ajout
    private void clearFields() {
        idFld.clear();
        nomFld.clear();
        distanceFld.clear();
        ecoFld.clear();
        classFld.clear();
        buissnesFld.clear();
        etatFld.clear();
    }
}