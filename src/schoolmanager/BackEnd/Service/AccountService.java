/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schoolmanager.BackEnd.Model.Account;
import schoolmanager.BackEnd.Model.Seance;
import schoolmanager.BackEnd.Model.Section;
import schoolmanager.BackEnd.Model.Student;
import schoolmanager.BackEnd.Results;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static schoolmanager.BackEnd.DataBaseConnection.con;

/**
 * @author Zed Yacine
 */
public class AccountService {

    private static final Section section = new Section();

    public static Results.Rstls addAccount(Account account) {
        if (account == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement(""
                    + "insert into account (idStudent,day,amount)"
                    + " values (?,?,?)");
            stm.setLong(1, account.getIdStudent());
            stm.setString(2, account.getDay());
            stm.setFloat(3, account.getAmount());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateAccount(Account  account) {
        if (account == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE "
                    + " account SET idStudent = ?"
                    + ", day = ? ,amount = ?  "
                    + " WHERE id = ? ");
            stm.setLong(1, account.getIdStudent());
            stm.setString(2, account.getDay());
            stm.setFloat(3, account.getAmount());
            stm.setLong(4, account.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteAccount(Account  account) {
        if (account == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM "
                    + " account WHERE id = ?");
            stm.setLong(1, account.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_DELETED;
        }
    }

    public static ObservableList<Account> getAccountOfStudent(Account account) {
        String query;
        query = "SELECT A.id,A.day,S.firstName,S.lastName,S.phone1,S.phone2,A.amount " +
                "FROM account A , student S where S.id=A.idStudent" +
                " and A.idStudent="+account.getIdStudent()+" order by id desc ";
        ObservableList<Account> listAccountOfStudents = FXCollections.observableArrayList(new Account());
        listAccountOfStudents.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account accountOfStudent = new Account();
                accountOfStudent.setId(rs.getLong("id"));
                accountOfStudent.setFirstName(rs.getString("firstName"));
                accountOfStudent.setLastName(rs.getString("lastName"));
                accountOfStudent.setPhone1(rs.getString("phone1"));
                accountOfStudent.setPhone2(rs.getString("phone2"));
                accountOfStudent.setDay(rs.getString("day"));
                accountOfStudent.setAmount(rs.getFloat("amount"));
                listAccountOfStudents.add(accountOfStudent);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listAccountOfStudents;
    }


}
