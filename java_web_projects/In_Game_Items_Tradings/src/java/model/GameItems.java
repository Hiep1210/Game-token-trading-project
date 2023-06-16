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
    private String skinname;
    private String itemname;
    private String type;
    private String rarity;
    private String exterior;
    private String img;

    public GameItems() {
    }
    
    public GameItems(int id, String skinname, String itemname, String type, String rarity,String exterior, String img) {
        this.id = id;
        this.skinname = skinname;
        this.itemname = itemname;
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

    public String getSkinname() {
        return skinname;
    }

    public void setSkinname(String skinname) {
        this.skinname = skinname;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
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
