/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "MainController",urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    public boolean isValidLogin (String username, String password){
        return username.equals("admin") && password.equals("12345678");
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String txtUsername = request.getParameter("txtUsername");
        String txtPassword = request.getParameter("txtPassword");
        
        if (txtUsername.trim().length()==0){
            out.println("Please enter username!");
            return;
        }
        
        if (txtPassword.trim().length()==0|| txtPassword.trim().length()<8){
            out.println("Password must be than 8 characters");
            return;
        }
        
        if(isValidLogin(txtUsername, txtPassword)){
            //forward search.html
            RequestDispatcher rd = request.getRequestDispatcher("search.html");
            rd.forward(request, response);
        }else{
           // forward/ redirect invalid.html
           //forward search.html
           //RequestDispatcher rd = request.getRequestDispatcher("invalid.html");
           //rd.forward(request, response);
           
           //redirect search.html
           response.sendRedirect("invalid.html");
           
           //compare RequestDispatcher /  response.sendRedirect? khac biet? khi nao nen dung?
        }    
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
