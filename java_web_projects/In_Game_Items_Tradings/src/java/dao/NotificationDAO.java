/*
*Programmer: Nguyễn Chí Trung
*Description: This file is a DAO for doing CRUD operations on the Notification table
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
import com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord;

/**
 *
 * @author Asus
 */
public class NotificationDAO {
    //Function to get all items in the market 

    public static ArrayList<Notification> getAllUserNotification(int user_id) {
        ArrayList<Notification> list = new ArrayList<>();
        Notification notification = null;
        int notificationLimit = 10;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = " SELECT * FROM Notification "
                        + " WHERE user_id = "+ user_id 
                        + " OR content_type = 'admin'"
                        + " ORDER BY id DESC LIMIT "+ notificationLimit;
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model   
                while (rs.next()) {
                    notification = new Notification();
                    notification.setNoti_id(rs.getInt("id"));
                    notification.setUser_id(rs.getInt("user_id"));
                    notification.setDate(rs.getString("date"));
                    notification.setNoti_content(rs.getString("noti_content"));
                    notification.setContent_type(rs.getString("content_type"));
                    list.add(notification);
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR IN getAllUserNotification");
        }
        return list;
    }

    public static void insertNotification(Notification notification) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "INSERT INTO notification (date, user_id, "
                        + "noti_content, content_type) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, notification.getDate());
                preparedStatement.setInt(2, notification.getUser_id());
                preparedStatement.setString(3, notification.getNoti_content());
                preparedStatement.setString(4, notification.getContent_type());
                // if insert command failed
                if (preparedStatement.executeUpdate() != 1) {
                    System.out.println("ERROR INSERTING NOTIFICATION");
                }
                con.close();
            }
        } catch (Exception e) {
            System.out.println("ERROR IN insertNotification");
        }
    }

    public static void main(String[] args) {
        NotificationDAO dao = new NotificationDAO();
//        insertNotification(new Notification (1, "2003-05-01", "bruh", "buy"));
        ArrayList<Notification> list = getAllUserNotification(1);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
