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

    public static ObservableList<Group> getGroupOfStudent (Student std) {
        String query="SELECT G.id ,G.name,G.nbrPlace , O.offerName ,O.id as 'idO' ,O.nameModule,O.nameLevel" +
                " FROM  belongs B , Groupe G , Offer O where" +
                " G.id=B.idGroupe and O.id= G.idOffer" +
                " and B.idStudnet="+std.getId()+" group by G.id  order by B.idStudnet";
        ObservableList<Group> listGroups = FXCollections.observableArrayList(new Group());
        listGroups.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Group grp = new Group();
                grp.setId(rs.getLong("id"));
                grp.setIdOffer(rs.getLong("idO"));
                grp.setNameGroup(rs.getString("name"));
                grp.setNbrPlace(rs.getInt("nbrPlace"));
                grp.setNameOffer(rs.getString("offerName"));
                grp.setModule(rs.getString("nameModule"));
                grp.setLevel(rs.getString("nameLevel"));
                listGroups.add(grp);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listGroups;
    }

    public static ObservableList<Student> searchStudentByName(Student student) {
        String query;
        query = "SELECT * FROM student where firstName LIKE'" + student.getFirstName() + "%'" +
                    " and  ( phone1 LIKE'"+student.getPhone1()+"%' or " +
                    "phone2 LIKE'"+student.getPhone2()+"%')";
        ObservableList<Student> listStudents = FXCollections.observableArrayList(new Student());
        listStudents.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student std = new Student();
                std.setId(rs.getLong("id"));
                std.setFirstName(rs.getString("firstName"));
                std.setLastName(rs.getString("lastName"));
                std.setPhone1(rs.getString("phone1"));
                std.setPhone2(rs.getString("phone2"));
                student.setSectionName(
                        ObjectService.getNameFromIdObject(new Section(rs.getLong("idSection")), "section"));
                listStudents.add(std);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listStudents;
    }
}
