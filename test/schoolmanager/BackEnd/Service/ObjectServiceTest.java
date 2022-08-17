package schoolmanager.BackEnd.Service;

import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Template;
import schoolmanager.BackEnd.Results;

import static org.junit.Assert.*;

public class ObjectServiceTest {

    public ObjectServiceTest() {
        DataBaseConnection.Connect();
    }

    @Test
    public void updateObject() {

    }

    @Test
    public void deleteObject() {
        Results.Rstls rst =ObjectService.deleteObject(new Template(4),"type");
        System.out.println(rst);
    }
}