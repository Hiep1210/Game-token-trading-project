/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.NotificationDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Cart;
import model.Notification;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "InsertBuyRequestNotifcationController", urlPatterns = {"/InsertBuyRequestNotifcationController"})
public class InsertBuyRequestNotifcationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Users are not allowed to access this servlet through the doGet method
        request.getRequestDispatcher("BuyPageController").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        insertBuyRequestNotifcation(request, response);
    }

    private void insertBuyRequestNotifcation(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            String redirect = "BuyPageController";
            ArrayList<Cart> cartList = (ArrayList<Cart>) request.getAttribute("cartList");
            String gameAccountName = request.getParameter("gameAccountName");
            String buyerNotificationContent;
            String sellerNotificationContent;
            Notification notificationForBuyer;
            Notification notificationForSeller;
            if (user == null) { // if session does not contain any user instance
                redirect = "BuyPageController";
            } else {
                for (Cart cart : cartList) {
                    buyerNotificationContent = "You have bought " + cart.getItemName() + "|" + cart.getType()
                            + " using game account : " + gameAccountName + ".Please wait for admin to trasnfer this item to you!";
                    sellerNotificationContent = "Your item :" + cart.getItemName() + "|" + cart.getType()
                            + " has been sold using game account :" + cart.getgameAccountName() + ".Please transfer this item to the server game account!";
                    notificationForBuyer = new Notification(cart.getBuyer_id(), getCurrentDate(), buyerNotificationContent, "buy");
                    notificationForSeller = new Notification(cart.getUserid(), getCurrentDate(), sellerNotificationContent, "sell");
                    NotificationDAO.insertNotification(notificationForBuyer);
                    NotificationDAO.insertNotification(notificationForSeller);
                }
            }
            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String getCurrentDate() {
        java.util.Date date = new java.util.Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        return strDate;
    }

}
