package controller;

import dao.GameItemsDAO;
import dao.SellListDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GameItems;
import model.SellItems;
import model.User;

/**
 *
 * @author VICTUS
 */
@WebServlet(name = "AddSellListController", urlPatterns = {"/addSellList"})
public class AddSellListController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String skinName = request.getParameter("skinName");
        String exterior = request.getParameter("exterior");
        int sellTime = Integer.parseInt(request.getParameter("sellTime"));
        double price = Double.parseDouble(request.getParameter("price"));
        String gameAccount = request.getParameter("gameAccount");

        int sellerId = user.getId();
        GameItems gameItem = GameItemsDAO.getItemBySkinName(skinName, exterior);

        if (gameItem != null) {
            SellItems sellItem = new SellItems(exterior, sellTime, price, gameAccount, sellerId, gameItem.getId());

            try {
                SellListDAO.insertSellListItem(sellItem);
                int sellItemId = SellListDAO.getSellItemId(sellItem);
                System.out.println("selliteid = " + sellItemId);
                
                SellListDAO.addToSellList(sellerId, sellItemId);
                // Commit the transaction here
            } catch (Exception e) {
                // Handle transaction errors
                // For example, you can roll back the transaction and show an error message to the user
                return;
            }

            // Redirect or display a success message to the user
        } else {
            // Handle case when gameItem is not found
            // For example, you can show an error message to the user
        }
    }

}
