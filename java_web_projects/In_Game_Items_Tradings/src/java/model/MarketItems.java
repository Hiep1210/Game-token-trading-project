/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the model of items that will appear on the market
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author Inspiron
 */
public class MarketItems extends GameItems {

    private int id;
    private String gameAccountName;
    private int userid;
    private double price;
    private LocalDateTime begindate;
    private LocalDateTime enddate;

    public MarketItems(int id, String gameAccountName, int userid, double price, LocalDateTime begindate, LocalDateTime enddate,
            int gid, String skinname, String itemname, String type, String rarity, String exterior, String img) {
        super(gid, skinname, itemname, type, rarity, exterior, img);
        this.id = id;
        this.gameAccountName = gameAccountName;
        this.userid = userid;
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

    public String getgameAccountName() {
        return gameAccountName;
    }

    public void setgameAccountName(String gameAccountName) {
        this.gameAccountName = gameAccountName;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getBegindate() {
        return begindate;
    }

    public void setBegindate(LocalDateTime begindate) {
        this.begindate = begindate;
    }

    public LocalDateTime getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDateTime enddate) {
        this.enddate = enddate;
    }

}
