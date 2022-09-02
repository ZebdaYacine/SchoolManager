/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Service;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import schoolmanager.BackEnd.Controller.CommunController;
import schoolmanager.BackEnd.Model.*;
import schoolmanager.BackEnd.Results;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static schoolmanager.BackEnd.Controller.PaiementSeancesController.*;
import static schoolmanager.BackEnd.DataBaseConnection.con;
import static schoolmanager.BackEnd.Service.GroupService.getGroupbyId;
import static schoolmanager.BackEnd.Service.OfferService.getOfferAttFromIdOffer;
import static schoolmanager.BackEnd.Service.RoomService.searchRoomById;
import static schoolmanager.BackEnd.Service.TeacherService.searchTeacherById;

/**
 * @author kadri
 */
public class SeanceService {

    private static long id;

    public static Results.Rstls addSeance(Seance seance) {
        if (seance == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement(""
                    + "insert into seance (`idOffer`, `idTeacher`, `idRoom`, `presenceTeacher`, `day`, `idGroupe`)"
                    + " values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setLong(1, seance.getIdOffer());
            stm.setLong(2, seance.getIdTeacher());
            stm.setLong(3, seance.getIdRoom());
            stm.setInt(4, seance.getPresenceTeacher());
            stm.setString(5, seance.getDate());
            stm.setLong(6, seance.getIdGroupe());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            seance.setId(id);
            ObservableList<Student> listStudentInGroup = StudentService.getAllStudentsFollow(seance, "empty");
            for (Student std : listStudentInGroup) {
                Follow flw = new Follow(std.getId(), seance.getId(), 0, 0);
                FollowService.addFollow(flw);
            }
            stm.close();
            return Results.Rstls.OBJECT_INSERTED;
        } catch (
                Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }

    }

    public static Results.Rstls updateSeance(Seance seance) {
        if (seance == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE "
                    + " seance SET idOffer = ?"
                    + ", idTeacher = ? , idRoom = ? ,presenceTeacher=? , day = ? ,idGroupe = ? "
                    + " WHERE id = ? ");
            stm.setLong(1, seance.getIdOffer());
            stm.setLong(2, seance.getIdTeacher());
            stm.setLong(3, seance.getIdRoom());
            stm.setInt(4, seance.getPresenceTeacher());
            stm.setString(5, seance.getDate());
            stm.setLong(6, seance.getIdGroupe());
            stm.setLong(7, seance.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_UPDATED;
        }
    }

    public static Results.Rstls updatePaiementSeance(Seance  seance) {
        if (seance == null) {
            return Results.Rstls.OBJECT_NOT_UPDATED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE "
                    + " seance SET idPaiement = ? "
                    + " WHERE id = ? ");
            stm.setLong(1, seance.getIdPaiement());
            stm.setLong(2, seance.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_UPDATED;
        }
    }

    public static Results.Rstls updateNbrSeanceInPaiement(Paiement  p) {
        if (p == null) {
            return Results.Rstls.OBJECT_NOT_UPDATED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE "
                    + " paiement SET nbrSeance = ? "
                    + " WHERE id = ? ");
            stm.setLong(1, p.getNbrSeance());
            stm.setLong(2, p.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_UPDATED;
        }
    }

    public static long getIdSeanceByIdPaiement(long idPaiement) {
        String query= "SELECT count(*) as id  FROM schoolmanager.seance where idPaiement="+idPaiement;
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

    public static int countSeanceOfPaiment(long idPaiement) {
        String query= "SELECT count(*) as 'nbrSeance' FROM seance where idPaiement="+idPaiement;
        int id=0;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id=rs.getInt("nbrSeance");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public static Results.Rstls deleteSeance(Seance seance) {
        if (seance == null) {
            return Results.Rstls.OBJECT_NOT_INSERTED;
        }
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM "
                    + " seance WHERE id = ?");
            stm.setLong(1, seance.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.OBJECT_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.OBJECT_NOT_DELETED;
        }
    }

    public static ObservableList<Seance> getAllSeances(Paiement paiement) {
        String query;
        if (paiement == null) {
            query = "SELECT * FROM seance order by id desc ";
        } else {
            query = "select S.id,S.presenceTeacher,S.idTeacher,F.presenceStudent,S.idRoom," +
                    "S.day,S.idGroupe,F.status " +
                    " from seance S , groupe G , follow F  " +
                    " where S.presenceTeacher=1 and F.idSeance= S.id and S.idGroupe=G.id " +
                    " and G.id=" + paiement.getGrp().getId()
                    + " and F.idStudent=" + paiement.getStd().getId() + " and F.status=0 group by S.id ";
        }
        ObservableList<Seance> listOffers = FXCollections.observableArrayList(new Seance());
        listOffers.remove(0);
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Seance seance = new Seance();
                seance.setId(rs.getLong("id"));
                //
                if (paiement == null) {
                    seance.setIdOffer(rs.getLong("idOffer"));
                    Offer off = new Offer(seance.getIdOffer());
                    seance.setNameOffer(getOfferAttFromIdOffer(off,"offerName"));
                }
                //
                seance.setIdTeacher(rs.getLong("idTeacher"));
                Teacher t = new Teacher(seance.getIdTeacher());
                t = searchTeacherById(t).get(0);
                seance.setNameTeacher(t.getFirstName() + " " + t.getLastName());
                //
                seance.setIdRoom(rs.getLong("idRoom"));
                Room r = new Room(seance.getIdRoom());
                r = searchRoomById(r).get(0);
                seance.setNameRoom(r.getName());
                //
                seance.setPresenceTeacher(rs.getInt("presenceTeacher"));
                if (seance.getPresenceTeacher() == 1) {
                    seance.setTest("حاضر");
                } else {
                    seance.setTest("غائب");
                }
                seance.setDate(rs.getString("day"));
                seance.setIdGroupe(rs.getLong("idGroupe"));
                Group g = new Group(seance.getIdGroupe());
                g = getGroupbyId(g).get(0);
                seance.setNameGroup(g.getNameGroup());
                if (paiement != null) {
                    seance.setPresenceStudent(rs.getInt("presenceStudent"));
                    if (seance.getPresenceStudent() == 1) {
                        seance.setTest1("حاضر");
                    } else {
                        seance.setTest1("غائب");
                    }
                    seance.setPresenceStudent(rs.getInt("status"));
                    CheckBox ch = new CheckBox();
                    ch.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            Seance ob = new Seance(seance.getId(), seance.getPresenceStudent(), seance.getPresenceTeacher());
                            float amountC= Float.parseFloat(amuntCLStatic.getText().split(" ")[0]);
                            if(amountC>=0){
                                if (newValue) {
                                    if(amountC >= priceSeance){
                                        amountC=amountC-priceSeance;
                                        amuntCLStatic.setText(amountC+" Da ");
                                        list.add(ob);
                                    }else{
                                        ch.setSelected(false);
                                        CommunController.alert("المبلغ غير كافي لتسديد هذه الحصة");
                                    }
                                } else {
                                    int index=0;
                                    boolean find=false;
                                    while (!find && index<=list.size()-1){
                                        if(list.get(index).getId()==ob.getId()){
                                            find=true;
                                        }else{
                                            index++;
                                        }
                                    }
                                    if(find){
                                        list.remove(index);
                                        amuntCLStatic.setText(amountC+priceSeance+" Da ");
                                    }
                                }
                            }
                        }
                    });
                    if (seance.getStatus() == 1) {
                        ch.setSelected(true);
                        ch.setDisable(true);
                    } else {
                        ch.setSelected(false);
                        ch.setDisable(false);
                    }
                    seance.setPstatus(ch);
                }

                listOffers.add(seance);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOffers;
    }


}
