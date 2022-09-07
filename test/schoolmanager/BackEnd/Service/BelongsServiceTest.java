package schoolmanager.BackEnd.Service;

import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Service.BelongsService;

public class BelongsServiceTest {

    public BelongsServiceTest() {DataBaseConnection.Connect();}

    @Test
    public void getGroupOfStudent() {
        for(Group grp : BelongsService.getGroupOfStudent(new Student(5))){
            grp.PresentGroupe();
        }
        for(Group grp : BelongsService.getGroupOfStudent(new Student(5))){
            grp.PresentGroupe();
        }
    }
}