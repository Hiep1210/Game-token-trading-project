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
public class CartItems {
    private List<GameItems> items;
    private int quantity;
    
    public CartItems(){
    
}
    public CartItems(GameItems gameitems, int quantity) {
        this.items = items;
        this.quantity = quantity;
    }
    public List<GameItems> getItems() {
        return items;
    }

    public void setItems(List<GameItems> items) {
        this.items = items;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
