/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ksiks
 */

import dao.CartDAO;
import dao.ProcessItemsDAO;
import dao.SellDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Cart;
import model.MarketItems;
import model.ProcessItem;
import model.User;
import model.SellList;

public class SellController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Users are not allowed to access this servlet through the doGet method
        request.getRequestDispatcher("SellPageController").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String gameAccountName = request.getParameter("gameAccountName");
        String redirect = "InsertBuyRequestNotifcationController";
        ArrayList<SellList> sellList;
        ProcessItem processItem;
        double totalSellListAmount = 0;
        double updatedUserAmount = 0;
        int limitInOneDay = 0;
        String message = "You cannot sell more than 5 items in one day! Please remove some items in your sell list!";
        try {
            if (user == null) {
                redirect = "SellPageController";
            } else {
                //Get all item in sell list
                sellList = SellDAO.getAllSellListItems(user.getId());
                if (sellList.isEmpty()) {
                    redirect = "BuyPageController";
                } else {
                    for (SellList list : sellList) {
                        totalSellListAmount += list.getPrice();
                        limitInOneDay +=1;
                    }
                    if (limitInOneDay > 5) {
                        request.setAttribute("message", message);
                        redirect = "SellPageController";
                    } else {
                        //Update user wallet after buying
                        updatedUserAmount = user.getMoney() + totalSellListAmount;
                        UserDAO.updateUserMoney(user.getId(), updatedUserAmount);
                        user.setMoney(updatedUserAmount);
                        //Delete all items in list 
                        for (SellList list : sellList) {
                             
                            SellDAO.deleteSellItem(list.getSellItemId());
                            processItem = new ProcessItem(list.getUserid(), list.getSeller_id(), list.getSellItemId() , 1, gameAccountName, LocalDateTime.now());
                            ProcessItemsDAO.insertProcessItems(processItem);
                        }
                        request.setAttribute("sellList", sellList);
                    }
                }
            }
            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
