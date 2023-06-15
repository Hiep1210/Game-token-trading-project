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
    private String skin_name;
    private String item_name;
    private String type;
    private String rarity;
    private String exterior;
    private String img;

    public GameItems() {
    }
    
    public GameItems(int id, String skin_name, String item_name, String type, String rarity,String exterior, String img) {
        this.id = id;
        this.skin_name = skin_name;
        this.item_name = item_name;
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

    public String getSkin_name() {
        return skin_name;
    }

    public void setSkin_name(String skin_name) {
        this.skin_name = skin_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
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
