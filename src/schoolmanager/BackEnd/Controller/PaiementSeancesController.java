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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import schoolmanager.BackEnd.Model.*;
import schoolmanager.BackEnd.Service.FollowService;
import schoolmanager.BackEnd.Service.SeanceService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private Label amuntCL, idL;

    @FXML
    public static Label amuntCLStatic;

    public static ObservableList<Seance> listSeance;

    @FXML
    private TableColumn<?, ?> roomC, dateTimeC, teacherC, pTeacherC, pStudentC, paiementC;


    private Group group = new Group();
    private Student std = new Student();
    private Paiement p = new Paiement();

    public static ArrayList<Seance> list;
    public static float amountRound;
    public static float priceSeance;
    public static String source;


    /**
     * Initializes the controller class.adminضa
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        amuntCLStatic = amuntCL;
        list = new ArrayList<>();
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
        listSeance = SeanceService.getAllSeances(new Paiement(std, grp));
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
        table.setItems(listSeance);
    }


    @FXML
    private void pay(ActionEvent event) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        p.PresentObject();
        for (Seance seance : list) {
            seance.PresentSeance();
            seance.setIdPaiement(p.getId());
            SeanceService.updatePaiementSeance(seance);
            Follow f = new Follow();
            f.setStatus(1);
            f.setIdStudent(p.getStd().getId());
            f.setIdSeance(seance.getId());
            FollowService.updateFollow(f,"status");
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        refrechSeance(seanceTable, roomC, dateTimeC, teacherC, pTeacherC, pStudentC, paiementC, p.getStd(),p.getGrp());

    }

    @FXML
    private void selectItem(MouseEvent event) {

    }

    private float getAmountSeance(Paiement p) {
        float amountSeance = 0f;
        switch (p.getTypeOfOffer().toLowerCase()) {
            case "simple": {
                amountSeance = p.getAmount() / 8;
                break;
            }
            case "double": {
                amountSeance = p.getAmount() / 4;
                break;
            }
            case "vip": {
                amountSeance = p.getAmount() / 2;
                break;
            }
        }
        return amountSeance;
    }


    public void setInput(Paiement paiement) {
        p = paiement;
        paiement.PresentObject();
        std = paiement.getStd();
        group = paiement.getGrp();
        amuntCL.setText(paiement.getAmountC() + " Da");
        idL.setText(idL.getText() + " " + p.getId());
        amountRound = paiement.getAmount();
        priceSeance = getAmountSeance(paiement);
        showSeancsOfGroup(std, group);
    }



}
