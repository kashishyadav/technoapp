/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import techquiz.dbutil.DBConnection;
import techquiz.dto.UserPojo;

/**
 *
 * @author devanshi
 */
public class UserDAO {
    private static PreparedStatement ps,ps1,ps2;
    static
    {
        try
        {
            ps=DBConnection.getConnection().prepareStatement("Select * from users where userid=? and password=? and usertype=?");
        }
        catch(SQLException ex)
        {
           ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Cannot connect to the DB!"); 
        }
    }
    public static boolean validateUser(UserPojo user)throws SQLException
    {  boolean done=false;                                                       
      ps.setString(1,user.getUserId());
      ps.setString(2,user.getPassword());
      ps.setString(3,user.getUserType().toLowerCase());
      ResultSet rs=ps.executeQuery();
     if(rs.next())
         done=true;
        System.out.println("done val:"+done);
        return done;
    }
    public static boolean addUser(UserPojo user)throws SQLException
    {
        ps1=DBConnection.getConnection().prepareStatement("Select * from users where userid=?");
        ps1.setString(1,user.getUserId());
        boolean status=true;
        ResultSet rs=ps1.executeQuery();
        if(rs.next())
        {
            status=false;
        }
        else
        {
            ps2=DBConnection.getConnection().prepareStatement("Insert into users values(?,?,?)");
            ps2.setString(1,user.getUserId());
            ps2.setString(2,user.getPassword());
            ps2.setString(3,user.getUserType());
            ps2.executeUpdate();
        }
        return status;
    }
    
    public static boolean updateUser(UserPojo user)throws SQLException
    {
        PreparedStatement ps3=DBConnection.getConnection().prepareStatement("update users set password=? where userid=? and usertype='Student'");
        ps3.setString(1, user.getPassword());
        ps3.setString(2,user.getUserId());
        int ans=ps3.executeUpdate();
        return (ans!=0); 
    }
}
