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
import java.time.LocalDateTime;
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
import schoolmanager.BackEnd.Model.*;
import schoolmanager.BackEnd.Results;
import schoolmanager.BackEnd.Service.*;
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
    private final MenuItem addPayItem = new MenuItem("حاضر");
    private final MenuItem showProfile = new MenuItem("عرض ملف التلميذ");
    private final MenuItem showProfile1 = new MenuItem("عرض ملف التلميذ");
    private ObservableList<Offer> AllOffers;

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

    private ObservableList<Room> roomlist;
    private ObservableList<Teacher> teacherlist;
    ObservableList<Group> grouplist;
    @FXML
    private TableColumn<?, ?> paymentPC;
    @FXML
    private TableColumn<?, ?> paymentAC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ApsentMenu.getItems().addAll(addPayItem, showProfile1);
        PresentMenu.getItems().addAll(delItem, showProfile);
        uiseance = new UiSeance(OfferErr, teacherErr, roomErr, dateErr, timeErr, groupErr, GroupCmb, OfferCmb, teacherCmb, RoomCmb, dateSeance, time, pTeacher);
        AllOffers = OfferService.getAllOffers();
        OfferCmb.setItems(AllOffers);
        roomlist = RoomService.getAllRoom();
        RoomCmb.getItems().addAll(roomlist);
        teacherlist = TeacherService.getAllTeachers();
        teacherCmb.getItems().addAll(teacherlist);
        refrechSeance(SeanceTable, OfferC, TeacherC, RoomC, GroupC, dateC, pTeacherC, new Seance(), "");
    }

    public void refrechSeance(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5, TableColumn Column6,
            Seance seance, String type) {
        ObservableList<Seance> pr = SeanceService.getAllSeances(null, 0);
        if (pr.size() > 0) {
            seanceSelect = pr.get(0);
            refrechStudents(studentATable, firstNameAC, lastNameAC, phone1AC, phone2AC, sectionNameAC, paymentAC, pr.get(0), "apsent");
            refrechStudents(studentPTable, firstNamePC, lastNamePC, phone1PC, phone2PC, sectionNamePC, paymentPC, pr.get(0), "present");
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
                    new PropertyValueFactory<>("test")
            );
            table.setItems(pr);
        }
    }

    public static void refrechStudents(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5, TableColumn Column6,
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
        Column6.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );
        table.setItems(pr);
    }

    @FXML
    private void add(ActionEvent event) {
        seanceSelect = Mapping.getObjectSeanceFromUiSeance(uiseance);
        if (seanceSelect != null) {
            Results.Rstls r = SeanceService.addSeance(seanceSelect);
            if (r == Results.Rstls.OBJECT_NOT_INSERTED) {
                CommunController.alert(r.toString());
            } else {
                uiseance.clearInputs();
            }
            refrechSeance(SeanceTable, OfferC, TeacherC, RoomC, GroupC, dateC, pTeacherC, new Seance(), "");
        }
    }

    @FXML
    private void update(ActionEvent event) {
        if (seanceSelect.getId() != 0) {
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
    }

    @FXML
    private void delete(ActionEvent event) {
        if (seanceSelect.getId() != 0) {
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
    }

    @FXML
    private void onchangecontent(ActionEvent event) {
        Offer off = OfferCmb.getSelectionModel().getSelectedItem();
        if (off != null) {
            GroupCmb.getSelectionModel().select(null);
            grouplist = GroupService.getAllGroups(off,"offer");
            GroupCmb.setItems(grouplist);
        }

    }

    private int getIndexOffer(int id) {
        int i = 0;
        while (id != AllOffers.get(i).getId()) {
            i++;
        }
        return i;
    }

    private int getIndexTeacher(int id) {
        int i = 0;
        while (id != teacherlist.get(i).getId()) {
            i++;
        }
        return i;
    }

    private int getIndexRoom(int id) {
        int i = 0;
        while (id != roomlist.get(i).getId()) {
            i++;
        }
        return i;
    }

    private int getIndexgroop(int id) {
        int i = 0;
        while (id != grouplist.get(i).getId()) {
            i++;
        }
        return i;
    }

    @FXML
    private void selectSeance(MouseEvent event) {
        seanceSelect = (Seance) SeanceTable.getSelectionModel().getSelectedItem();
        seanceSelect.PresentSeance();
        if (seanceSelect != null) {
            refrechStudents(studentATable, firstNameAC, lastNameAC, phone1AC, phone2AC, sectionNameAC, paymentAC, seanceSelect, "apsent");
            refrechStudents(studentPTable, firstNamePC, lastNamePC, phone1PC, phone2PC, sectionNamePC, paymentPC, seanceSelect, "present");
            OfferCmb.getSelectionModel().select(getIndexOffer((int) seanceSelect.getIdOffer()));
            teacherCmb.getSelectionModel().select(getIndexTeacher((int) seanceSelect.getIdTeacher()));
            RoomCmb.getSelectionModel().select(getIndexRoom((int) seanceSelect.getIdRoom()));
            GroupCmb.getSelectionModel().select(getIndexgroop((int) seanceSelect.getIdGroupe()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dattime = LocalDateTime.parse(seanceSelect.getDate(), formatter);
            dateSeance.setValue(dattime.toLocalDate());
            time.setValue(dattime.toLocalTime());
            pTeacher.setSelected(seanceSelect.getPresenceTeacher() == 1);
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
                    FollowService.updateFollow(f, "presenceStudent");
                    refrechStudents(studentATable, firstNameAC, lastNameAC, phone1AC, phone2AC, sectionNameAC, paymentAC, seanceSelect, "apsent");
                    refrechStudents(studentPTable, firstNamePC, lastNamePC, phone1PC, phone2PC, sectionNamePC, paymentPC, seanceSelect, "present");
                    studentPTable.setContextMenu(null);
                });
                showProfile1.setOnAction(event1 -> {
                    CommunController.alert("details profile");
                    studentPTable.setContextMenu(null);
                });
            }
        }

    }

    /*<<<<<<< HEAD*/
    private boolean isPaiementFull(Paiement p) {
        return p.getNbrSeance() == CommunController.getnbrSeanceInOffer(PaiementService
                .getPaiementForThisGroupIfExist(p));
    }

    private boolean isPaiementEnough(Paiement p) {
        float seancePrice = CommunController.getAmountSeance(p);
        float amountRest = p.getAmountC() - seancePrice * p.getNbrSeance();
        return amountRest >= seancePrice;
    }

    private boolean isPaiementAvailable(Paiement p) {
        if (p.getId() != 0) {
            return isPaiementEnough(p) && !isPaiementFull(p);
        } else {
            return false;
        }
    }

    @FXML
    private void selectStudentApsent(MouseEvent event) {
        Student std = (Student) studentATable.getSelectionModel().getSelectedItem();
        std.PresentObject();
        if (std != null) {
            if (event.getButton() == MouseButton.PRIMARY) {
            } else if (event.getButton() == MouseButton.SECONDARY) {
                studentATable.setContextMenu(ApsentMenu);
                addPayItem.setOnAction(event1 -> {
                    Follow f = new Follow();
                    f.setIdSeance(seanceSelect.getId());
                    f.setIdStudent(std.getId());
                    f.setPresenceStudent(1);
                    Paiement p = new Paiement(std, new Group(seanceSelect.getIdGroupe()));
                    p = PaiementService.getPaiementForThisGroupIfExist(p);
                    p.setStd(std);
                    p.setGrp(new Group(seanceSelect.getIdGroupe()));
                    p.PresentObject();
                    if (isPaiementAvailable(p)) {
                        p.setNbrSeance(p.getNbrSeance() + 1);
                        SeanceService.updateNbrSeanceInPaiement(p);
                        f.setIdStudent(p.getStd().getId());
                        f.setIdSeance(seanceSelect.getId());
                        f.setIdPaiement(p.getId());
                        f.setStatus(1);
                        f.PresentFollow();
                        SeanceService.updatePaiementInFollow(f);
                        FollowService.updateFollow(f, "statusWithP");
                    } else {
                        f.setStatus(0);
                        FollowService.updateFollow(f, "presenceStudent");
                    }
                    refrechStudents(studentATable, firstNameAC, lastNameAC, phone1AC, phone2AC, sectionNameAC, paymentAC, seanceSelect, "apsent");
                    refrechStudents(studentPTable, firstNamePC, lastNamePC, phone1PC, phone2PC, sectionNamePC, paymentPC, seanceSelect, "present");
                    studentATable.setContextMenu(null);
                });
                showProfile.setOnAction(event1 -> {
                    CommunController.alert("details profile");
                    studentPTable.setContextMenu(null);
                });
            }
        }

    }

}
