/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.MarketItemsDAO;
import dao.SellListDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.MarketItems;
import model.SellItems;
import model.User;

/**
 *
 * @author VICTUS
 */
@WebServlet(name = "ViewUserMarketItems", urlPatterns = {"/ViewUserMarketItems"})
public class ViewUserMarketItems extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ArrayList<MarketItems> sellingList = new ArrayList<>();
        if (user != null) {
            sellingList = MarketItemsDAO.getUserSellingItems(user.getId());
        }
        request.setAttribute("sellingList", sellingList);
        request.getRequestDispatcher("userSellingItems.jsp").forward(request, response);
    }

}
