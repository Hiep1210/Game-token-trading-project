/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.NotificationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.MarketItems;
import model.Notification;
import model.PaymentRequest;
import model.ProcessItem;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "InsertProcessNotificationController", urlPatterns = {"/InsertProcessNotificationController"})
public class InsertProcessNotificationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        insertProcessNotification(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        insertProcessNotification(request, response);
    }

    private void insertProcessNotification(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            String redirect = "GetProcessItemController";
            ProcessItem processItem = (ProcessItem) request.getAttribute("processItem");
            String decision = request.getParameter("decision");
            String denyReason = request.getParameter("denyReason");
            String buyerNotificationContent;
            String sellerNotificationContent;
            Notification notificationForBuyer;
            Notification notificationForSeller;
            if (user == null) { // if session does not contain any user instance
                redirect = "BuyPageController";
            } else if (user.getRoleid() != 2) { //if user in session is not an admin
                redirect = "BuyPageController";
            } else {
                if (decision.equals("accept")) {
                    buyerNotificationContent = "Item" + ((MarketItems) processItem.getObject()).getItemName() + "|" + ((MarketItems) processItem.getObject()).getType()
                            + "has been transfered to game account : " + processItem.getGameAccountName();
                    sellerNotificationContent = "Item :" + ((MarketItems) processItem.getObject()).getItemName() + "|" + ((MarketItems) processItem.getObject()).getType()
                            + " has successfully sold for : " + ((MarketItems) processItem.getObject()).getPrice();
                } else {
                    buyerNotificationContent = "Item" + ((MarketItems) processItem.getObject()).getItemName() + "|" + ((MarketItems) processItem.getObject()).getType()
                            + "has been canceled. Reason: " + denyReason;
                    sellerNotificationContent = "Item :" + ((MarketItems) processItem.getObject()).getItemName() + "|" + ((MarketItems) processItem.getObject()).getType()
                            + " has been canceled. Reason : " + denyReason;

                }
                notificationForBuyer = new Notification(processItem.getReceiverId(), getCurrentDate(), buyerNotificationContent, "buy");
                notificationForSeller = new Notification(processItem.getSenderId(), getCurrentDate(), sellerNotificationContent, "sell");
                NotificationDAO.insertNotification(notificationForBuyer);
                NotificationDAO.insertNotification(notificationForSeller);
            }
            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception e) {
            System.out.println("Error in insertProcessNotification");
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
