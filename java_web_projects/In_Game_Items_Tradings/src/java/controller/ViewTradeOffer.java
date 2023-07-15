/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.TradeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import model.OfferItem;
import model.ReceiveItem;
import model.TradeItem;

/**
 *
 * @author Inspiron
 */
@WebServlet(name="ViewTradeOffer", urlPatterns={"/ViewTradeOffer"})
public class ViewTradeOffer extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doPost(request, response);
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
        ArrayList<TradeItem> trade = TradeDAO.getAllTradeOffers();
        HashMap<Integer, ArrayList<OfferItem>> offerItem = new HashMap<>();
        HashMap<Integer, ArrayList<ReceiveItem>> receiveItem = new HashMap<>();
        for (int i = 0; i < trade.size(); i++) {
            offerItem.put(trade.get(i).getId(), TradeDAO.getAllOffersInATrade(trade.get(i).getId()));
            receiveItem.put(trade.get(i).getId(), TradeDAO.getAllRecsInATrade(trade.get(i).getId()));
        }
        request.setAttribute("trade", trade);
        request.setAttribute("offer", offerItem);
        request.setAttribute("rec", receiveItem);
        request.getRequestDispatcher("ViewTrade.jsp").forward(request, response);
    }



}
