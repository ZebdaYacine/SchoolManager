/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static schoolmanager.BackEnd.DataBaseConnection.con;
import schoolmanager.BackEnd.Model.*;
import schoolmanager.BackEnd.Results;

/**
 *
 * @author Zed Yacine
 */
public class ObjectService {

    public static Results.Rstls addObject(Template objTemplate,String tab) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into "+tab+" (nameLevel) values (?)");
            stm.setString(1, objTemplate.getName());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateObject(Template objTemplate , String tab) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + " "+tab+" SET name = ?"
                    + " WHERE id = ? ");
            stm.setString(1, objTemplate.getName());
            stm.setLong(2, objTemplate.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_UPDATED;
        }
    }

     public static Results.Rstls deleteObject(Template objTemplate, String tab) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " "+tab+" WHERE id = ?");
            stm.setLong(1, objTemplate.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_DELETED;
        }
    }

    public static ObservableList<Template> getAllObjects(String tab) {
        String query;
        query = "SELECT * FROM  "+tab;
        ObservableList<Template> listObjects = FXCollections.observableArrayList(new Template());
        listObjects.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Template objTemplate = new Template();
                objTemplate.setId(rs.getLong("id"));
                objTemplate.setName(rs.getString("nameLevel"));
                listObjects.add(objTemplate);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listObjects;
    }

    public static Template searchObjectByName(Template obTemplate,String tab) {
        String query;
        query = "SELECT * FROM "+tab+" where name='" + obTemplate.getName();
        Template obTemplate1 = new Template();
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obTemplate1.setId(rs.getLong("id"));
                obTemplate1.setName(rs.getString("name"));
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return obTemplate1;
    }

}
