/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.GameItemsDAO;
import dao.MarketItemsDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.GameItems;
import model.MarketItems;

/**
 *
 * @author Inspiron
 */
@WebServlet(name="SortController", urlPatterns={"/SortController"})
public class SortController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        String currentpage = request.getParameter("page");
        switch (currentpage) {
            case "/sell.jsp":
                ArrayList<GameItems> game_items = GameItemsDAO.Filter(request.getParameter("type"), 
                        request.getParameter("rarity"), request.getParameter("exterior"));
                request.setAttribute("game_items", game_items);
                request.getRequestDispatcher(currentpage).forward(request, response);
                break;
            case "/buy.jsp":
                ArrayList<MarketItems> market_items = MarketItemsDao.Filter(request.getParameter("priceorder"), 
                        request.getParameter("type"), request.getParameter("rarity"), request.getParameter("exterior"));
                request.setAttribute("market_list", market_items);
                request.getRequestDispatcher(currentpage).forward(request, response);
                break;
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
