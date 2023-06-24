package controller;

import dao.GameItemsDAO;
import dao.SellDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.GameItems;

@WebServlet(name = "SellPageController", urlPatterns = {"/SellPageController"})
public class SellPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<GameItems> allSellItems = SellDAO.getTopTwelveItems();
        ArrayList<GameItemsDAO> sellList = new ArrayList<>();
        for (GameItems gameItems : allSellItems) {
            //trim all spaces character for offcanvas ids
            String trimedSkinName = gameItems.getSkinName().replaceAll("\\s", "");
            GameItemsDAO gameItem = new GameItemsDAO(gameItems, trimedSkinName);
            sellList.add(gameItem);
        }
        request.setAttribute("sellList", sellList);
        request.getRequestDispatcher("sell.jsp").forward(request, response);
    }
}
