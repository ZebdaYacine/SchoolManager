/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static schoolmanager.BackEnd.DataBaseConnection.con;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Offer;
import schoolmanager.BackEnd.Model.Room;
import schoolmanager.BackEnd.Model.Seance;
import schoolmanager.BackEnd.Model.Teacher;
import schoolmanager.BackEnd.Results;
import static schoolmanager.BackEnd.Service.GroupService.getGroupbyId;
import static schoolmanager.BackEnd.Service.OfferService.getOfferNameFromIdOffer;
import static schoolmanager.BackEnd.Service.RoomService.searchRoomById;
import static schoolmanager.BackEnd.Service.TeacherService.searchTeacherById;

/**
 *
 * @author kadri
 */
public class SeanceService {

    public static Results.Rstls addSeance(Seance seance) {
        if (seance == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement(""
                    + "insert into seance (`idOffer`, `idTeacher`, `idRoom`, `presenceTeacher`, `day`, `idGroupe`)"
                    + " values (?,?,?,?,?,?)");
            stm.setLong(1, seance.getIdOffer());
            stm.setLong(2, seance.getIdTeacher());
            stm.setLong(3, seance.getIdRoom());
            stm.setInt(4, seance.getPresenceTeacher());
            stm.setString(5, seance.getDate());
            stm.setLong(6, seance.getIdGroupe());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateSeance(Seance seance) {
        if (seance == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE "
                    + " seance SET idOffer = ?"
                    + ", idTeacher = ? , idRoom = ? ,presenceTeacher=? , day = ? ,idGroupe = ? "
                    + " WHERE id = ? ");
            stm.setLong(1, seance.getIdOffer());
            stm.setLong(2, seance.getIdTeacher());
            stm.setLong(3, seance.getIdRoom());
            stm.setInt(4, seance.getPresenceTeacher());
            stm.setString(5, seance.getDate());
            stm.setLong(6, seance.getIdGroupe());
            stm.setLong(7, seance.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteSeance(Seance seance) {
        if (seance == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM "
                    + " seance WHERE id = ?");
            stm.setLong(1, seance.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_DELETED;
        }
    }

    public static ObservableList<Seance> getAllSeances() {
        String query;
        query = "SELECT * FROM seance order by id desc ";
        ObservableList<Seance> listOffers = FXCollections.observableArrayList(new Seance());
        listOffers.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Seance seance = new Seance();
                seance.setId(rs.getLong("id"));
                seance.setIdOffer(rs.getLong("idOffer"));
                Offer off = new Offer(seance.getIdOffer());
                seance.setNameOffer(getOfferNameFromIdOffer(off));
                //
                seance.setIdTeacher(rs.getLong("idTeacher"));
                Teacher t = new Teacher(seance.getIdTeacher());
                t = searchTeacherById(t).get(0);
                seance.setNameTeacher(t.getFirstName() + " " + t.getLastName());
                //
                seance.setIdRoom(rs.getLong("idRoom"));
                Room r = new Room(seance.getIdRoom());
                r = searchRoomById(r).get(0);
                seance.setNameRoom(r.getName());
                //
                seance.setPresenceTeacher(rs.getInt("presenceTeacher"));
                if (seance.getPresenceTeacher() == 1) {
                    seance.setpTeacher("present");
                } else {
                    seance.setpTeacher("apsent");
                }
                seance.setDate(rs.getString("day"));
                seance.setIdGroupe(rs.getLong("idGroupe"));
                Group g = new Group(seance.getIdGroupe());
                g = getGroupbyId(g).get(0);
                seance.setNameGroup(g.getNameGroup());
                listOffers.add(seance);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOffers;
    }
}
