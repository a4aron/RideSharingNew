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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderController extends HttpServlet {
    private OrderDAO orderDAO ;

    @Override
    public void init() throws ServletException {
        if(Constant.TEST) return;
        orderDAO = OrderDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
            String action = req.getParameter("action") != null ? req.getParameter("action") : "" ;
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

        System.out.println("LocalDate:"+LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE));
        User user = (User)req.getSession().getAttribute(Constant.SESSION_KEY_USER);
        Order order = new Order(-1,LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE),departure,destination,comment,false,true,user,null,comment,"");
        //Order order = new Order("",date,departure,destination,comment,false,true,user,null);
        makeOrder(order);
    }

    private void makeOrder(Order order){
        orderDAO.create(order);
    }
}
