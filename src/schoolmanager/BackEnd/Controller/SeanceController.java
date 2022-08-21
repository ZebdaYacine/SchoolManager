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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import schoolmanager.BackEnd.Mapper.Mapping;
import schoolmanager.BackEnd.Model.Follow;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Offer;
import schoolmanager.BackEnd.Model.Room;
import schoolmanager.BackEnd.Model.Seance;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Model.Teacher;
import schoolmanager.BackEnd.Results;
import schoolmanager.BackEnd.Service.FollowService;
import schoolmanager.BackEnd.Service.GroupService;
import schoolmanager.BackEnd.Service.OfferService;
import schoolmanager.BackEnd.Service.RoomService;
import schoolmanager.BackEnd.Service.SeanceService;
import schoolmanager.BackEnd.Service.StudentService;
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
    private Seance seanceSelect = new Seance();
    private final ContextMenu ApsentMenu = new ContextMenu();
    private final ContextMenu PresentMenu = new ContextMenu();
    private final MenuItem delItem = new MenuItem("غائب");
    private final MenuItem addPayItem = new MenuItem("حاضر و دفع ثمن الحصة");
    private final MenuItem addNoPayItem = new MenuItem("حاضر ولم يدفع ثمن الحصة");
    private final MenuItem showProfile = new MenuItem("عرض ملف التلميذ");
    private final MenuItem showProfile1 = new MenuItem("عرض ملف التلميذ");

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
    @FXML
    private TableColumn<?, ?> pTeacherC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ApsentMenu.getItems().addAll(addPayItem, addNoPayItem, showProfile1);
        PresentMenu.getItems().addAll(delItem, showProfile);
        uiseance = new UiSeance(OfferErr, teacherErr, roomErr, dateErr, timeErr, groupErr, GroupCmb, OfferCmb, teacherCmb, RoomCmb, dateSeance, time, pTeacher);
        ObservableList<Offer> listOffers = OfferService.getAllOffers();
        OfferCmb.setItems(listOffers);

        ObservableList<Room> roomlist = RoomService.getAllRoom();
        RoomCmb.getItems().addAll(roomlist);
        ObservableList<Teacher> teacherlist = TeacherService.getAllTeachers();
        teacherCmb.getItems().addAll(teacherlist);
        refrechSeance(SeanceTable, OfferC, TeacherC, RoomC, GroupC, dateC, pTeacherC, new Seance(), "");
    }

    public void refrechSeance(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5, TableColumn Column6,
            Seance seance, String type) {
        ObservableList<Seance> pr = SeanceService.getAllSeances();
        if(pr.size()>0){
            seanceSelect = pr.get(0);
            System.err.println(pr.get(0).getpTeacher());
            refrechStudents(studentATable, firstNameAC, lastNameAC, phone1AC, phone2AC, sectionNameAC, pr.get(0), "apsent");
            refrechStudents(studentPTable, firstNamePC, lastNamePC, phone1PC, phone2PC, sectionNamePC, pr.get(0), "present");
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
            Column6.setCellValueFactory(
                    new PropertyValueFactory<>("pTeacher")
            );
            table.setItems(pr);
        }
    }

    public static void refrechStudents(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5,
            Seance seance, String type) {
        ObservableList<Student> pr = StudentService.getAllStudentsFollow(seance, type);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("phone1")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("phone2")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("sectionName")
        );
        table.setItems(pr);
    }

    @FXML
    private void add(ActionEvent event) {
        seanceSelect = Mapping.getObjectSeanceFromUiSeance(uiseance);
        Results.Rstls r = SeanceService.addSeance(seanceSelect);
        if (r == Results.Rstls.OBJECT_NOT_INSERTED) {
            CommunController.alert(r.toString());
        } else {
            uiseance.clearInputs();
        }
        refrechSeance(SeanceTable, OfferC, TeacherC, RoomC, GroupC, dateC, pTeacherC, new Seance(), "");
    }

    @FXML
    private void update(ActionEvent event) {
        if (CommunController.confirm("sure de modifier  le seance")) {
            Seance newSnc = Mapping.getObjectSeanceFromUiSeance(uiseance);
            newSnc.setId(seanceSelect.getId());
            Results.Rstls r = SeanceService.updateSeance(newSnc);
            if (r == Results.Rstls.OBJECT_NOT_UPDATED) {
                CommunController.alert(r.toString());
            } else {
                uiseance.clearInputs();
            }
            refrechSeance(SeanceTable, OfferC, TeacherC, RoomC, GroupC, dateC, pTeacherC, new Seance(), "");
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        if (CommunController.confirm("sure de supprimer  le seance")) {
            Results.Rstls r = SeanceService.deleteSeance(seanceSelect);
            if (r == Results.Rstls.OBJECT_NOT_DELETED) {
                CommunController.alert(r.toString());
            } else {
                uiseance.clearInputs();
            }
            refrechSeance(SeanceTable, OfferC, TeacherC, RoomC, GroupC, dateC, pTeacherC, new Seance(), "");
        }
    }

    @FXML
    private void onchangecontent(ActionEvent event) {
        Offer off = OfferCmb.getSelectionModel().getSelectedItem();
        if (off != null) {
            GroupCmb.getSelectionModel().select(null);
            ObservableList<Group> grouplist = GroupService.getAllGroupsOnOffer(off);
            GroupCmb.setItems(grouplist);
        }

    }

    @FXML
    private void selectSeance(MouseEvent event) {
        seanceSelect = (Seance) SeanceTable.getSelectionModel().getSelectedItem();
        if (seanceSelect != null) {
            refrechStudents(studentATable, firstNameAC, lastNameAC, phone1AC, phone2AC, sectionNameAC, seanceSelect, "apsent");
            refrechStudents(studentPTable, firstNamePC, lastNamePC, phone1PC, phone2PC, sectionNamePC, seanceSelect, "present");
            Offer o = new Offer(seanceSelect.getIdOffer());
            ObservableList<Offer> listO = OfferService.getOfferbyid(o);
            System.err.println(listO.get(0).getName());
            OfferCmb.getSelectionModel().select(listO.get(0));
            Teacher t = new Teacher(seanceSelect.getIdTeacher());
            ObservableList<Teacher> listT = TeacherService.searchTeacherById(t);
            System.err.println(listT.get(0).getFirstName() + " " + listT.get(0).getLastName());
            teacherCmb.getSelectionModel().select(listT.get(0));
            Room r = new Room(seanceSelect.getIdRoom());
            ObservableList<Room> listR = RoomService.searchRoomById(r);
            System.err.println(listR.get(0).getName());
            RoomCmb.getSelectionModel().select(listR.get(0));
            Group g = new Group(seanceSelect.getIdGroupe());
            ObservableList<Group> listg = GroupService.getGroupbyId(g);
            System.err.println(listg.get(0).getNameGroup());
            GroupCmb.getSelectionModel().select(listg.get(0));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dattime = LocalDateTime.parse(seanceSelect.getDate(), formatter);
            dateSeance.setValue(dattime.toLocalDate());
            time.setValue(dattime.toLocalTime());
            if (seanceSelect.getPresenceTeacher() == 1) {
                pTeacher.setSelected(true);
            } else {
                pTeacher.setSelected(false);
            }

            uiseance = new UiSeance(OfferErr, teacherErr, roomErr, dateErr, timeErr, groupErr, GroupCmb, OfferCmb, teacherCmb, RoomCmb, dateSeance, time, pTeacher);
        }
    }

    @FXML
    private void selectStudentPresent(MouseEvent event) {
        Student std = (Student) studentPTable.getSelectionModel().getSelectedItem();
        if (std != null) {
            if (event.getButton() == MouseButton.PRIMARY) {

            } else if (event.getButton() == MouseButton.SECONDARY) {
                studentPTable.setContextMenu(PresentMenu);
                delItem.setOnAction(event1 -> {
                   /* Follow f = new Follow();
                    f.setIdSeance(seanceSelect.getId());
                    f.setIdStudent(std.getId());
                    FollowService.deleteFollow(f);*/
                    Follow f = new Follow();
                    f.setIdSeance(seanceSelect.getId());
                    f.setIdStudent(std.getId());
                    f.setPresenceStudent(0);
                    f.setStatus(0);
                    FollowService.updateFollow(f);
                    refrechStudents(studentATable, firstNameAC, lastNameAC, phone1AC, phone2AC, sectionNameAC, seanceSelect, "apsent");
                    refrechStudents(studentPTable, firstNamePC, lastNamePC, phone1PC, phone2PC, sectionNamePC, seanceSelect, "present");
                    studentPTable.setContextMenu(null);
                });
                showProfile1.setOnAction(event1 -> {
                    CommunController.alert("details profile");
                    studentPTable.setContextMenu(null);
                });
            }
        }

    }

    @FXML
    private void selectStudentApsent(MouseEvent event) {
        Student std = (Student) studentATable.getSelectionModel().getSelectedItem();
        if (std != null) {
            if (event.getButton() == MouseButton.PRIMARY) {
            } else if (event.getButton() == MouseButton.SECONDARY) {
                studentATable.setContextMenu(ApsentMenu);
                addPayItem.setOnAction(event1 -> {
                    Follow f = new Follow();
                    f.setIdSeance(seanceSelect.getId());
                    f.setIdStudent(std.getId());
                    f.setPresenceStudent(1);
                    f.setStatus(1);
                    FollowService.updateFollow(f);
                    refrechStudents(studentATable, firstNameAC, lastNameAC, phone1AC, phone2AC, sectionNameAC, seanceSelect, "apsent");
                    refrechStudents(studentPTable, firstNamePC, lastNamePC, phone1PC, phone2PC, sectionNamePC, seanceSelect, "present");
                    studentATable.setContextMenu(null);
                });
                addNoPayItem.setOnAction(event1 -> {
                    Follow f = new Follow();
                    f.setIdSeance(seanceSelect.getId());
                    f.setIdStudent(std.getId());
                    f.setPresenceStudent(1);
                    f.setStatus(0);
                    FollowService.addFollow(f);
                    refrechStudents(studentATable, firstNameAC, lastNameAC, phone1AC, phone2AC, sectionNameAC, seanceSelect, "apsent");
                    refrechStudents(studentPTable, firstNamePC, lastNamePC, phone1PC, phone2PC, sectionNamePC, seanceSelect, "present");
                    studentPTable.setContextMenu(null);
                });
                showProfile.setOnAction(event1 -> {
                    CommunController.alert("details profile");
                    studentPTable.setContextMenu(null);
                });
            }
        }

    }

}
