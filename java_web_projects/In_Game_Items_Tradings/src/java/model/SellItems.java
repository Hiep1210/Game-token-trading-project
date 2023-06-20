/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ksiks
 */
public class SellItems extends GameItems {
    private int id;
    private String gameAccountName;
    private int userid;
    private double price;
    String timeleft;

    public SellItems(int id, String gameAccountName, int userid, double price, String timeleft,
            int gid, String skinname, String itemname, String type, String rarity, String exterior, String img) {
        super(gid, skinname, itemname, type, rarity, exterior, img);
        this.id = id;
        this.gameAccountName = gameAccountName;
        this.userid = userid;
        this.price = price;
        this.timeleft=timeleft;
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

    public String getTimeleft() {
        return timeleft;
    }

    public void setTimeleft(String timeleft) {
        this.timeleft = timeleft;
    }

    
}
