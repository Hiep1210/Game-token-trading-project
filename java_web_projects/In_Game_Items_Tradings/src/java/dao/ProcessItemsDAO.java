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
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.ProcessItem;

/**
 *
 * @author Asus
 */
public class ProcessItemsDAO {

    public static boolean insertProcessItems(ProcessItem processItem) {
        boolean insertStatus = true;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "INSERT INTO processitems (transactionType_id , transaction_id , sender_id,receiver_id,game_account_name,process_date) "
                        + "  SELECT ?,?,?,?,?,? "
                        + "WHERE NOT EXISTS "
                        + "  (SELECT transaction_id FROM processitems WHERE transaction_id=? AND transactionType_id = ?);";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, processItem.getTransactionTypeIdId());
                preparedStatement.setInt(2, processItem.getTransactionId());
                preparedStatement.setInt(3, processItem.getSenderId());
                preparedStatement.setInt(4, processItem.getReceiverId());
                preparedStatement.setString(5, processItem.getGameAccountName());
                preparedStatement.setObject(6, processItem.getProcessTime());
                preparedStatement.setInt(7, processItem.getTransactionTypeIdId());
                preparedStatement.setInt(8, processItem.getTransactionId());
                // if insert command failed
                if (preparedStatement.executeUpdate() != 1) {
                    insertStatus = false;
                }
                preparedStatement.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error in ProcessItems");
            System.out.println(e);
        }
        return insertStatus;
    }

    public static ArrayList<ProcessItem> getAllProcessItems() {
        ArrayList<ProcessItem> processItemList = new ArrayList<>();
        ProcessItem processItem = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT * FROM ProcessItems "
                        + " ORDER BY id DESC";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model  
                while (rs.next()) {
                    processItem = new ProcessItem();
                    processItem.setId(rs.getInt("id"));
                    processItem.setTransactionTypeIdId(rs.getInt("transactionType_id"));
                    processItem.setTransactionId(rs.getInt("transaction_id"));
                    processItem.setSenderId(rs.getInt("sender_id"));
                    processItem.setReceiverId(rs.getInt("receiver_id"));
                    processItem.setGameAccountName(rs.getString("game_account_name"));
                    processItem.setProcessTime(rs.getObject("process_date", LocalDateTime.class));
                    processItemList.add(processItem);
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return processItemList;
    }

    public static boolean deleteProcessItems(int processItemId) {
        boolean deleteStatus = true;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "DELETE FROM ProcessItems WHERE id = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, processItemId);
            if (preparedStatement.executeUpdate() != 1) {
                deleteStatus = false;
                System.out.println("ERROR DELETING PROCESS ITEM "
                        + processItemId);
            }
            preparedStatement.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error in delete payment request");
        }
        return deleteStatus;
    }

//    public static void main(String[] args) {
//        ProcessItem processItem = new ProcessItem(1, 2, 1, 2,"Dave", LocaDateTime. );
//
////        deleteProcessItems(1);
//        for (ProcessItem pi : getAllProcessItems()) {
//            System.out.println(pi);
//        }
//    }
}
