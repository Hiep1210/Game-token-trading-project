package controller;

import dao.AuctionDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MarketItems;
import dao.MarketItemsDAO;
import java.util.ArrayList;
import model.Auction;

@WebServlet(name = "SearchAuctionController", urlPatterns = {"/SearchAuctionController"})
public class SearchAuctionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("AuctionPageController");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String input = request.getParameter("search").trim();
        String[] analyze = input.split("\\s");
        ArrayList<Auction> searchAuctionList = AuctionDAO.search(analyze);
        request.setAttribute("searchAuctionList", searchAuctionList);
        request.getRequestDispatcher("AuctionPageController").forward(request, response);
    }
}
