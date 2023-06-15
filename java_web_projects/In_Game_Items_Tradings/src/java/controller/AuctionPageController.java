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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
