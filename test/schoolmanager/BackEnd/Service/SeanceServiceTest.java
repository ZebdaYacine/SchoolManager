package schoolmanager.BackEnd.Service;

import javafx.collections.ObservableList;
import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Paiement;
import schoolmanager.BackEnd.Model.Seance;
import schoolmanager.BackEnd.Model.Student;

import java.sql.Connection;

import static org.junit.Assert.*;

public class SeanceServiceTest {

    public SeanceServiceTest() {
        DataBaseConnection.Connect();

    }

    @Test
    public void addSeance() {

    }

    @Test
    public void updateSeance() {
 /*       Seance snc = new Seance(1,1,1,1,10,"2022-08-13",
                "","","","",1);
        SeanceService.updateSeance(snc);*/
    }

    @Test
    public void deleteSeance() {
        Seance snc = new Seance(2);
        SeanceService.deleteSeance(snc);
    }

    @Test
    public void getAllSeances() {
        Paiement paiement = new Paiement(new Student(10L),new Group(5L));
        ObservableList<Seance> list= SeanceService.getAllSeances(paiement);
        for (Seance snc:list) {
            snc.PresentSeance();
        }
    }
}