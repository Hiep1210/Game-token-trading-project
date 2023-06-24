package controller;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "ChangePasswordController", urlPatterns = {"/ChangePasswordController"})
public class ChangePasswordController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldPass = request.getParameter("oldpass");
        String newPass = request.getParameter("newpass");
        String newCfPass = request.getParameter("cfpass");
        UserDAO dao = new UserDAO();
        HttpSession ses = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        if (!oldPass.equals(user.getPassword())) {
            ses.setAttribute("mess1", "Old password not match");
            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
        } else {
            if (newPass.equals(newCfPass)) {
                dao.ChangePassword(user.getId(), newPass);
                ses.invalidate();
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                ses.setAttribute("mess1", "New pass and confirm not match");
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            }
        }
    }

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

}
