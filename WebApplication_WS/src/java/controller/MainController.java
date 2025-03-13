/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjectDAO;
import dao.UserDAO;
import dto.ProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author baothy2004
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    private ProjectDAO projectDAO = new ProjectDAO();
    private static final String LOGIN_PAGE ="login.jsp";
    
    public UserDTO getUser (String strUsername){
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readById(strUsername);
        return user;
    }
    
    public boolean isValidLogin (String strUsername, String strPassword){
        UserDTO user = getUser(strUsername);
        System.out.println(user);
        System.out.println(strPassword);
        return user != null && user.getPassword().equals(strPassword);
    }
    
    public void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm == null) {
            searchTerm = "";
        }
        List<ProjectDTO> projects = projectDAO.searchByName(searchTerm);
        request.setAttribute("projects", projects);
        request.setAttribute("searchTerm", searchTerm);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                HttpSession session = request.getSession();
                UserDTO user = (UserDTO) session.getAttribute("user");

                switch (action) {
                    case "login":
                        String strUsername = request.getParameter("txtUsername");
                        String strPassword = request.getParameter("txtPassword");
                        if (isValidLogin(strUsername, strPassword)) {
                            url = "search.jsp";
                            user = getUser(strUsername);
                            session.setAttribute("user", user);
                            search(request, response);
                        } else {
                            request.setAttribute("message", "Incorrect UserID or Password");
                        }
                        break;
                        
                    case "logout":
                        if (session.getAttribute("user") != null) {
                            session.invalidate(); 
                        }
                        url = LOGIN_PAGE;
                        break;
                        
                    case "search":
                        if (user != null) {
                            search(request, response);
                            url = "search.jsp";
                        }
                        break;

                    case "delete":
                        if (user != null && user.getRole().equals("Founder")) {
                            String id = request.getParameter("id");
                            if (id != null && !id.trim().isEmpty()) {
                                projectDAO.delete(id);
                            }
                            search(request, response);
                            url = "search.jsp";
                        }
                        break;

                    case "add":
                        if (user != null && user.getRole().equals("Founder")) {
                            boolean checkError = false;
                            String project_id = request.getParameter("txtProjectID");
                            String project_name = request.getParameter("txtProjectName");
                            String description = request.getParameter("txtDescription");
                            String status = request.getParameter("txtStatus");
                            String estimated_launch = request.getParameter("txtEstimatedLaunch");

                            if (project_id == null || project_id.trim().isEmpty()) {
                                checkError = true;
                                request.setAttribute("txtProjectID_error", "Project ID cannot be empty.");
                            }

                            ProjectDTO project = new ProjectDTO(project_id, project_name, description, status, estimated_launch);
                            
                            if (!checkError) {
                                projectDAO.create(project);
                                search(request, response);
                                url = "search.jsp";
                            } else {
                                url = "ProjectForm.jsp";
                                request.setAttribute("project", project);
                            }
                        }
                        break;
                        
                    default:
                        url = LOGIN_PAGE;
                        break;
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