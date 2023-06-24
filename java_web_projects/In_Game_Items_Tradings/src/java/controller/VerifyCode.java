package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "VerifyCode", urlPatterns = {"/VerifyCode"})
public class verifyCode extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String verifyCode = request.getParameter("cfcode");
        HttpSession verifyUser = request.getSession();
        String Code = String.valueOf(verifyUser.getAttribute("verifyCode"));
        if (verifyCode.equals(Code)) {
            request.getRequestDispatcher("resetPass.jsp").forward(request, response);
        } else {
            request.setAttribute("AlertC", "Code sai hoac khong ton tai");
            request.getRequestDispatcher("confirmCode.jsp").forward(request, response);
        }
    }

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

}
