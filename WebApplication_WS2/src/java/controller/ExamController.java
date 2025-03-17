/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ExamDAO;
import dto.ExamDTO;
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
@WebServlet(name = "ExamController", urlPatterns = {"/ExamController"})
public class ExamController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ExamDAO examDAO = new ExamDAO();
        List<ExamDTO> examList = examDAO.getAllExams();

        request.setAttribute("examList", examList);
        request.getRequestDispatcher("examList.jsp").forward(request, response);
    }
}