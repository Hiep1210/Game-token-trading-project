/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.GameItemsDAO;
import dao.SellDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.GameItems;

/**
 *
 * @author VICTUS
 */
@WebServlet(name = "sortSellController", urlPatterns = {"/sortSell"})
public class sortSellController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String order = request.getParameter("order");
        ArrayList<GameItems> allSellItems = SellDAO.sortByRarity(order);
        ArrayList<GameItemsDAO> sellList = new ArrayList<>();
        for (GameItems gameItems : allSellItems) {
            //trim all spaces character for offcanvas ids
            String trimedSkinName = gameItems.getSkinName().replaceAll("\\s", "");
            GameItemsDAO gameItem = new GameItemsDAO(gameItems, trimedSkinName);
            sellList.add(gameItem);
        }
        PrintWriter out = response.getWriter();

        for (GameItemsDAO gameItems : sellList) {
            out.println("<div class=\"col-lg-2 item-card mt-2 mb-2 \" id=\"item-card\" data-bs-toggle=\"offcanvas\" href=\"#offcanvas" + gameItems.getTrimedSkinName() + "\">\n"
                    + "                                <div class=\"card rarity-" + gameItems.getGameItems().getRarity().toLowerCase() + "\" data-bs-toggle = \"dropdown\" aria-expanded=\"false\">\n"
                    + "                                    <img src=\"UI/image/" + gameItems.getGameItems().getImg() + ".png\" alt =\"displayfailed\" class=\"card-img-top\">\n"
                    + "                                    <div class=\"card-body\">\n"
                    + "                                        <p>" + gameItems.getGameItems().getType() + " | " + gameItems.getGameItems().getItemName() + " " + gameItems.getGameItems().getSkinName() + "</p>\n"
                    + "                                        <a href=\"#\" class=\"btn item-card-button\">\n"
                    + "                                            <i class=\"fa-solid fa-cart-shopping\"></i>\n"
                    + "                                        </a>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"offcanvas offcanvas-start\" data-bs-theme=\"dark\" tabindex=\"-1\" id=\"offcanvas" + gameItems.getTrimedSkinName() + "\"\n"
                    + "                                 aria-labelledby=\"offcanvas" + gameItems.getGameItems().getImg() + "\">\n"
                    + "                                <div class=\"offcanvas-header\">\n"
                    + "                                    <h5 class=\"offcanvas-title\" id=\"offcanvas\">\n"
                    + "                                        " + gameItems.getGameItems().getType() + " | " + gameItems.getGameItems().getItemName() + " " + gameItems.getGameItems().getSkinName() + "\n"
                    + "                                    </h5>\n"
                    + "                                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"offcanvas\"\n"
                    + "                                            aria-label=\"Close\"></button>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"offcanvas-body\">\n"
                    + "                                    <img class=\"img-fluid\" src=\"UI/image/" + gameItems.getGameItems().getImg() + ".png\" alt=\"\">\n"
                    + "                                    <div class=\"d-flex justify-content-between mt-2\">\n"
                    + "                                        <p class=\"sell-info-select-name\">Exterior:</p>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"d-flex justify-content-between mt-2\">\n"
                    + "                                        <p class=\"sell-info-select-name\">Rarity:</p>\n"
                    + "                                        <h5>" + gameItems.getGameItems().getRarity() + "</h5>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"d-flex justify-content-between mt-2\">\n"
                    + "                                        <p class=\"sell-info-select-name\">Time Left:</p>\n"
                    + "                                        <h5>1:00:00</h5>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"d-flex justify-content-between mt-2\">\n"
                    + "                                        <p class=\"sell-info-select-name\">Sell Price:</p>\n"
                    + "\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"summit-button mt-2\">\n"
                    + "                                        <button type=\"submit\">Add to Sell List</button>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>");
        }
    }

}
