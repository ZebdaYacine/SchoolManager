package schoolmanager.BackEnd.Service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Room;
import schoolmanager.BackEnd.Service.RoomService;

public class RoomServiceTest {

    public RoomServiceTest() {
        DataBaseConnection.Connect();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addRoom() {
        Room rm = new Room("B",15);
        System.out.println(RoomService.addRoom(rm).toString());
    }

    @Test
    public void updateRoom() {
        Room rm = new Room(1,"A",20);
        System.out.println(RoomService.updateRoom(rm).toString());
    }

    @Test
    public void deleteStudent() {
        Room rm = new Room(2);
        System.out.println(RoomService.deleteRoom(rm).toString());
    }

    @Test
    public void getAllRoom() {
        RoomService.getAllRoom().forEach(room -> {
            room.PresentTemplate();
        });
    }

    @Test
    public void searchRoomByName() {
        RoomService.searchRoomByName(new Room("B")).forEach(room -> {
            room.PresentTemplate();
        });
    }
}