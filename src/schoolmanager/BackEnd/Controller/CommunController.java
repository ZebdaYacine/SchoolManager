/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import schoolmanager.BackEnd.Model.Paiement;

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

    public static boolean confirm(String header) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Information Dialog");
        a.setContentText(header);
        final Optional<ButtonType> result = a.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static float getAmountSeance(Paiement p) {
        float amountSeance = 0f;
        switch (p.getTypeOfOffer().toLowerCase()) {
            case "simple": {
                amountSeance = p.getAmount() / 4;
                break;
            }
            case "double": {
                amountSeance = p.getAmount() / 8;
                break;
            }
            case "vip": {
                amountSeance = p.getAmount() / 2;
                break;
            }
        }
        return amountSeance;
    }

    public static int getnbrSeanceInOffer(Paiement p) {
        int nbr = 0;
        switch (p.getTypeOfOffer().toLowerCase()) {
            case "simple": {
                nbr = 4;
                break;
            }
            case "double": {
                nbr =  8;
                break;
            }
            case "vip": {
                nbr = 2;
                break;
            }
        }
        return nbr;
    }
}
