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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.GameItems;
import model.User;

@WebServlet(name = "LoadMoreController", urlPatterns = {"/load"})
public class LoadMoreController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int amount = Integer.parseInt(request.getParameter("exist"));
        String printAddButton = "";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            printAddButton = "<div class=\"d-flex justify-content-between mt-2\">\n"
                    + "                                            <p class=\"sell-info-select-name\">Exterior:</p>\n"
                    + "                                            <select name=\"exterior\" class=\"form-control w-50\">\n"
                    + "                                                <option value=\"Factory New\">Factory New</option>\n"
                    + "                                                <option value=\"Minimal Wear\">Minimal Wear</option>\n"
                    + "                                                <option value=\"Field-Tested\">Field-Tested</option>\n"
                    + "                                                <option value=\"Well-Worn\">Well-Worn</option>\n"
                    + "                                                <option value=\"Battle-Scarred\">Battle-Scarred</option>\n"
                    + "                                            </select>\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"d-flex justify-content-between align-items-center mt-2\">\n"
                    + "                                            <p class=\"sell-info-select-name\">Sell time:</p>\n"
                    + "                                            <input class=\"mb-3\" type=\"radio\" name =\"sellTime\" value=\"1\">\n"
                    + "                                            <label class=\"mb-3\">1 Day</label>\n"
                    + "                                            <input class=\"mb-3\" type=\"radio\" name =\"sellTime\" value=\"2\">\n"
                    + "                                            <label class=\"mb-3\">2 Day</label>\n"
                    + "                                            <input class=\"mb-3\" type=\"radio\" name =\"sellTime\" value=\"3\">\n"
                    + "                                            <label class=\"mb-3\">3 Day</label>\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"d-flex justify-content-between mt-2\">\n"
                    + "                                            <p class=\"sell-info-select-name\">Sell Price:</p>\n"
                    + "                                            <div class=\"form-group w-50\">\n"
                    + "                                                <input class=\"form-control\" type=\"number\" name =\"price\" placeholder=\"Enter price\">\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"d-flex justify-content-between mt-2\">\n"
                    + "                                            <p class=\"sell-info-select-name\">Game Account:</p>\n"
                    + "                                            <div class=\"form-group w-50\">\n"
                    + "                                                <input class=\"form-control\" type=\"text\" name =\"gameAccount\" placeholder=\"G.Account Name\">\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"summit-button mt-2\">\n"
                    + "                                            <button class=\"btn item-card-button\" onclick=\"addToSellList(event)\">\n"
                    + "                                                Add to Sell List\n"
                    + "                                            </button>\n"
                    + "                                        </div>";
        }
        ArrayList<GameItems> allSellItems = SellDAO.getNextTwelveItems(amount);
        ArrayList<GameItemsDAO> sellList = new ArrayList<>();
        for (GameItems gameItems : allSellItems) {
            //trim all spaces character for offcanvas ids
            String trimedSkinName = gameItems.getImg().replaceAll("\\s", "");
            GameItemsDAO gameItem = new GameItemsDAO(gameItems, trimedSkinName);
            sellList.add(gameItem);
        }
        PrintWriter out = response.getWriter();

        for (GameItemsDAO gameItems : sellList) {
            out.println("<div class=\"col-lg-2 item-card mt-2 mb-2 \" id=\"item-card\" data-bs-toggle=\"offcanvas\" href=\"#offcanvas" + gameItems.getTrimedSkinName() + "\">\n"
                    + "                                <div class=\"card rarity-" + gameItems.getGameItems().getRarity().toLowerCase() + "\" data-bs-toggle = \"dropdown\" aria-expanded=\"false\">\n"
                    + "                                    <img src=\"UI/image/" + gameItems.getGameItems().getImg() + ".png\" alt =\"displayfailed\" class=\"card-img-top\">\n"
                    + "                                    <div class=\"card-body\">\n"
                    + "                                        <p class=\"item-full-name\">" + gameItems.getGameItems().getType() + " | " + gameItems.getGameItems().getItemName() + " " + gameItems.getGameItems().getSkinName() + "</p>\n"
                    + "                                        <a href=\"#\" class=\"btn item-card-button\">\n"
                    + "                                            <i class=\"fa-solid fa-cart-shopping\"></i>\n"
                    + "                                        </a>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"offcanvas offcanvas-start item-details\" data-bs-theme=\"dark\" tabindex=\"-1\" id=\"offcanvas" + gameItems.getTrimedSkinName() + "\"\n"
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
                    + "                                        <p class=\"sell-info-select-name\">Rarity:</p>\n"
                    + "                                        <h5>" + gameItems.getGameItems().getRarity() + "</h5>\n"
                    + "                                    </div>\n"
                    + printAddButton
                    + "                                </div>\n"
                    + "                            </div>");
        }

    }
}
