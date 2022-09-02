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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import schoolmanager.BackEnd.Mapper.Mapping;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Offer;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Results;
import schoolmanager.BackEnd.Service.GroupService;
import schoolmanager.BackEnd.Service.OfferService;
import schoolmanager.BackEnd.Service.StudentService;
import schoolmanager.BackEnd.uiPresenter.UiGroupe;
import schoolmanager.BackEnd.uiPresenter.UiStudent;

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
    private JFXComboBox<String> OfferCmb;
    @FXML
    private TableColumn<?, ?> groupeC;
    @FXML
    private TableColumn<?, ?> placeC;
    @FXML
    private TableColumn<?, ?> offerC;
    @FXML
    private TableColumn<?, ?> moduleC;
    @FXML
    private TableColumn<?, ?> levelC;
    @FXML
    private TableView<Group> GroupeTable;
    @FXML
    private Label name_err, OfferCmb_err, nbrPlace_err;
    private Group grp = new Group();
    private UiGroupe uigrp = new UiGroupe();


    private final ArrayList offerlist = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uigrp = new UiGroupe(name, nbrPlace, name_err, nbrPlace_err, OfferCmb_err, OfferCmb);
        for (Offer offer : OfferService.getAllOffers()) {
            offerlist.add(offer.getName());
        }
        OfferCmb.getItems().addAll(offerlist);
        GroupeTable.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                switch (event.getClickCount()) {
                    case 1: {
                        fillInputs();
                        return;
                    }
                    case 2: {
                        showBelongsScene();
                        return;
                    }
                }
            }
        });
        GroupeTable.setRowFactory(new Callback<TableView<Group>, TableRow<Group>>() {
            @Override
            public TableRow<Group> call(TableView param) {
                return new TableRow<Group>() {

                    protected void updateItem(Group grp, boolean b) {
                        super.updateItem(grp, b);
                        if (grp != null) {
                            switch (grp.getLevel().toLowerCase()){
                                case "الثانوي" :{
                                    setStyle("-fx-background-color: #00ff7f;");
                                    break;
                                }
                                case "المتوسط" :{
                                    setStyle("-fx-background-color: #ef910e;");
                                    break;
                                }
                                    case "الابتدائي" :{
                                    setStyle("-fx-background-color: #d48eaf;");
                                    break;
                                }
                            }
                        } else {
                            setStyle("-fx-background-color: #081018;");
                        }
                    }
                };
            }
        });
        refrechGroup(GroupeTable, groupeC, placeC,offerC, moduleC,levelC, new Group(), "");
    }

    private void fillInputs(){
        grp = GroupeTable.getSelectionModel().getSelectedItem();
        grp.PresentGroupe();
        if (grp != null) {
            name.setText(grp.getNameGroup());
            nbrPlace.setText(grp.getNbrPlace() + "");
/*
            OfferCmb.getSelectionModel().select(grp.getNameGroup()+"");
*/
        }
    }

    private void showBelongsScene() {
        grp = GroupeTable.getSelectionModel().getSelectedItem();
        URL url1 = null;
        try {
            url1 = new File("src/schoolmanager/FrontEnd/layout/Belongs.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(url1);
            Parent uigrp = loader.load();
            BelongsController belongsController = loader.getController();
            belongsController.setInputs(grp);
            Scene scene = new Scene(uigrp);
            if (!SecodStage.isShowing()) {
                SecodStage.setScene(scene);
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

    public static void refrechGroup(TableView table, TableColumn Column1, TableColumn Column2,
                                    TableColumn Column3,TableColumn Column4,TableColumn Column5,
                                    Group group, String type) {
        ObservableList<Group> pr;
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
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("module")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("level")
        );
        table.setItems(pr);
    }

    @FXML
    private void add(ActionEvent event) {
        grp = Mapping.getObjectGroupeFromUiGroupe(uigrp);
        Results.Rstls r = GroupService.addGroup(grp);
        if (r == Results.Rstls.OBJECT_NOT_INSERTED) {
            CommunController.alert(r.toString());
        } else {
            uigrp.clearInputs();
        }
        refrechGroup(GroupeTable, groupeC,placeC, offerC, moduleC,levelC, new Group(), "");
    }

    @FXML
    private void update(ActionEvent event) {
        if(CommunController.confirm("sure de modifier  le group")){
            Group newGrp = Mapping.getObjectGroupeFromUiGroupe(uigrp);
            newGrp.setId((int) grp.getId());
            Results.Rstls r = GroupService.updateGroup(newGrp);
            if (r == Results.Rstls.OBJECT_NOT_UPDATED) {
                CommunController.alert(r.toString());
            } else {
                uigrp.clearInputs();
                refrechGroup(GroupeTable, groupeC, placeC, offerC,moduleC,levelC, new Group(), "");
            }
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        if(CommunController.confirm("sure de supprimer  le group")){
            Results.Rstls r = GroupService.deleteGroup(grp);
            if (r == Results.Rstls.OBJECT_NOT_DELETED) {
                CommunController.alert(r.toString());
            } else {
                uigrp.clearInputs();
                refrechGroup(GroupeTable, groupeC, placeC,offerC,moduleC,levelC, new Group(), "");
            }
        }
    }

    @FXML
    private void selectGroup(MouseEvent event) {
    }

}
