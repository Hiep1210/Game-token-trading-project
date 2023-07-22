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
import java.util.List;
import model.GameItems;
import model.User;

@WebServlet(name = "FilterAuctionItem", urlPatterns = {"/filterAuction"})
public class FilterAuctionItem extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String[] typeList = request.getParameterValues("types");
        String searchName = request.getParameter("txt");
        String sortOrder = request.getParameter("order");

        String printAddButton = "";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            printAddButton = "<div class=\"summit-button mt-2\">\n"
                    + "                                        <button class=\"btn item-card-button\" onclick=\"addToSellList(event)\">\n"
                    + "                                                Create Auctiont\n"
                    + "                                            </button>\n"
                    + "                                    </div>";
        }

        SellDAO sellDAO = new SellDAO();
        List<GameItems> itemList = sellDAO.filterByType(typeList, searchName, sortOrder);
        ArrayList<GameItemsDAO> listWithTrimed = new ArrayList<>();
        for (GameItems gameItems : itemList) {
            //trim all spaces character for offcanvas ids
            String trimedSkinName = gameItems.getImg().replaceAll("\\s", "");
            GameItemsDAO gameItem = new GameItemsDAO(gameItems, trimedSkinName);
            listWithTrimed.add(gameItem);
        }

        PrintWriter out = response.getWriter();

        if (listWithTrimed.isEmpty()) {
            out.print("<h2 class=\"card-title\">No items matches</h2>");
        }

        for (GameItemsDAO gameItems : listWithTrimed) {
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
                    + "                                        <p class=\"sell-info-select-name\">Exterior:</p>\n"
                    + "                                        <select name=\"exterior\" class=\"form-control w-50\">\n"
                    + "                                            <option value=\"Factory New\">Factory New</option>\n"
                    + "                                            <option value=\"Minimal Wear\">Minimal Wear</option>\n"
                    + "                                            <option value=\"Field-Tested\">Field-Tested</option>\n"
                    + "                                            <option value=\"Well-Worn\">Well-Worn</option>\n"
                    + "                                            <option value=\"Battle-Scarred\">Battle-Scarred</option>\n"
                    + "                                        </select>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"d-flex justify-content-between mt-2\">\n"
                    + "                                        <p class=\"sell-info-select-name\">Rarity:</p>\n"
                    + "                                        <h5>" + gameItems.getGameItems().getRarity() + "</h5>\n"
                    + "                                    </div>\n"
                    + "                                        <div class=\"d-flex justify-content-between align-items-center mt-2\">\n"
                    + "                                            <p class=\"sell-info-select-name\" required >Auction duration:</p>\n"
                    + "                                            <input class=\"mb-3\" type=\"radio\" name =\"sellTime\" value=\"1\" required=\"\" >\n"
                    + "                                            <label class=\"mb-3\">1 Day</label>\n"
                    + "                                            <input class=\"mb-3\" type=\"radio\" name =\"sellTime\" value=\"2\">\n"
                    + "                                            <label class=\"mb-3\">2 Day</label>\n"
                    + "                                            <input class=\"mb-3\" type=\"radio\" name =\"sellTime\" value=\"3\">\n"
                    + "                                            <label class=\"mb-3\">3 Day</label>\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"d-flex justify-content-between mt-2\">\n"
                    + "                                            <p class=\"sell-info-select-name\">Starting price:</p>\n"
                    + "                                            <div class=\"form-group w-50\">\n"
                    + "                                                <input class=\"form-control\" type=\"number\" step=\"0.01\" min=\"0.01\" max=\"10000\" id=\"lowestBid\" name=\"price\" class=\"form-control\"  placeholder=\"Insert starting bid\" required>\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"d-flex justify-content-between mt-2\">\n"
                    + "                                            <p class=\"sell-info-select-name\">Bid increment: %</p>\n"
                    + "                                            <div class=\"form-group w-50\">\n"
                    + "                                                <input class=\"form-control\" type=\"number\" step=\"1\" min=\"5\" max=\"20\" value=\"10\" id=\"bidIncrement\" name=\"bidIncrement\" class=\"form-control\"  placeholder=\"Insert bid step\" required>\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"d-flex justify-content-between mt-2\">\n"
                    + "                                            <p class=\"sell-info-select-name\">Game Account:</p>\n"
                    + "                                            <div class=\"form-group w-50\">\n"
                    + "                                                <input class=\"form-control\" type=\"text\" name =\"gameAccount\" placeholder=\"G.Account Name\"required=\"\" >\n"
                    + "                                            </div>\n"
                    + "                                        </div>"
                    + printAddButton
                    + "                                </div>\n"
                    + "                            </div>");
        }
    }
}
