/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import schoolmanager.BackEnd.Mapper.Mapping;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Results;
import schoolmanager.BackEnd.Service.SectionService;
import schoolmanager.BackEnd.Service.StudentService;
import schoolmanager.BackEnd.uiPresenter.UiStudent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static schoolmanager.BackEnd.Controller.BelongsController.*;
import static schoolmanager.BackEnd.Controller.LoginController.loginUser;
import static schoolmanager.SchoolManager.alertUpdate;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class AddStudentController implements Initializable {


    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phone2;
    @FXML
    private TextField phone1;
    @FXML
    private JFXComboBox sectionName;

    @FXML
    private Label firstName_err;
    @FXML
    private Label lastName_err;
    @FXML
    private Label phone1_err;
    @FXML
    private Label phone2_err;

    private Student std = new Student();

    private UiStudent uistd = new UiStudent();



    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> sectionsName = SectionService.getAllObjectName("section");
        this.sectionName.setItems(sectionsName);
        uistd = new UiStudent(firstName, lastName, phone2, phone1, sectionName,
                firstName_err, lastName_err, phone1_err, phone2_err);
    }

    public static void refrechStudent(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5, Student std, String type) {
        ObservableList<Student> pr;
        if (type.equals("searche")) {
            pr = StudentService.searchStudentByName(std);
        } else {
            pr = StudentService.getAllStudents("", new Student());
        }
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
        std = Mapping.getObjectStudentFromUiStudent(uistd);
        if (std != null) {
            Results.Rstls r = StudentService.addStudent(std);
            if (r == Results.Rstls.OBJECT_NOT_INSERTED) {
                CommunController.alert(r.toString());
            } else {
                uistd.clearInputs();
                refrechStudent(belongsTable1, firstNameC11, lastNameC11, phone1C11,
                        phone2C11, sectionNameC11, new Student(), "belongs");
                refrechStudent(studentTable1, firstNameC10, lastNameC10, phone1C10,
                        phone2C10, sectionNameC10, new Student(), "");
            }
        }
    }

}
