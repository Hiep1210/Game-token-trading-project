/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the actions of the model GameAccount
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import model.GameAccount;

/**
 *
 * @author Inspiron
 */
public class GameAccountDAO {

    public static GameAccount GameLogIn(String username, String pass) {
        GameAccount guser = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from game_items_trading.gameaccount"
                        + " where username = " + "'" + username + "'"
                        + "AND PASSWORD = " + "'" + pass + "' limit 1";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                while (rs.next()) {             //needed even if just 1 row       
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String dob = df.format(rs.getDate("dob"));
                    guser = new GameAccount(rs.getInt("id"),
                            username, pass, dob, rs.getString("email"),
                            rs.getString("gender"), rs.getString("avatar"));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return guser;

    }

    public GameAccount GetUserInformation(int id) {
        GameAccount gui = new GameAccount();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from GameAccount where id= '" + id + "';";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    gui = new GameAccount();
                    gui.setId(rs.getInt(1));
                    gui.setUsername(rs.getString(2));
                    gui.setPassword(rs.getString(3));
                    gui.setDob(rs.getString(4));
                    gui.setEmail(rs.getString(5));
                    gui.setGender(rs.getString(6));
                    gui.setAvatar(rs.getString(7));
                }
                rs.close();
                st.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gui;
    }

    public static void main(String[] args) {
        GameAccount g = GameLogIn("laamwwibu", "123456");
        System.out.println(g.getDob());
    }
}
