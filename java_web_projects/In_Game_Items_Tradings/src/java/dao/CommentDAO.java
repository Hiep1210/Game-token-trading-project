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
import model.Comment;

/**
 *
 * @author ACER
 */
public class CommentDAO {
    public static ArrayList<model.Comment> getAllComment(int tid){
        ArrayList<Comment> clist = new ArrayList<>();
        Comment c = null;
        try{
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            
            if(con != null){
                String sql = "Select id,ccontent,user_id,thread_id from COMMENT where thread_id = '" + tid + "';";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                while(rs.next()){
                    c =  new Comment(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
                    clist.add(c);
                }
                call.close();
                con.close();
    } 
}catch(Exception e){
            System.out.println(e.getMessage());
}
        return clist;
    }
    
    public static void insertComment(String ccontent, int user_id, int tid){
        try{
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "Insert into COMMENT (ccontent,user_id,thread_id) values (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setString(1, ccontent);
            st.setInt(2, user_id);
            st.setInt(3, tid);
            if (st.executeUpdate() != 1) {
                System.out.println("ERROR INSERTING Comment");
            }
            st.close();
            con.close();
    }catch (Exception e){
            System.out.println(e.getMessage());
    }
}

    public static boolean updateComment(String ccontent, int user_id, int cid){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if(con != null){
                String sql = "Update COMMENT Set ccontent = '"+ ccontent +"', where id ='"+ cid +"' and user_id ='" + user_id + "'";
                 Statement st = con.createStatement();
                int rows = st.executeUpdate(sql);
                if (rows < 1) {
                    throw new Exception();
                }
                return true;
            }
    }catch(Exception e){
            System.out.println(e.getMessage());
    }
        return false;
}
    public static boolean deleteComment( int cid){
         try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if(con != null){
                String sql = "Delete from COMMENT where id ='" + cid + "'";
                 Statement st = con.createStatement();
                int rows = st.executeUpdate(sql);
                if (rows < 1) {
                    throw new Exception();
                }
                return true;
            }
    }catch(Exception e){
            System.out.println(e.getMessage());
    }
        return false;
}
    }


