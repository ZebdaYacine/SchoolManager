package schoolmanager.BackEnd.Service;

import javafx.collections.ObservableList;
import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Paiement;
import schoolmanager.BackEnd.Model.Student;

public class PaiementServiceTest {

    private Paiement paiement;

    public PaiementServiceTest() {
        DataBaseConnection.Connect();
        paiement= new Paiement(new Student(4),new Group(6L),"2022-08-23 19:12:00",2500,2000);

    }

    @Test
    public void addAccount() {
       PaiementService.addPaiement(paiement);
    }

    @Test
    public void updateAccount() {
        paiement.setId(1);
        paiement.setAmount(3000);
        paiement.PresentObject();
        PaiementService.updatePaiement(paiement);
    }

    @Test
    public void deleteAccount() {
        paiement.setId(1);
        PaiementService.deletePaiement(paiement);
    }

    @Test
    public void getAccountOfStudent() {
        ObservableList<Paiement> list = PaiementService.getPaiementOfStudent(paiement);
        for (Paiement p : list) {
            p.PresentObject();
        }
    }
}