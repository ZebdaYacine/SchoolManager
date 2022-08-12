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
import static schoolmanager.SchoolManager.SecodStage;

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
    private JFXButton presence;

    private double offset_x1;
    private double offset_y1;
    @FXML
    private JFXButton seance;
    @FXML
    private JFXButton offres;
    @FXML
    private JFXButton groupes;
    @FXML
    private MenuItem user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//<<<<<<< HEAD
//        stat.setStyle("-fx-background-color : #dfe3ee;"
//                + "-fx-border-color:  #0099cc #0099cc #dfe3ee #0099cc;"
//=======
        stat.setStyle("-fx-background-color : white;"
                + "-fx-border-color:  #0099cc #0099cc white #0099cc;"
//>>>>>>> 28fa90b4c34870dedb390330eecf3117addc63c5
                + "-fx-padding: 0");
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/schoolmanager/FrontEnd/layout/stat.fxml"));
            main.setCenter(null);
            if (root != null) {
                main.setCenter(root);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changePane(JFXButton btn1, JFXButton btn2, JFXButton btn3,
                           JFXButton btn4,JFXButton btn5, String rsc) throws IOException {
//<<<<<<< HEAD
//        btn1.setStyle("-fx-background-color :  linear-gradient(#081018 0%, #283f54 70%);"
//                + "-fx-border-color:  #0099cc #0099cc #dfe3ee #0099cc;"
//=======
        btn1.setStyle("-fx-background-color :  white;"
                + "-fx-border-color:  #0099cc #0099cc white #0099cc;"
//>>>>>>> 28fa90b4c34870dedb390330eecf3117addc63c5
                + "    -fx-padding: 0");
        btn2.setStyle("-fx-background-color : transparent;");
        btn3.setStyle("-fx-background-color : transparent;");
        btn4.setStyle("-fx-background-color : transparent;");
        btn5.setStyle("-fx-background-color : transparent;");
        Parent root = FXMLLoader.load(getClass().getResource("/schoolmanager/FrontEnd/layout/" + rsc + ".fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }else{
            System.out.println(root);
        }
    }


    private void btn4(ActionEvent event) throws IOException {

    }

    @FXML
    private void goToStudent(ActionEvent event) throws IOException {
        URL url = new File("src/schoolmanager/FrontEnd/layout/Student.fxml").toURI().toURL();
        swithchLayout(url, SecodStage, "gestion des etudiant");
    }

    @FXML
    private void goToTeacher(ActionEvent event) throws IOException {
        URL url = new File("src/schoolmanager/FrontEnd/layout/Teacher.fxml").toURI().toURL();
        swithchLayout(url, SecodStage, "gestion des professeurs");
    }

    @FXML
    private void goToLevel(ActionEvent event) throws IOException {
        URL url = new File("src/schoolmanager/FrontEnd/layout/level.fxml").toURI().toURL();
        swithchLayout(url, SecodStage, "gestion des niveaux");

    }

    @FXML
    private void goToModule(ActionEvent event) throws IOException {
        URL url = new File("src/schoolmanager/FrontEnd/layout/module.fxml").toURI().toURL();
        swithchLayout(url, SecodStage, "gestion des modules");
    }

    @FXML
    private void goToType(ActionEvent event) throws IOException {
        URL url = new File("src/schoolmanager/FrontEnd/layout/Type.fxml").toURI().toURL();
        swithchLayout(url, SecodStage, "gestion des types");

    }

    @FXML
    private void goToSections(ActionEvent event) throws IOException {
        URL url = new File("src/schoolmanager/FrontEnd/layout/Section.fxml").toURI().toURL();
        swithchLayout(url, SecodStage, "gestion des types");

    }

    @FXML
    private void goToRooms(ActionEvent event) throws IOException {
        URL url = new File("src/schoolmanager/FrontEnd/layout/Room.fxml").toURI().toURL();
        swithchLayout(url, SecodStage, "gestion des salles");

    }



    @FXML
    private void About(ActionEvent event) {

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

    @FXML
    private void stat(ActionEvent event) throws IOException {
        String rsc="stat";
        changePane(stat,offres,presence,seance,groupes,rsc);
    }


    @FXML
    private void presence(ActionEvent event) throws IOException {
        String rsc="follow";
        changePane(presence,stat,seance,offres,groupes,rsc);
    }

    @FXML
    private void seance(ActionEvent event) throws IOException {
        String rsc="seance";
        changePane(seance,stat,presence,offres,groupes,rsc);
    }

    @FXML
    private void offer(ActionEvent event) throws IOException {
        String rsc="Offer";
        changePane(offres,groupes,stat,presence,seance,rsc);
    }

    @FXML
    private void groupes(ActionEvent event) throws IOException {
        String rsc="Groupes";
        changePane(groupes,offres,stat,presence,seance,rsc);
    }

}
