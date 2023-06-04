/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the actions of the model MarketItems
*/
package dao;
import Context.DBContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.MarketItems;
/**
 *
 * @author Inspiron
 */

public class MarketItemsDao {
    //Function to get all items in the market 
     public static ArrayList<MarketItems> getAllMarketItems() {
        ArrayList<MarketItems> list = new ArrayList<>();
        MarketItems items = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "Select * from MarketItems ";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model
                while (rs.next()) {             
                    items = new MarketItems(0, rs.getInt(2), rs.getInt(3));
                    list.add(items);
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
     public static void main(String[] args) {
        MarketItemsDao dao = new MarketItemsDao();
        ArrayList<MarketItems> list = dao.getAllMarketItems();
         for (int i = 0; i < list.size(); i++) {
             System.out.println(list.get(i).getItem_id());
         }
    }
}
