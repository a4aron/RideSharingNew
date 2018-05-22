package controller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import constant.Constant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import model.User;
import model.Order;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderController extends HttpServlet {
    ObjectMapper mapper = new ObjectMapper();
    private OrderDAO orderDAO = OrderDAO.getInstance();
    private UserDAO userDAO = UserDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        String action = "";
        User user = (User)req.getSession().getAttribute(Constant.SESSION_KEY_USER);
        if(user.getType().equals(Constant.TYPE_REQUESTOR)){
            req.getRequestDispatcher("./requestor.jsp").forward(req,resp);
        }
        else {
            // Provider Page
            action = req.getParameter("action") != null ? req.getParameter("action") : "" ;
            if (action.equalsIgnoreCase("confirm")){
                     //confirm
                String id = req.getParameter("id");
                String provComment = req.getParameter("providercomment");
                Order o = orderDAO.getOrder(Integer.valueOf(id));
                o.setProvComment(provComment);
                o.setConfirmed(true);
                o.setProviderUser(userDAO.getUser(user.getId()));
                PrintWriter out =resp.getWriter();
                if(orderDAO.update(o)){
                    out.print("success");
                }
                else{
                    out.print("success");
                }
            }
            else if (action.equalsIgnoreCase("orderinfo")){
                String id = req.getParameter("id");
//                User u = userDAO.getUser(Integer.valueOf(id));
                Order o = orderDAO.getOrder(Integer.valueOf(id));
                PrintWriter out =resp.getWriter();
                out.print(mapper.writeValueAsString(o));
            }
            else {
                req.setAttribute("orders", orderDAO.getUnconfirmedOrder());
                req.getRequestDispatcher("./provider.jsp").forward(req, resp);
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//        req.getRequestDispatcher("./requestor.jsp").forward(req,resp);
    }
}
