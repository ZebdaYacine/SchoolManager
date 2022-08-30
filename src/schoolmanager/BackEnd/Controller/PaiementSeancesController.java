/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Paiement;
import schoolmanager.BackEnd.Model.Seance;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Printer.Print;
import schoolmanager.BackEnd.Service.BelongsService;
import schoolmanager.BackEnd.Service.SeanceService;
import schoolmanager.BackEnd.Service.StudentService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static schoolmanager.SchoolManager.SecodStage;

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
public class PaiementSeancesController implements Initializable {


    @FXML
    private TableView<?> seanceTable;

    @FXML
    private TableColumn<?, ?> roomC,dateTimeC,teacherC,pTeacherC,pStudentC,paiementC;



    private static Group group = new Group();
    private static Student std = new Student();
    private static  Paiement paiement = new Paiement();


    /**
     * Initializes the controller class.adminضa
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {



    }



    private void showSeancsOfGroup(Student std, Group grp) {
        if (std != null && grp != null) {
            refrechSeance(seanceTable, roomC, dateTimeC, teacherC, pTeacherC, pStudentC, paiementC, std, grp);
        } else {
            System.out.println(std + " is null");
        }
    }



    public static void refrechSeance(TableView table, TableColumn Column1, TableColumn Column2,
                                     TableColumn Column3, TableColumn Column4, TableColumn Column5,
                                     TableColumn Column6, Student std, Group grp) {
        ObservableList<Seance> seance = SeanceService.getAllSeances(new Paiement(std, grp));
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("nameRoom")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("nameTeacher")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("test")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("test1")
        );
        Column6.setCellValueFactory(
                new PropertyValueFactory<>("pstatus")
        );
        table.setItems(seance);
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



    public void setInput(Paiement paiement) {
        paiement.PresentObject();
        /*group = grp;
        group.PresentGroupe();
        group.setNbrRest(group.getNbrPlace() - (BelongsService.getStudentsOfGroup(group.getId()).size()));
        nbrPlace.setText(Integer.toString(group.getNbrRest()));
        groupName.setText("الفوج : " + group.getNameGroup());
        offerName.setText("العرض : " + group.getNameOffer());
        refrechStudent(belongsTable, firstNameC1, lastNameC1, phone1C1,
                phone2C1, sectionNameC1, new Student(), "belongs");*/
    }




}
