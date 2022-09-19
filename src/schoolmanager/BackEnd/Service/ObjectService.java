/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static schoolmanager.BackEnd.DataBaseConnection.con;
import schoolmanager.BackEnd.Model.*;
import schoolmanager.BackEnd.Results;

/**
 *
 * @author Zed Yacine
 */
public class ObjectService  {

    public static Results.Rstls addObject(Template objTemplate, String tab) {
        try {
            PreparedStatement stm = con.prepareStatement(""
                    + "insert into " + tab + " (name) values (?)");
            stm.setString(1, objTemplate.getName());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateObject(Template objTemplate, String tab) {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE "
                    + " " + tab + " SET name = ?"
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
            PreparedStatement stm = con.prepareStatement("DELETE FROM "
                    + " " + tab + " WHERE id = ?");
            stm.setLong(1, objTemplate.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_DELETED;
        }
    }

    public static ObservableList<Object> getAllObjects(String tab) {
        String query;
        query = "SELECT * FROM  " + tab;
        ObservableList<Object> listObjects = FXCollections.observableArrayList(new Template());
        listObjects.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Template objTemplate = new Template();
                objTemplate.setId(rs.getLong("id"));
                objTemplate.setName(rs.getString("name"));
                listObjects.add(objTemplate);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listObjects;
    }

    public static ObservableList<Object> searchObjectByName(Template obTemplate, String tab) {
        String query;
        query = "SELECT * FROM " + tab + " where name LIKE '" + obTemplate.getName() + "%'";
        Template obTemplate1 = new Template();
        ObservableList<Object> listObjects = FXCollections.observableArrayList(new Template());
        listObjects.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obTemplate1.setId(rs.getLong("id"));
                obTemplate1.setName(rs.getString("name"));
                listObjects.add(obTemplate);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listObjects;
    }

    public static long getIdObject(Template objTemplate, String tab) {
        String query;
        String nameAtt="name";
        if(tab.equals("offer")){
            nameAtt="OfferName";
        }
        query = "SELECT id FROM " + tab + " where "+nameAtt+" = '" + objTemplate.getName() + "'";
        System.out.println(query);
        long id=0;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id=rs.getLong("id");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public static String getNameFromIdObject(Template template,String tab) {
        String query;
        String nameAtt="name";
        if(tab.equals("Offer")){
            nameAtt="OfferName";
        }
        query = "SELECT "+nameAtt+" FROM "+tab+" where id ="+template.getId();
        String name ="";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name=rs.getString(nameAtt);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return name;
    }

    public static ObservableList<String> getAllObjectName(String tab) {
        String query;
        query = "SELECT name FROM "+tab;
        ObservableList<String> list = FXCollections.observableArrayList("");
        list.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static String getCurrentDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        return  new String(formatter.format(date));
    }

}
