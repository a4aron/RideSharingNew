package dao;

import dbconnection.DatabaseException;
import model.Order;
import model.User;
import dao.UserDAO;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO extends DAO {
    // singleton + lazy init
    private static OrderDAO instance;
    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    private OrderDAO() {
        super();
    }

    public boolean create(Order order) {
        int rec_num = databaseConnection.insert(
                "INSERT INTO `orders`(`requesterId`, `date`, `departure`, `destination`, `confirmed`, `active`, `reqComment`, `provComment`) " +
                        "VALUES ( ?, ?, ?, ? , ?, ?, ?, ?)",
                new Object[] {
                        order.getRequestorUser().getId(), order.getDate(), order.getDeparture(),
                        order.getDestination(),0,1,order.getReqComment(),order.getProvComment()
                }
        ).size();
       return  rec_num > 0 ? true : false;
    }

    public boolean update(Order order) {
        return databaseConnection.update(
                "UPDATE `orders` SET `requesterId`=?,`providerId`=?,`date`=?," +
                        "`departure`=?,`destination`=?,`confirmed`=?,`active`=?  WHERE `id` = ?;",
                new Object[] {
                        order.getRequestorUser().getId(), order.getProviderUser().getId(), order.getDate(),
                        order.getDeparture(), order.getDestination(), order.isConfirmed(),
                        order.isActive(), order.getId()
                }
        ) == 1;
    }

    public boolean delete(long id) {
        return databaseConnection.delete(
                "DELETE FROM `orders` WHERE `id` = ?",
                new Object[]{id}
        ) == 1;

    }

    public List<Order> getUnconfirmedOrder (){
        List<Order> ret = new ArrayList<>();
        ResultSet rs = databaseConnection.select("select * from `orders` WHERE  confirmed = 0 AND active = 1", new Object[]{});
        try {
            while(rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                User requestor = UserDAO.getInstance().getUser(rs.getInt("requesterId"));
                o.setRequestorUser(requestor);
                o.setDate(rs.getDate("date").toLocalDate());
                o.setDeparture(rs.getString("departure"));
                o.setDestination(rs.getString("destination"));
                o.setReqComment(rs.getString("reqComment"));
                ret.add(o);
            }
        } catch (SQLException e) {
            throw new DatabaseException("getAllCustomers: " + e);
        }
        return ret;
    }

    public List<Order> getConfirmedOrderOfProvider (int id){
        List<Order> ret = new ArrayList<>();
        ResultSet rs = databaseConnection.select("select * from `orders` WHERE  `confirmed` = 1 AND `providerId` = ?", new Object[]{id});
        try {
            while(rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setDate(rs.getDate("date").toLocalDate());
                o.setDeparture(rs.getString("departure"));
                o.setDestination(rs.getString("destination"));
                User requestor = UserDAO.getInstance().getUser(rs.getInt("requesterId"));
                o.setRequestorUser(requestor);
                User provider = UserDAO.getInstance().getUser(rs.getInt("providerId"));
                o.setProviderUser(provider);
                o.setReqComment(rs.getString("reqComment"));
                o.setConfirmed(rs.getBoolean("confirmed"));
                o.setProvComment(rs.getString("active"));
                o.setProvComment(rs.getString("provComment"));
                ret.add(o);
            }
        } catch (SQLException e) {
            throw new DatabaseException("getAllCustomers: " + e);
        }
        return ret;
    }

    public Order getOrder(int id) {
        ResultSet rs = databaseConnection.select("select * from `orders` where `id` = ? ", new Object[]{id});
        try {
            if (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setDate(rs.getDate("date").toLocalDate());
                o.setDeparture(rs.getString("departure"));
                o.setDestination(rs.getString("destination"));
                User requestor = UserDAO.getInstance().getUser(rs.getInt("requesterId"));
                o.setRequestorUser(requestor);
                User provider = UserDAO.getInstance().getUser(rs.getInt("providerId"));
                o.setProviderUser(provider);
                o.setReqComment(rs.getString("reqComment"));
                o.setConfirmed(rs.getBoolean("confirmed"));
                o.setProvComment(rs.getString("active"));
                o.setProvComment(rs.getString("provComment"));
                return o;
            }
        } catch (SQLException e) {
            throw new DatabaseException("getOrder: " + e);
        }
        return null;
    }
//]
//    public RentalRecord getRentalRecord(long id) {
//        ResultSet rs = databaseConnection.select(
//                "SELECT `id`, `date`, `expected_return_date`, `actual_return_date`, `fine_amount`, `staff_id`, `video_id`, `customer_id`" +
//                        "FROM `rental_record` where `id` = ?",
//                new Object[]{id});
//        try {
//            if (rs.next()) {
//                return new RentalRecord(
//                        rs.getLong(1),
//                        rs.getDate(2).toLocalDate(),
//                        rs.getDate(3).toLocalDate(),
//                        rs.getDate(4).toLocalDate(),
//                        rs.getDouble(5),
//                        VideosDAO.getInstance().getVideoById(rs.getInt(7)),
//                        CustomerDAO.getInstance().getCustomer(rs.getLong(8)),
//                        StaffDAO.getInstance().getStaff(rs.getLong(6))
//                );
//            }
//        } catch (SQLException e) {
//            throw new DatabaseException("getRentalRecord: " + e);
//        }
//        return null;
//    }
//
//    public List<RentalRecord> getAllRentalRecords() {
//        List<RentalRecord> ret = new ArrayList<>();
//        ResultSet rs = databaseConnection.select("SELECT `id`, `date`, `expected_return_date`, `actual_return_date`, " +
//                "`fine_amount`, `staff_id`, `car_id`, `customer_id` FROM `rental_record`", new Object[]{});
//        try {
//            while(rs.next()) {
//                Date actualReturnDate_ = rs.getDate(4);
//                LocalDate actualReturnDate = (actualReturnDate_ == null)? null: actualReturnDate_.toLocalDate();
//                ret.add(new RentalRecord(
//                        rs.getLong(1),
//                        rs.getDate(2).toLocalDate(),
//                        rs.getDate(3).toLocalDate(),
//                        actualReturnDate,
//                        rs.getDouble(5),
//                        VideosDAO.getInstance().getVideoById(rs.getInt(7)),
//                        CustomerDAO.getInstance().getCustomer(rs.getLong(8)),
//                        StaffDAO.getInstance().getStaff(rs.getLong(6))
//                ));
//            }
//        } catch (SQLException e) {
//            throw new DatabaseException("getAllRentalRecords: " + e);
//        }
//        return ret;
//    }
//
//    public List<Long> getRentalRecordforReturn(long uid) {
//        List<Long> ret = new ArrayList<>();
//        ResultSet rs = databaseConnection.select("SELECT `video_id` FROM `rental_record` WHERE `customer_id`= ?", new Object[]{uid});
//        try {
//            while(rs.next()) {
//                ret.add(
//                        rs.getLong(1)
//                );
//            }
//        } catch (SQLException e) {
//            throw new DatabaseException("getRentalRecordforReturn: " + e);
//        }
//        return ret;
//    }

}
