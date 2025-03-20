/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ExamDAO;
import dto.ExamDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dto.UserDTO;


/**
 *
 * @author baothy2004
 */
@WebServlet(name = "CreateExamServlet", urlPatterns = {"/CreateExamServlet"})
public class CreateExamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("loggedInUser");

        if (user == null || !"Instructor".equals(user.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String title = request.getParameter("examTitle");
        String subject = request.getParameter("subject");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        int totalMarks = Integer.parseInt(request.getParameter("totalMarks"));
        int duration = Integer.parseInt(request.getParameter("duration"));

        ExamDAO examDAO = new ExamDAO();
        ExamDTO newExam = new ExamDTO(0, title, subject, categoryID, totalMarks, duration);
        boolean success = examDAO.createExam(newExam);

        if (success) {
            response.sendRedirect("examList.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to create exam.");
            request.getRequestDispatcher("createExam.jsp").forward(request, response);
        }
    }
}