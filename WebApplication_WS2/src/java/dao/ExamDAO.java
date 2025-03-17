/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ExamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;
/**
 *
 * @author baothy2004
 */
public class ExamDAO {
    public List<ExamDTO> getAllExams() {
        List<ExamDTO> exams = new ArrayList<>();
        String sql = "SELECT * FROM tblExams";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                exams.add(new ExamDTO(
                    rs.getInt("exam_id"),
                    rs.getString("exam_title"),
                    rs.getString("subject"),
                    rs.getInt("category_id"),
                    rs.getInt("total_marks"),
                    rs.getInt("duration")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exams;
    }

    public boolean createExam(ExamDTO exam) {
        String sql = "INSERT INTO tblExams (exam_title, subject, category_id, total_marks, duration) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, exam.getExamTitle());
            ps.setString(2, exam.getSubject());
            ps.setInt(3, exam.getCategoryID());
            ps.setInt(4, exam.getTotalMarks());
            ps.setInt(5, exam.getDuration());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ExamDTO getExamByID(int examID) {
        String sql = "SELECT * FROM tblExams WHERE exam_id = ?";
        try (Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ExamDTO(
                    rs.getInt("exam_id"),
                    rs.getString("exam_title"),
                    rs.getString("subject"),
                    rs.getInt("category_id"),
                    rs.getInt("total_marks"),
                    rs.getInt("duration")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
