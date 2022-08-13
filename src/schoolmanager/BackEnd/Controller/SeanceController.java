/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import static schoolmanager.BackEnd.Controller.GroupeController.refrechGroup;
import schoolmanager.BackEnd.Mapper.Mapping;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Offer;
import schoolmanager.BackEnd.Model.Room;
import schoolmanager.BackEnd.Model.Seance;
import schoolmanager.BackEnd.Model.Teacher;
import schoolmanager.BackEnd.Results;
import schoolmanager.BackEnd.Service.GroupService;
import schoolmanager.BackEnd.Service.OfferService;
import schoolmanager.BackEnd.Service.RoomService;
import schoolmanager.BackEnd.Service.SeanceService;
import schoolmanager.BackEnd.Service.TeacherService;
import schoolmanager.BackEnd.uiPresenter.UiSeance;

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
    private JFXComboBox<Offer> OfferCmb;
    @FXML
    private JFXComboBox<Teacher> teacherCmb;
    @FXML
    private JFXComboBox<Room> RoomCmb;
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
    private TableView<?> studentATable;
    @FXML
    private JFXComboBox<Group> GroupCmb;
    @FXML
    private Label groupErr;
    @FXML
    private TableColumn<?, ?> GroupC;
    @FXML
    private JFXTimePicker time;

    private UiSeance uiseance = new UiSeance();
    private Seance seance = new Seance();

    @FXML
    private TableColumn<?, ?> OfferC;
    @FXML
    private TableColumn<?, ?> TeacherC;
    @FXML
    private TableColumn<?, ?> RoomC;
    @FXML
    private TableColumn<?, ?> dateC;
    @FXML
    private TableColumn<?, ?> firstNamePC;
    @FXML
    private TableColumn<?, ?> lastNamePC;
    @FXML
    private TableColumn<?, ?> phone1PC;
    @FXML
    private TableColumn<?, ?> phone2PC;
    @FXML
    private TableColumn<?, ?> sectionNamePC;
    @FXML
    private TableColumn<?, ?> firstNameAC;
    @FXML
    private TableColumn<?, ?> lastNameAC;
    @FXML
    private TableColumn<?, ?> phone1AC;
    @FXML
    private TableColumn<?, ?> phone2AC;
    @FXML
    private TableColumn<?, ?> sectionNameAC;
    @FXML
    private JFXCheckBox pTeacher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uiseance = new UiSeance(OfferErr, teacherErr, roomErr, dateErr, timeErr, groupErr, GroupCmb, OfferCmb, teacherCmb, RoomCmb, dateSeance, time, pTeacher);
        ObservableList<Offer> listOffers = OfferService.getAllOffers();
        OfferCmb.setItems(listOffers);
        ObservableList<Group> grouplist = GroupService.getAllGroups();
        GroupCmb.setItems(grouplist);
        ObservableList<Room> roomlist = RoomService.getAllRoom();
        RoomCmb.getItems().addAll(roomlist);
        ObservableList<Teacher> teacherlist = TeacherService.getAllTeachers();
        teacherCmb.getItems().addAll(teacherlist);
        /*
        studentATable.setOnMouseClicked(event -> {
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
         */
        refrechSeance(SeanceTable, OfferC, TeacherC, RoomC, GroupC, dateC, new Seance(), "");
    }

    public static void refrechSeance(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5,
            Seance group, String type) {
        ObservableList<Seance> pr;
        pr = SeanceService.getAllSeances();
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("nameOffer")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("nameTeacher")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("nameRoom")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("nameGroup")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        table.setItems(pr);
    }

    @FXML
    private void add(ActionEvent event) {
        seance = Mapping.getObjectSeanceFromUiSeance(uiseance);
        Results.Rstls r = SeanceService.addSeance(seance);
        if (r == Results.Rstls.OBJECT_NOT_INSERTED) {
            CommunController.alert(r.toString());
        } else {
            uiseance.clearInputs();
        }
        refrechSeance(SeanceTable, OfferC, TeacherC, RoomC, GroupC, dateC, new Seance(), "");
        
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
