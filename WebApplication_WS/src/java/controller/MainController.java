package controller;

import dao.ProjectDAO;
import dao.UserDAO;
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
import utils.AuthUtils;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private ProjectDAO projectDAO = new ProjectDAO();
    private static final String LOGIN_PAGE = "login.jsp";

    private String processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        if (AuthUtils.isValidLogin(username, password)) {
            url = "search.jsp";
            UserDTO user = AuthUtils.getUser(username);
            request.getSession().setAttribute("user", user);
            processSearch(request, response);
        } else {
            request.setAttribute("message", "Incorrect Username or Password");
        }
        return url;
    }

    private String processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            session.invalidate();
        }
        return url;
    }

    public String processSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            String searchTerm = request.getParameter("searchTerm");
            if (searchTerm == null) {
                searchTerm = "";
            }
            List<ProjectDTO> projects = projectDAO.searchByName(searchTerm);
            request.setAttribute("projects", projects);
            request.setAttribute("searchTerm", searchTerm);
            url = "search.jsp";
        }
        return url;
    }

    public String processDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isAdmin(session)) {
            String project_id = request.getParameter("project_id");
            projectDAO.deleteProject(project_id);
            url = processSearch(request, response);
        }
        return url;
    }

    public String processAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isAdmin(session)) {
            try {
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
                    url = processSearch(request, response);
                } else {
                    url = "projectForm.jsp";
                    request.setAttribute("project", project);
                }
            } catch (Exception e) {
                e.printStackTrace();
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
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                switch (action) {
                    case "login":
                        url = processLogin(request, response);
                        break;
                    case "logout":
                        url = processLogout(request, response);
                        break;
                    case "search":
                        url = processSearch(request, response);
                        break;
                    case "delete":
                        url = processDelete(request, response);
                        break;
                    case "add":
                        url = processAdd(request, response);
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
        return "Main Controller for Project Management";
    }
}
