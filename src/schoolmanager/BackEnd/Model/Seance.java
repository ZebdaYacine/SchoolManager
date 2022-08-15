/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Logger;

/**
 *
 * @author kadri
 */
public class Seance {
    private long id,idOffer,idTeacher,idRoom,idGroupe;
    private String date,nameOffer,nameGroup,nameTeacher,nameRoom,pTeacher;
    private int presenceTeacher;

    public Seance() {
    }

    public Seance(long id) {
        this.id = id;
    }

    public Seance(long idOffer, long idTeacher, long idRoom, long idGroupe, String date, int presenceTeacher) {
        this.idOffer = idOffer;
        this.idTeacher = idTeacher;
        this.idRoom = idRoom;
        this.idGroupe = idGroupe;
        this.date = date;
        this.presenceTeacher = presenceTeacher;
    }

    public Seance(long id, long idOffer, long idTeacher, long idRoom, long idGroupe, String date, String nameOffer, String nameGroup, String nameTeacher, String nameRoom, String pTeacher, int presenceTeacher) {
        this.id = id;
        this.idOffer = idOffer;
        this.idTeacher = idTeacher;
        this.idRoom = idRoom;
        this.idGroupe = idGroupe;
        this.date = date;
        this.nameOffer = nameOffer;
        this.nameGroup = nameGroup;
        this.nameTeacher = nameTeacher;
        this.nameRoom = nameRoom;
        this.pTeacher = pTeacher;
        this.presenceTeacher = presenceTeacher;
    }

    public String getpTeacher() {
        return pTeacher;
    }

    public void setpTeacher(String pTeacher) {
        this.pTeacher = pTeacher;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    
    public String getNameOffer() {
        return nameOffer;
    }

    public void setNameOffer(String nameOffer) {
        this.nameOffer = nameOffer;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(long idOffer) {
        this.idOffer = idOffer;
    }

    public long getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(long idTeacher) {
        this.idTeacher = idTeacher;
    }

    public long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(long idRoom) {
        this.idRoom = idRoom;
    }

    public long getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(long idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPresenceTeacher() {
        return presenceTeacher;
    }

    public void setPresenceTeacher(int presenceTeacher) {
        this.presenceTeacher = presenceTeacher;
    }

    public void PresentSeance() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(this);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Person.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
