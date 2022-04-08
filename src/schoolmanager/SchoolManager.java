/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import schoolmanager.BackEnd.DataBaseConnection;

/**
 *
 * @author Zed Yacine
 */
public class SchoolManager extends Application {

    public static Stage loginStage = new Stage();
    public static Stage studentStage = new Stage();
    public static Stage teacherStage = new Stage();
    public static Stage levelstage = new Stage();

    public static final Alert alertUpdate = Template(Alert.AlertType.CONFIRMATION,
             "Modifier", "Modifier confirmation", "Vérifier avant de modifier");
    public static final Alert alertDelete = Template(Alert.AlertType.CONFIRMATION,
            "suprimer", "suprimer confirmation", "Vérifier avant de supprimer");

    public static Alert Template(Alert.AlertType type, String titel, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(titel);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FrontEnd/layout/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataBaseConnection.Connect();
        new Thread(() -> {
            while (DataBaseConnection.con == null) {

            }
            launch(args);
        }).start();

    }

}
