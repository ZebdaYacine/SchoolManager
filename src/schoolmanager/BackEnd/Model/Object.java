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
    private String name;

    public Object() {
    }

    public Object(long id) {
        this.id = id;
    }

    public Object(String name) {
        this.name = name;
    }
    
    public Object(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
