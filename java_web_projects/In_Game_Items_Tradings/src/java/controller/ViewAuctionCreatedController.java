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
import model.User;

@WebServlet(name = "ViewAuctionCreatedController", urlPatterns = {"/ViewAuctionCreatedController"})
public class ViewAuctionCreatedController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String redirect = "auctionCreated.jsp";
        ArrayList<Auction> auctionList;
        int id;
        if (user != null) {
            id = user.getId();
            auctionList = AuctionDAO.getAllAuctionFromUser(id);
            request.setAttribute("auctionList", auctionList);
            for (Auction auction : auctionList) {
                auction.setBidList(BidDAO.getBidsFromAuctionId(auction.getAuctionId()));
            }
        } else {
            redirect = "AuctionPageController";
        }
        request.getRequestDispatcher(redirect).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
