/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.uiPresenter;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Zed Yacine
 */
public class UiStudent extends TestingMethods{
    
    private TextField firstName;
    private TextField lastName;
    private TextField phone2;
    private TextField phone1;

    private Label firstName_err;
    private Label lastName_err;
    private Label phone1_err;
    private Label phone2_err;

    public UiStudent() {
    }

    public UiStudent(TextField firstName, TextField lastName, TextField phone2, TextField phone1, Label firstName_err, Label lastName_err, Label phone1_err, Label phone2_err) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone2 = phone2;
        this.phone1 = phone1;
        this.firstName_err = firstName_err;
        this.lastName_err = lastName_err;
        this.phone1_err = phone1_err;
        this.phone2_err = phone2_err;
    }
    
    public TextField getFirstName() {
        return firstName;
    }

    public void setFirstName(TextField firstName) {
        this.firstName = firstName;
    }

    public TextField getLastName() {
        return lastName;
    }

    public void setLastName(TextField lastName) {
        this.lastName = lastName;
    }

    public TextField getPhone2() {
        return phone2;
    }

    public void setPhone2(TextField phone2) {
        this.phone2 = phone2;
    }

    public TextField getPhone1() {
        return phone1;
    }

    public void setPhone1(TextField phone1) {
        this.phone1 = phone1;
    }

    public Label getFirstName_err() {
        return firstName_err;
    }

    public void setFirstName_err(Label firstName_err) {
        this.firstName_err = firstName_err;
    }

    public Label getLastName_err() {
        return lastName_err;
    }

    public void setLastName_err(Label lastName_err) {
        this.lastName_err = lastName_err;
    }

    public Label getPhone1_err() {
        return phone1_err;
    }

    public void setPhone1_err(Label phone1_err) {
        this.phone1_err = phone1_err;
    }

    public Label getPhone2_err() {
        return phone2_err;
    }

    public void setPhone2_err(Label phone2_err) {
        this.phone2_err = phone2_err;
    }

    public static boolean UiStudentInputIsValid(UiStudent uistd) {
        boolean bFirstName = false, blastName = false, bphone1 = false, bphone2 = false;
        if (uistd.getFirstName().getText().isEmpty()) {
            uistd.getFirstName_err().setText("le nom est vide");
            uistd.getFirstName_err().setVisible(true);
        } else {
            uistd.getFirstName_err().setVisible(false);
            bFirstName = true;
        }
        if (uistd.getLastName().getText().isEmpty()) {
            uistd.getLastName_err().setText("le prenom est vide");
            uistd.getLastName_err().setVisible(true);
        } else {
            uistd.getLastName_err().setVisible(false);
            blastName = true;
        }
        if (uistd.getPhone1().getText().isEmpty()) {
            uistd.getPhone1_err().setText("le premier numiro de Telephone est vide");
            uistd.getPhone1_err().setVisible(true);
        } else {
            if (testInt(uistd.getPhone1())) {
                uistd.getPhone1_err().setVisible(false);
                bphone1 = true;
            } else {
                uistd.getPhone1_err().setText("Entrer des nombres");
                uistd.getPhone1_err().setVisible(true);
            }
        }
        if (uistd.getPhone2().getText().isEmpty()) {
            uistd.getPhone2_err().setText("le deuxième numiro de Telephone est vide");
            uistd.getPhone2_err().setVisible(true);
        } else {
            if (testInt(uistd.getPhone2())) {
                uistd.getPhone2_err().setVisible(false);
                bphone2 = true;
            } else {
                uistd.getPhone2_err().setText("Entrer des nombres");
                uistd.getPhone2_err().setVisible(true);
            }
        }

        return bFirstName && bphone1 && bphone2;
    }
}
