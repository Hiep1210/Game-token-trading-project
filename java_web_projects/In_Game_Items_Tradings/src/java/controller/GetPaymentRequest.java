/*
*Programmer: Trần Thế Hùng 
*Description: This file describes the model of notification that will appear on dashboard
 */
package controller;

import dao.MarketItemsDao;
import dao.PaymentRequestDAO;
import dao.RoleDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.MarketItems;
import model.PaymentRequest;
import model.Role;
import model.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "GetPaymentRequest", urlPatterns = {"/GetPaymentRequest"})
public class GetPaymentRequest extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        ArrayList<PaymentRequest> paymentRequestList = PaymentRequestDAO.getAllPaymentRequest();
        ArrayList<Role> roleList = RoleDAO.getRoleList();
        if (user == null) {
            request.setAttribute("redirect", "buy.jsp");
        } else if (!isAdmin(user.getRole_id())) {
            request.setAttribute("redirect", "buy.jsp");
        } else {
            request.setAttribute("paymentRequestList", paymentRequestList);
        }
        
    }

    public boolean isAdmin(int role_id) {
        ArrayList<Role> roleList = RoleDAO.getRoleList();
        boolean isAdmin = false;
        for (Role role : roleList) {
            if (role.getRole() == role_id && role.getRole_name() == "admin") {
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
