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
 * @author Zed Yacine
 */
public class PaiementService {

    private static final Section section = new Section();

    public static Results.Rstls addPaiement(Paiement paiement) {
        if (paiement == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement(""
                    + "insert into paiement (idStudent,idGroupe,day,amount,amountC)"
                    + " values (?,?,?,?,?)");
            stm.setLong(1, paiement.getStd().getId());
            stm.setLong(2, paiement.getGrp().getId());
            stm.setString(3, paiement.getDate());
            stm.setFloat(4, paiement.getAmount());
            stm.setFloat(5, paiement.getAmountC());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
    }

    public static Results.Rstls updatePaiement(Paiement  paiement) {
        if (paiement == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE "
                    + " paiement SET idStudent = ? , idGroupe = ?"
                    + ", day = ? ,amount = ? , amountC=?  "
                    + " WHERE id = ? ");
            stm.setLong(1, paiement.getStd().getId());
            stm.setLong(2, paiement.getGrp().getId());
            stm.setString(3, paiement.getDate());
            stm.setFloat(4, paiement.getAmount());
            stm.setFloat(5, paiement.getAmountC());
            stm.setLong(6, paiement.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_UPDATED;
        }
    }

    public static Results.Rstls deletePaiement(Paiement  paiement) {
        if (paiement == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM "
                    + " account WHERE id = ?");
            stm.setLong(1, paiement.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_DELETED;
        }
    }

    public static ObservableList<Paiement> getPaiementOfStudent(Paiement paiement) {
        String query;
        query = " SELECT A.id,A.idStudent,A.day,A.amount,A.amountC,A.idGroupe ,  G.idOffer " +
                " FROM paiement A ,  Groupe G " +
                " where A.idGroupe=G.id  and " +
                " A.idStudent="+paiement.getStd().getId()+" order by A.idStudent desc ";
        System.out.println(query);
        ObservableList<Paiement> listPaiementsOfStudents = FXCollections.observableArrayList(new Paiement());
        listPaiementsOfStudents.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paiement paiementOfStd = new Paiement();
                paiementOfStd.setId(rs.getLong("id"));
                paiementOfStd.setStd(StudentService.getAllStudents("search",
                                new Student(rs.getLong("idStudent")))
                        .get(0));
                Group grp1 = new Group();
                grp1=GroupService.getGroupbyId
                        (new Group(rs.getLong("idGroupe"))).get(0);
                paiementOfStd.setGrp(grp1);
                paiementOfStd.setGroupName(grp1.getNameGroup());
                paiementOfStd.setOfferName(grp1.getNameOffer());
                paiementOfStd.setTypeOfOffer(OfferService.getOfferbyid(
                        new Offer(rs.getLong("idOffer"))).get(0).getType());
                paiementOfStd.setAmount(rs.getFloat("amount"));
                paiementOfStd.setAmountC(rs.getFloat("amountC"));
                paiementOfStd.setDate(rs.getString("day"));
                listPaiementsOfStudents.add(paiementOfStd);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPaiementsOfStudents;
    }


}
