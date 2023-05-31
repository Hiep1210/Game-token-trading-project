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
import java.sql.CallableStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *
 * @author Inspiron
 */
public class UserDAO {
    //This function is to find if there is an user with provided username
    public static String FindUserName(String username){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from UserAccount where Username = " + "'" + username + "' limit 1";
                Statement statement = con.createStatement();
                ResultSet set = statement.executeQuery(sql);
                //if there is a set is not null returned then return username
                if(set!=null){
                    return username;
                }
                statement.close();
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
                String sql = "Select * from UserAccount where Username = " + "'" + username + "'" + 
                        "AND PASSWORD = " + "'" + pass + "' limit 1";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                while (rs.next()) {             //needed even if just 1 row       
                    user = new User(rs.getInt("User_id"), 
                            username, pass, rs.getInt("game_account_id)"), rs.getInt("role_id"), rs.getDouble("money_amount"));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public static boolean InsertUser(User user){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "INSERT INTO `game_items_trading`.`useraccount` (`username`, `password`, `game_account_id`, `role_id`, `money_amount`) "
                        + "VALUES ('"+user.getUsername()+"', '"+user.getPassword()+"', "+user.getGame_id()
                        +", 1,0);";
                Statement statement = con.createStatement();
                int rows = statement.executeUpdate(sql);
                statement.close();
                con.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
     public static boolean updateLinkedGameAccount(int user, int game) {//nut bam
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "UPDATE `game_items_trading`.`useraccount` SET `game_account_id` = '"+game+
                        "' WHERE (`id` = '"+user+"');";
                Statement statement = con.createStatement();
                int rows = statement.executeUpdate(sql);
                //if no row updated, throw exception
                if(rows<1){
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
        System.out.println(dao.FindUserName("hiep"));
        System.out.println(dao.InsertUser(new User(0, "hung", "hung123", 1, 0, 0)));
        System.out.println(dao.updateLinkedGameAccount(3, 4));
    }
}
