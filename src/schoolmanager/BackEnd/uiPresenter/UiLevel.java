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
public class UiLevel extends TestingMethods {

    private TextField name;
    private Label name_err;


    public UiLevel() {
    }

    public UiLevel(TextField name , Label name_err) {
        this.name = name;
        this.name_err = name_err;
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public Label getName_err() {
        return name_err;
    }

    public void setName_err(Label name_err) {
        this.name_err = name_err;
    }

   
    public static boolean UiLevelInputIsValid(UiLevel uilevl) {
        boolean bname = false;
        if (uilevl.getName().getText().isEmpty()) {
            uilevl.getName_err().setText("le nom est vide");
            uilevl.getName_err().setVisible(true);
        } else {
           if (uilevl.getName().getText().length() > 20) {
                uilevl.getName_err().setText("le lieu de traviall doit compose < 20 caractere");
                uilevl.getName_err().setVisible(true);
            } else {
                uilevl.getName_err().setVisible(false);
                bname = true;
            }
        }
        return bname ;
    }

    public  void clearInputs() {
        this.name.setText("");
        this.name_err.setText("");
    }

}
