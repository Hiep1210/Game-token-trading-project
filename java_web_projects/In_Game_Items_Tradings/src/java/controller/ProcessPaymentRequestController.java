package controller;

import dao.PaymentRequestDAO;
import dao.RoleDAO;
import dao.UserDAO;
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

@WebServlet(name = "ProcessPaymentRequestController", urlPatterns = {"/ProcessPaymentRequestController"})
public class ProcessPaymentRequestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //Users are not allowed to access this servlet through the doGet method
        request.getRequestDispatcher("BuyPageController").forward(
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
                redirect = "BuyPageController";
            } else if (!isAdmin(user.getRoleid())) {
                redirect = "BuyPageController";
            } else if (rawPaymentRequestId == null || decision == null) {
                redirect = "BuyPageController";
            } else {
                //Get payment request information by payment request id
                paymentRequestId = Integer.parseInt(rawPaymentRequestId);
                paymentRequest = PaymentRequestDAO.getPaymentRequest(paymentRequestId);
                request.setAttribute("paymentRequest", paymentRequest);
                request.setAttribute("type", "payment");
                // If payment request is accepted add funds to user account
                if (decision.equals("accept")) {
                    newMoneyAmount = user.getMoney() + paymentRequest.getMoney();
                    UserDAO.updateUserMoney(paymentRequest.getUser_id(), newMoneyAmount);
                    if (user.getId() == paymentRequest.getUser_id()) {
                        user.setMoney(newMoneyAmount);
                    }
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

}
