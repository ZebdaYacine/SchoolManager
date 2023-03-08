package schoolmanager.BackEnd.Service;

import javafx.collections.ObservableList;
import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Paiement;
import schoolmanager.BackEnd.Model.Seance;
import schoolmanager.BackEnd.Model.Student;

public class SeanceServiceTest {

    public SeanceServiceTest() {
        DataBaseConnection.Connect();

    }

    @Test
    public void addSeance() {

    }

    @Test
    public void updateSeance() {
        Seance snc = new Seance(251);
        snc.setIdOffer(40);
        snc.setIdTeacher(19);
        snc.setIdRoom(10);
        snc.setIdGroupe(51);
        snc.setDate("2022-09-26 22:49:00");
        snc.setPresenceTeacher(1);
        SeanceService.updateSeance(snc);
    }

    @Test
    public void deleteSeance() {
        Seance snc = new Seance(2);
        SeanceService.deleteSeance(snc);
    }

    @Test
    public void getAllSeances() {
        Paiement paiement = new Paiement(new Student(10L), new Group(5L));
        ObservableList<Seance> list = SeanceService.getAllSeances(paiement, 0);
        for (Seance snc : list) {
            snc.PresentSeance();
        }
    }

    @Test
    public void updateIdPaiementInSeance() {
        SeanceService.updateIdPaiementInSeance(8, 73);
    }

    @Test
    public void countPaidSeances() {
        System.out.println(SeanceService.countPaidSeances(134));
    }

    @Test
    public void testUpdateSeance() {
    }
}