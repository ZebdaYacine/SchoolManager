/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Zed Yacine
 */
public class CommunController {

    public static void alert(String header) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information Dialog");
        a.setHeaderText(header);
        a.showAndWait();
    }
}
