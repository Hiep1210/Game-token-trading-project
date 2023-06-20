/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author ksiks
 */
public class SellList extends MarketItems{
    int id;
    List<SellItems> items ;
    
    public SellList(int id, int buyer_id, int mid, String game, int user_id, double price, String begindate, String enddate, int gid, String skin_name, String item_name, String type, String rarity, String exterior, String img) {
        super(mid, game, user_id, price, begindate, enddate, gid, skin_name, item_name, type, rarity, exterior, img);
        this.id = id;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    SellItems getItemById(int id) {
        for (SellItems item : items) {
            if ( item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    
    public void removeItem(int id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }
}
