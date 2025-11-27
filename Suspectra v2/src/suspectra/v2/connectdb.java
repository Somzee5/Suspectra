/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suspectra.v2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class connectdb {
    Connection conn = null;
    public static Connection ConnectDB()
    {
        try 
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:sqlite:login.sqlite");
            return conn;
        }catch (SQLException | ClassNotFoundException e) {
            System.err.println("JavaConnect : "+e.getMessage());            
            return null;
          }
    }
}
