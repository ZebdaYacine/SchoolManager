/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import schoolmanager.BackEnd.Model.Belongs;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Service.BelongsService;
import schoolmanager.BackEnd.Service.StudentService;
import schoolmanager.BackEnd.uiPresenter.UiStudent;

import java.net.URL;
import java.util.ResourceBundle;

import static schoolmanager.BackEnd.Controller.LoginController.loginUser;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class BelongsController implements Initializable {

    @FXML
    private TableView<?> studentTable;
    @FXML
    private TableView<?> belongsTable;
    @FXML
    private TextField firstName;

    @FXML
    private Label idG;

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
    private TableColumn<?, ?> firstNameC1;
    @FXML
    private TableColumn<?, ?> lastNameC1;
    @FXML
    private TableColumn<?, ?> phone1C1;
    @FXML
    private TableColumn<?, ?> phone2C1;
    @FXML
    private TableColumn<?, ?> sectionNameC1;
    @FXML
    private Label nbrPlace;
    @FXML
    private Label groupName;
    @FXML
    private Label offerName;

    private Student std = new Student();
    private static Group group = new Group();

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
                    std.setFirstName(firstName.getText());
/*
                    refrechStudent(studentTable, firstNameC, lastNameC, phone1C, phone2C, sectionNameC, "belongs");
*/
                } else {
                    enableSearch.setSelected(false);
                }
            }
        }));
        firstName.setOnKeyTyped(((event) -> {
            if (firstName.getText().equals("")) {
                enableSearch.setSelected(false);
            }
        }));
        refrechStudent(studentTable, firstNameC, lastNameC, phone1C, phone2C, sectionNameC, "student");

    }

    public static void refrechStudent(TableView table, TableColumn Column1, TableColumn Column2,
                                      TableColumn Column3, TableColumn Column4, TableColumn Column5, String type) {
        ObservableList<Student> pr = null;
        if(type.equals("student")){
            pr = StudentService.getAllStudents();
        }else if (type.equals("belongs")){
            pr = BelongsService.getStudentsOfGroup(group.getId());
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
       /* std = Mapping.getObjectStudentFromUiStudent(uistd);
        Results.Rstls r = StudentService.addStudent(std);
        if (r == Results.Rstls.OBJECT_NOT_INSERTED) {
            CommunController.alert(r.toString());
        } else {
            uistd.clearInputs();
        }*/
    }

    @FXML
    private void printList(ActionEvent event) {
      group.PresentGroupe();
    }

    @FXML
    private void update(ActionEvent event) {
       /* if (std.getId() != 0) {
            Student newStd = Mapping.getObjectStudentFromUiStudent(uistd);
            newStd.setId(std.getId());
            Optional<ButtonType> option = alertUpdate.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = StudentService.updateStudent(newStd);
                if (r == Results.Rstls.OBJECT_NOT_UPDATED) {
                    CommunController.alert(r.toString());
                } else {
                    uistd.clearInputs();
                    refrechStudent(studentTable, firstNameC, lastNameC, phone1C, phone2C,sectionNameC, new Student(), "");
                }
                std = new Student();
                uistd.clearInputs();
            }
        }*/
    }

    @FXML
    private void delete(ActionEvent event) {
        /*if (std.getId() != 0) {
            Optional<ButtonType> option = alertUpdate.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = StudentService.deleteStudent(std);
                if (r == Results.Rstls.OBJECT_NOT_DELETED) {
                    CommunController.alert(r.toString());
                } else {
                    uistd.clearInputs();
                    refrechStudent(studentTable, firstNameC, lastNameC, phone1C, phone2C,sectionNameC, new Student(), "");
                }
                std = new Student();
                uistd.clearInputs();
            }
        }*/
    }

    @FXML
    private void selectStudent(MouseEvent event) {
        std = (Student) studentTable.getSelectionModel().getSelectedItem();
        if (std != null) {
            std.PresentObject();
            group.PresentGroupe();
            if(group.getNbrRest()>0){
                BelongsService.addBelongs(new Belongs(std.getId(), group.getId()));
                refrechStudent(belongsTable, firstNameC1, lastNameC1, phone1C1, phone2C1, sectionNameC1, "belongs");
                group.setNbrRest(group.getNbrPlace()-(BelongsService.getStudentsOfGroup(group.getId()).size()));
                nbrPlace.setText(Integer.toString(group.getNbrRest()));
            }else{
                CommunController.alert("group est plain ");
            }

        }
    }

    public void setInputs(Group grp) {
        group=grp;
        group.PresentGroupe();
        group.setNbrRest(group.getNbrPlace()-(BelongsService.getStudentsOfGroup(group.getId()).size()));
        nbrPlace.setText(Integer.toString(group.getNbrRest()));
        groupName.setText(group.getNameGroup());
        offerName.setText("Offer :" + group.getNameOffer());
        refrechStudent(belongsTable, firstNameC1, lastNameC1, phone1C1, phone2C1, sectionNameC1, "belongs");
    }

}
