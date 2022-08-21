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
import schoolmanager.BackEnd.Model.Seance;

import schoolmanager.BackEnd.Model.Section;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Results;

/**
 * @author Zed Yacine
 */
public class StudentService {

    private static final Section section = new Section();

    public static Results.Rstls addStudent(Student student) {
        if (student == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement(""
                    + "insert into student (firstName,lastName,phone1,phone2,idSection)"
                    + " values (?,?,?,?,?)");
            stm.setString(1, student.getFirstName());
            stm.setString(2, student.getLastName());
            stm.setString(3, student.getPhone1());
            stm.setString(4, student.getPhone2());
            section.setName(student.getSectionName());
            stm.setLong(5, ObjectService.getIdObject(section, "section"));
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateStudent(Student student) {
        if (student == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE "
                    + " student SET firstName = ?"
                    + ", lastName = ? ,phone1 = ? ,phone2 = ? , idSection=? "
                    + " WHERE id = ? ");
            stm.setString(1, student.getFirstName());
            stm.setString(2, student.getLastName());
            stm.setString(3, student.getPhone1());
            stm.setString(4, student.getPhone2());
            section.setName(student.getSectionName());
            stm.setLong(5, ObjectService.getIdObject(section, "section"));
            stm.setLong(6, student.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteStudent(Student student) {
        if (student == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM "
                    + " student WHERE id = ?");
            stm.setLong(1, student.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_DELETED;
        }
    }

    public static ObservableList<Student> getAllStudents() {
        String query;
        query = "SELECT * FROM student order by id desc ";
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

    public static ObservableList<Student> getAllStudentsFollow(Seance s, String type) {
        String query = "";
        if (type.equals("apsent")) {
            query="SELECT S.id,S.idSection,S.firstName,S.lastName,S.phone1,S.phone2 FROM follow F, student S , belongs B " +
                    " where F.idStudent= S.id and S.id = B.idStudnet  and F.presenceStudent=0 and B.idGroupe ="+s.getIdGroupe()+
                    " order by S.id ";
            //query = "SELECT * FROM student where id not in(select idStudent from follow where idSeance = " + s.getId() + ") order by id desc";
        } else if (type.equals("present")) {
            query = "SELECT S.id,S.idSection,S.firstName,S.lastName,S.phone1,S.phone2 FROM follow F , student S  where S.id = F.idStudent " +
                    "and F.presenceStudent=1  and F.idSeance = " + s.getId() +" order by S.id desc";
            //query = "SELECT * FROM student where id in(select idStudent from follow where idSeance = " + s.getId() + ") order by id desc";

        }else if (type.equals("empty")){
            query = "SELECT * FROM student where id  in(select idStudnet from belongs where idGroupe = " + s.getIdGroupe() + ") order by id desc";
        }
        ObservableList<Student> listStudents = FXCollections.observableArrayList(new Student());
        listStudents.remove(0);
        System.out.println(query);
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

    public static ObservableList<Student> searchStudentByName(Student student) {
        String query = "";
        query = "SELECT * FROM student where firstName LIKE'" + student.getFirstName() + "%'";
        System.out.println(query);
        Student std = new Student();
        ObservableList<Student> listStudents = FXCollections.observableArrayList(new Student());
        listStudents.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
