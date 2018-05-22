package controller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.corba.se.idl.constExpr.Or;
import constant.Constant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;

import model.Order;
import model.User;
import java.io.IOException;

public class OrderController extends HttpServlet {
    ObjectMapper mapper = new ObjectMapper();
    private OrderDAO orderDAO = OrderDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        String action = "";
        if(Constant.TEST){
            req.getRequestDispatcher("./requestor.jsp").forward(req,resp);
            return;
        }
        User user = (User)req.getSession().getAttribute(Constant.SESSION_KEY_USER);
        if(user.getType().equals(Constant.TYPE_REQUESTOR)){
            req.getRequestDispatcher("./requestor.jsp").forward(req,resp);
        }
        else {
            // Provider Page
            action = req.getParameter("action") != null ? req.getParameter("action") : "" ;
            if (action.equalsIgnoreCase("confirm")){
                     //confirm
            }
            else {
                req.setAttribute("orders", orderDAO.getUnconfirmedOrder());
                req.getRequestDispatcher("./provider.jsp").forward(req, resp);
            }
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String departure = req.getParameter("departure");
        String destination = req.getParameter("destination");
        String date = req.getParameter("date");
        String comment = req.getParameter("comment");

        User user = (User)req.getSession().getAttribute(Constant.SESSION_KEY_USER);
        Order order = new Order("",date,departure,destination,comment,false,true,user,null);
        makeOrder(order);
    }

    private void makeOrder(Order order){

    }
}
