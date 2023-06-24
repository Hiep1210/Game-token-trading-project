package controller;

import dao.MarketItemsDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.MarketItems;

@WebServlet(name = "SortBuyController", urlPatterns = {"/SortBuyController"})
public class SortBuyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("BuyPageController");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] type = request.getParameterValues("type");
        String[] exterior = request.getParameterValues("exterior");
        String[] rarity = request.getParameterValues("rarity");
        ArrayList<MarketItems> marketItems = MarketItemsDAO.Filter(request.getParameter("priceorder"), type, rarity, exterior);
        request.setAttribute("marketlist", marketItems);
        request.getRequestDispatcher("buy.jsp").forward(request, response);

    }

}
