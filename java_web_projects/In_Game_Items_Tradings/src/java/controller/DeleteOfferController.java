/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.ProcessItemsDAO;
import dao.TradeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Inspiron
 */
@WebServlet(name="DeleteOfferController", urlPatterns={"/DeleteOfferController"})
public class DeleteOfferController extends HttpServlet {
   
   
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
        int tradeId = Integer.parseInt(request.getParameter("trad"));
        TradeDAO.deleteOfferItems(tradeId);
        TradeDAO.deleteRecitems(tradeId);
        TradeDAO.deleteTrade(tradeId);
        request.setAttribute("mess", "Trade Offer delete successfully");
        request.getRequestDispatcher("ViewTradeOffer").forward(request, response);
    }



}
