/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the model of items that will appear on the market
*/
package model;

/**
 *
 * @author Inspiron
 */
public class MarketItems {
    private int id;
    private int user_id;
    private int item_id;
    private double price;

    public MarketItems(int id, int user_id, int item_id, double price) {
        this.id = id;
        this.user_id = user_id;
        this.item_id = item_id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
