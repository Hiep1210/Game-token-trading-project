/*
*Programmer: Trần Thế Hùng 
*Description: This file is responsible for inserting bid from users.
 */
package controller;

import dao.BidDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import model.Bid;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "InsertBidController", urlPatterns = {"/InsertBidController"})
public class InsertBidController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sendBid(request, response);
    }

    /* Add bid to database*/
    public void sendBid(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int actionId = 0;
        int bidId = 0;
        double amount = 0;
        double newMoneyAmount = 0;
        String gameAccountName = "";
        Bid bid;
        LocalDateTime currentTime;
        try {
            gameAccountName = request.getParameter("gameAccountName");
            amount = Double.parseDouble(request.getParameter("bidAmount"));
            actionId = Integer.parseInt(request.getParameter("auctionId"));
            bidId = Integer.parseInt(request.getParameter("bidId"));

        } catch (Exception e) {
            System.out.println(e);
        }
        User user = (User) request.getSession().getAttribute("user");
        /* If user does not have enough money*/
        if (user.getMoney() < amount) {
            request.setAttribute("errorMessage", "You do not have enough funds to bid! Please top up your account before bidding");
        } else if (gameAccountName.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Game account name must not be empty!");
        } else {
            /* If form does not contain userBidId (meaning user is bidding for the first time on this auction ) add new bid to table*/
            if (bidId == 0) {
                System.out.println(gameAccountName);
                bid = new Bid(user.getId(), LocalDateTime.now(), amount, gameAccountName, actionId);
                BidDAO.insertBid(bid);
                /* If form contain userBidId (meaning user is increasing his bid) change bid amount on old bid*/
            } else {
                BidDAO.changeBidAmount(bidId, amount, LocalDateTime.now());
            }
            newMoneyAmount = user.getMoney() - amount;
            UserDAO.updateUserMoney(user.getId(), newMoneyAmount);
            user.setMoney(newMoneyAmount);
        }

        request.getRequestDispatcher("AuctionPageController").forward(request, response);
    }
}
