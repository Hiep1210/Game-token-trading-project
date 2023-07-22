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
@WebServlet(name="SearchItemTrade", urlPatterns={"/SearchItemTrade"})
public class SearchItemTrade extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String search = request.getParameter("txt");
        String side = request.getParameter("side");
        PrintWriter out = response.getWriter();
        ArrayList<GameItems> result = GameItemsDAO.Search(search);
        for (GameItems game : result) {
            out.println("<div class=\"item-card mb-3 col-lg-3\"  id=\"item-card\">\n" +
"                                        <div class=\"card rarity-"+game.getRarity().toLowerCase()+"\">\n" +
"                                            <img  data-bs-toggle=\"offcanvas\" href=\"#offcanvas"+game.getId()+"\" src=\"UI/image/"+game.getImg()+".png\" class=\"img-fluid rounded\" alt=\"...\">\n" +
"                                            <div class=\"card-body\">\n" +
"                                                <p>"+game.getType()+" | "+game.getItemName()+" "+game.getSkinName()+" ("+game.getExterior()+")</p>\n" +
"                                                <input id=\"itemno"+game.getId()+side+"\" type=\"checkbox\" value=\""+game.getId()+"\" name=\""+side+"\" hidden=\"\" onchange=\"handleCheckboxChange(this)\"/>\n" +
"                                                <label for=\"itemno"+game.getId()+side+"\" class=\"btn item-card-button\">\n" +
"                                                    <i style=\"color:white\" class=\"material-icons navbar-item-icon\">compare_arrows</i>\n" +
"                                                </label>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"                                    </div>\n" +
"                                    <!-- Item Details -->\n" +
"                                    <div class=\"offcanvas offcanvas-start\" data-bs-theme=\"dark\" tabindex=\"-1\" id=\"offcanvas"+game.getId()+"\"\n" +
"                                         aria-labelledby=\"offcanvas\">\n" +
"                                        <div class=\"offcanvas-header\">\n" +
"                                            <h5 class=\"offcanvas-title\" id=\"offcanvas\">\n" +
"                                               "+game.getType()+" | "+game.getSkinName()+"\n" +
"                                            </h5>\n" +
"                                            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"offcanvas\"\n" +
"                                                    aria-label=\"Close\"></button>\n" +
"                                        </div>\n" +
"                                        <div class=\"offcanvas-body\">\n" +
"                                            <img class=\"img-fluid\" src=\"UI/image/"+game.getImg()+".png\" alt=\"\">\n" +
"                                            <div class=\"d-flex justify-content-between mt-2\">\n" +
"                                                <p class=\"sell-info-select-name\">Type: </p>\n" +
"                                                <h5>"+game.getType()+"</h5>\n" +
"                                            </div>\n" +
"                                            <div class=\"d-flex justify-content-between mt-2\">\n" +
"                                                <p class=\"sell-info-select-name\">Exterior:</p>\n" +
"                                                <h5>"+game.getExterior()+"</h5>\n" +
"                                            </div>\n" +
"                                            <div class=\"d-flex justify-content-between mt-2\">\n" +
"                                                <p class=\"sell-info-select-name\">Rarity:</p>\n" +
"                                                <h5>"+game.getRarity()+"</h5>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"                                    </div>");
        }
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
        
    }



}
