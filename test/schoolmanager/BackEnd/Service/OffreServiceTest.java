package schoolmanager.BackEnd.Service;

import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Offer;

public class OffreServiceTest {

    public OffreServiceTest() {
        DataBaseConnection.Connect();
    }

    @Test
    public void updateOffer() {
        Offer offer = new Offer(7,"test","Double","math","lycee", 2000);
        System.out.println(OfferService.updateOffer(offer));
    }

    @Test
    public void deleteOffer() {
        Offer offer = new Offer(7);
        System.out.println(OfferService.deleteOffer(offer));
    }
}