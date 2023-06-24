/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This files is controller for displaying items on the market
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.MarketItemsDao;
import java.util.ArrayList;
import model.MarketItems;

@WebServlet(name = "BuyPageController", urlPatterns = {"/BuyPageController"})
public class BuyPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<MarketItems> marketlist = MarketItemsDao.getAllMarketItems();
        request.setAttribute("marketlist", marketlist);
        request.getRequestDispatcher("buy.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
