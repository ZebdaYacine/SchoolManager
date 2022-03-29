/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Controller.model;

/**
 *
 * @author Zed Yacine
 */
public class Student extends Object{

    public Student() {
    }

    public Student(long id) {
        super(id);
    }

    public Student(String firstName, String lastName, String phone) {
        super(firstName, lastName, phone);
    }

    public Student(long id, String firstName, String lastName, String phone) {
        super(id, firstName, lastName, phone);
    }
    
    
    
}
