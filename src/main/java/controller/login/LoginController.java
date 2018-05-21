package controller.login;

import constant.Constant;
import dao.UserDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class LoginController extends HttpServlet {
    private UserDAO dao;
//    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        dao = UserDAO.getInstance();
        List<User> cust= dao.getAllUsers();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        resp.sendRedirect("./login.html");
//        req.getRequestDispatcher("./login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("uname");
        String password = req.getParameter("pass");
        String type = req.getParameter("type");
        User user = isSuccess(username, password);
        if (user != null) {
            HttpSession session = req.getSession();
//            User user = new User(1, "",  LocalDate.now(), "", "", username, password, type,"");
            session.setAttribute(Constant.SESSION_KEY_USER, user);
            session.setMaxInactiveInterval(20*60);
//            RequestDispatcher view ;
            if(user.getType().equals(Constant.TYPE_REQUESTOR)){

//                view=req.getRequestDispatcher("./requestor.jsp");
//                view=req.getRequestDispatcher("/order");
                resp.sendRedirect("/order");
            }
            else {
                resp.sendRedirect("/order");
//                view=req.getRequestDispatcher("./provider.jsp");
            }
//            view.forward(req, resp);
        } else {
            req.getSession().invalidate();
            resp.sendRedirect("./login.html");
//            req.getRequestDispatcher("/login").forward(req,resp);
        }
    }

    private User isSuccess(String username, String password) {
        User user =  UserDAO.getInstance().login(username,password);
        return user;
    }
}
