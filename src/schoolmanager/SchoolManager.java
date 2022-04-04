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
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Room;
import schoolmanager.BackEnd.Model.Student;

/**
 *
 * @author Zed Yacine
 */
public class SchoolManager extends Application {
    
    public static Stage loginstage = new Stage();
    
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
