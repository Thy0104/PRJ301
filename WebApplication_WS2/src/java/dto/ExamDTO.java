/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author baothy2004
 */
public class ExamDTO {
    private int examID;
    private String examTitle;
    private String subject;
    private int categoryID;
    private int totalMarks;
    private int duration; // in minutes

    public ExamDTO() {}

    public ExamDTO(int examID, String examTitle, String subject, int categoryID, int totalMarks, int duration) {
        this.examID = examID;
        this.examTitle = examTitle;
        this.subject = subject;
        this.categoryID = categoryID;
        this.totalMarks = totalMarks;
        this.duration = duration;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
