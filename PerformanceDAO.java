/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import techquiz.dbutil.DBConnection;
import techquiz.dto.PerformancePojo;
import techquiz.dto.StudentScorePojo;

/**
 *
 * @author devanshi
 */
public class PerformanceDAO {
    private static Statement st;
    static
    {
        try
        {    
        st=DBConnection.getConnection().createStatement();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Cannot connect to the DB!");
        }
    }
    
    public static ArrayList<String> getAllStudentId()throws SQLException
    {
        ResultSet rs=st.executeQuery("select distinct userid from performance");
        ArrayList<String> studentList=new ArrayList<>();
        while(rs.next())
        {
            studentList.add(rs.getString(1));
        }
        return studentList;
    }
    
    //to check when the student visits the score page to check its scores and it might happen that the student havent given any exam yet. 
    public static ArrayList<String> getAllExamId(String studentId)throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("select examid from performance where userid=?");
        ps.setString(1,studentId);
        ResultSet rs=ps.executeQuery();
        ArrayList<String> examList=new ArrayList<>();
        while(rs.next())
        {
            examList.add(rs.getString(1));
        }
        return examList;
    }
    public static StudentScorePojo getScore(String examId,String studentId)throws SQLException
    {
        PreparedStatement ps1=DBConnection.getConnection().prepareStatement("select per,language from performance where userid=? and examid=?");
        ps1.setString(1,studentId);
        ps1.setString(2,examId);
        StudentScorePojo obj=new StudentScorePojo();
        ResultSet rs=ps1.executeQuery();
        if(rs.next())
        {
            obj.setPerc(rs.getDouble(1));
            obj.setLanguage(rs.getString(2));
        }
        return obj;
    }
    
    public static void addPerformance(PerformancePojo performance)throws SQLException
    {
        PreparedStatement ps2=DBConnection.getConnection().prepareStatement("Insert into performance values(?,?,?,?,?,?,?)");
        ps2.setString(1,performance.getUserId());
        ps2.setString(2,performance.getExamId());
        ps2.setInt(3,performance.getRight());
        ps2.setInt(4,performance.getWrong());
        ps2.setInt(5,performance.getUnattempted());
        ps2.setDouble(6,performance.getPer());
        ps2.setString(7,performance.getLanguage());
        ps2.executeUpdate();
        
    }
    public static ArrayList<PerformancePojo> getAllData()throws SQLException
    {
        ArrayList<PerformancePojo> performanceList=new ArrayList<>();
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("Select * from perormance");
        while(rs.next())
        {
         PerformancePojo obj=new PerformancePojo();
         obj.setUserId(rs.getString(1));
         obj.setExamId(rs.getString(2));
         obj.setRight(rs.getInt(3));
         obj.setWrong(rs.getInt(4));
         obj.setUnattempted(rs.getInt(5));
         obj.setPer(rs.getDouble(6));
         obj.setLanguage(rs.getString(7));
         performanceList.add(obj);
        }
        return performanceList;
    }
    
}
