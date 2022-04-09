/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import schoolmanager.BackEnd.Results;
import schoolmanager.BackEnd.Service.StudentService;
import schoolmanager.BackEnd.uiPresenter.UiStudent;
import static schoolmanager.SchoolManager.alertUpdate;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class StudentController implements Initializable {

    @FXML
    private TableView<?> studentTable;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phone2;
    @FXML
    private TextField phone1;
    @FXML
    private TableColumn<?, ?> firstNameC;
    @FXML
    private TableColumn<?, ?> lastNameC;
    @FXML
    private TableColumn<?, ?> phone1C;
    @FXML
    private TableColumn<?, ?> phone2C;
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

    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton update;
    @FXML
    private JFXToggleButton enableSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        enableSearch.setSelected(false);
        enableSearch.setOnAction(((event) -> {
            if (enableSearch.isSelected()) {
                if (!firstName.getText().equals("")) {
                    try {
                        std.setFirstName(firstName.getText());
                        refrechStudent(studentTable, firstNameC, lastNameC, phone1C, phone2C,
                                std, "searche");
                    } catch (SQLException ex) {
                        Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    enableSearch.setSelected(false);
                }
            } else {
                try {
                    uistd.clearInputs();
                    refrechStudent(studentTable, firstNameC, lastNameC, phone1C, phone2C,
                            std, "");
                } catch (SQLException ex) {
                    Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }));
        firstName.setOnKeyTyped(((event) -> {
            if (firstName.getText().equals("")) {
                enableSearch.setSelected(false);
            }
        }));
        uistd = new UiStudent(firstName, lastName, phone2, phone1,
                firstName_err, lastName_err, phone1_err, phone2_err);
        if (loginUser.getRole().equals("simple")) {
            update.setVisible(false);
            delete.setVisible(false);
        } else {
            update.setVisible(true);
            delete.setVisible(true);
        }
        try {
            refrechStudent(studentTable, firstNameC, lastNameC, phone1C, phone2C, new Student(), "");
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void refrechStudent(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, Student std, String type)
            throws SQLException {
        ObservableList<Student> pr;
        if (type.equals("searche")) {
            pr = (ObservableList<Student>) StudentService.searchStudentByName(std);
        } else {
            pr = (ObservableList<Student>) StudentService.getAllStudents();
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
        table.setItems(pr);
    }

    @FXML
    private void add(ActionEvent event) {
        Student std = Mapping.getObjecStudentFromUiStudent(uistd);
        Results.Rstls r = StudentService.addStudent(std);
        if (r == Results.Rstls.OBJECT_NOT_INSERTED) {
            CommunController.alert(r.toString());
        } else {
            uistd.clearInputs();
        }
        try {
            refrechStudent(studentTable, firstNameC, lastNameC, phone1C, phone2C, new Student(), "");
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void update(ActionEvent event) {
        if (std.getId() != 0) {
            Student newStd = Mapping.getObjecStudentFromUiStudent(uistd);
            newStd.setId(std.getId());
            Optional<ButtonType> option = alertUpdate.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = StudentService.updateStudent(newStd);
                if (r == Results.Rstls.OBJECT_NOT_UPDATED) {
                    CommunController.alert(r.toString());
                } else {
                    uistd.clearInputs();
                    try {
                        refrechStudent(studentTable, firstNameC, lastNameC, phone1C, phone2C, new Student(), "");
                    } catch (SQLException ex) {
                        Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                std = new Student();
                uistd.clearInputs();
            }
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        if (std.getId() != 0) {
            Optional<ButtonType> option = alertUpdate.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = StudentService.deleteStudent(std);
                if (r == Results.Rstls.OBJECT_NOT_DELETED) {
                    CommunController.alert(r.toString());
                } else {
                    uistd.clearInputs();
                    try {
                        refrechStudent(studentTable, firstNameC, lastNameC, phone1C, phone2C, new Student(), "");
                    } catch (SQLException ex) {
                        Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                std = new Student();
                uistd.clearInputs();
            }
        }
    }

    @FXML
    private void selectStudent(MouseEvent event) {
        UiStudent uistd = new UiStudent(firstName, lastName, phone2, phone1,
                firstName_err, lastName_err, phone1_err, phone2_err);
        uistd.clearInputs();
        std = (Student) studentTable.getSelectionModel().getSelectedItem();
        if (std != null) {
            firstName.setText(std.getFirstName());
            lastName.setText(std.getLastName());
            phone1.setText(std.getPhone1());
            phone2.setText(std.getPhone2());
        }
    }

}
