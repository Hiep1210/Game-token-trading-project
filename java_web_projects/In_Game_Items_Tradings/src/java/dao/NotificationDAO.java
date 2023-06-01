/*
*Programmer: Trần Thế Hùng 
*Description: This file describes the model of notification that will appear on dashboard
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import model.Notification;

/**
 *
 * @author Asus
 */
public class NotificationDAO {
    //Function to get all items in the market 

    public static ArrayList<Notification> getAllUserNotification(int user_id) {
        ArrayList<Notification> notiList = new ArrayList<>();
        Notification notification = null;
        int notificationLimit = 10;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT * FROM Notification WHERE user_id = "
                        + user_id + " ORDER BY date DESC LIMIT "
                        + notificationLimit;
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model   
                while (rs.next()) {
                    notification = new Notification();
                    notification.setNoti_id(rs.getInt("id"));
                    notification.setUser_id(rs.getInt("user_id"));
                    notification.setDate(rs.getString("date"));
                    notification.setNoti_content(rs.getString("noti_content"));
                    notification.setImg(rs.getString("img"));
                    notiList.add(notification);
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return notiList;
    }

    public static void insertNotification(Notification notification) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "INSERT INTO notification (date, user_id, "
                        + "noti_content, img) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, notification.getDate());
                preparedStatement.setInt(2, notification.getUser_id());
                preparedStatement.setString(3, notification.getNoti_content());
                preparedStatement.setString(4, notification.getImg());
                // if insert command failed
                if (preparedStatement.executeUpdate() != 1) {
                    System.out.println("ERROR INSERTING NOTIFICATION");
                }
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
//    To be further plan    
//    public void deleteNotification(int news_id) {
//        try {
//            DBContext db = new DBContext();
//            Connection con = db.getConnection();
//            st.close();
//            con.close();
//        } catch (Exception e) {
//            System.out.println("Error");
//        }
//    }
    
    public static void main(String[] args) {
        NotificationDAO dao = new NotificationDAO();
//        insertNotification(new Notification (1, "2003-05-01", "bruh", "buy"));
        ArrayList<Notification> list = getAllUserNotification(1);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
