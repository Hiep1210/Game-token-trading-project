package controller;

import dao.CartDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import dao.GameItemsDAO;
import dao.RoleDAO;
import dao.SellDAO;
import java.util.ArrayList;
import model.GameItems;
import model.Role;

@WebServlet(name = "DeleteGameItemController", urlPatterns = {"/DeleteGameItemController"})
public class DeleteGameItemController extends HttpServlet {

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
        String skinName = request.getParameter("skinName");
        String itemName = request.getParameter("itemName");
        String type = request.getParameter("type");
        String rarity = request.getParameter("rarity");
        String img = request.getParameter("img");

        String redirect = "ViewGameItem.jsp";
        if (user == null) {
            redirect = "BuyPageController";
        } else if (!isAdmin(user.getRoleid())) {
            redirect = "BuyPageController";
        } else {
            if (!GameItemsDAO.deleteGameItem(skinName, itemName, type, rarity, img)) {
            request.setAttribute("message", "deleted game item failed");
            request.getRequestDispatcher("ViewGameItemController").forward(request, response);
        } else {
            
            int userid = user.getId();
            response.sendRedirect("ViewGameItemController?id=" + userid);
        
            
        }
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
