/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Model.Template;
import schoolmanager.BackEnd.Results;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static schoolmanager.BackEnd.DataBaseConnection.con;

/**
 *
 * @author Zed Yacine
 */
public class GroupService {

    public static Results.Rstls addGroup(Group group) {
        if (group == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement(""
                    + "insert into groupe (name,idOffer,nbrPlace)"
                    + " values (?,?,?)");
            stm.setString(1, group.getNameGroup());
            stm.setLong(2, ObjectService.getIdObject(new Template(group.getNameOffer()), "offer"));
            stm.setInt(3, group.getNbrPlace());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
    }

    public static Results.Rstls deleteGroup(Group group) {
        if (group == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM "
                    + " groupe WHERE id = ?");
            stm.setLong(1, group.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_DELETED;
        }
    }

    public static Results.Rstls updateGroup(Group group) {
        if (group == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            String query = "UPDATE groupe SET name = '" + group.getNameGroup() + "', "
                    + "idOffer = '" + ObjectService.getIdObject(new Template(group.getNameOffer()), "offer") + "' , "
                    + "nbrPlace = '" + group.getNbrPlace() + "' WHERE id =  " + group.getId();
            System.out.println(query);
            PreparedStatement stm = con.prepareStatement(query);
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_UPDATED;
        }
    }

    public static ObservableList<Group> getAllGroups() {
        String query;
        query = "SELECT * FROM groupe order by id desc ";
        ObservableList<Group> listGroups = FXCollections.observableArrayList(new Group());
        listGroups.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setNameGroup(rs.getString("name"));
                group.setIdOffer(rs.getInt("idOffer"));
                group.setNameOffer(ObjectService.getNameFromIdObject(
                        new Template(rs.getInt("idOffer")),
                         "Offer"));
                group.setNbrPlace(rs.getInt("nbrPlace"));
                listGroups.add(group);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listGroups;
    }

    public static ObservableList<Group> getGroupbyId(Group g) {
        String query;
        query = "SELECT * FROM groupe where id = " + g.getId() + "";
        ObservableList<Group> listGroups = FXCollections.observableArrayList(new Group());
        listGroups.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setNameGroup(rs.getString("name"));
                group.setIdOffer(rs.getInt("idOffer"));
                group.setNameOffer(ObjectService.getNameFromIdObject(
                        new Template(rs.getInt("idOffer")),
                         "Offer"));
                group.setNbrPlace(rs.getInt("nbrPlace"));
                listGroups.add(group);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listGroups;
    }

}
