/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Thread;
/**
 *
 * @author ACER
 */
public class ThreadDAO {
    public static ArrayList<Thread> getAllThread(){
        ArrayList<Thread> list = new ArrayList<>();
        Thread t = null;
        
        try{
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            
            if(con != null){
                String sql = "Select id,ttitle,tcontent,ttag,tauthor,user_id from THREAD;";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                while(rs.next()){
                t = new Thread(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
                list.add(t);
            }       
            call.close();
            con.close();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public static void insertNewThread(String title, String content, String tag, String author, int user_id){
        try{
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "Insert into THREAD (ttitle,tcontent,ttag,tauthor,user_id) values (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setString(1, title);
            st.setString(2, content);
            st.setString(3, tag);
            st.setString(4, author);
            st.setInt(5, user_id);
            if (st.executeUpdate() != 1) {
                System.out.println("ERROR INSERTING Thread");
            }
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
    }
    public static Thread getThreadById(int id){
        Thread t = new Thread();
        String sql = "Select id,ttitle,tcontent,ttag,tauthor,user_id from THREAD where id = '" + id + "';";
        try{
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t.setId(rs.getInt(1));
                t.setTtitle(rs.getString(2));
                t.setTcontent(rs.getString(3));
                t.setTtag(rs.getString(4));
                t.setTauthor(rs.getString(5));
                t.setUser_id(rs.getInt(6));
                return t;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
    }
        return null;
}
}
