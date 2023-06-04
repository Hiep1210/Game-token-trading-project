/*
*Programmer: Trần Thế Hùng 
*Description: This file describes the model of notification that will appear on dashboard
 */
package controller;

import dao.PaymentRequestDAO;
import dao.RoleDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.PaymentRequest;
import model.Role;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "GetPaymentRequestController", urlPatterns = {"/GetPaymentRequestController"})
public class GetPaymentRequestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            ArrayList<PaymentRequest> paymentRequestList;
            String redirect = "DisplayMarketItemsController";
            if (user == null) {
                redirect = "DisplayMarketItemsController";
            } else if (!isAdmin(user.getRole_id())) {
                redirect = "DisplayMarketItemsController";
            } else {
                paymentRequestList = PaymentRequestDAO.getAllPaymentRequest();
                request.setAttribute("paymentRequestList",paymentRequestList);
            }
            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean isAdmin(int role_id) {
        ArrayList<Role> roleList = RoleDAO.getRoleList();
        boolean isAdmin = false;
        for (Role role : roleList) {
            if (role.getRole() == role_id && "admin".equals(role.getRole_name())) {
                isAdmin = true;
                break;
            }
        }
        return isAdmin;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
