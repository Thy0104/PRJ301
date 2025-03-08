/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjectDAO;
import dto.ProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Utils.AuthUtils;

/**
 *
 * @author baothy2004
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private ProjectDAO projectDAO = new ProjectDAO();

    private static final String LOGIN_PAGE = "login.jsp";

    private String processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        //
        String strUsername = request.getParameter("txtUsername");
        String strPassword = request.getParameter("txtPassword");
        if (AuthUtils.isValidLogin(strUsername, strPassword)) {
            url = "search.jsp";
            UserDTO user = AuthUtils.getUser(strUsername);
            request.getSession().setAttribute("user", user);

            // search
            processSearch(request, response);
        } else {
            request.setAttribute("message", "Incorrect Username or Password");
            url = "login.jsp";
        }
        //
        return url;
    }

    private String processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        //
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            request.getSession().invalidate(); // Hủy bỏ session
            url = "login.jsp";
        }
        //
        return url;
    }

    public String processSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            // search
            String searchTerm = request.getParameter("searchTerm");
            if (searchTerm == null) {
                searchTerm = "";
            
            request.setAttribute("projects", projects);
            request.setAttribute("searchTerm", searchTerm);
        }
        return url;
    }

    public String processDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isAdmin(session)) {
            String id = request.getParameter("id");
            // search
            processSearch(request, response);
            url = "search.jsp";
        }
        return url;
    }

    public String processAdd(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String url = LOGIN_PAGE;
    HttpSession session = request.getSession();
    if (AuthUtils.isFounder(session)) {  // Only Founders can add projects
        try {
            boolean checkError = false;

            String project_name = request.getParameter("txtProjectName");
            String description = request.getParameter("txtDescription");
            String status = request.getParameter("txtStatus");
            String estimated_launch = request.getParameter("txtEstimatedLaunch");

            if (project_name == null || project_name.trim().isEmpty()) {
                checkError = true;
                request.setAttribute("txtProjectName_error", "Project name cannot be empty.");
            }

            if (!checkError) {
                ProjectDTO project = new ProjectDTO(project_name, description, status, estimated_launch);
                projectDAO.create(project);
                url = processSearch(request, response);
            } else {
                url = "ProjectForm.jsp";
                request.setAttribute("project", new ProjectDTO(project_name, description, status, estimated_launch));
            }
        } catch (Exception e) {
            log("Error at processAdd: " + e.getMessage());
        }
    }
    return url;
}


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            System.out.println("action: " + action);
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                if (action.equals("login")) {
                    url = processLogin(request, response);
                } else if (action.equals("logout")) {
                    url = processLogout(request, response);
                } else if (action.equals("search")) {
                    url = processSearch(request, response);
                } else if (action.equals("delete")) {
                    url = processDelete(request, response);
                } else if (action.equals("add")) {
                    url = processAdd(request, response);
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}