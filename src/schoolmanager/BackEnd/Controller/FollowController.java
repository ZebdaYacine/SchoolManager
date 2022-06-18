/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class FollowController implements Initializable {

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
    private TableColumn<?, ?> sectionNameC1;
    @FXML
    private TableView<?> studentATable;

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
