/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static schoolmanager.SchoolManager.studentStage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class MainController implements Initializable {

    @FXML
    private BorderPane main;
    @FXML
    private JFXButton stat;
    @FXML
    private JFXButton paiement;
    @FXML
    private JFXButton presence;

    private double offset_x1;
    private double offset_y1;
    @FXML
    private JFXButton seance;
    @FXML
    private JFXButton offres;
    @FXML
    private MenuItem user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void changePane(JFXButton btn1, JFXButton btn2, JFXButton btn3, JFXButton btn4, JFXButton btn5, String rsc) throws IOException {
        btn1.setStyle("-fx-background-color :  linear-gradient(#081018 0%, #283f54 70%);"
                + "    -fx-padding: 0");
        btn2.setStyle("-fx-background-color : transparent;");
        btn3.setStyle("-fx-background-color : transparent;");
        btn4.setStyle("-fx-background-color : transparent;");
        btn5.setStyle("-fx-background-color : transparent;");
        Parent root = FXMLLoader.load(getClass().getResource("/FrontEnd/" + rsc + ".fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void btn1(ActionEvent event) throws IOException {

    }

    @FXML
    private void btn2(ActionEvent event) throws IOException {

    }

    @FXML
    private void btn3(ActionEvent event) throws IOException {
    }

    private void btn4(ActionEvent event) throws IOException {

    }

    @FXML
    private void goToStudent(ActionEvent event) throws IOException {
        URL url = new File("src/schoolmanager/FrontEnd/layout/Student.fxml").toURI().toURL();
        swithchLayout(url, studentStage, "gestion des etudiant");
    }

    @FXML
    private void goToTeacher(ActionEvent event) throws IOException {
        URL url = new File("src/schoolmanager/FrontEnd/layout/Teacher.fxml").toURI().toURL();
        swithchLayout(url, studentStage, "gestion des professeurs");
    }

    @FXML
    private void goToLevel(ActionEvent event) throws IOException {
        URL url = new File("src/schoolmanager/FrontEnd/layout/level.fxml").toURI().toURL();
        swithchLayout(url, studentStage, "gestion des niveaux");

    }
    
     @FXML
    private void Provider(ActionEvent event) throws IOException {
        URL url = new File("src/schoolmanager/FrontEnd/layout/level.fxml").toURI().toURL();
        swithchLayout(url, studentStage, "gestion des niveaux");

    }

    @FXML
    private void About(ActionEvent event) {

    }

    @FXML
    private void Facture_Purchase(ActionEvent event) throws IOException {

    }

    @FXML
    private void Facture_Sale(ActionEvent event) throws IOException {

    }

    @FXML
    private void close(MouseEvent event) {

    }

    @FXML
    private void drag(MouseEvent event) {

    }

    @FXML
    private void press(MouseEvent event) {

    }

    @FXML
    private void User(ActionEvent event) throws IOException {

    }

    private void swithchLayout(URL url, Stage stage, String title) {
        try {
            Parent uistd = FXMLLoader.load(url);
            Scene scene = new Scene(uistd);
            if (!stage.isShowing()) {
                stage.setScene(scene);
                stage.setTitle(title);
                stage.showAndWait();
            } else {
                stage.setAlwaysOnTop(true);
                stage.setAlwaysOnTop(false);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
