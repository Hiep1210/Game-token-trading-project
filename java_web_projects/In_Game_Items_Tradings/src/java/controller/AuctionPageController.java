/*
*Programmer: Trần Thế Hùng 
*Description: This file is responsible for getting and showing auction page info
 */
package controller;

import dao.AuctionDAO;
import dao.BidDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Auction;

/**
 *
 * @author Asus
 */
@WebServlet(name = "AuctionPageController", urlPatterns = {"/AuctionPageController"})
public class AuctionPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        goToAuctionPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        goToAuctionPage(request, response);
    }

    public void goToAuctionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Auction> auctionList = AuctionDAO.getAllAuctions();
        double amount;
        for (Auction auction : auctionList) {
            amount = BidDAO.getHighestBidFromAuctionId(auction.getAuctionId());
            //If highest bid is > 0 set as lowest bid, if not found amount would be = -1 
            if (amount > 0) {
                auction.setLowestBid(amount);
            }
        }
        request.setAttribute("auctionList", auctionList);
        request.getRequestDispatcher("auction.jsp").forward(request, response);
    }

}
