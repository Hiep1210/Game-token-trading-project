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
@WebServlet(name = "GetNotification", urlPatterns = {"/GetNotification"})
public class GetNotification extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String redirect = "DisplayMarketItemsController";//In case redirect attribute is null
        // Only retrieve notification if there is an user attribute in session
        if (user != null) {
            redirect = request.getParameter("redirect");
            int id = user.getId();
            ArrayList<Notification> notiList
                    = NotificationDAO.getAllUserNotification(id);
            request.setAttribute("notiList", notiList);
        }
        request.getRequestDispatcher(redirect).forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
