/*
*Programmer: Trần Thế Hùng 
*Description: This file retreive notification list according  to user id
 */
package controller;

import dao.NotificationDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Notification;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "GetNotificationController", urlPatterns = {"/GetNotificationController"})
public class GetNotificationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        ArrayList<Notification> notificationList;
        int id;
        String redirect = request.getParameter("redirect");
        //In case session contain no user attribute or redirect parameter is missing
        if (redirect == null || user == null) {
            redirect = "DisplayMarketItemsController";
        } else {
            id = user.getId();
            notificationList = NotificationDAO.getAllUserNotification(id);
            request.setAttribute("notificationList", notificationList);
        }
        request.getRequestDispatcher(redirect).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
