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
import schoolmanager.BackEnd.Mapper.Mapping;
import schoolmanager.BackEnd.Model.*;
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
import static schoolmanager.SchoolManager.thirdStage;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class UpdatePaiementController extends PaiementController implements Initializable {


    public static int t = 0;
    private static Paiement paiement = new Paiement();
    private static Student std1 = new Student();
    private static Group grp1 = new Group();
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
    private JFXButton update;

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

    public void setInputsUpdatePaiement(Paiement pa) {
        std1 = pa.getStd();
        grp1 = pa.getGrp();
        grp1.PresentGroupe();
        paiement=pa;
        fullName.setText(std1.getFirstName() + " " + std1.getLastName());
        fullName.setEditable(false);
        ObservableList<Group> grouplist = BelongsService.getGroupOfStudent(std1);
        GroupCmb.getItems().addAll(grouplist);
        GroupCmb.getSelectionModel().select(grp1);
        OfferN.setText(grp1.getNameOffer());
        amount.setText(pa.getAmount()+ " ");
        amountP.setText(pa.getAmountC()+"");
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
    private void update(ActionEvent event) throws InterruptedException, IOException {
        Paiement paiementUpdated = Mapping.getObjectAccountFromUiStudentPaiementHistory(uistd);
        paiementUpdated.setStd(std1);
        paiementUpdated.setId(paiement.getId());
        Seance s= PaiementService.PaiementHasAseans(paiementUpdated);
        Results.Rstls resulat;
        if(s.getId()==0){
             resulat = PaiementService.updatePaiement(paiementUpdated);
        }else {
            if(s.getIdGroupe()==paiementUpdated.getGrp().getId()){
                 resulat = PaiementService.updatePaiement(paiementUpdated);
            }else{
                resulat=Results.Rstls.OBJECT_NOT_UPDATED;
            }
        }
        paiementUpdated.setTypeOfOffer(getOfferAttFromIdOffer(new Offer(paiementUpdated.getGrp().getIdOffer()),"nameType"));
        paiementUpdated.setOfferName(getOfferAttFromIdOffer(new Offer(paiementUpdated.getGrp().getIdOffer()),"offerName"));
        if (resulat.toString().equals("OBJECT_NOT_UPDATED")) {
            CommunController.alert("تعذر تعديل على عملية الدفع");
        } else {
            //editProgressBar();
            CommunController.alert("تم تعديل  عملية دفع الحالية ");
            refrechPaiement(PaiementTable1, groupC1, offerC1, datePC1, amountC1, amountRC1, nbrseanceC1, std1);
            URL url = new File("src/schoolmanager/FrontEnd/layout/PaiementSeances.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent ui = loader.load();
            PaiementSeancesController paiementSeancesController = loader.getController();
            paiementSeancesController.setInput(paiementUpdated);
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
