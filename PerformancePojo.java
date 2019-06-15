/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dto;

/**
 *
 * @author devanshi
 */
public class PerformancePojo {
    private String userId;
    private String examId;
    private String language;
    private int right;
    private int wrong;
    private int unattempted;
    private double per;

    public PerformancePojo()
    {
        
    }
    public PerformancePojo(String userId, String examId, String language, int right, int wrong, int unattempted, double per) {
        this.userId = userId;
        this.examId = examId;
        this.language = language;
        this.right = right;
        this.wrong = wrong;
        this.unattempted = unattempted;
        this.per = per;
    }

    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getUnattempted() {
        return unattempted;
    }

    public void setUnattempted(int unattempted) {
        this.unattempted = unattempted;
    }

    public double getPer() {
        return per;
    }

    public void setPer(double per) {
        this.per = per;
    }
    
}
