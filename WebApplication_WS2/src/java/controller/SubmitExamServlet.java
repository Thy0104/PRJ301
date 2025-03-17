/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDAO;
import dto.QuestionDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author baothy2004
 */
@WebServlet(name = "SubmitExamServlet", urlPatterns = {"/SubmitExamServlet"})
public class SubmitExamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int examID = Integer.parseInt(request.getParameter("examID"));
        QuestionDAO questionDAO = new QuestionDAO();
        List<QuestionDTO> questions = questionDAO.getQuestionsByExam(examID);

        int score = 0;
        for (QuestionDTO question : questions) {
            String userAnswer = request.getParameter("question_" + question.getQuestionID());
            if (userAnswer != null && userAnswer.equals(question.getCorrectOption())) {
                score++;
            }
        }

        int totalMarks = questions.size();
        request.setAttribute("score", score);
        request.setAttribute("totalMarks", totalMarks);
        request.getRequestDispatcher("examDetails.jsp").forward(request, response);
    }
}
