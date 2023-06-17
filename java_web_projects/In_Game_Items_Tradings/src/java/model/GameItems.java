/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the items in the game
 */
package model;

/**
 *
 * @author Inspiron
 */
public class GameItems {

    private int id;
    private String skinName;
    private String itemName;
    private String type;
    private String rarity;
    private String exterior;
    private String img;

    public GameItems() {
    }

    public GameItems(int id, String skinName, String itemName, String type, String rarity, String exterior, String img) {
        this.id = id;
        this.skinName = skinName;
        this.itemName = itemName;
        this.type = type;
        this.rarity = rarity;
        this.exterior = exterior;
        this.img = img;
    }

    public GameItems(String skinName, String itemName, String type, String rarity, String img) {
        this.skinName = skinName;
        this.itemName = itemName;
        this.type = type;
        this.rarity = rarity;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
