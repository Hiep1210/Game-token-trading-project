/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import model.GameItems;
import model.Notification;
import model.PaymentRequest;
import model.Role;
import model.User;

/**
 *
 * @author ksiks
 */
@WebServlet(name = "SellNotificationController", urlPatterns = {"/SellNotificationController"})
public class sellNotificationController extends HttpServlet {
    public void sellNotification(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            GameItems gameItems = (GameItems) request.getAttribute("gameItems");
            String redirect = "DisplayMarketItemsController";// if redirect attribute is null auto redirect to DisplayMarketItemsController
            String notiContent;
            Notification notification;
            if (user == null)
            { // if session does not contain any user instance
                redirect = "DisplayMarketItemsController";
            }
            else 
            { // if the user sell the item correctly
                notiContent = "You have sell " + gameItems.getSkinName()+" "+gameItems.getItemName() +"successfully" ;
                notification = new Notification(user.getId(), getCurrentDate(),notiContent, "sell");
                NotificationDAO.insertNotification(notification);
            }
            request.getRequestDispatcher(redirect).forward(request, response);
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    
    private String getCurrentDate() {
        java.util.Date date = new java.util.Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        return strDate;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
