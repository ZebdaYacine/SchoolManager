/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd.Model;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Zed Yacine
 */
public class Student extends Person {

    private String phone1;
    private String phone2;

    public Student() {
    }

    public Student(long id) {
        super(id);
    }

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Student(String firstName, String lastName,String phone1, String phone2) {
        super(firstName, lastName);
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    public Student(long id, String firstName, String lastName,String phone1, String phone2) {
        super(id, firstName, lastName);
        this.phone1 = phone1;
        this.phone2 = phone2;
    }
        

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
    
     
    
    
    

}
