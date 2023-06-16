/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the model of items that will appear on the market
*/
package model;

/**
 *
 * @author Inspiron
 */
public class MarketItems extends GameItems{
    private int id;
    private String game;
    private int user_id;
    private double price;
    private String begindate;
    private String enddate;

    public MarketItems(int id, String game, int user_id, double price, String begindate, String enddate, int gid, String skin_name, String item_name, String type, String rarity, String exterior, String img) {
        super(gid, skin_name, item_name, type, rarity, exterior, img);
        this.id = id;
        this.game = game;
        this.user_id = user_id;
        this.price = price;
        this.begindate = begindate;
        this.enddate = enddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
    
    
}
