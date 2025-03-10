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
        String url= LOGIN_PAGE;
        try {
           String action = request.getParameter("action"); 
            System.out.println(action);
           if(action==null){
               url= LOGIN_PAGE;
           }else{
               if(action.equals("login")){
                   String strUsername = request.getParameter("txtUsername");
                   String strPassword = request.getParameter("txtPassword");
                   if(isValidLogin(strUsername, strPassword)){
                       url = "search.jsp";
                       UserDTO user = getUser(strUsername);
                        request.getSession().setAttribute("user", user);
                    }else{
                        request.setAttribute("message", "Incorrect UserID or Password");
                        url ="login.jsp";
                   }
               }else if (action.equals("logout")){
                   request.getSession().invalidate();
                   url = "login.jsp";
               }else if(action.equals("search")){
                   ProjectDAO pdao = new ProjectDAO();
                   String searchTearm = request.getParameter("searchTerm");
                   List<ProjectDTO> projects = pdao.searchByName(searchTearm);
                   request.setAttribute("projects", projects);
                   url ="search.jsp";
               }
           }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        }finally{
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
