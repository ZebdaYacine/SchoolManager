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
import javafx.stage.Stage;
import schoolmanager.BackEnd.Controller.model.Room;
import schoolmanager.BackEnd.Controller.model.Type;
import schoolmanager.BackEnd.Controller.model.Student;
import schoolmanager.BackEnd.Controller.model.Teacher;

/**
 *
 * @author Zed Yacine
 */
public class SchoolManager extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FrontEnd/layout/RootFxml.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        Room room = new Room(0, "room 1");
        System.err.println(room.getRoomName());
    }

}
