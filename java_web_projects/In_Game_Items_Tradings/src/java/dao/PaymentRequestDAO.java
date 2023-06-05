/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.PaymentRequest;

/*
*Programmer: Trần Thế Hùng 
*Description: This file is a DAO for doing CRUD operations on the PaymentRequest table
 */
public class PaymentRequestDAO {

    public static String insertPaymentRequest(PaymentRequest paymentRequest) {
        ResultSet generatedKey;
        int key = 0;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "INSERT INTO PaymentRequest (user_id, "
                        + "money, date, img) VALUES ( ?, ?, ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, paymentRequest.getUser_id());
                preparedStatement.setDouble(2, paymentRequest.getMoney());
                preparedStatement.setString(3, paymentRequest.getDate());
                preparedStatement.setString(4, paymentRequest.getImg());
                // if insert command failed
                if (preparedStatement.executeUpdate() != 1) {
                    System.out.println("ERROR INSERTING PAYMENTREQUEST, "
                            + "NO ROWS AFFECTED");
                }
                generatedKey = preparedStatement.getGeneratedKeys();
                if (generatedKey.next()) {
                    key = generatedKey.getInt(1);
                } else {
                    throw new SQLException("ERROR INSERTING PAYMENTREQUEST,"
                            + " NO ID OBTAINED.");
                }
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error in insertPaymentRequest");
            System.out.println(e);
        }
        return String.valueOf(key);
    }

    public static ArrayList<PaymentRequest> getAllPaymentRequest() {
        ArrayList<PaymentRequest> paymentRequestList = new ArrayList<>();
        PaymentRequest paymentRequest = null;
        String invoiceImgName;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT * FROM PaymentRequest ORDER BY date";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model  
                while (rs.next()) {
                    paymentRequest = new PaymentRequest();
                    invoiceImgName = String.valueOf(rs.getInt("id")) + ".webp";
                    paymentRequest.setId(rs.getInt("id"));
                    paymentRequest.setUser_id(rs.getInt("user_id"));
                    paymentRequest.setMoney(rs.getDouble("money"));
                    paymentRequest.setDate(rs.getString("date"));
                    paymentRequest.setImg(invoiceImgName);
                    paymentRequestList.add(paymentRequest);
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return paymentRequestList;
    }

    public static PaymentRequest getPaymentRequest(int id) {
        PaymentRequest paymentRequest = null;
        String invoiceImgName;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT * FROM PaymentRequest"
                        + " WHERE id = " + id;
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model  
                while (rs.next()) {
                    paymentRequest = new PaymentRequest();
                    invoiceImgName = String.valueOf(rs.getInt("id")) + ".webp";
                    paymentRequest.setId(rs.getInt("id"));
                    paymentRequest.setUser_id(rs.getInt("user_id"));
                    paymentRequest.setMoney(rs.getDouble("money"));
                    paymentRequest.setDate(rs.getString("date"));
                    paymentRequest.setImg(invoiceImgName);
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return paymentRequest;
    }

    public static void deletePaymentRequest(int paymentRequestId) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "DELETE FROM PaymentRequest WHERE id = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, paymentRequestId);
            if (preparedStatement.executeUpdate() != 1) {
                System.out.println("ERROR DELETING PAYMENTREQUEST ID = "
                        + paymentRequestId);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in delete payment request");
        }
    }
//FOR LATER ITERATIONS    
//    public static boolean deletePaymentRequest(int[] paymentRequestIdList) {
//        try {
//            DBContext db = new DBContext();
//            Connection con = db.getConnection();
//            String sql = "DELETE FROM PaymentRequest WHERE id = ?;";
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            //Loop through all payment request id and delete it from the table
//            for (int paymentRequestId : paymentRequestIdList) {
//                preparedStatement.setInt(1, paymentRequestId);
//                if (preparedStatement.executeUpdate() != 1) {
//                    System.out.println("ERROR DELETING PAYMENTREQUEST ID = "
//                            + paymentRequestId);
//                    return false;
//                }
//            }
//            con.close();
//        } catch (Exception e) {
//            System.out.println("Error in delete payment request");
//        }
//        return true;
//    }

//    public static void main(String[] args) {
//        PaymentRequestDAO dao = new PaymentRequestDAO();
//        insertPaymentRequest(new PaymentRequest(1, 1000, "2023-05-01", "invoice"));
//        ArrayList<PaymentRequest> paymentRequestList = getAllPaymentRequest();
//        int[] idArray = new int[paymentRequestList.size()];
//        for (int i = 0; i < paymentRequestList.size(); i++) {
//            idArray[i] = paymentRequestList.get(i).getId();
//        }
//        deletePaymentRequest(idArray);
//    }
}
