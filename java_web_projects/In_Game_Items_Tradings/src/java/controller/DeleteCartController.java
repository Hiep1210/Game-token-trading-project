package controller;

import dao.CartDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name = "DeleteCartController", urlPatterns = {"/DeleteCartController"})
public class DeleteCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("BuyPageController");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (!CartDAO.deleteCartItem(id)) {
            request.setAttribute("message", "deleted Cart failed");
            request.getRequestDispatcher("BuyPageController").forward(request, response);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            int userid = user.getId();
            response.sendRedirect("ViewCartController?id=" + userid);
        }
    }

}
