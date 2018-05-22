package controller.info;

import constant.Constant;
import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/updateInfo")
public class UserInfoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegistrationController doPost");
//        "name": $('#user_name').val(),
//                "address": $('#user_address').val(),
//                "email": $('#user_email').val(),
//                "tel": $('#user_tel').val()

        String email = req.getParameter("email");
        String tel = req.getParameter("tel");
        String address = req.getParameter("address");

        User user = (User)req.getSession().getAttribute(Constant.SESSION_KEY_USER);
        user.setAddress(address);
        user.setUserName(email);
        user.setTelNum(tel);

        UserDAO.getInstance().update(user);
    }
}
