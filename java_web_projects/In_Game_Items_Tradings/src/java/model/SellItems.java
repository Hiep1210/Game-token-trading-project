/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author VICTUS
 */
public class SellItems extends GameItems {

    private int id;
    private String exterior;
    private int sellTime;
    private double price;
    private String gameAccount;
    private int sellerId;

    public SellItems() {
    }

    public SellItems(int id, int sellerId, int gid) {
        super(gid);
        this.id = id;
        this.sellerId = sellerId;
    }

    public SellItems(int id, String exterior, int sellTime, double price, String gameAccount, int sellerId) {
        this.id = id;
        this.exterior = exterior;
        this.sellTime = sellTime;
        this.price = price;
        this.gameAccount = gameAccount;
        this.sellerId = sellerId;
    }

    public SellItems(String exterior, int sellTime, double price, String gameAccount, int sellerId, int gid) {
        super(gid);
        this.exterior = exterior;
        this.sellTime = sellTime;
        this.price = price;
        this.gameAccount = gameAccount;
        this.sellerId = sellerId;
    }

    public SellItems(int id, String exterior, int sellTime, double price, String gameAccount, int sellerId, int gid, String skinName, String itemName, String type, String rarity, String img) {
        super(gid, skinName, itemName, type, rarity, img);
        this.id = id;
        this.exterior = exterior;
        this.sellTime = sellTime;
        this.price = price;
        this.gameAccount = gameAccount;
        this.sellerId = sellerId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public int getSellTime() {
        return sellTime;
    }

    public void setSellTime(int sellTime) {
        this.sellTime = sellTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGameAccount() {
        return gameAccount;
    }

    public void setGameAccount(String gameAccount) {
        this.gameAccount = gameAccount;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getGameItemId() {
        return super.getId();
    }

    public void setGameItemId(int gid) {
        super.setId(gid);
    }
}
