/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import dao.ProcessItemsDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Cart;
import model.MarketItems;
import model.ProcessItem;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "ProcessCartController", urlPatterns = {"/ProcessCartController"})
public class ProcessCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Users are not allowed to access this servlet through the doGet method
        request.getRequestDispatcher("BuyPageController").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String gameAccountName = request.getParameter("gameAccountName");
        String rawCartId = request.getParameter("cartId");
        String redirect = "InsertBuyRequestNotifcationController";
        ArrayList<Cart> cartList = new ArrayList<>();
        ProcessItem processItem;
        double totalCartAmount = 0;
        double updatedUserAmount = 0;
        int cartId = 0;
        String message = "You do not have enough funds to buy all items in your cart! Please top up or delete some item in your cart!";
        try {
            
            if (user == null) {
                redirect = "BuyPageController";
            } else {
                if (rawCartId != null) {
                    cartId = Integer.parseInt(rawCartId);
                    cartList.add(CartDAO.getCartById(cartId));
                } else {
                    cartList = CartDAO.getAllCartItems(user.getId());
                }
                
                if (cartList.isEmpty()) {
                    redirect = "BuyPageController";
                } else {
                    for (Cart cart : cartList) {
                        totalCartAmount += cart.getPrice();
                    }
                    if (totalCartAmount > user.getMoney()) {
                        request.setAttribute("message", message);
                        redirect = "ViewCartController";
                    } else {
                        //Update user wallet after buying
                        updatedUserAmount = user.getMoney() - totalCartAmount;
                        UserDAO.updateUserMoney(user.getId(), updatedUserAmount);
                        user.setMoney(updatedUserAmount);
                        //Delete all items in cart 
                        for (Cart cart : cartList) {
                            //Delete all cart record that contain market item id 
                            CartDAO.deleteCartWithMarketItemId(cart.getMarketItemId());
                            processItem = new ProcessItem(cart.getUserid(), cart.getBuyer_id(), cart.getMarketItemId() , 1, gameAccountName, LocalDateTime.now());
                            ProcessItemsDAO.insertProcessItems(processItem);
                        }
                        request.setAttribute("cartList", cartList);
                    }
                }
            }
            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
