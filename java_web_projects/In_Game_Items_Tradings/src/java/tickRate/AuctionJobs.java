/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tickRate;

import dao.AuctionDAO;
import dao.BidDAO;
import dao.NotificationDAO;
import dao.ProcessItemsDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Auction;
import model.Bid;
import model.Notification;
import model.ProcessItem;

/**
 *
 * @author Asus
 */
public class AuctionJobs implements Runnable {

    public void run() {
        processEndedAuctonItem();
    }

    private void processEndedAuctonItem() {
        //Only get ended auctions that is also not in process item
        ArrayList<Auction> endedAuction = AuctionDAO.getAllEndedAuction();
        ArrayList<Bid> bidList;
        //Do not execute if there is no auction that has ended
        if (!endedAuction.isEmpty()) {
            for (Auction auction : endedAuction) {
                auction.setBidList(BidDAO.getBidsFromAuctionId(auction.getAuctionId()));
                if (auction.getBidList().isEmpty()) {
                    processUnsuccessfullAuction(auction);
                } else {
                    processSuccessfullAuction(auction);
                }
            }
        }
    }

    private void processUnsuccessfullAuction(Auction auction) {
        String sellerNotificationContent;
        String bidderNotificationContent;
        Notification notificationForSeller;
        Notification notificationForBidder;
        sellerNotificationContent = "Your auction has ended! No one have bid your : " + auction.getGameItem().getItemName() + " | "
                + auction.getGameItem().getSkinName();
        notificationForSeller = new Notification(auction.getSellerId(), LocalDateTime.now(), sellerNotificationContent, "auction");
        NotificationDAO.insertNotification(notificationForSeller);
        AuctionDAO.deleteAuction(auction.getAuctionId());
    }

    private void processSuccessfullAuction(Auction auction) {
        String sellerNotificationContent;
        String bidderNotificationContent;
        Notification notificationForSeller;
        Notification notificationForBidder;
        ArrayList<Bid> bidList = auction.getBidList();
        Bid highestBid = bidList.get(0);
        ProcessItem processItem;
        sellerNotificationContent = "Your : " + auction.getGameItem().getItemName() + "|" + auction.getGameItem().getSkinName()
                + " has been sold for " + highestBid.getAmount() + "Please transfer this item to admins game account!";
        bidderNotificationContent = "Your bid for : " + auction.getGameItem().getItemName() + "|" + auction.getGameItem().getSkinName()
                + " has won! Please wait for admins to transfer this item to you!";
        notificationForSeller = new Notification(auction.getSellerId(), LocalDateTime.now(), sellerNotificationContent, "auction");
        notificationForBidder = new Notification(highestBid.getBidderId(), LocalDateTime.now(), bidderNotificationContent, "auction");
        NotificationDAO.insertNotification(notificationForSeller);
        NotificationDAO.insertNotification(notificationForBidder);
        processItem = new ProcessItem(auction.getSellerId(), highestBid.getBidderId() , auction.getAuctionId(), 2 , auction.getGameAccountName(),
                LocalDateTime.now());
        ProcessItemsDAO.insertProcessItems(processItem);
        for (Bid bid : bidList.subList(1, bidList.size())) {
            bidderNotificationContent = "Your bid for : " + auction.getGameItem().getItemName() + "|" + auction.getGameItem().getSkinName()
                    + " has lost!";
            notificationForBidder = new Notification(bid.getBidderId(), LocalDateTime.now(), bidderNotificationContent, "auction");
            NotificationDAO.insertNotification(notificationForBidder);
            BidDAO.deleteBid(bid.getBidId());
        }
    }

}
