package controller;

import dao.GameItemsDAO;
import dao.PaymentRequestDAO;
import dao.RoleDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.GameItems;
import dao.SellDAO;
import model.PaymentRequest;
import model.Role;
import model.User;

@WebServlet(name = "ViewGameItemController", urlPatterns = {"/ViewGameItemController"})
public class ViewGameItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
    try {
        User user = (User) request.getSession().getAttribute("user");

        String redirect = "ViewGameItem.jsp";
        if (user == null) {
            redirect = "BuyPageController";
        } else if (!isAdmin(user.getRoleid())) {
            redirect = "BuyPageController";
        } else {
            ArrayList<GameItems> allSellItems = SellDAO.getAllSellItems();
            ArrayList<GameItemsDAO> sellList = new ArrayList<>();
            for (GameItems gameItems : allSellItems) {
                // trim all spaces character for offcanvas ids
                String trimedSkinName = gameItems.getSkinName().replaceAll("\\s", "");
                GameItemsDAO gameItem = new GameItemsDAO(gameItems, trimedSkinName);
                sellList.add(gameItem);
            }
            request.setAttribute("sellList", sellList);
            request.getRequestDispatcher("ViewGameItem.jsp").forward(request, response);
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
