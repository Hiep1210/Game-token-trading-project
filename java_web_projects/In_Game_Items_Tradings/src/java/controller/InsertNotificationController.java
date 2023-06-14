/*
*Programmer: Trần Thế Hùng & Nguyễn Chí Trung
*Description: This file contain the various method to generate a notification of various types
 */
package controller;

import dao.NotificationDAO;
import dao.RoleDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Notification;
import model.PaymentRequest;
import model.Role;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "InsertNotificationController", urlPatterns = {"/InsertNotificationController"})
public class InsertNotificationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //Users are not allowed to access this servlet through the doGet method
        request.getRequestDispatcher("DisplayMarketItemsController").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String type = request.getParameter("type");
            switch (type) {
                case "admin":
                    adminNotification(request, response);
                    break;
                case "payment":
                    paymentNotification(request, response);
                    break;
                default:
                    return;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void adminNotification(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            String redirect = "DisplayMarketItemsController";// if redirect attribute is null auto redirect to DisplayMarketItemsController
            String notiContent;
            Notification notification;
            if (user == null) { // if session does not contain any user instance
                redirect = "DisplayMarketItemsController";
            } else if (!isAdmin(user.getRole_id())) { //if user in session is not an admin
                redirect = "DisplayMarketItemsController";
            } else {
                notiContent = request.getParameter("content");
                notification = new Notification(user.getId(), getCurrentDate(),notiContent, "admin");
                NotificationDAO.insertNotification(notification);
            }
            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Check user role is admin
    public boolean isAdmin(int role_id) {
        ArrayList<Role> roleList = RoleDAO.getRoleList();
        boolean isAdmin = false;
        for (Role role : roleList) {
            if (role.getRole() == role_id && "admin".equals(role.getRole_name())) {
                isAdmin = true;
                break;
            }
        }
        return isAdmin;
    }

    private void paymentNotification(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            String redirect = "GetPaymentRequestController";
            PaymentRequest paymentRequest = (PaymentRequest) request.getAttribute("paymentRequest");
            String decision = request.getParameter("decision");
            String notiContent;
            Notification notification;
            if (user == null) { // if session does not contain any user instance
                redirect = "DisplayMarketItemsController";
            } else if (!isAdmin(user.getRole_id())) { //if user in session is not an admin
                redirect = "DisplayMarketItemsController";
            } else {
                if (decision.equals("accept")) {
                    notiContent = "Your payment request for " + paymentRequest.getMoney() + "$ has been ACCEPTED!";
                } else {
                    notiContent = "Your payment request for " + paymentRequest.getMoney() + "$ has been DENIED!";
                }
                notification = new Notification(paymentRequest.getUser_id(), getCurrentDate(), notiContent, "payment");
                NotificationDAO.insertNotification(notification);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
