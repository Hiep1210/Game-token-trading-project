/*
*Programmer: Trần Thế Hùng 
*Description: This file contain the various method to generate a notification of various types
 */
package controller;

import dao.NotificationDAO;
import dao.RoleDAO;
import dao.UserDAO;
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
import model.Role;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "InsertNotification", urlPatterns = {"/InsertNotification"})
public class InsertNotification extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Users are not allowed to access this servlet through the doGet method
        request.getRequestDispatcher("DisplayMarketItemsController").forward(
                request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String type = request.getParameter("type");
            switch (type) {
                case "adminNotification":
                    adminNotification(request, response);
                default:
                    return;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void adminNotification(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            java.util.Date date = new java.util.Date();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(date);
            String redirect;// if redirect attribute is null auto redirect to DisplayMarketItemsController
            String notiContent;
            Notification notification;
            if (user == null) { // if session does not contain any user instance
                redirect = "DisplayMarketItemsController";
            } else if (!isAdmin(user.getRole_id())) { //if user in session is not an admin
                redirect = "DisplayMarketItemsController";
            } else {
                redirect = request.getParameter("redirect");
                notiContent = request.getParameter("content");
                notification = new Notification(user.getId(), strDate,
                        notiContent, "admin");
                //Get all user id in database
                ArrayList<Integer> userIdList = UserDAO.getAllUserId();
                //Send notification instance to all user id in user id list
                NotificationDAO.sendNotificationToAllUser(notification,
                        userIdList);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
