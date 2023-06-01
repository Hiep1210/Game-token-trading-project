/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.NotificationDAO;
import dao.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
//        processRequest(request, response);
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

        }
    }

    public void adminNotification(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            LocalDate date = java.time.LocalDate.now();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String strDate = formatter.format(date);
            String noti_content;
            Notification notification;
            ArrayList<Role> roleList = RoleDAO.getRoleList();
            if (user == null) {
                //do something
            }
            if (!isAdmin(user.getRole_id())) {
                //do something
            }
            noti_content = request.getParameter("noti_content");
            notification = new Notification(user.getId(), strDate, noti_content, "admin");
        } catch (Exception e) {
            System.out.println(e);
        }
        return;
    }

    public boolean isAdmin(int role_id) {
        ArrayList<Role> roleList = RoleDAO.getRoleList();
        boolean isAdmin = false;
        for (Role role : roleList) {
            if (role.getRole() == role_id && role.getRole_name() == "admin") {
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
