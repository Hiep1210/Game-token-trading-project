/*
*Programmer: Trần Thế Hùng 
*Description: This file is for getting and creating auction 
 */
package controller;

import dao.AuctionDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import model.Auction;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "CreateAuctionController", urlPatterns = {"/CreateAuctionController"})
public class CreateAuctionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //Users are not allowed to access this servlet through the doGet method
        request.getRequestDispatcher("DisplayMarketItemsController").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        createAuction(request, response);
    }

    public void createAuction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // if redirect attribute is null auto redirect to DisplayMarketItemsController
        Auction auction;
        String redirect = "AuctionPageController";
        int itemId;
        double lowestBid;
        int auctionDuration;
        String gameAccountName;
        User user;
        try {
            user = (User) request.getSession().getAttribute("user");
            itemId = Integer.parseInt(request.getParameter("itemId")) ;
            lowestBid = Double.parseDouble(request.getParameter("lowestBid"));
            auctionDuration = Integer.parseInt(request.getParameter("auctionDuration"));
            gameAccountName = request.getParameter("content");
            auction = new Auction(itemId, user.getId() , lowestBid, gameAccountName, 
                    LocalDateTime.now() , LocalDateTime.now().plusDays(auctionDuration));
            AuctionDAO.insertAuction(auction);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Something went wrong creating your auction!");
            redirect = "createAuction.jsp";
            System.out.println(e);
        } finally {
            request.getRequestDispatcher(redirect).forward(request, response);
        }
    }

}
