/*
*Programmer: Nguyễn Chí Trung
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
import java.io.PrintWriter;
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
        PrintWriter out = response.getWriter();
        User user = (User) request.getSession().getAttribute("user");
        int id = user.getId();
        ArrayList<Notification> notificationList = NotificationDAO.getAllUserNotification(id);
        for (Notification noti : notificationList) {
            out.println("<a class=\"dropdown-item\" href=\"#\">"+noti.getNoti_content() +"</a>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
