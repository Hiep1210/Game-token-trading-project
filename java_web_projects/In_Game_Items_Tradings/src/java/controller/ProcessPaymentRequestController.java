/*
*Programmer: Trần Thế Hùng 
*Description: This files is controller for processing payment request sent by users
 */
package controller;

import dao.PaymentRequestDAO;
import dao.RoleDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ProcessPaymentRequestController", urlPatterns = {"/ProcessPaymentRequestController"})
public class ProcessPaymentRequestController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //Users are not allowed to access this servlet through the doGet method
        request.getRequestDispatcher("DisplayMarketItemsController").forward(
                request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            String rawPaymentRequestId = request.getParameter("paymentRequestId");
            String decision = request.getParameter("decision");
            PaymentRequest paymentRequest;
            String redirect = "InsertNotificationController";
            int paymentRequestId;
            double newMoneyAmount = 0;
            if (user == null) {
                redirect = "DisplayMarketItemsController";
            } else if (!isAdmin(user.getRole_id())) {
                redirect = "DisplayMarketItemsController";
            } else if (rawPaymentRequestId == null || decision == null) {
                redirect = "DisplayMarketItemsController";
            } else {
                //Get payment request information by payment request id
                paymentRequestId = Integer.parseInt(rawPaymentRequestId);
                paymentRequest = PaymentRequestDAO.getPaymentRequest(paymentRequestId);
                request.setAttribute("paymentRequest", paymentRequest);
                request.setAttribute("type", "payment");
                // If payment request is accepted add funds to user account
                if (decision.equals("accept")) {
                    newMoneyAmount = user.getMoney() + paymentRequest.getMoney();
                    UserDAO.acceptPaymentRequest(paymentRequest.getUser_id(), newMoneyAmount);
                    user.setMoney(newMoneyAmount);
                }
                PaymentRequestDAO.deletePaymentRequest(paymentRequestId);//Delete payment request record from table
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
