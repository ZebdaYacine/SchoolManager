package schoolmanager.BackEnd.Service;

import javafx.collections.ObservableList;
import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Follow;
import schoolmanager.BackEnd.Model.Paiement;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Service.FollowService;

import static org.junit.Assert.*;

public class FollowServiceTest {

    public FollowServiceTest() {
        DataBaseConnection.Connect();
    }

    @Test
    public void addFollow() {
        Follow flw= new Follow(10,1,1,1);
        FollowService.addFollow(flw,"");
    }

    @Test
    public void deleteFollow() {
        Follow flw= new Follow(10,1);
        FollowService.deleteFollow(flw);
    }

    @Test
    public void getStudentsPresence() {
        ObservableList<Student> listStudents= FollowService.getStudentsPresence(1);
        for (Student std : listStudents){
            std.PresentObject();
        }
    }

    @Test
    public void getCountSeancePaid() {
        int id=FollowService.getCountSeancePaid(9,38);
        System.out.println(id);
    }
}