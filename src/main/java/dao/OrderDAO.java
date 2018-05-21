package dao;

import model.Order;

import java.sql.Date;


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
                "INSERT INTO `orders`(`requesterId`, `providerId`, `date`, `departure`, `destination`, `confirmed`, `active`) " +
                        "VALUES ( ?, ?, ?, ?, ? , ?, ?)",
                new Object[] {
                        order.getRequestorUser().getId(), order.getProviderUser().getId(),
                        Date.valueOf(order.getDate()), order.getDeparture(), order.getDestination(),order.isConfirmed(),order.isActive()

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
