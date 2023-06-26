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
        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<Cart> clist = new ArrayList<>();
        clist = CartDAO.getAllCartItems(id);
        request.setAttribute("clist", clist);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

}
