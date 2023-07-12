package controller;

import dao.NotificationDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import model.GameItems;
import model.Notification;
import model.User;

@WebServlet(name = "SellNotificationController", urlPatterns = {"/SellNotificationController"})
public class SellNotificationController extends HttpServlet {

    public void sellNotification(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            GameItems gameItems = (GameItems) request.getAttribute("gameItems");
            String redirect = "BuyPageController";// if redirect attribute is null auto redirect to BuyPageController
            String notiContent;
            Notification notification;
            if (user == null) { // if session does not contain any user instance
                redirect = "BuyPageController";
            } else { // if the user sell the item correctly
                notiContent = "You have sold " + gameItems.getSkinName() + " " + gameItems.getItemName() + "successfully";
                notification = new Notification(user.getId(), LocalDateTime.now(), notiContent, "sell");
                NotificationDAO.insertNotification(notification);
            }
            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}