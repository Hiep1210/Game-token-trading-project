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
import model.User;

/**
 *
 * @author VICTUS
 */
@WebServlet(name = "DeleteUserMarketItems", urlPatterns = {"/DeleteUserMarketItems"})
public class DeleteUserMarketItems extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the parameter values sent from AJAX
        String itemIdStr = request.getParameter("itemId");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                int itemId = Integer.parseInt(itemIdStr);

                MarketItemsDAO.deleteUserSellingItems(itemId);

            } catch (NumberFormatException e) {
                response.getWriter().write("Invalid itemId format.");
            }
        }
        request.getRequestDispatcher("userSellingItems.jsp").forward(request, response);
    }
}
