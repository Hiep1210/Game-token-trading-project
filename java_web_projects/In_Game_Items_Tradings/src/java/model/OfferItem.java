/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Inspiron
 */
public class OfferItem {
    private int id;
    private int giveId;
    private int tradeId;
    private GameItems give;
    private TradeItem trade;

    public OfferItem(int id, int giveId, int tradeId, GameItems give, TradeItem trade) {
        this.id = id;
        this.giveId = giveId;
        this.tradeId = tradeId;
        this.give = give;
        this.trade = trade;
    }
    public OfferItem(int id, int giveId, int tradeId, GameItems give){
        this.id = id;
        this.giveId = giveId;
        this.tradeId = tradeId;
        this.give = give;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGiveId() {
        return giveId;
    }

    public void setGiveId(int giveId) {
        this.giveId = giveId;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public GameItems getGive() {
        return give;
    }

    public void setGive(GameItems give) {
        this.give = give;
    }

    public TradeItem getTrade() {
        return trade;
    }

    public void setTrade(TradeItem trade) {
        this.trade = trade;
    }
    
}
