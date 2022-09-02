/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.uiPresenter;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Zed Yacine
 */
public class UiGroupe extends TestingMethods {

    private TextField name, nbrPlace;
    private Label name_err, nbrPlace_err, OfferCmb_err;
    private ComboBox OfferCmb;

    public UiGroupe() {
    }

    public UiGroupe(TextField name, TextField price, ComboBox OfferCmb) {
        this.name = name;
        this.nbrPlace = price;
        this.OfferCmb = OfferCmb;
    }

    public UiGroupe(TextField name, TextField nbrPlace, Label name_err, Label price_err, Label OfferCmb_err, ComboBox OfferCmb) {
        this.name = name;
        this.nbrPlace = nbrPlace;
        this.name_err = name_err;
        this.OfferCmb_err = OfferCmb_err;
        this.nbrPlace_err = price_err;
        this.OfferCmb = OfferCmb;
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public TextField getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(TextField nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public Label getName_err() {
        return name_err;
    }

    public void setName_err(Label name_err) {
        this.name_err = name_err;
    }

    public Label getNbrPlace_err() {
        return nbrPlace_err;
    }

    public void setNbrPlace_err(Label nbrPlace_err) {
        this.nbrPlace_err = nbrPlace_err;
    }

    public ComboBox getOfferCmb() {
        return OfferCmb;
    }

    public void setOfferCmb(ComboBox offerCmb) {
        OfferCmb = offerCmb;
    }

    public static boolean UiGroupeInputIsValid(UiGroupe uiGroupe) {
        boolean bName = false, bnbrPlace = false, bOffer = false;
        if (uiGroupe.getName().getText().isEmpty()) {
            uiGroupe.getName_err().setText("le nom est vide");
            uiGroupe.getName_err().setVisible(true);
        } else {
            uiGroupe.getName_err().setVisible(false);
            bName = true;
        }
        if (uiGroupe.getNbrPlace().getText().isEmpty()) {
            uiGroupe.getNbrPlace_err().setText("le nombre de place  est vide");
            uiGroupe.getNbrPlace_err().setVisible(true);
        } else {
            if (!testInt(uiGroupe.getNbrPlace())) {
                uiGroupe.getNbrPlace_err().setText("le nombre de place ne doit pas contenir un caractère");
                uiGroupe.getNbrPlace_err().setVisible(true);
            } else {
                uiGroupe.getNbrPlace_err().setVisible(false);
                bnbrPlace = true;
            }
        }
        if (uiGroupe.getOfferCmb().getSelectionModel().getSelectedItem() == null) {
            uiGroupe.OfferCmb_err.setText("l'Offer est vide");
            uiGroupe.OfferCmb_err.setVisible(true);
        } else {
            uiGroupe.OfferCmb_err.setVisible(false);
            bOffer = true;
        }
        return bName && bnbrPlace && bOffer;
    }

    public void clearInputs() {
        this.OfferCmb.getSelectionModel().clearSelection();
        this.name.setText("");
        this.name_err.setText("");
        this.nbrPlace.setText("");
    }

}
