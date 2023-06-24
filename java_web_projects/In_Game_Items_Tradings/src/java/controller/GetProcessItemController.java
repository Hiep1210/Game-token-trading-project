package controller;

import dao.MarketItemsDAO;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            ArrayList<ProcessItem> processItemList;
            String redirect = "processItem.jsp";
            if (user == null) {
                redirect = "BuyPageController";
            } else if (user.getRoleid() != 2) {
                redirect = "BuyPageController";
            } else {
                processItemList = ProcessItemsDAO.getAllProcessItems();
                request.setAttribute("processItemList", processItemList);
                for (ProcessItem processItem : processItemList) {
                    if (processItem.getTransactionTypeIdId() == 1) {
                        processItem.setObject(MarketItemsDAO.getMarketItem(processItem.getTransactionId()));
                    }

                }

            }
            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
