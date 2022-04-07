/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.security.Provider;
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
import schoolmanager.BackEnd.Results;
import schoolmanager.BackEnd.Service.StudentService;
import schoolmanager.BackEnd.uiPresenter.UiStudent;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class StudentController implements Initializable {

    @FXML
    private TableView<?> StudentTable;
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
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (loginUser.getRole().equals("simple")) {
            update.setVisible(false);
            delete.setVisible(false);
        }
        try {
            refrechStudent(StudentTable, firstNameC, lastNameC, phone1C, phone2C, new Student());
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void refrechStudent(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, Student std)
            throws SQLException {
        ObservableList<Student> pr = (ObservableList<Student>) StudentService.getAllStudents();
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
        UiStudent uistd = new UiStudent(firstName, lastName, phone2, phone1,
                firstName_err, lastName_err, phone1_err, phone2_err);
        Student std = Mapping.getObjecStudentFromUiStudent(uistd);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information Dialog");
        a.setHeaderText(null);
        Results.Rstls r = StudentService.addStudent(std);
        if (r == Results.Rstls.OBJECT_NOT_INSERTED) {
            a.setContentText(r + "");
            a.showAndWait();
        }
        try {
            refrechStudent(StudentTable, firstNameC, lastNameC, phone1C, phone2C, new Student());
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void update(ActionEvent event) {

    }

    @FXML
    private void delete(ActionEvent event) {

    }

    @FXML
    private void select_provider(MouseEvent event) {

    }

}
