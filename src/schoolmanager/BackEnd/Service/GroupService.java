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
            stm.setString(1, group.getNameOffer());
            stm.setLong(2, group.getIdOffer());
            stm.setInt(3, group.getNbrPlace());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_INSERTED;
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
                        new Template(rs.getInt("idOffer"))
                        ,"Offer"));
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
