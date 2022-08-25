/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.uiPresenter;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Zed Yacine
 */
public class UiStudentPaiementHistory extends TestingMethods {

    private TextField amount;
    private JFXDatePicker dateP;
    private TextField idStudent;

    public UiStudentPaiementHistory() {
    }

    public UiStudentPaiementHistory(TextField amount, JFXDatePicker dateP, TextField idStudent) {
        this.amount = amount;
        this.dateP = dateP;
        this.idStudent = idStudent;
    }

    public TextField getAmount() {
        return amount;
    }

    public void setAmount(TextField amount) {
        this.amount = amount;
    }

    public JFXDatePicker getDateP() {
        return dateP;
    }

    public void setDateP(JFXDatePicker dateP) {
        this.dateP = dateP;
    }

    public TextField getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(TextField idStudent) {
        this.idStudent = idStudent;
    }

    public static boolean UiStudentInputIsValid(UiStudentPaiementHistory uistd) {
        boolean bamount = false,bDateP=false;
        String amountP=uistd.getAmount().getText();
        if (!amountP.isEmpty() || Integer.parseInt(amountP)>0) {
            bamount = true;
        } else {
           /* uistd.getFirstName_err().setVisible(false);
            bamount = true;*/
        }

        return bamount;
    }

    public void clearInputs() {
        this.amount.setText("");
    }

}
