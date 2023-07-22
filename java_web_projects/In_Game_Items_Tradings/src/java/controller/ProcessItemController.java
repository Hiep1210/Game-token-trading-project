package controller;

import dao.AuctionDAO;
import dao.BidDAO;
import dao.MarketItemsDAO;
import dao.NotificationDAO;
import dao.ProcessItemsDAO;
import dao.TradeDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import model.Auction;
import model.Bid;
import model.MarketItems;
import model.Notification;
import model.OfferItem;
import model.ProcessItem;
import model.ReceiveItem;
import model.TradeItem;
import model.User;

@WebServlet(name = "ProcessItemController", urlPatterns = {"/ProcessItemController"})
public class ProcessItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Users are not allowed to access this servlet through the doGet method
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
        }

        ArrayList<ProcessItem> tradeProcess = ProcessItemsDAO.getAllTradeOffersInProcess();
        HashMap<Integer, ArrayList<OfferItem>> offerItem = new HashMap<>();
        HashMap<Integer, ArrayList<ReceiveItem>> receiveItem = new HashMap<>();
        for (int i = 0; i < tradeProcess.size(); i++) {
            offerItem.put(tradeProcess.get(i).getTransactionId(), TradeDAO.getAllOffersInATrade(tradeProcess.get(i).getTransactionId()));
            receiveItem.put(tradeProcess.get(i).getTransactionId(), TradeDAO.getAllRecsInATrade(tradeProcess.get(i).getTransactionId()));
        }
        request.setAttribute("trade", tradeProcess);
        request.setAttribute("offer", offerItem);
        request.setAttribute("rec", receiveItem);
        request.getRequestDispatcher("processTrade.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            User userForTransaction;
            String rawProcessItemId = request.getParameter("processItemId");
            ProcessItem processItem;
            String decision = request.getParameter("decision");
            String redirect = "InsertProcessNotificationController";
            Auction auction;
            Bid bid = new Bid();
            int processItemId;
            double newMoneyAmount = 0;
            int senderId;
            int recId;
            if (user == null || user.getRoleid() != 2) {
                redirect = "BuyPageController";
            } else if (rawProcessItemId == null || decision == null) {
                redirect = "BuyPageController";
            } else {
                //Get payment request information by payment request id
                processItemId = Integer.parseInt(rawProcessItemId);
                processItem = ProcessItemsDAO.getProcessItems(processItemId);
                if (processItem.getTransactionTypeIdId() == 1) {
                    processItem.setObject(MarketItemsDAO.getMarketItem(processItem.getTransactionId()));
                    MarketItemsDAO.deletelMarketItem(processItem.getTransactionId());
                } else if (processItem.getTransactionTypeIdId() == 2) {
                    auction = AuctionDAO.getAuction(processItem.getTransactionId());
                    auction.setBidList(BidDAO.getBidsFromAuctionId(auction.getAuctionId()));
                    bid = (Bid) auction.getBidList().get(0);
                    processItem.setObject(auction);
                    BidDAO.deleteBid(bid.getBidId());
                    AuctionDAO.deleteAuction(processItem.getTransactionId());
                } else if (processItem.getTransactionTypeIdId() == 3) {
                    senderId = Integer.parseInt(request.getParameter("sender"));
                    recId = Integer.parseInt(request.getParameter("receiver"));
                    int tradeId = processItem.getTransactionId();
                    TradeDAO.deleteOfferItems(tradeId);
                    TradeDAO.deleteRecitems(tradeId);
                    TradeDAO.deleteTrade(tradeId);
                    ProcessItemsDAO.deleteProcessItems(processItemId);
                    if (decision.equals("accept")) {
                        NotificationDAO.insertNotification(new Notification(senderId, LocalDateTime.now(),
                                "Admin has accepted your trade offer, please wait for items to be sent to your game account", "trade"));
                        NotificationDAO.insertNotification(new Notification(recId, LocalDateTime.now(),
                                "Admin has accepted your trade offer, please wait for items to be sent to your game account", "trade"));
                    } else {
                        NotificationDAO.insertNotification(new Notification(senderId, LocalDateTime.now(),
                                "Admin has rejected your trade offer", "trade"));
                        NotificationDAO.insertNotification(new Notification(recId, LocalDateTime.now(),
                                "Admin has rejected your trade offer", "trade"));
                    }
                    request.setAttribute("message", "Trade offer processed");
                    doGet(request, response);
                }
                ProcessItemsDAO.deleteProcessItems(processItemId);
                // If payment request is accepted add funds to user account
                if (decision.equals("accept")) {
                    if (processItem.getTransactionTypeIdId() == 1) {
                        userForTransaction = UserDAO.GetUserInformation(processItem.getSenderId());
                        newMoneyAmount = userForTransaction.getMoney() + ((MarketItems) processItem.getObject()).getPrice();
                        UserDAO.updateUserMoney(processItem.getSenderId(), newMoneyAmount);
                    } else if (processItem.getTransactionTypeIdId() == 2) {
                        userForTransaction = UserDAO.GetUserInformation(processItem.getSenderId());
                        newMoneyAmount = userForTransaction.getMoney() + (bid.getAmount());
                        UserDAO.updateUserMoney(processItem.getSenderId(), newMoneyAmount);
                    }
                } else {
                    if (processItem.getTransactionTypeIdId() == 1) {
                        userForTransaction = UserDAO.GetUserInformation(processItem.getReceiverId());
                        newMoneyAmount = userForTransaction.getMoney() + ((MarketItems) processItem.getObject()).getPrice();
                        UserDAO.updateUserMoney(processItem.getReceiverId(), newMoneyAmount);
                    } else if (processItem.getTransactionTypeIdId() == 2) {
                        userForTransaction = UserDAO.GetUserInformation(processItem.getReceiverId());
                        newMoneyAmount = userForTransaction.getMoney() + (bid.getAmount());
                        UserDAO.updateUserMoney(processItem.getSenderId(), newMoneyAmount);
                    }
                }
                request.setAttribute("processItem", processItem);
            }
            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception e) {
            System.out.println("Error in ProcessItemController");
            e.printStackTrace();
        }
    }
}
