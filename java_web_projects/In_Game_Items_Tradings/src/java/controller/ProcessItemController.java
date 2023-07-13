package controller;

import dao.AuctionDAO;
import dao.BidDAO;
import dao.MarketItemsDAO;
import dao.ProcessItemsDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Auction;
import model.Bid;
import model.MarketItems;
import model.ProcessItem;
import model.User;

@WebServlet(name = "ProcessItemController", urlPatterns = {"/ProcessItemController"})
public class ProcessItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Users are not allowed to access this servlet through the doGet method
        request.getRequestDispatcher("BuyPageController").forward(request, response);
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
            System.out.println(e);
        }
    }
}
