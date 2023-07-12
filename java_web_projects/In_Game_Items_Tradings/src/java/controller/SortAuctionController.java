package controller;

import dao.AuctionDAO;
import dao.MarketItemsDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Auction;


@WebServlet(name = "SortAuctionController", urlPatterns = {"/SortAuctionController"})
public class SortAuctionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("AuctionPageController");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] type = request.getParameterValues("type");
        String[] exterior = request.getParameterValues("exterior");
        String[] rarity = request.getParameterValues("rarity");
        ArrayList<Auction> filteredAuctionList = AuctionDAO.filter(request.getParameter("priceorder"), type, rarity, exterior);
        request.setAttribute("filteredAuctionList", filteredAuctionList);
        request.getRequestDispatcher("AuctionPageController").forward(request, response);

    }

}
