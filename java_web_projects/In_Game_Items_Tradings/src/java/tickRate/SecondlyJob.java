/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tickRate;

import dao.CartDAO;
import dao.MarketItemsDao;
import dao.NotificationDAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.MarketItems;
import model.Notification;

/**
 *
 * @author Asus
 */
public class SecondlyJob implements Runnable {

    public void run() {
        processUnsuccessfullMarketItem();
    }

    private void processUnsuccessfullMarketItem() {
        //Only get ended auctions that is also not in process item
        ArrayList<MarketItems> unsuccessfulMarketItems = MarketItemsDao.getUnsuccessfulMarketItems();
        //Do not execute if there is no unsuccessfull market item (Ended and no one bought their item)
        if (!unsuccessfulMarketItems.isEmpty()) {
            for (MarketItems marketItem : unsuccessfulMarketItems) {
                CartDAO.deleteCartWithMarketItemId(marketItem.getId());
                MarketItemsDao.deletelMarketItem(marketItem.getId());
            }
            insertUnsuccessfullMarketItemNotification(unsuccessfulMarketItems);
        }
    }

    private void insertUnsuccessfullMarketItemNotification(ArrayList<MarketItems> unsuccessfulMarketItems) {
        String sellerNotificationContent;
        Notification notificationForSeller;
        for (MarketItems marketItem : unsuccessfulMarketItems) {
            sellerNotificationContent = "Your listing has ended! No one have bought your : " + marketItem.getItemName() + "|" + marketItem.getType();
            notificationForSeller = new Notification(marketItem.getUserid(), getCurrentDate(), sellerNotificationContent, "sell");
            NotificationDAO.insertNotification(notificationForSeller);
        }
    }

    private String getCurrentDate() {
        java.util.Date date = new java.util.Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        return strDate;
    }

}
