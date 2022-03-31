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
    private String phoneTeacher;
    private String phone1;
    private String phone2;
    private String workePlace;
    private String rootName;
    private String nbrchair;
    private String nameType;
    private String nameLevel;
    private String className = this.getClass().getName();

    public Object() {
    }

    public Object(long id) {
        this.id = id;
    }

     public Object(String firstName, String lastName, String arg1, String arg2) {
        String obj = this.getClass().getName();
        this.firstName = firstName;
        this.lastName = lastName;
        switch (obj) {
            case "schoolmanager.BackEnd.Model.Student":
                this.phone1 = arg1;
                this.phone2 = arg2;
                break;
            case "schoolmanager.BackEnd.Model.Teacher":
                this.phoneTeacher = arg1;
                this.workePlace=arg2;
                break;
        }

    }

    public Object(long id, String firstName, String lastName, String arg1, String arg2) {
        String obj = this.getClass().getName();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        switch (obj) {
            case "schoolmanager.BackEnd.Model.Student":
                this.phone1 = arg1;
                this.phone2 = arg2;
                break;
            case "schoolmanager.BackEnd.Model.Teacher":
                this.phoneTeacher = arg1;
                this.workePlace=arg2;
                break;
        }

    }

    public Object(String rootName, String nbrchair) {
        this.rootName = rootName;
        this.nbrchair = nbrchair;
    }

    public Object(long id, String rootName, String nbrchair) {
        this.id = id;
        this.rootName = rootName;
        this.nbrchair = nbrchair;
    }

    public Object(long id, String arg) {
        String obj = this.getClass().getName();
        this.id = id;
        switch (obj) {
            case "schoolmanager.BackEnd.Model.Type":
                this.nameType = arg;
                break;
            case "schoolmanager.BackEnd.Model.Level":
                this.nameLevel = arg;
                break;
        }
    }

    public Object(String arg) {
        String obj = this.getClass().getName();
        switch (obj) {
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

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
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

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getWorkePlace() {
        return workePlace;
    }

    public void setWorkePlace(String workePlace) {
        this.workePlace = workePlace;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public String getNbrchair() {
        return nbrchair;
    }

    public void setNbrchair(String nbrchair) {
        this.nbrchair = nbrchair;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
