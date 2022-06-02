/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import schoolmanager.BackEnd.Mapper.Mapping;
import schoolmanager.BackEnd.Model.Offer;
import schoolmanager.BackEnd.Results;
import schoolmanager.BackEnd.Service.OffreService;
import schoolmanager.BackEnd.Service.TypeService;
import schoolmanager.BackEnd.uiPresenter.UiOffre;

import static schoolmanager.BackEnd.Controller.LoginController.loginUser;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class OfferController implements Initializable {

    @FXML
    private TableView<?> offerTable;
    @FXML
    private TextField name, price;
    @FXML
    private Label nameErr, priceErr, typeErr, moduleErr, levelErr;
    @FXML
    private JFXComboBox typeCmb, moduleCmb, levelCmb;
    @FXML
    private TableColumn<?, ?> nameC,TypeC,ModuleC,LevelC,PriceC;
    @FXML
    private JFXButton update,delete;

    private Offer offer = new Offer();

    private UiOffre uiOffre = new UiOffre();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ObservableList<String> typesNameList = TypeService.getAllObjectName("type");
        ObservableList<String> ModulesNameList = TypeService.getAllObjectName("module");
        ObservableList<String> LevelsNameList = TypeService.getAllObjectName("level");
        this.typeCmb.setItems(typesNameList);
        this.moduleCmb.setItems(ModulesNameList);
        this.levelCmb.setItems(LevelsNameList);
        uiOffre = new UiOffre( name, price, nameErr, priceErr, typeErr, moduleErr, levelErr, typeCmb
                ,  moduleCmb,  levelCmb);
        if (loginUser.getRole().equals("simple")) {
            update.setVisible(false);
            delete.setVisible(false);
        } else {
            update.setVisible(true);
            delete.setVisible(true);
        }
    }

    public static void refrechOffre(TableView table, TableColumn Column1, TableColumn Column2,
                                    TableColumn Column3, TableColumn Column4, TableColumn Column5, Offer offer, String type)
    {
        ObservableList<Offer> pr;
        if (type.equals("searche")) {
            pr = OffreService.searchStudentByName(offer);
        } else {
            pr = OffreService.getAllOffers();
        }
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("module")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("level")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        table.setItems(pr);
    }

    @FXML
    private void add(ActionEvent event) {
        offer = Mapping.getOffreObjectFromOffreUi(uiOffre);
        Results.Rstls r = OffreService.addOffre(offer);
        if (r == Results.Rstls.OBJECT_NOT_INSERTED) {
            CommunController.alert(r.toString());
        } else {
            uiOffre.clearInputs();
        }
        refrechOffre(offerTable,nameC,TypeC,ModuleC,LevelC,PriceC, offer,"");
    }

    @FXML
    private void update(ActionEvent event) {

    }

    @FXML
    private void delete(ActionEvent event) {

    }


    @FXML
    private void selectOffer(MouseEvent event) {

    }

    @FXML
    private void search(KeyEvent event) {

    }
    
}
