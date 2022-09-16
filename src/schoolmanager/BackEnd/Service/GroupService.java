/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schoolmanager.BackEnd.Model.Group;
import schoolmanager.BackEnd.Model.Template;
import schoolmanager.BackEnd.Results;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static schoolmanager.BackEnd.DataBaseConnection.con;
import schoolmanager.BackEnd.Model.Offer;

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
        query = "SELECT * FROM groupe G, offer O  where O.id=G.idOffer order by O.idLevel asc";
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
                group.setLevel(rs.getString("nameLevel"));
                group.setModule(rs.getString("nameModule"));
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

    public static ObservableList<Group> getAllGroups(Offer o, String att) {
        String query = null;
        if(att.equals("offer")){
            query = "SELECT * FROM groupe where idOffer = " + o.getId();
        }else if(att.equals("all")){
            query = "SELECT * FROM groupe ";
        }
        ObservableList<Group> listGroups = FXCollections.observableArrayList(new Group());
        listGroups.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            Group group = new Group();
            if(att.equals("all")){
                group.setNameGroup("عرض الكل");
                listGroups.add(group);
            }
            while (rs.next()) {
                 group = new Group();
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
