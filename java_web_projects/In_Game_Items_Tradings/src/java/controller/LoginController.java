package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import dao.UserDAO;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //if there is username given then find it
        if (username != null) {
            //if there is password given then proceed to log in
            if (password != null) {
                LogIn(request, response, username, password);
            } else {
                FindUserName(request, response, username);
            }
        }
    }

    //if there is username in db then send it back 
    private void FindUserName(HttpServletRequest request, HttpServletResponse response, String username)
            throws IOException, ServletException {
        String name = UserDAO.FindUserName(username);
        if (name != null) {
            request.setAttribute("name", name);
        }//if not found, send a message
        else {
            request.setAttribute("message", "Username not found");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void LogIn(HttpServletRequest request, HttpServletResponse response, String username, String password)
            throws IOException, ServletException {
        User user = UserDAO.LogIn(username, password);

        if (user == null) {
            request.setAttribute("message", "Password not found");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect("BuyPageController");
    }

}
