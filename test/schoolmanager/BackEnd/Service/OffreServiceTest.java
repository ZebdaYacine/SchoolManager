package schoolmanager.BackEnd.Service;

import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Level;
import schoolmanager.BackEnd.Model.Module;
import schoolmanager.BackEnd.Model.Offer;
import schoolmanager.BackEnd.Model.Type;
import schoolmanager.BackEnd.Results;

import java.sql.PreparedStatement;

import static org.junit.Assert.*;
import static schoolmanager.BackEnd.DataBaseConnection.con;

public class OffreServiceTest {

    public OffreServiceTest() {
        DataBaseConnection.Connect();
    }

    @Test
    public void updateOffer() {
        Offer offer = new Offer(7,"test","Double","math","lycee", 2000);
        System.out.println(OffreService.updateOffer(offer));
    }

    @Test
    public void deleteOffer() {
        Offer offer = new Offer(7);
        System.out.println(OffreService.deleteOffer(offer));
    }
}