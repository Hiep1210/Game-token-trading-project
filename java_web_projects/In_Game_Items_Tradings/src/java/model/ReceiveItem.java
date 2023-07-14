/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Inspiron
 */
public class ReceiveItem {

    private int id;
    private int recId;
    private int tradeId;
    private GameItems rec;
    private TradeItem trade;

    public ReceiveItem(int id, int recId, int tradeId, GameItems rec, TradeItem trade) {
        this.id = id;
        this.recId = recId;
        this.tradeId = tradeId;
        this.rec = rec;
        this.trade = trade;
    }

    public ReceiveItem(int id, int recId, int tradeId, GameItems rec) {
        this.id = id;
        this.recId = recId;
        this.tradeId = tradeId;
        this.rec = rec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecId() {
        return recId;
    }

    public void setRecId(int recId) {
        this.recId = recId;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public GameItems getRec() {
        return rec;
    }

    public void setRec(GameItems rec) {
        this.rec = rec;
    }

    public TradeItem getTrade() {
        return trade;
    }

    public void setTrade(TradeItem trade) {
        this.trade = trade;
    }

}
