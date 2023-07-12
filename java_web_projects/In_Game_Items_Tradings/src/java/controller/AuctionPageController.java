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
import model.Bid;
import model.User;

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
        User user = (User) request.getSession().getAttribute("user");
        ArrayList<Auction> auctionList = AuctionDAO.getAllAuctions();
        ArrayList<Auction> joinedAuctionList = new ArrayList<>();
        ArrayList<Auction> notJoinedAuctionList = new ArrayList<>();
        //If user is logged in, create an array list for auctions that user has joined in and exclude auction that user created
        if (user != null) {
            for (Auction auction : auctionList) {
                //Exclude auction that user created
                if (auction.getSellerId() != user.getId()) {
                    auction.setBidList(BidDAO.getBidsFromAuctionId(auction.getAuctionId()));
                    if (checkIfUserIsBidder(auction.getBidList() , user)) {
                        joinedAuctionList.add(auction);
                    } else {
                        notJoinedAuctionList.add(auction);
                    }
                }
            }
            
            request.setAttribute("joinedAuctionList", joinedAuctionList);
            request.setAttribute("notJoinedAuctionList", notJoinedAuctionList);
            //If user is not logged in, show every thing
        } else {
            for (Auction auction : auctionList) {
                auction.setBidList(BidDAO.getBidsFromAuctionId(auction.getAuctionId()));
            }
            request.setAttribute("notJoinedAuctionList", auctionList);
        }
        request.getRequestDispatcher("auction.jsp").forward(request, response);
    }

    public boolean checkIfUserIsBidder(ArrayList<Bid> bidList, User user) {
        for (Bid bid : bidList) {
            if (bid.getBidderId() == user.getId()) {
                return true;
            }
        }
        return false;
    }

}
