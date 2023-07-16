package controller;

import dao.GameItemsDAO;
import dao.SellDAO;
import dao.SellListDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.GameItems;
import model.SellItems;
import model.User;

@WebServlet(name = "SellPageController", urlPatterns = {"/SellPageController"})
public class SellPageController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ArrayList<SellItems> userSellList = new ArrayList<>();
        int userSellItemAmount = 0;
        if (user != null) {
            userSellList = SellListDAO.getUserSellList(user.getId());
            userSellItemAmount = SellDAO.getUserSellingAmount(user.getId());
        }
        
        ArrayList<GameItems> allSellItems = SellDAO.getTopTwelveItems();
        ArrayList<GameItemsDAO> sellItemList = new ArrayList<>();
        
        for (GameItems gameItems : allSellItems) {
            //trim all spaces character for offcanvas ids
            String trimedSkinName = gameItems.getSkinName().replaceAll("\\s", "");
            GameItemsDAO gameItem = new GameItemsDAO(gameItems, trimedSkinName);
            sellItemList.add(gameItem);
        }
        
        request.setAttribute("sellList", sellItemList);
        request.setAttribute("userSellList", userSellList);
        request.setAttribute("userSellItemsAmount", userSellItemAmount);
        request.getRequestDispatcher("sell.jsp").forward(request, response);
    }
}
