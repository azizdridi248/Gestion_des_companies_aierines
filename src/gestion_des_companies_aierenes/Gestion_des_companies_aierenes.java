/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package gestion_des_companies_aierenes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gestion_des_companies_aierenes extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Adjusted path to use an absolute resource path
        Parent root = FXMLLoader.load(getClass().getResource("/View/Schedule.fxml"));

        // Set up the scene and stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login - Gestion des Companies Aériennes");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
