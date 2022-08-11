/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schoolmanager.BackEnd.Model.*;
import schoolmanager.BackEnd.Results;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static schoolmanager.BackEnd.DataBaseConnection.con;

/**
 *
 * @author Zed Yacine
 */
public class BelongsService {


    public static Results.Rstls addBelongs(Belongs blgns) {
        if (blgns == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement(""
                    + "insert into belongs (idGroupe,idStudnet)"
                    + " values (?,?)");
            stm.setLong(1, blgns.getIdGroup());
            stm.setLong(2, blgns.getIdStudent());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
    }

    public static Results.Rstls deleteBelongs(Belongs blgns) {
        if (blgns == null) {
            return Results.Rstls.OBJECT_NOT_DELETED;
        }
        try {
            String query="delete from  belongs  where idStudnet="+blgns.getIdStudent()
                    +" and idGroupe ="+blgns.getIdGroup();
            PreparedStatement stm = con.prepareStatement(query);
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_DELETED;
        }
    }

    public static ObservableList<Student> getStudentsOfGroup(long id) {
        String query;
        query = "SELECT * FROM student S , belongs B " +
                " where S.id=B.idStudnet and  B.idGroupe ="+id+" order by S.id desc ";
        System.out.println(query);
        ObservableList<Student> listStudents = FXCollections.observableArrayList(new Student());
        listStudents.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong("id"));
                student.setFirstName(rs.getString("firstName"));
                student.setLastName(rs.getString("lastName"));
                student.setPhone1(rs.getString("phone1"));
                student.setPhone2(rs.getString("phone2"));
                student.setSectionName(
                        ObjectService.getNameFromIdObject(new Section(rs.getLong("idSection")), "section"));
                listStudents.add(student);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listStudents;
    }


}
