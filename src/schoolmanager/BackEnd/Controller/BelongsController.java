/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import schoolmanager.BackEnd.Model.Belongs;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Service.BelongsService;
import schoolmanager.BackEnd.Service.StudentService;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import schoolmanager.BackEnd.Printer.Print;

/*import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;*/
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
    private TextField phone;
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

    private final ContextMenu contextMenu1 = new ContextMenu();
    private final ContextMenu contextMenu2 = new ContextMenu();
    private final MenuItem delete = new MenuItem("supprimer");
    private final MenuItem add = new MenuItem("ajouter");
    private final MenuItem showProfile = new MenuItem("voir le dossier de l'etudiant");
    private final MenuItem showProfile1 = new MenuItem("voir le dossier de l'etudiant");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refrechStudent(studentTable, firstNameC, lastNameC, phone1C, phone2C,
                sectionNameC, new Student(), "student");
        contextMenu1.getItems().addAll(delete, showProfile1);
        contextMenu2.getItems().addAll(add, showProfile);
        studentTable.setOnMouseClicked(event -> {
            std = (Student) studentTable.getSelectionModel().getSelectedItem();
            if (std != null) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (event.getClickCount() == 2) {
                        addingToBelongs(std);
                    }
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    studentTable.setContextMenu(contextMenu2);
                    add.setOnAction(event1 -> {
                        addingToBelongs(std);
                    });
                    showProfile.setOnAction(event1 -> {
                        CommunController.alert("details profile");
                    });
                }
            }
        });
        belongsTable.setOnMouseClicked(event -> {
            std = (Student) belongsTable.getSelectionModel().getSelectedItem();
            if (std != null) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    //belongsTable.setContextMenu(contextMenu1);
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    std.PresentObject();
                    group.PresentGroupe();
                    belongsTable.setContextMenu(contextMenu1);
                    delete.setOnAction(event1 -> {
                        boolean a = CommunController.confirm("sure de supprimer cet etudiant ?");
                        if (a) {
                            BelongsService.deleteBelongs(new Belongs(std.getId(), group.getId()));
                            refrechStudent(belongsTable, firstNameC1, lastNameC1, phone1C1,
                                    phone2C1, sectionNameC1, new Student(), "belongs");
                            group.setNbrRest(group.getNbrPlace() - (BelongsService.getStudentsOfGroup(group.getId()).size()));
                            nbrPlace.setText(Integer.toString(group.getNbrRest()));
                        }
                    });
                    showProfile1.setOnAction(event1 -> {
                        CommunController.alert("details profile 1");
                    });
                }
            }
        });
    }

    private void addingToBelongs(Student std) {
        std.PresentObject();
        group.PresentGroupe();
        if (group.getNbrRest() > 0) {
            BelongsService.addBelongs(new Belongs(std.getId(), group.getId()));
            refrechStudent(belongsTable, firstNameC1, lastNameC1, phone1C1,
                    phone2C1, sectionNameC1, new Student(), "belongs");
            group.setNbrRest(group.getNbrPlace() - (BelongsService.getStudentsOfGroup(group.getId()).size()));
            nbrPlace.setText(Integer.toString(group.getNbrRest()));
        } else {
            CommunController.alert("group est plain ");
        }
    }

    public static void refrechStudent(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5, Student std, String type) {
        ObservableList<Student> pr = null;
        if (type.equals("student")) {
            pr = StudentService.getAllStudents();
        } else if (type.equals("belongs")) {
            pr = BelongsService.getStudentsOfGroup(group.getId());
        } else {
            pr = BelongsService.searchStudentByName(std);
        }
        for (Student s : pr) {
            System.out.println(s.getSectionName());
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
    private void printList(ActionEvent event) throws JRException {
        Print prt = new Print(group);
        new Thread(() -> {
            try {
                prt.PrintListStudent();
            } catch (JRException ex) {
                Logger.getLogger(BelongsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
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
        /*std = (Student) studentTable.getSelectionModel().getSelectedItem();
        if (std != null) {
            std.PresentObject();
            group.PresentGroupe();
            if (group.getNbrRest() > 0) {
                BelongsService.addBelongs(new Belongs(std.getId(), group.getId()));
                refrechStudent(belongsTable, firstNameC1, lastNameC1, phone1C1, phone2C1, sectionNameC1, "belongs");
                group.setNbrRest(group.getNbrPlace() - (BelongsService.getStudentsOfGroup(group.getId()).size()));
                nbrPlace.setText(Integer.toString(group.getNbrRest()));
            } else {
                CommunController.alert("group est plain ");
            }

        }*/
    }

    public void setInputs(Group grp) {
        group = grp;
        group.PresentGroupe();
        group.setNbrRest(group.getNbrPlace() - (BelongsService.getStudentsOfGroup(group.getId()).size()));
        nbrPlace.setText(Integer.toString(group.getNbrRest()));
        groupName.setText("الفوج : " + group.getNameGroup());
        offerName.setText("العرض : " + group.getNameOffer());
        refrechStudent(belongsTable, firstNameC1, lastNameC1, phone1C1,
                phone2C1, sectionNameC1, new Student(), "belongs");
    }

    @FXML
    private void searchByNameAndPhone(KeyEvent event) {
        Student std = new Student();
        String name = firstName.getText();
        String phn = phone.getText();
        if (!name.isEmpty() && !phn.isEmpty()) {
            std.setFirstName(name);
            std.setPhone1(phn);
            std.setPhone2(phn);
        } else if (!name.isEmpty()) {
            std.setFirstName(name);
            std.setPhone1("");
            std.setPhone2("");
        } else {
            std.setFirstName("");
            std.setPhone1(phn);
            std.setPhone2(phn);
        }
        std.PresentObject();
        refrechStudent(studentTable, firstNameC1, lastNameC1, phone1C1,
                phone2C1, sectionNameC1, std, "");
    }

}
