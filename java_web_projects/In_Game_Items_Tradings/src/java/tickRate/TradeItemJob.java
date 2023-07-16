/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tickRate;

import dao.CartDAO;
import dao.MarketItemsDAO;
import dao.NotificationDAO;
import dao.TradeDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.MarketItems;
import model.Notification;
import model.TradeItem;

/**
 *
 * @author Inspiron
 */
public class TradeItemJob implements Runnable{

    @Override
    public void run() {
        processUnsuccessfullTrade();
    }
    private void processUnsuccessfullTrade() {
        //Only get ended auctions that is also not in process item
        ArrayList<TradeItem> unsuccessfulTrade = TradeDAO.getUnsuccessfulTrade();
        //Do not execute if there is no unsuccessfull market item (Ended and no one bought their item)
        if (!unsuccessfulTrade.isEmpty()) {
            for (TradeItem marketItem : unsuccessfulTrade) {
                TradeDAO.deleteOfferItems(marketItem.getId());
                TradeDAO.deleteRecitems(marketItem.getId());
                TradeDAO.deleteTrade(marketItem.getId());
            }
            insertUnsuccessfullTradeNotification(unsuccessfulTrade);
        }
    }
    private void insertUnsuccessfullTradeNotification(ArrayList<TradeItem> unsuccessfulMarketItems) {
        String sellerNotificationContent;
        Notification notificationForSeller;
        for (TradeItem marketItem : unsuccessfulMarketItems) {
            sellerNotificationContent = "Your listing has ended! No one have accepted your trade" ;
            notificationForSeller = new Notification(marketItem.getCreatorId(), LocalDateTime.now() , sellerNotificationContent, "trade");
            NotificationDAO.insertNotification(notificationForSeller);
        }
    }
}
