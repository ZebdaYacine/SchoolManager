package schoolmanager.BackEnd.Service;

import javafx.collections.ObservableList;
import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Seance;

import java.sql.Connection;

import static org.junit.Assert.*;

public class SeanceServiceTest {

    public SeanceServiceTest() {
        DataBaseConnection.Connect();

    }

    @Test
    public void addSeance() {
        Seance snc = new Seance(1,1,1,10,"2021-11-26",
                "","","","",1);
        SeanceService.addSeance(snc);
    }

    @Test
    public void updateSeance() {
        Seance snc = new Seance(1,1,1,1,10,"2022-08-13",
                "","","","",1);
        SeanceService.updateSeance(snc);
    }

    @Test
    public void deleteSeance() {
        Seance snc = new Seance(2);
        SeanceService.deleteSeance(snc);
    }

    @Test
    public void getAllSeances() {
        ObservableList<Seance> list= SeanceService.getAllSeances();
        for (Seance snc:list) {
            snc.PresentSeance();
        }
    }
}