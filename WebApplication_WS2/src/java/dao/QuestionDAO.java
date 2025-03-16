/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.QuestionDTO;
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
public class QuestionDAO {
    public List<QuestionDTO> getQuestionsByExam(int examID) {
        List<QuestionDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblQuestions WHERE exam_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, examID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new QuestionDTO(
                    rs.getInt("question_id"),
                    rs.getInt("exam_id"),
                    rs.getString("question_text"),
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d"),
                    rs.getString("correct_option").charAt(0)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}