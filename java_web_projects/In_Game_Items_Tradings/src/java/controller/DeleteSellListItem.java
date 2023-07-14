package controller;

import dao.SellListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author VICTUS
 */
@WebServlet(name = "DeleteSellListItem", urlPatterns = {"/deleteSellListItem"})
public class DeleteSellListItem extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int sellItemId = Integer.parseInt(request.getParameter("sellItemId"));

        if (user != null) {
            int sellerId = user.getId();
            SellListDAO.deleteSellListItem(sellerId, sellItemId);
            SellListDAO.deleteSellItemsItem(sellerId, sellItemId);
            PrintWriter out = response.getWriter();
            out.print("Item deleted successfully");
        }
    }

}
