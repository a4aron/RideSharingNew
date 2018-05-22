package controller.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import constant.Constant;
import dao.UserDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class LoginController extends HttpServlet {

    private UserDAO dao;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        if(Constant.TEST) return;
        dao = UserDAO.getInstance();
        List<User> cust= dao.getAllUsers();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("./login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(Constant.TEST){
            String username = req.getParameter("uname");
            String remember = req.getParameter("remember");
            User user = new User(-1, "ming", LocalDate.MIN, "FairField", "1800000000", "zhaohangqi@gmail.com", "123", "requestor");
            if ("yes".equals(remember)) {
                Cookie c = new Cookie("user", username);
                c.setMaxAge(30 * 24 * 60 * 60);
                resp.addCookie(c);
            } else {
                Cookie c = new Cookie("user", null);
                c.setMaxAge(0);
                resp.addCookie(c);
            }
            HttpSession session = req.getSession();
            session.setAttribute(Constant.SESSION_KEY_USER, user);
            session.setMaxInactiveInterval(20 * 60);
            resp.sendRedirect("/order");
            return;
        }

        String username = req.getParameter("uname");
        String password = req.getParameter("pass");
        String remember = req.getParameter("remember");
        User user = isSuccess(username, password);
        if (user != null) {
            if ("yes".equals(remember)) {
                Cookie c = new Cookie("user", username);
                c.setMaxAge(30 * 24 * 60 * 60);
                resp.addCookie(c);
            } else {
                Cookie c = new Cookie("user", null);
                c.setMaxAge(0);
                resp.addCookie(c);
            }

            HttpSession session = req.getSession();
            session.setAttribute(Constant.SESSION_KEY_USER, user);
            session.setMaxInactiveInterval(20*60);
            resp.sendRedirect("/order");
        } else {
            resp.sendRedirect("./login.jsp");
        }
    }

    private User isSuccess(String username, String password) {
        if(Constant.TEST){
            return null;
        }
        User user =  UserDAO.getInstance().login(username,password);
        return user;
    }
}
