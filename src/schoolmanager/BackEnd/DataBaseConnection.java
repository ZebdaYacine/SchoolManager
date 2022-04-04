/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanager.BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import static schoolmanager.LocalSettings.Password;
import static schoolmanager.LocalSettings.url;
import static schoolmanager.LocalSettings.user;
/**
 *
 * @author Zed Yacine
 */
public class DataBaseConnection {

    public static Connection con;
    public static Statement stm;
    public static ResultSet rst;


    public static Connection Connect() {
        try {
            con = (Connection) DriverManager.getConnection(url, user, Password);
            System.out.println("Connection with Data Base");
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + e.getStackTrace());
            System.err.println("No Connection with Data Base");
        }
        return con;
    }

}
