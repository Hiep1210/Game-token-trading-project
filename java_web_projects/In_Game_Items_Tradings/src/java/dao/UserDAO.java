/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the actions of the model User
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import model.User;
import Context.DBContext;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author Inspiron
 */
public class UserDAO {
    
    public static User GetUserInformation(int id) {
        User user = new User();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from UserAccount where id= '" + id + "';";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt(1));
                    user.setUsername(rs.getString(2));
                    user.setPassword(rs.getString(3));
                    user.setDob(rs.getString(4));
                    user.setEmail(rs.getString(5));
                    user.setGender(rs.getString(6));
                    user.setAvatar(rs.getString(7));
                }
                rs.close();
                st.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public static String FindUserName(String username) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM UserAccount WHERE Username = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, username);
                ResultSet rs = st.executeQuery();
                // Check if any rows exist in the result set
                if (rs.next()) {
                    st.close();
                    con.close();
                    return username;
                }
                st.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static User LogIn(String username, String pass) {
        User user = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from UserAccount where Username = " + "'" + username + "'"
                        + "AND PASSWORD = " + "'" + pass + "' limit 1";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                while (rs.next()) {
                    user = new User(rs.getInt("id"),
                            username, pass, rs.getString("dob"), rs.getString("email"),
                            rs.getString("gender"), rs.getString("avatar"),
                            rs.getInt("role_id"), rs.getDouble("money_amount"));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public static boolean InsertUser(User user) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "INSERT INTO `game_items_trading`.`useraccount` "
                        + "(`username`, `password`, `dob`, `email`, `gender`, `avatar`, `role_id`, `money_amount`) "
                        + "VALUES (?, ?, ?, ?, ?, ?, 1, 0)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getDob());
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getGender());
                statement.setInt(6, user.getRole_id());

                int rows = statement.executeUpdate();
                statement.close();
                con.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
        return false;
    }

    public static boolean updateLinkedGameAccount(int user, int game) {//nut bam
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "UPDATE `game_items_trading`.`useraccount` SET `game_account_id` = '" + game
                        + "' WHERE (`id` = '" + user + "');";
                Statement statement = con.createStatement();
                int rows = statement.executeUpdate(sql);
                //if no row updated, throw exception
                if (rows < 1) {
                    throw new Exception();
                }
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static ArrayList<Integer> getAllUserId() {
        ArrayList<Integer> userIdlist = new ArrayList<>();
        int notificationLimit = 10;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT * FROM UserAccount";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model   
                while (rs.next()) {
                    userIdlist.add(rs.getInt("id"));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return userIdlist;

    }

    public static boolean checkDuplicateGameAccount(int game_id) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "select * from UserAccount where game_account_id = " + game_id;
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                //if rs has entries means this game account is already linked
                if (rs.next()) {
                    throw new Exception();
                }
                con.close();
                statement.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean ChangePassword(int user_id, String password) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "UPDATE `game_items_trading`.`useraccount` SET `password` = '" + password
                        + "' WHERE (`id` = '" + user_id + "');";
                Statement st = con.createStatement();
                int rows = st.executeUpdate(sql);
                if (rows < 1) {
                    throw new Exception();
                }
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean acceptPaymentRequest(int user, double money) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "UPDATE `game_items_trading`.`useraccount`"
                        + " SET money_amount = " + money
                        + " WHERE id = " + user;
                Statement statement = con.createStatement();
                int rows = statement.executeUpdate(sql);
                //if no row updated, throw exception
                if (rows < 1) {
                    throw new Exception();
                }
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User userInfo = GetUserInformation(1);
        System.out.println(userInfo.getEmail());
//        System.out.println(dao.FindUserName("laamwwibu1"));
//        System.out.println(dao.FindUserName("hiep"));
//        System.out.println(dao.InsertUser(new User(0, "hung", "hung123", 1, 0, 0)));
//        System.out.println(dao.updateLinkedGameAccount(3, 4));
//        for (int id : dao.getAllUserId()) {
//            System.out.println(id);
//        }

    }
}
