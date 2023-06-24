package controller;

import dao.SellDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name = "DeleteSellItem", urlPatterns = {"/DeleteSellItem"})
public class SellListRemoveController {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("SellPageController");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String message = null;
        if (!SellDAO.deleteSellItem(id)) {
            message = "deleted Cart failed";
        }
        request.setAttribute("message", message);
        User user = (User) request.getSession().getAttribute("user");
        int userid = user.getId();
        response.sendRedirect("SellPageController?id=" + userid);
    }

}
