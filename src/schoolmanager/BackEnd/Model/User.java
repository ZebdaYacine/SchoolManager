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
public class User {

    private int id;
    private String userName;
    private String password;
    private String role;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(int id, String userName, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void PresentUser(User user) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(user);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Person.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
