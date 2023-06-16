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
    private int userId;
    private int itemId;
    private double price;
    private String skinName;
    private String itemName;
    private String type;
    private String rarity;
    private String exterior;
    private String img;

    public MarketItems() {
    }

    public MarketItems(int id, int userId, int itemId, double price, String skinName, String itemName, String type, String rarity, String exterior, String img) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.price = price;
        this.skinName = skinName;
        this.itemName = itemName;
        this.type = type;
        this.rarity = rarity;
        this.exterior = exterior;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
   
}
