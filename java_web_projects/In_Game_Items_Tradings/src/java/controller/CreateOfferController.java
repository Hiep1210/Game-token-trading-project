/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.GameItemsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.GameItems;

/**
 *
 * @author Inspiron
 */
@WebServlet(name = "CreateOfferController", urlPatterns = {"/CreateOfferController"})
public class CreateOfferController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String[] offers = request.getParameterValues("offer");
        String[] receive = request.getParameterValues("receive");
        if (offers == null || receive == null) {
            String message = "Make sure to select both offer and receive item";
            request.setAttribute("mess", message);
            request.getRequestDispatcher("TradePageController").forward(request, response);
        } else {
            int[] offerId = new int[offers.length];
            int[] receiveId = new int[receive.length];
            ArrayList<GameItems> offeritem = new ArrayList<>();
            ArrayList<GameItems> receiveitem = new ArrayList<>();
            for (int i = 0; i < offers.length; i++) {
                offerId[i] = Integer.parseInt(offers[i]);
                offeritem.add(GameItemsDAO.getGameItemById(offerId[i]));
            }
            for (int i = 0; i < receive.length; i++) {
                receiveId[i] = Integer.parseInt(receive[i]);
                receiveitem.add(GameItemsDAO.getGameItemById(receiveId[i]));
            }
            request.setAttribute("offer", offeritem);
            request.setAttribute("rec", receiveitem);
            request.getRequestDispatcher("CreateOffer.jsp").forward(request, response);
        }
    }

}
