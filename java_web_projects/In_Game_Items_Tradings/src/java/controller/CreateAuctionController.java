/*
*Programmer: Trần Thế Hùng 
*Description: This file is for getting and creating auction 
 */
package controller;

import dao.AuctionDAO;
import dao.GameItemsDAO;
import dao.SellDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Auction;
import model.GameItems;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "CreateAuctionController", urlPatterns = {"/CreateAuctionController"})
public class CreateAuctionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        createAuction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        createAuction(request, response);
    }

    public void createAuction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String redirect = "createAuction.jsp";
        ArrayList<Auction> userAuctionList;
        ArrayList<GameItems> allSellItems;
        ArrayList<GameItemsDAO> sellItemList = new ArrayList<>();
        int userAuctionAmount = 0;
        if (user != null) {
            allSellItems = SellDAO.getTopTwelveItems();
            userAuctionList = new ArrayList<>();
            userAuctionList = AuctionDAO.getAllAuctionFromUser(user.getId());
            userAuctionAmount = AuctionDAO.getUserAuctionItemAmount(user.getId());
            for (GameItems gameItems : allSellItems) {
                //trim all spaces character for offcanvas ids
                String trimedSkinName = gameItems.getSkinName().replaceAll("\\s", "");
                GameItemsDAO gameItem = new GameItemsDAO(gameItems, trimedSkinName);
                sellItemList.add(gameItem);
            }
            request.setAttribute("userSellList", userAuctionList);
            request.setAttribute("sellList", sellItemList);
            request.setAttribute("userSellItemsAmount", userAuctionAmount);
        } else {
            redirect = "BuyPageController";
        }

        request.getRequestDispatcher("createAuction.jsp").forward(request, response);
    }

}
