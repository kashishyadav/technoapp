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
import techquiz.dbutil.DBConnection;
import techquiz.dto.ExamPojo;

/**
 *
 * @author devanshi
 */
public class ExamDAO {
    
    //this is used to generate the exam id for the next exam which is need to be set
    public static String getExamId()throws SQLException
    {
        String qry="Select count(*) as totalrows from exams";
        int rowCount=0;
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery(qry);
        if(rs.next())
        {
            rowCount=rs.getInt(1);
        }
        String newId="EX-"+(rowCount+1);
        return newId;
    }
    
    //used to add the exam created by admin in the db
    public static boolean addExam(ExamPojo exam)throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("Insert into exams values(?,?,?)");
        ps.setString(1,exam.getExamId());
        ps.setString(2,exam.getLanguage());
        ps.setInt(3,exam.getTotalQuestion());
        int ans=ps.executeUpdate();
        return (ans!=0);
    }
    
    public static boolean removeExam(String examId)throws SQLException
    {
       PreparedStatement ps4=DBConnection.getConnection().prepareStatement("delete from exams where examid=?");
       ps4.setString(1,examId);
       int ans=ps4.executeUpdate();
       return (ans!=0);
    }
    
    //to check that for the selected subject the exam is set or not.
    public static boolean isPaperSet(String language)throws SQLException
    {
        PreparedStatement ps1=DBConnection.getConnection().prepareStatement("Select examid from exams where language=?");
        ps1.setString(1,language);
        ResultSet rs=ps1.executeQuery();
        return rs.next();
    }
    
    //used when we give option to student to select those ids from drop down which papers he can give and he cannot give those exams which he has already given so we directly subtract it from performance table.
    public static ArrayList<String> getExamIdBySubject(String userid,String language)throws SQLException
    {
        PreparedStatement ps2=DBConnection.getConnection().prepareStatement("Select examid from exams where language=? minus select examid from performance where userid=?");
        ps2.setString(1,language);
        ps2.setString(2,userid);
        ArrayList<String> examId=new ArrayList<>();
        ResultSet rs=ps2.executeQuery();
        while(rs.next())
        {
            examId.add(rs.getString(1));
        }
        return examId;      
    }
    //for the no. of questions to display to the student to attempt in a question paper.
    public static int getQuestionCountByExam(String examId)throws SQLException
    {
        PreparedStatement ps3=DBConnection.getConnection().prepareStatement("Select total_question from exams where examid=?");
        ps3.setString(1,examId);
        int rowCount=0;
        ResultSet rs=ps3.executeQuery();
        if(rs.next())
        {
            rowCount=rs.getInt(1);
        }
        return rowCount;
    }
    
    //used when we edit the paper of existing papers.
    public static ArrayList<String> getExamIdByLanguage(String language)throws SQLException
    {
        PreparedStatement ps1=DBConnection.getConnection().prepareStatement("Select examid from exams where language=?");
        ps1.setString(1,language);
        ArrayList<String> examIds=new ArrayList<>();
        ResultSet rs=ps1.executeQuery();
        while(rs.next())
        {
            examIds.add(rs.getString(1));
        }
       return examIds;
    }
    
    
    
}
