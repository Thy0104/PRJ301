/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import dao.UserDAO;
import dto.UserDTO;
import javax.servlet.http.HttpSession;

/**
 *
 * @author baothy2004
 */
public class AuthUtils {
    public static final String ADMIN_ROLE = "AD";
    private static final String USER_ROLE = "US";
    
    public static UserDTO getUser(String username) {
        UserDAO udao = new UserDAO();
        return udao.readById(username);
    } 
    
    public static boolean isValidLogin(String username, String password) {
        UserDTO user = getUser(username);
        System.out.println(user);
        System.out.println(password);
        return user != null && user.getPassword().equals(password);
    }
    
    public static UserDTO getUser(HttpSession session) {
        Object obj = session.getAttribute("user");
        return (obj != null) ? (UserDTO) obj : null;
    }
    
    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }
    
    public static boolean isAdmin(HttpSession session) {
        if (!isLoggedIn(session)) {
            return false;
        }
        UserDTO user = (UserDTO) session.getAttribute("user");
        return user.getRole().equals(ADMIN_ROLE);
    }
}
