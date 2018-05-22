package controller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import constant.Constant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;

import model.Order;
import model.User;
import model.Order;
import dao.UserDAO;
import java.io.IOException;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderController extends HttpServlet {
    private OrderDAO orderDAO ;
    private UserDAO userDAO;
    ObjectMapper mapper = new ObjectMapper();
    @Override
    public void init() throws ServletException {
        if(Constant.TEST) return;
        orderDAO = OrderDAO.getInstance();
        userDAO = UserDAO.getInstance();
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
                    out.print("error");
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
        String departure = req.getParameter("departure");
        String destination = req.getParameter("destination");
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        String comment = req.getParameter("comment");

//        System.out.println("LocalDate:"+LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE));
        User user = (User)req.getSession().getAttribute(Constant.SESSION_KEY_USER);
        Order order = new Order(-1,date,departure,destination,false,true,user,null,comment,"");
        //Order order = new Order("",date,departure,destination,comment,false,true,user,null);
        makeOrder(order);
    }

    private void makeOrder(Order order){
        orderDAO.create(order);
    }
}
