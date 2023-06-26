package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.CartDAO;
import java.util.ArrayList;
import model.Cart;
import model.User;

@WebServlet(name = "ViewCartController", urlPatterns = {"/ViewCartController"})
public class ViewCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String redirect = "cart.jsp";
        ArrayList<Cart> clist = new ArrayList<>();
        int id;
        if (user != null) {
            id = user.getId();
            clist = CartDAO.getAllCartItems(id);
            request.setAttribute("clist", clist);
        } else {
            redirect = "BuyPageController";
        }
        request.getRequestDispatcher(redirect).forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
