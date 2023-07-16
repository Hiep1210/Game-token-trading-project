/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.ProcessItemsDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import model.ProcessItem;
import model.User;

/**
 *
 * @author Inspiron
 */
@WebServlet(name="ProcessTradeController", urlPatterns={"/ProcessTradeController"})
public class ProcessTradeController extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
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
        int tradeId = Integer.parseInt(request.getParameter("trade"));
        String gAcc = request.getParameter("gAcc");
        int sender = Integer.parseInt(request.getParameter("sender"));
        int recId = ((User)request.getSession().getAttribute("user")).getId();
        ProcessItem newProcess = new ProcessItem(sender, recId, tradeId, 3, gAcc, LocalDateTime.now());
        ProcessItemsDAO.insertProcessItems(newProcess);
        request.setAttribute("mess", "Trade Request Added Successfully, please wait for admin to approve");
        request.getRequestDispatcher("ViewTradeOffer").forward(request, response);
    }



}
