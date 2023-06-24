/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Inspiron
 */
@WebServlet(name="DeleteCartController", urlPatterns={"/DeleteCartController"})
public class DeleteCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("BuyPageController");
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
             int id = Integer.parseInt(request.getParameter("id"));
        if(!CartDAO.deleteCartItem(id)) {
            request.setAttribute("message", "deleted Cart failed");
            request.getRequestDispatcher("BuyPageController").forward(request, response);
        }else{
        User user = (User)request.getSession().getAttribute("user");
        int userid = user.getId();
        response.sendRedirect("ViewCartController?id="+userid);
        }
    }

}
