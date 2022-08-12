/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class SeanceController implements Initializable {

    @FXML
    private JFXButton update;
    @FXML
    private JFXButton delete;
    @FXML
    private TableView<?> SeanceTable;
    @FXML
    private TableColumn<?, ?> nameC;
    @FXML
    private TableColumn<?, ?> TypeC;
    @FXML
    private TableColumn<?, ?> ModuleC;
    @FXML
    private TableColumn<?, ?> LevelC;
    @FXML
    private JFXComboBox<?> OfferCmb;
    @FXML
    private JFXComboBox<?> teacherCmb;
    @FXML
    private JFXComboBox<?> RoomCmb;
    @FXML
    private JFXDatePicker dateSeance;
    @FXML
    private Label OfferErr;
    @FXML
    private Label teacherErr;
    @FXML
    private Label roomErr;
    @FXML
    private Label dateErr;
    @FXML
    private Label timeErr;
    @FXML
    private TableView<?> studentPTable;
    @FXML
    private TableColumn<?, ?> firstNameC;
    @FXML
    private TableColumn<?, ?> lastNameC;
    @FXML
    private TableColumn<?, ?> phone1C;
    @FXML
    private TableColumn<?, ?> phone2C;
    @FXML
    private TableColumn<?, ?> sectionNameC;
    @FXML
    private TableView<?> studentATable;
    @FXML
    private JFXComboBox<?> GroupCmb;
    @FXML
    private Label groupErr;
    @FXML
    private TableColumn<?, ?> GroupC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) {
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
    
}
