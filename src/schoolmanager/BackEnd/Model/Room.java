/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Model;

/**
 *
 * @author Zed Yacine
 */
public class Room extends Object {

    private String nbrchair;

    public Room() {
    }

    public Room(long id) {
        super(id);
    }

    public Room(String name,String nbrchair) {
        super(name);
        this.nbrchair = nbrchair;
    }
    
    public Room( long id, String name , String nbrchair) {
        super(id, name);
        this.nbrchair = nbrchair;
    }

  
    public String getNbrchair() {
        return nbrchair;
    }

    public void setNbrchair(String nbrchair) {
        this.nbrchair = nbrchair;
    }
    
    

}
