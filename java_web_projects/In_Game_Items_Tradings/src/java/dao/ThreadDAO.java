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
        String sql = "Select id,ttitle,tcontent,ttag,ticon from THREAD;";
        try{
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            Statement call = con.createStatement();
            ResultSet rs = call.executeQuery(sql);
            if(rs.next()){
                t = new Thread(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                list.add(t);
            }
            call.close();
            con.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    
//    public boolean insertNewThread(){
//        Thread t = new Thread();
//        String sql = "Insert into THREAD values "
//    }
}
