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
@WebServlet(name = "QuestionController", urlPatterns = {"/QuestionController"})
public class QuestionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int examID = Integer.parseInt(request.getParameter("examID"));

        QuestionDAO questionDAO = new QuestionDAO();
        List<QuestionDTO> questionList = questionDAO.getQuestionsByExam(examID);

        request.setAttribute("questionList", questionList);
        request.getRequestDispatcher("takeExam.jsp").forward(request, response);
    }
}