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
 * @author RD°INFO
 */
public class Group {

    private long id;
    private String nameOffer;
    private String nameGroup;
    private long idOffer;
    private int nbrPlace;
    private int nbrRest;

    public Group() {
    }

    public Group(int id) {
        this.id = id;
    }

    public Group(String nameOffer, String nameGroupe, int nbrPlace) {
        this.nameOffer = nameOffer;
        this.nameGroup = nameGroupe;
        this.nbrPlace = nbrPlace;
    }

    public Group( String name, int idOffer, int nbrPlace) {
        this.nameOffer = name;
        this.idOffer = idOffer;
        this.nbrPlace = nbrPlace;
    }



    public Group(int id, String name, int idOffer, int nbrPlace) {
        this.id = id;
        this.nameOffer = name;
        this.idOffer = idOffer;
        this.nbrPlace = nbrPlace;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOffer() {
        return nameOffer;
    }

    public void setNameOffer(String nameOffer) {
        this.nameOffer = nameOffer;
    }

    public long getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(int idOffer) {
        this.idOffer = idOffer;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public int getNbrRest() {
        return nbrRest;
    }

    public void setNbrRest(int nbrRest) {
        this.nbrRest = nbrRest;
    }

    public void PresentGroupe() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(this);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Person.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
