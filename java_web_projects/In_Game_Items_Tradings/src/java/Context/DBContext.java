/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file is to connect to the database
*/
package Context;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author Inspiron
 */
public class DBContext {
    public  Connection getConnection(){
        String db = "Game_items_trading";
        String url = "jdbc:mysql://localhost:3306/"+db;
        String user = "root";
        String password = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            System.out.println("loi"+ex.getMessage());
        } 
        return null;
    }
}
