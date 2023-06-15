/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AuctionDAO;
import dao.BidDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Auction;
import model.Bid;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "GetAuctionInfoController", urlPatterns = {"/GetAuctionInfoController"})
public class GetAuctionInfoController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getAnAuction(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getAnAuction(request, response);
    }

    public void getAnAuction(HttpServletRequest request, HttpServletResponse response) {
        String rawAuctionId = request.getParameter("auctionId");
        int auctionId; 
        try {
            auctionId = Integer.parseInt(rawAuctionId);
            Auction auction = AuctionDAO.getAuction(auctionId);
            User user = (User) request.getSession().getAttribute("user");
            /* get seller id for the auction */
            int sellerId = auction.getSellerId();
            /* Guest session */
            if (user == null) {
                request.setAttribute("isSeller", false);
            } else {
                request.setAttribute("isSeller", sellerId == user.getId());
            }
            auction.getStartingDate().until(auction.getEndingDate(),ChronoUnit.MILLIS );
            /* get the highest bid */
            List<Bid> bidList = BidDAO.getBidsFromAuctionId(auctionId);
            List<User> biddersList = new ArrayList<>();
            for (Bid bid : bidList) {
                biddersList.add(UserDAO.GetUserInformation(bid.getBidderId()));
            }
            if (auction.getEndingDate().isBefore(LocalDateTime.now())) {
                request.setAttribute("isEnded", true);
            } else {
                request.setAttribute("isEnded", false);
            }
            
            
            User seller = UserDAO.GetUserInformation(auction.getSellerId());
            request.setAttribute("auction", auction);
            request.setAttribute("seller", seller);
            request.setAttribute("biddersList", biddersList);
            request.setAttribute("bidList", bidList);

            request.getRequestDispatcher("auctionInfo.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
