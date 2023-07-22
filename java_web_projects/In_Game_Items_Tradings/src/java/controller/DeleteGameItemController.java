package controller;

import dao.GameItemsDAO;
import dao.RoleDAO;
import dao.SellListDAO;
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
import model.Role;
import model.User;

/**
 *
 * @author VICTUS
 */
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
            int Id = Integer.parseInt(request.getParameter("id"));
            String redirect = "ViewGameItem.jsp";

            if (user == null || !isAdmin(user.getRoleid())) {
                redirect = "BuyPageController";
            } else {
                GameItemsDAO.deleteGameItemById(Id);
            
            PrintWriter out = response.getWriter();
            out.print("Item deleted successfully");
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
