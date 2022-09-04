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
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Paiement;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.ReceiptPrinter.MainPrinter;
import schoolmanager.BackEnd.Service.BelongsService;
import schoolmanager.BackEnd.Service.PaiementService;
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
import static schoolmanager.SchoolManager.thirdStage;

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
public class PaiementController implements Initializable {

    @FXML
    private TableView<?> studentTable, PaiementTable;
    @FXML
    public static TableView<?> PaiementTable1;
    @FXML
    private TextField firstName, phone;
    @FXML
    private TableColumn<?, ?> firstNameC, lastNameC, phone1C, phone2C, sectionNameC;
    @FXML
    private Label stdLbl;
    @FXML
    private TableColumn<?, ?> offerC, datePC, groupC, amountC, amountRC, nbrseanceC;
    @FXML
    public static TableColumn<?, ?> offerC1, datePC1, groupC1, amountC1, amountRC1, nbrseanceC1;

    private static Student std = new Student();
    private static final Group group = new Group();
    private static Paiement paiement = new Paiement();

    private final ContextMenu contextMenu = new ContextMenu();
    private final MenuItem showGroups = new MenuItem("عرض الأفواج ");

    private final ContextMenu contextMenu1 = new ContextMenu();
    private final MenuItem PrinteP = new MenuItem("طباعة ");
    private final MenuItem showP = new MenuItem("عرض  ");

    private URL url1 = null;

    /**
     * Initializes the controller class.adminضa
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PaiementTable1 = PaiementTable;
        offerC1 = offerC;
        datePC1 = datePC;
        groupC1 = groupC;
        amountC1 = amountC;
        amountRC1 = amountRC;
        nbrseanceC1 = nbrseanceC;
        refrechStudent(studentTable, firstNameC, lastNameC, phone1C, phone2C,
                sectionNameC, new Student(), "student");
        contextMenu.getItems().addAll(showGroups);
        contextMenu1.getItems().addAll(PrinteP, showP);
        studentTable.setOnMouseClicked(event -> {
            std = (Student) studentTable.getSelectionModel().getSelectedItem();
            if (std != null) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    refrechPaiement(PaiementTable, groupC, offerC, datePC, amountC, amountRC, nbrseanceC, std);
                    stdLbl.setText("التلميذ : " + std.getFirstName() + " " + std.getLastName());
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    studentTable.setContextMenu(contextMenu);
                    showGroups.setOnAction(event1 -> {
                        try {
                            url1 = new File("src/schoolmanager/FrontEnd/layout/StudentPaiementHistory.fxml").toURI().toURL();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        showPaiementLayout(std, url1, "سجل الدفع", "StudentPaiementHistoryController");
                    });
                }
            }
        });

        PaiementTable.setOnMouseClicked(event -> {
            paiement = (Paiement) PaiementTable.getSelectionModel().getSelectedItem();
            if (paiement != null) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (event.getClickCount() == 2) {
                        paiement.setNbrSeance(SeanceService.countSeanceOfPaiment(paiement.getId()));
                        paiement.PresentObject();
                        if (CommunController.getnbrSeanceInOffer(paiement) == paiement.getNbrSeance()) {
                            CommunController.alert("عملية الدفع مقفلة");
                        } else {
                            try {
                                url1 = new File("src/schoolmanager/FrontEnd/layout/UpdatePaiement.fxml").toURI().toURL();
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            showPaiementLayout(paiement, url1, " عملية دفع جديدة ", "UpdatePaiementController");
                        }
                    }
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    PaiementTable.setContextMenu(contextMenu1);
                    PrinteP.setOnAction(event1 -> {
                        paiement.PresentObject();
                        try {
                            MainPrinter.Print(paiement);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    showP.setOnAction(event1 -> {
                        try {
                            url1 = new File("src/schoolmanager/FrontEnd/layout/ShowPaiement.fxml").toURI().toURL();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        showPaiementLayout(paiement, url1, " جدول الحصص ",
                                "ShowPaiementController");
                    });
                }
            }
        });
    }

    private void showPaiementLayout(Object obj, URL url, String titleLayout, String object) {
        try {
            FXMLLoader loader = new FXMLLoader(url);
            Parent uigrp = loader.load();
            Stage stage = SecodStage;
            switch (object) {
                case "PaiementCouresController": {
                    /* PaiementCouresController paiementCouresController = loader.getController();
                    paiementCouresController.setInputs((Paiement) obj);*/
                    //TODO this is for show all seance
                    break;
                }
                case "PaiementSeancesController": {
                    PaiementSeancesController paiementSeancesController = loader.getController();
                    paiementSeancesController.setInput((Paiement) obj);
                    stage = thirdStage;
                    //TODO this is for show student presence
                    break;
                }
                case "ShowPaiementController": {
                    ShowPaiementController showPaiementController = loader.getController();
                    showPaiementController.setInput((Paiement) obj);
                    stage = thirdStage;
                    //TODO this is for show student presence
                    break;
                }
                case "NewPaiementController": {
                    //TODO this is for add or update a paiement
                    NewPaiementController newPaiementController = loader.getController();
                    newPaiementController.setInputsNewPaiement((Student) obj);
                    break;
                }
                case "UpdatePaiementController": {
                    //TODO this is for add or update a paiement
                    UpdatePaiementController updatePaiementController = loader.getController();
                    updatePaiementController.setInputsUpdatePaiement((Paiement) obj);
                    break;
                }
            }
            Scene scene = new Scene(uigrp);
            if (!stage.isShowing()) {
                stage.setScene(scene);
                stage.setTitle(titleLayout);
                stage.showAndWait();
            } else {
                stage.setAlwaysOnTop(true);
                stage.setAlwaysOnTop(false);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void refrechPaiement(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4,
            TableColumn Column5, TableColumn Column6,
             Student std) {
        paiement.setStd(std);
        ObservableList<Paiement> pr = PaiementService.getPaiementOfStudent(paiement);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("groupName")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("OfferName")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("amount")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("amountC")
        );
        Column6.setCellValueFactory(
                new PropertyValueFactory<>("typeOfOffer")
        );

        table.setItems(pr);
    }

    public static void refrechStudent(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5, Student std, String type) {
        ObservableList<Student> pr = null;
        if (type.equals("student")) {
            pr = StudentService.getAllStudents("", new Student());
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
    private void addPaiement(ActionEvent event) throws JRException {
        try {
            url1 = new File("src/schoolmanager/FrontEnd/layout/NewPaiement.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        showPaiementLayout(std, url1, " عملية دفع جديدة ", "NewPaiementController");
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
    }

    public void setInputs(Group grp) {
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
        refrechStudent(studentTable, firstNameC, lastNameC, phone1C,
                phone2C, sectionNameC, std, "");
    }

}
