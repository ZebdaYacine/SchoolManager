/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.uiPresenter;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.scene.control.TextField;
import schoolmanager.BackEnd.Model.Group;

/**
 *
 * @author Zed Yacine
 */
public class UiStudentPaiement extends TestingMethods {

    private TextField amount;
    private TextField amountP;
    private JFXDatePicker dateP;
    private JFXComboBox<Group> groupCB;

    public UiStudentPaiement() {
    }

    public UiStudentPaiement(TextField amount, TextField amountP, JFXDatePicker dateP, JFXComboBox<Group> groupCB) {
        this.amount = amount;
        this.amountP = amountP;
        this.dateP = dateP;
        this.groupCB = groupCB;
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

    public TextField getAmountP() {
        return amountP;
    }

    public void setAmountP(TextField amountP) {
        this.amountP = amountP;
    }

    public JFXComboBox<Group> getGroupCB() {
        return groupCB;
    }

    public void setGroupCB(JFXComboBox<Group> groupCB) {
        this.groupCB = groupCB;
    }

    public static boolean UiStudentInputIsValid(UiStudentPaiement uistd) {
        boolean bamount = false,bamountP = false,bDateP=false,groupeB=false;
        String amountP=uistd.getAmountP().getText();
        String amount=uistd.getAmount().getText();
        String date=uistd.dateP.toString();
        Group group = uistd.groupCB.getSelectionModel().getSelectedItem();
        if (!amountP.isEmpty() || Integer.parseInt(amountP)>0) {
            bamountP = true;
        } else {
           /* uistd.getFirstName_err().setVisible(false);
            bamountP = true;*/
        }
        if (!amount.isEmpty() || Integer.parseInt(amount)>0) {
            bamount = true;
        } else {
           /* uistd.getFirstName_err().setVisible(false);
            bamount = true;*/
        }
        if (!date.isEmpty()) {
            bDateP = true;
        } else {
           /* uistd.getFirstName_err().setVisible(false);
            bDateP = true;*/
        }
        if (group!=null) {
            groupeB = true;
        } else {
           /* uistd.getFirstName_err().setVisible(false);
            groupeB = true;*/
        }

        System.out.println(bamount +" "+ bamountP +" "+ bDateP +" "+ groupeB);


        return bamount && bamountP && bDateP && groupeB;
    }

    public void clearInputs() {
        this.amount.setText("");
        this.amountP.setText("");
    }

}
