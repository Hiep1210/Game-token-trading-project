/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tickRate;

import dao.AuctionDAO;
import dao.ProcessItemsDAO;
import java.util.HashMap;
import model.Auction;
import model.Bid;
import model.ProcessItem;

/**
 *
 * @author Asus
 */
public class SecondlyJob implements Runnable {

    public void run() {
        processAuctionItem();
    }

    public void processAuctionItem() {
        AuctionDAO.deleteUnsuccessfulAuction();
        Auction auction;
        ProcessItem processItem;
        HashMap<Bid, Auction> auctionList = AuctionDAO.getAllSuccessfullAuction();
        if (!auctionList.isEmpty()) {
            for (Bid bid : auctionList.keySet()) {
                auction = auctionList.get(bid);
                processItem = new ProcessItem(auction.getSellerId(), bid.getBidderId(), auction.getAuctionId(), 2, bid.getGameAccountName());
                ProcessItemsDAO.insertProcessItems(processItem);
            }
        }
    }

}
