/*
*Programmer: Trần Thế Hùng 
*Description: This file is for getting and creating auction 
 */
package controller;

import dao.AuctionDAO;
import dao.GameItemsDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import model.Auction;
import model.GameItems;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "InsertAuctionController", urlPatterns = {"/InsertAuctionController"})
public class InsertAuctionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Users are not allowed to access this servlet through the doGet method
        request.getRequestDispatcher("BuyPageController").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        createAuction(request, response);
    }

    public void createAuction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // if redirect attribute is null auto redirect to BuyPageController
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Auction auction;
        int userAuctionAmount = AuctionDAO.getUserAuctionItemAmount(user.getId());
        if (userAuctionAmount > 5) {
            return;
        }
        String skinName = request.getParameter("skinName");
        String exterior = request.getParameter("exterior");
        int sellTime = Integer.parseInt(request.getParameter("sellTime"));
        double staringPrice = Double.parseDouble(request.getParameter("price"));
        String gameAccountName = request.getParameter("gameAccount");
        int bidIncrement = Integer.parseInt(request.getParameter("bidIncrement"));

        GameItems gameItem = GameItemsDAO.getItemBySkinName(skinName, exterior);
        if (gameItem != null) {
            auction = new Auction(user.getId(), gameItem.getId(), bidIncrement, staringPrice,
                    gameAccountName, LocalDateTime.now(), LocalDateTime.now().plusDays(sellTime), gameItem);
            AuctionDAO.insertAuction(auction);
        }
    }

}
