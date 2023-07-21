package controller;

import dao.SellListDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
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
                SellItems sellItemInfo = SellListDAO.getSellItemInfo(sellItems.getId());

                long currentTime = System.currentTimeMillis();
                Timestamp timestamp = new Timestamp(currentTime);

                // Convert the sell time from days to milliseconds and then calculate the endDate
                long sellTimeInMillis = sellItemInfo.getSellTime() * 24 * 60 * 60 * 1000;
                Timestamp endDate = new Timestamp(currentTime + sellTimeInMillis);

                SellListDAO.sellToMarket(sellItemInfo, timestamp, endDate);
                SellListDAO.deleteSellItemsItem(user.getId(), sellItemInfo.getId());
            }
        }
    }
}