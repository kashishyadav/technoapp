/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import techquiz.dbutil.DBConnection;
import techquiz.dto.QuestionPojo;
import techquiz.dto.QuestionStore;

/**
 *
 * @author devanshi
 */
public class QuestionDAO {
    public static boolean addQuestions(QuestionStore ques)throws SQLException
    {
     PreparedStatement ps=DBConnection.getConnection().prepareStatement("Insert into question values(?,?,?,?,?,?,?,?,?)");
     ArrayList<QuestionPojo> questionList=ques.getAllQuestions();
     boolean res=false;
     for(QuestionPojo que:questionList)
     {
         ps.setString(1,que.getExamId());
         ps.setInt(2,que.getQno());
         ps.setString(3,que.getQuestion());
         System.out.println("in que add dao:"+que.getQuestion());
         ps.setString(4,que.getAnswer1());
         ps.setString(5,que.getAnswer2());
         ps.setString(6,que.getAnswer3());
         ps.setString(7,que.getAnswer4());
         ps.setString(8,que.getCorrectAnswer());
         ps.setString(9,que.getLanguage());
         ps.executeUpdate(); 
         res=true;
         System.out.println(res);
     }
     return res;
    }
    
    public static boolean removeQuestions(String examID)throws SQLException
    {
       PreparedStatement ps2=DBConnection.getConnection().prepareStatement("delete from question where examid=?");
       ps2.setString(1,examID);
       int ans=ps2.executeUpdate();
       return (ans!=0);
    }
    
    
    public static ArrayList<QuestionPojo> getQuestionsByExamId(String examId)throws SQLException
    {
        PreparedStatement ps1=DBConnection.getConnection().prepareStatement("Select * from question where examid=? order by qno");
        ArrayList<QuestionPojo> questionList=new ArrayList<>();
        ps1.setString(1, examId);
        ResultSet rs=ps1.executeQuery();
        while(rs.next())
        {
            int qno=rs.getInt(2);
            String question=rs.getString(3);
            System.out.println("question in dao by id: "+question);
            String option1=rs.getString(4);
            String option2=rs.getString(5);
            String option3=rs.getString(6);
            String option4=rs.getString(7);
            String correctAnswer=rs.getString(8);
            String language=rs.getString(9);
            QuestionPojo obj=new QuestionPojo(examId,qno,option1,option2,option3,option4,correctAnswer,language,question);
            System.out.println("in grt by id:"+obj);
            questionList.add(obj);
        }
        return questionList;
    }
}
