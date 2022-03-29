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
public class Object {

    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String rootName;
    private String nameType;
    private String nameLevel;
    private String className = this.getClass().getName();

    public Object() {
    }

    public Object(long id) {
        this.id = id;
    }

    public Object(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Object(long id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Object(long id, String arg) {
        String obj = this.getClass().getName();
        this.id = id;
        switch (obj) {
            case "schoolmanager.BackEnd.Model.Room":
                this.rootName = arg;
                break;
            case "schoolmanager.BackEnd.Model.Type":
                this.nameType = arg;
                break;
            case "schoolmanager.BackEnd.Model.Level":
                this.nameLevel = arg;
                break;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoomName() {
        return rootName;
    }

    public void setRoomName(String rootName) {
        this.rootName = rootName;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getNameLevel() {
        return nameLevel;
    }

    public void setNameLevel(String nameLevel) {
        this.nameLevel = nameLevel;
    }

}
