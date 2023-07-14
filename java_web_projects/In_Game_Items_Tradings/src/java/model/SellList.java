/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author VICTUS
 */
public class SellList extends MarketItems {

    int id;
    int sellerId;

    public SellList(int id, int sellerId, int mid, String gameAccountName, int userid, double price, LocalDateTime begindate, LocalDateTime enddate, int gid, String skinname, String itemname, String type, String rarity, String exterior, String img) {
        super(mid, gameAccountName, userid, price, begindate, enddate, gid, skinname, itemname, type, rarity, exterior, img);
        this.id = id;
        this.sellerId = sellerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

}
