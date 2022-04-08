/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static schoolmanager.BackEnd.Controller.LoginController.loginUser;
import schoolmanager.BackEnd.Mapper.Mapping;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Model.Teacher;
import schoolmanager.BackEnd.Results;
import schoolmanager.BackEnd.Service.StudentService;
import schoolmanager.BackEnd.Service.TeacherService;
import schoolmanager.BackEnd.uiPresenter.UiTeacher;
import static schoolmanager.SchoolManager.alertUpdate;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class TeacherController implements Initializable {

    @FXML
    private TableView<?> teacherTable;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phone;
    @FXML
    private TextField workePlace;
    @FXML
    private TableColumn<?, ?> firstNameC;
    @FXML
    private TableColumn<?, ?> lastNameC;
    @FXML
    private TableColumn<?, ?> phoneC;
    @FXML
    private TableColumn<?, ?> workPlaceC;
    @FXML
    private Label firstName_err;
    @FXML
    private Label lastName_err;
    @FXML
    private Label phone_err;
    @FXML
    private Label workeSpace_err;

    private Teacher tech = new Teacher();

    private UiTeacher uitech = new UiTeacher();

    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uitech = new UiTeacher(firstName, lastName, phone, workePlace,
                firstName_err, lastName_err, phone_err, workeSpace_err);
        if (loginUser.getRole().equals("simple")) {
            update.setVisible(false);
            delete.setVisible(false);
        } else {
            update.setVisible(true);
            delete.setVisible(true);
        }
        try {
            refrechTeacher(teacherTable, firstNameC, lastNameC, phoneC, workPlaceC, new Teacher());
        } catch (SQLException ex) {
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void refrechTeacher(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, Teacher tech)
            throws SQLException {
        ObservableList<Teacher> pr = (ObservableList<Teacher>) TeacherService.getAllTeachers();
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("workePlace")
        );
        table.setItems(pr);
    }

    @FXML
    private void add(ActionEvent event) {
        Teacher tech = Mapping.getObjecTeacherFromUiTeacher(uitech);
        Results.Rstls r = TeacherService.addTeacher(tech);
        if (r == Results.Rstls.OBJECT_NOT_INSERTED) {
            CommunController.alert(r.toString());
        }
        try {
            refrechTeacher(teacherTable, firstNameC, lastNameC, phoneC, workPlaceC, new Teacher());
        } catch (SQLException ex) {
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void update(ActionEvent event) {
        if (tech.getId() != 0) {
            Teacher newtech = Mapping.getObjecTeacherFromUiTeacher(uitech);
            newtech.setId(tech.getId());
            Optional<ButtonType> option = alertUpdate.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = TeacherService.updateTeacher(newtech);
                if (r == Results.Rstls.OBJECT_NOT_UPDATED) {
                    CommunController.alert(r.toString());
                } else {
                    try {
                        refrechTeacher(teacherTable, firstNameC, lastNameC, phoneC, workPlaceC, new Teacher());
                    } catch (SQLException ex) {
                        Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                tech = new Teacher();
                uitech.clearInputs();
            }
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        if (tech.getId() != 0) {
            Teacher newtech = Mapping.getObjecTeacherFromUiTeacher(uitech);
            newtech.setId(tech.getId());
            Optional<ButtonType> option = alertUpdate.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = TeacherService.deleteTeacher(newtech);
                if (r == Results.Rstls.OBJECT_NOT_UPDATED) {
                    CommunController.alert(r.toString());
                } else {
                    try {
                        refrechTeacher(teacherTable, firstNameC, lastNameC, phoneC, workPlaceC, new Teacher());
                    } catch (SQLException ex) {
                        Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                tech = new Teacher();
                uitech.clearInputs();
            }
        }
    }

    @FXML
    private void selectTeacher(MouseEvent event) {
        UiTeacher uitech = new UiTeacher(firstName, lastName, phone, workePlace,
                firstName_err, lastName_err, phone_err, workeSpace_err);
        uitech.clearInputs();
        tech = (Teacher) teacherTable.getSelectionModel().getSelectedItem();
        if (tech != null) {
            firstName.setText(tech.getFirstName());
            lastName.setText(tech.getLastName());
            phone.setText(tech.getPhone());
            workePlace.setText(tech.getWorkePlace());
        }
    }
}
