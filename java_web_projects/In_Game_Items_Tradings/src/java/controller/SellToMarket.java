package controller;

import dao.SellDAO;
import dao.SellListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.SellItems;
import model.User;

/**
 *
 * @author VICTUS
 */
@WebServlet(name = "SellToMarket", urlPatterns = {"/sellToMarket"})
public class SellToMarket extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user != null) {
            ArrayList<SellItems> sellList = SellListDAO.getUserSellList(user.getId());
            for (SellItems sellItems : sellList) {
                SellItems sellItemInfo = SellListDAO.getSellListItemInfo(sellItems.getId());
                SellListDAO.sellToMarket(sellItemInfo);
                System.out.println("added to market");
                SellListDAO.deleteSellListItem(user.getId(), sellItemInfo.getId());
            }
        }
        
        
    }

}
