package schoolmanager.BackEnd.Service;

import javafx.collections.ObservableList;
import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Service.GroupService;

public class GroupServiceTest {


    public GroupServiceTest() {
        DataBaseConnection.Connect();
    }

    @Test
    public void addGroup() {
       /* Group group= new Group("math Physique","Group" ,20);
        GroupService.addGroup(group);*/
    }

    @Test
    public void getAllGroups() {
        ObservableList<Group> groups=  GroupService.getAllGroups();
        for (Group group: groups) {
            group.PresentGroupe();
        }
    }
}