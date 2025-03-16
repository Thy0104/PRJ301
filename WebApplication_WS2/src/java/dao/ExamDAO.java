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
        List<ExamDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblExams";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ExamDTO(
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
        return list;
    }

    public ExamDTO getExamByID(int examID) {
        String sql = "SELECT * FROM tblExams WHERE exam_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, examID);
            ResultSet rs = stmt.executeQuery();
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
