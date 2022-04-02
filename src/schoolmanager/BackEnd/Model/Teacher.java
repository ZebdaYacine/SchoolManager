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
public class Teacher extends Person {

    private String phoneTeacher;
    private String workePlace;

    public Teacher() {
    }

    public Teacher(long id) {
        super(id);
    }

    public Teacher(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Teacher(String firstName, String lastName, String phoneTeacher, String workePlace) {
        super(firstName, lastName);
        this.phoneTeacher = phoneTeacher;
        this.workePlace = workePlace;
    }

    public Teacher(long id, String firstName, String lastName, String phoneTeacher, String workePlace) {
        super(id, firstName, lastName);
        this.phoneTeacher = phoneTeacher;
        this.workePlace = workePlace;
    }

    public String getPhoneTeacher() {
        return phoneTeacher;
    }

    public void setPhoneTeacher(String phoneTeacher) {
        this.phoneTeacher = phoneTeacher;
    }

    public String getWorkePlace() {
        return workePlace;
    }

    public void setWorkePlace(String workePlace) {
        this.workePlace = workePlace;
    }

    

}
