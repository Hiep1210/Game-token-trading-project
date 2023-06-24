package controller;

import java.io.IOException;
import dao.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "AddCartController", urlPatterns = {"/AddCartController"})
public class AddCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("BuyPageController");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int marketid = Integer.parseInt(request.getParameter("marketid"));
        int buyerid = Integer.parseInt(request.getParameter("buyerid"));
        String message = "Failed to add cart";
        if (CartDAO.checkDuplicateCart(marketid, buyerid)) {
            message = "Already Added to Cart";
        } else {
            if (CartDAO.insertCartItem(buyerid, marketid)) {
                message = "Added To Cart";
            }
        }
        out.println("<h5>" + message + "</h5>\n");
    }
}
