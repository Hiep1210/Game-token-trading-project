/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This files is controller for logging in game account
*/
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.GameAccount;
import dao.GameAccountDAO;
/**
 *
 * @author Inspiron
 */
@WebServlet(name = "LogInGameAccountController", urlPatterns = {"/LogInGameAccountController"})
public class LogInGameAccountController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GameAccount guser = GameAccountDAO.GameLogIn(req.getParameter("username"), req.getParameter("password"));
        //if guser is null then send message to UI
        if(guser == null){
            req.setAttribute("message", "Username or password is incorrect !");
            req.getRequestDispatcher("loginfi.jsp").forward(req, resp);
        }
        req.setAttribute("gameacc", guser);
        //reusability
        String redirect = req.getParameter("redirect");
        req.getRequestDispatcher(redirect).forward(req, resp);
    }
    
}
