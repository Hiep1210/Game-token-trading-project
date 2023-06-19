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
import java.util.ArrayList;
import model.ProcessItem;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "GetProcessItemController", urlPatterns = {"/GetProcessItemController"})
public class GetProcessItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            ArrayList<ProcessItem> processItemList;
            String redirect = "processItemRequest.jsp";
            if (user == null) {
                redirect = "BuyPageController";
            } else if (user.getRoleid() != 2) {
                redirect = "BuyPageController";
            } else {
                processItemList = ProcessItemsDAO.getAllProcessItems();
                request.setAttribute("processItemList",processItemList);
            }
            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
