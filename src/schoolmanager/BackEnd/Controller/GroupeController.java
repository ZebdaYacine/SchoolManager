/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import schoolmanager.BackEnd.Mapper.Mapping;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Offer;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Service.GroupService;
import schoolmanager.BackEnd.Service.OfferService;
import schoolmanager.BackEnd.Service.StudentService;
import schoolmanager.BackEnd.uiPresenter.UiGroupe;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static schoolmanager.SchoolManager.SecodStage;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class GroupeController implements Initializable {

    @FXML
    private JFXButton update;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField nbrPlace;
    @FXML
    private JFXComboBox<?> OfferCmb;
    @FXML
    private TableColumn<?, ?> groupeC;
    @FXML
    private TableColumn<?, ?> placeC;
    @FXML
    private TableColumn<?, ?> offerC;
    @FXML
    private TableView<?> GroupeTable;
    @FXML
    private Label name_err,OfferCmb_err,nbrPlace_err;
    private Group grp = new Group();
    private UiGroupe uigrp = new UiGroupe();


    private  ArrayList offerlist = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uigrp= new UiGroupe( name,  nbrPlace,  name_err,  nbrPlace_err,OfferCmb_err,  OfferCmb );
        for (Offer offer : OfferService.getAllOffers()){
            offerlist.add(offer.getName());
        }
        OfferCmb.getItems().addAll(offerlist);
        refrechGroup(GroupeTable, groupeC, offerC, placeC, new Group(), "");

    }

    public static void refrechGroup(TableView table, TableColumn Column1, TableColumn Column2,
                                    TableColumn Column3,  Group group, String type) {
        ObservableList<Group> pr ;
        if (type.equals("searche")) {
            //pr = StudentService.searchStudentByName(group);
            pr = GroupService.getAllGroups();

        } else {
            pr = GroupService.getAllGroups();
        }
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("nameGroup")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("nbrPlace")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("nameOffer")
        );
        
        table.setItems(pr);
    }

    @FXML
    private void add(ActionEvent event) {
        grp = Mapping.getObjectGroupeFromUiGroupe(uigrp);
        grp.PresentGroupe(grp);
    }

    @FXML
    private void update(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
    private void selectGroupe(MouseEvent event) {
        grp = (Group) GroupeTable.getSelectionModel().getSelectedItem();
        BelongsController controller = new BelongsController();
        controller.setUser(u);
        // Step 4
        loader.setController(controller);
        URL url = null;
        try {
            url = new File("src/schoolmanager/FrontEnd/layout/Belongs.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Parent uistd = FXMLLoader.load(url);
            Scene scene = new Scene(uistd);
            if (!SecodStage.isShowing()) {
                SecodStage.setScene(scene);
                SecodStage.setUserData(grp);
                SecodStage.setTitle("Inscription des étudiants");
                SecodStage.showAndWait();
            } else {
                SecodStage.setAlwaysOnTop(true);
                SecodStage.setAlwaysOnTop(false);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
