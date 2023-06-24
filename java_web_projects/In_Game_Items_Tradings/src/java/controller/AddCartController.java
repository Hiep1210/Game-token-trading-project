/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import dao.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 * @author Inspiron
 */
@WebServlet(name = "AddCartController", urlPatterns = {"/AddCartController"})
public class AddCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("BuyPageController");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        out.println("<h5>"+message+"</h5>\n");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

}
