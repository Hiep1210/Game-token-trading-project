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

@WebServlet(name = "UserProfileController", urlPatterns = {"/UserProfileController"})
public class UserProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            session.setAttribute("mess", "You have to Login before using this feature");
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession();
        UserDAO dao = new UserDAO();
        User user = (User) request.getSession().getAttribute("user");
        session.setAttribute("userInfo", dao.GetUserInformation(user.getId()));
        request.getRequestDispatcher("userProfile.jsp").forward(request, response);
    }

}
