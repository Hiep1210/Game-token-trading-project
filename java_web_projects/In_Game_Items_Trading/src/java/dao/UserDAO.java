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
                String sql = "Select * from UserAccount where Username = " + "'" + username + "'";
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
    public static User Login(String username, String pass) {
        User user = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from UserS where Username = " + "'" + username + "'" + 
                        "AND PASSWORD = " + "'" + pass + "'";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                while (rs.next()) {             //needed even if just 1 row       
                    user = new User(rs.getInt("User_id"), 
                            username, pass, rs.getInt("game_id)"), rs.getInt("role_id"));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        System.out.println(dao.FindUserName("hiep"));
    }
}
