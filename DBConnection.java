/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author devanshi
 */
public class DBConnection {
    
    private static Connection conn;
    static
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
              conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-850CNGA:1521/XE","techquiz","abc"); 
            JOptionPane.showMessageDialog(null,"Connected succesfully!");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Cannot connect to the DB!");
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try
        {
            conn.close();
            JOptionPane.showMessageDialog(null,"Connection successfully closed!");
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Cannot close the Connecton!");
        }
    }
}


