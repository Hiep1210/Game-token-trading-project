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

@WebServlet(name = "ResetPassController", urlPatterns = {"/ResetPassController"})
public class ResetPassController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newPass = request.getParameter("newpass");
        String newCfPass = request.getParameter("cfpass");
        UserDAO dao = new UserDAO();
        HttpSession session = request.getSession();
        User newUser = (User) request.getSession().getAttribute("userForgetPass");
        if (newPass.equals(newCfPass)) {
            dao.ResetPassword(newUser.getId(), newPass);
            session.invalidate();
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("Boy", "New Pass not match Confirm Pass");
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
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
