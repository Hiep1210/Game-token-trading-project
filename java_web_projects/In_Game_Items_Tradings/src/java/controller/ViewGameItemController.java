package controller;

import dao.GameItemsDAO;
import dao.RoleDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.GameItems;
import model.Role;
import model.User;

@WebServlet(name = "ViewGameItemController", urlPatterns = {"/ViewGameItemController"})

public class ViewGameItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            String redirect = "ViewGameItem.jsp";

            if (user == null || !isAdmin(user.getRoleid())) {
                redirect = "BuyPageController";
            } else {
                ArrayList<GameItems> allGameItems = GameItemsDAO.getAllGameItems();
                ArrayList<GameItemsDAO> itemList = new ArrayList<>();

                for (GameItems gameItems : allGameItems) {
                    // trim all spaces character for offcanvas ids
                    String trimmedSkinName = gameItems.getSkinName().replaceAll("\\s", "");
                    GameItemsDAO gameItem = new GameItemsDAO(gameItems, trimmedSkinName);
                    itemList.add(gameItem);
                }

                request.setAttribute("itemList", itemList);
            }

            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean isAdmin(int role_id) {
        ArrayList<Role> roleList = RoleDAO.getRoleList();
        boolean isAdmin = false;
        for (Role role : roleList) {
            if (role.getRole() == role_id && "admin".equals(role.getRole_name())) {
                isAdmin = true;
                break;
            }
        }
        return isAdmin;
    }
}
