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
import java.io.PrintWriter;
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

        //if sell list already have 5 item then return
        int userSellListLength = SellListDAO.getUserSellList(user.getId()).size();
        if (userSellListLength == 5) {
            return;
        }

        String skinName = request.getParameter("skinName");
        String exterior = request.getParameter("exterior");
        int sellTime = Integer.parseInt(request.getParameter("sellTime"));
        double price = Double.parseDouble(request.getParameter("price"));
        String gameAccount = request.getParameter("gameAccount");

        int sellerId = user.getId();
        GameItems gameItem = GameItemsDAO.getItemBySkinName(skinName, exterior);

        if (gameItem != null) {
            SellItems sellItem = new SellItems(exterior, sellTime, price, gameAccount, sellerId, gameItem.getId());
            
            //insert item to sellitems table
            SellListDAO.insertSellItemsItem(sellItem);
            
            //Insert item to selllist table
            int sellItemId = SellListDAO.getSellItemId(sellItem);
            SellListDAO.addToSellList(sellerId, sellItemId);
            
            //get sell item info for printing to UI
            SellItems sellItemInfo = SellListDAO.getSellListItemInfo(sellItemId);

            PrintWriter out = response.getWriter();
            out.println("<!-- Item Card -->\n"
                    + "                                <div class=\"sell-card mb-3\" id=\"sell-card-" + sellItemId + "\">\n"
                    + "                                    <div class=\"row g-0\">\n"
                    + "                                        <div class=\"col-md-4\">\n"
                    + "                                            <img src=\"UI/image/" + sellItemInfo.getImg() + ".png\" class=\"img-fluid rounded\" alt=\"...\">\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"col-md-8\">\n"
                    + "                                            <div class=\"card-body\">\n"
                    + "                                                <h5 class=\"card-title mb-2\">" + sellItemInfo.getType() + " | " + sellItemInfo.getItemName() + " " + sellItemInfo.getSkinName() + " (" + sellItem.getExterior() + ")\n"
                    + "                                                </h5>\n"
                    + "                                                <p class=\"card-text\">Selling price: " + sellItemInfo.getPrice() + "</p>\n"
                    + "                                                <p class=\"card-text\">Selling time: " + sellItemInfo.getSellTime() + "</p>\n"
                    + "                                                <p class=\"card-text\">Game Account: " + sellItemInfo.getGameAccount() + "</p>\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                        <button class=\"btn item-card-button sell-list-cart-button mt-2\" onclick=\"deleteSellListItem(" + sellItemId + ")\">\n"
                    + "                                            <i class=\"fa-solid fa-trash\"></i>\n"
                    + "                                        </button>\n"
                    + "                                    </div>\n"
                    + "                                </div>");

        }
    }
}
