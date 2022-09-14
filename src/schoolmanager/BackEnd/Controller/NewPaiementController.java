/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import schoolmanager.BackEnd.Mapper.Mapping;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Offer;
import schoolmanager.BackEnd.Model.Paiement;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Results;
import schoolmanager.BackEnd.Service.BelongsService;
import schoolmanager.BackEnd.Service.OfferService;
import schoolmanager.BackEnd.Service.PaiementService;
import schoolmanager.BackEnd.uiPresenter.UiStudentPaiement;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static schoolmanager.BackEnd.Service.OfferService.getOfferAttFromIdOffer;
import static schoolmanager.SchoolManager.SecodStage;
import static schoolmanager.SchoolManager.thirdStage;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class NewPaiementController extends PaiementController implements Initializable {

    public static int t = 0;
    private static Paiement paiement = new Paiement();
    private static Student std1 = new Student();
    @FXML
    private TextField fullName;
    @FXML
    private JFXDatePicker dateP;
    @FXML
    private TextField amount;
    @FXML
    private TextField amountP;
    @FXML
    private TextField OfferN;
    @FXML
    private JFXComboBox<Group> GroupCmb;
    private UiStudentPaiement uistd = new UiStudentPaiement();

    @FXML
    private JFXButton add;
    @FXML
    private ProgressIndicator prg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uistd = new UiStudentPaiement(amount, amountP, dateP, GroupCmb);
        prg.setProgress(0);
        GroupCmb.setOnAction(event -> {
            Group grp = GroupCmb.getSelectionModel().getSelectedItem();
            if (grp != null) {
                ObservableList<Offer> offer = OfferService.getOfferbyid(new Offer(grp.getIdOffer()));
                OfferN.setText(offer.get(0).getName());
                amount.setText(offer.get(0).getPrice() + "");
            }
        });
    }

    @FXML
    private void onchangecontent(ActionEvent event) {

    }

    public void setInputsNewPaiement(Student std) {
        fullName.setText(std.getFirstName() + " " + std.getLastName());
        fullName.setEditable(false);
        ObservableList<Group> grouplist = BelongsService.getGroupOfStudent(std);
        GroupCmb.getItems().addAll(grouplist);
        std1 = std;
    }

    private void editProgressBar() {
        prg.setVisible(true);
        new Thread(() -> {
            double value = prg.getProgress();
            while (value <= 1) {
                value += 0.1;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                prg.setProgress(value);
            }
            prg.setProgress(value);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prg.setVisible(false);
            prg.setProgress(0);
        }).start();
    }

    @FXML
    private void add(ActionEvent event) throws InterruptedException, IOException {
        paiement = Mapping.getObjectAccountFromUiStudentPaiementHistory(uistd);
        paiement.setTypeOfOffer(OfferService.
                getOfferAttFromIdOffer(new Offer(paiement.getGrp().getIdOffer()), "nameType"));
        paiement.setNbrSeance(0);
        paiement.setStd(std1);
        long lastID = PaiementService.addPaiement(paiement);
        paiement.setId(lastID);
        paiement.setTypeOfOffer(getOfferAttFromIdOffer(new Offer(paiement.getGrp().getIdOffer()), "nameType"));
        paiement.setOfferName(getOfferAttFromIdOffer(new Offer(paiement.getGrp().getIdOffer()), "offerName"));
        if (lastID == 0) {
            CommunController.alert(Results.Rstls.OBJECT_NOT_INSERTED.toString());
        } else {
            //editProgressBar();
            CommunController.alert("تم اضافة عملية دفع جديدة");
            refrechPaiement(PaiementTable1, groupC1, offerC1, datePC1, amountC1, amountRC1, nbrseanceC1, std1);
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("schoolmanager/FrontEnd/layout/PaiementSeances.fxml"));
            Parent ui = loader.load();
            PaiementSeancesController paiementSeancesController = loader.getController();
            paiementSeancesController.setInput(paiement);
            Scene scene = new Scene(ui);
            if (!thirdStage.isShowing()) {
                thirdStage.setScene(scene);
                thirdStage.setTitle("جدول الحصص ");
                thirdStage.showAndWait();
            } else {
                thirdStage.setAlwaysOnTop(true);
                thirdStage.setAlwaysOnTop(false);
            }
        }
    }

    @FXML
    private void selectStudent(MouseEvent event) {

    }

}
