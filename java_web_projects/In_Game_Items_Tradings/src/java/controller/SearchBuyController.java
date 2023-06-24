package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MarketItems;
import dao.MarketItemsDao;
import java.util.ArrayList;

@WebServlet(name = "SearchBuyController", urlPatterns = {"/SearchBuyController"})
public class SearchBuyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("BuyPageController");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String input = request.getParameter("search").trim();
        String[] analyze = input.split("\\s");
        ArrayList<MarketItems> marketItems = MarketItemsDao.Search(analyze);
        request.setAttribute("marketlist", marketItems);
        request.getRequestDispatcher("buy.jsp").forward(request, response);
    }
}
