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
public class Teacher extends Object{

    public Teacher() {
    }

    public Teacher(long id) {
        super(id);
    }

    public Teacher(String firstName, String lastName, String phone) {
        super(firstName, lastName, phone);
    }

    public Teacher(long id, String firstName, String lastName, String phone) {
        super(id, firstName, lastName, phone);
    }
    
    
    
}
