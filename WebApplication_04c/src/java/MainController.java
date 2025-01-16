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
           //So sánh RequestDispatcher và response.sendRedirect:
           /* Cách chuyển hướng:

            RequestDispatcher chuyển tiếp yêu cầu nội bộ trong server mà không thay đổi URL trên trình duyệt.
            response.sendRedirect thực hiện chuyển hướng từ client, tạo một yêu cầu HTTP mới và thay đổi URL trên trình duyệt.
            Hiệu suất:

            RequestDispatcher nhanh hơn vì không cần vòng lặp client-server.
            response.sendRedirect chậm hơn vì có thêm một yêu cầu từ client đến server.
            Phạm vi dữ liệu:

            RequestDispatcher cho phép chia sẻ dữ liệu qua request.setAttribute().
            response.sendRedirect không giữ dữ liệu vì yêu cầu mới được tạo.
            Trường hợp sử dụng:

            RequestDispatcher dùng khi chuyển tiếp trong cùng ứng dụng mà không cần thay đổi URL.
            response.sendRedirect dùng khi cần chuyển đến trang ngoài hoặc URL khác với URL mới hiển thị trên trình duyệt.
            Giữ trạng thái:
            RequestDispatcher giữ trạng thái request và response.
            response.sendRedirect không giữ trạng thái request và response.
            
            Khi nào nên dùng:
            RequestDispatcher:Khi cần chuyển tiếp yêu cầu mà không cần thay đổi URL trên trình duyệt.
                              Khi muốn giữ lại dữ liệu trong đối tượng request.
                

            response.sendRedirect:Khi cần chuyển hướng đến URL khác hoặc ứng dụng khác.
                                  Khi muốn thay đổi URL để phản ánh trang mới.*/


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
