package controller.regist;

import dao.OrderDAO;
import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class RegistrationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegistrationController doPost");
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String tel = req.getParameter("tel");
        String type = req.getParameter("type");
        String address = req.getParameter("address");
        User user = new User(-1,fullname,LocalDate.of(1993,8,01),address,tel,email,password,type);
        if(makeAccount(user)){
            resp.sendRedirect("./login.jsp");
        }

    }

    private boolean makeAccount(User user){
        return UserDAO.getInstance().create(user);
    }
}
