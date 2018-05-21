package dao;

import dbconnection.DatabaseException;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO {
    // singleton + lazy init
    private static UserDAO instance;
    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    private UserDAO() {
        super();
    }


    public boolean create(User user) {
        return databaseConnection.insert(
                "INSERT INTO `users`(`name`, `birthday`, `address`, `telnum`, `username`, `password`, `type`, `comment`) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[] {user.getName(), user.getBirthday(), user.getAddress(), user.getTelNum(),
                        user.getUserName(), user.getPassWord(), user.getType(), user.getComment()
                }
        ).size() == 1;
    }

    public boolean update(User user) {
        return databaseConnection.update(
                "UPDATE `users` SET `name`=?,`birthday`=?,`address`=?,`telnum`=?,`username`=?,`password`=?,`type`=? ,`comment`=? WHERE `id` = ?",
                new Object[] {
                        user.getName(), user.getBirthday(), user.getAddress(), user.getTelNum(),
                        user.getUserName(), user.getPassWord(), user.getType(), user.getComment(),
                        user.getId()
                }
        ) == 1;
    }

    public boolean delete(long id) {
        return databaseConnection.delete(
//                "DELETE FROM `users` WHERE `id` = ?",
//                new Object[]{id}
                "UPDATE `users` SET `active` = 0  WHERE `id` = ?",
                new Object[]{id}
        ) == 1;
    }

    public User getUser(int id) {
        ResultSet rs = databaseConnection.select("select `id`, `name`, `birthday`, `address`, `telnum`, `username`, `password`, `type`, `comment` from `users` where `id` = ?", new Object[]{id});
        try {
            if (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)
                );
            }
        } catch (SQLException e) {
            throw new DatabaseException("getUser: " + e);
        }
        return null;
    }

    public User login(String username, String password) {
        ResultSet rs = this.databaseConnection.select(
                "select id from users where username = ? and password = ?", new Object[]{username, password}
        );
        try {
            if (rs.next()) {
                return getUser(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DatabaseException("login: " + e);
        }
        return null;
    }
    //dont need type as well
    public List<User> getAllUsers() {
        List<User> ret = new ArrayList<>();
        ResultSet rs = databaseConnection.select("select `id`, `name`, `birthday`, `address`, `telnum`, `type`, `comment` from `users` ", new Object[]{});
        try {
            while(rs.next()) {
                ret.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getString(4),
                        rs.getString(5),
                        "",
                        "",
                        rs.getString(6),
                        rs.getString(7)
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseException("getAllCustomers: " + e);
        }
        return ret;
    }
}
