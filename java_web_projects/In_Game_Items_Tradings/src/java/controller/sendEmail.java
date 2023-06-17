/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.util.Properties;
import java.util.Random;
import model.User;

/**
 *
 * @author ACER
 */
@WebServlet(name="sendEmail", urlPatterns={"/sendEmail"})
public class sendEmail extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
                   String regexEmail = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b";
        User newUser = (User) request.getAttribute("userForgetPass");
        String recipient = newUser.getEmail();
        if (recipient.matches(regexEmail)) {
            // Get recipient email address and message from form data
            String verifyCode = generateVerifyCode();
            String message = generateEmailMessage(verifyCode);

            HttpSession verifyUser = request.getSession();
            verifyUser.setAttribute("verifyCode", verifyCode);
            verifyUser.setMaxInactiveInterval(2*60);

            // Set up mail server and authentication
             String host = "smtp.gmail.com";
            String user = "csgit47@gmail.com";
            String password = "svvuwvdjrbiucehn";

            // Create properties object for the mail session
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");

            // Create mail session and authenticate with credentials
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

            try {
                // Create message and set recipient, subject, and message body
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(user));
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                msg.setSubject("Account Verification");
                msg.setText(message);
                // Send message
                Transport.send(msg);             
                // Redirect to success page
                request.getRequestDispatcher("confirmCode.jsp").forward(request, response);
            } catch (MessagingException e) {
                // Redirect to error page
                response.getWriter().print("ko the gui mail");
            }
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
                   String regexEmail = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b";
        User newUser = (User) request.getAttribute("userForgetPass");
        String recipient = newUser.getEmail();
        if (recipient.matches(regexEmail)) {
            // Get recipient email address and message from form data
            String verifyCode = generateVerifyCode();
            String message = generateEmailMessage(verifyCode);

            HttpSession verifyUser = request.getSession();
            verifyUser.setAttribute("verifyCode", verifyCode);
            verifyUser.setMaxInactiveInterval(2*60);

            // Set up mail server and authentication
             String host = "smtp.gmail.com";
            String user = "csgit47@gmail.com";
            String password = "svvuwvdjrbiucehn";

            // Create properties object for the mail session
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");

            // Create mail session and authenticate with credentials
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

            try {
                // Create message and set recipient, subject, and message body
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(user));
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                msg.setSubject("Xác minh tài khoản");
                msg.setContent(message, "text/html; charset=utf-8");
                //send msg
                Transport.send(msg);
                // Redirect to success page
                request.getRequestDispatcher("confirmCode.jsp").forward(request, response);
            } catch (MessagingException e) {
                // Redirect to error page
                response.getWriter().print("chúng tôi không thể gửi mã code đến email của bạn");
            }
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
public static String generateVerifyCode(){
      Random rand = new Random();
      //random tu 0 den 999999
        int number = rand.nextInt(999999);
        //chac chan so random se tao ra day 6 chu so
        return String.format("%06d", number);
}
public static String generateEmailMessage(String generateVerifyCode){
    String message = "Đây là mã xác nhận quên mật khẩu của bạn,"
            +"vui lòng không chia sẻ bất kỳ ai khác."+
            "Nếu bạn không làm hành động này xin vui lòng bỏ qua."
            +"Mã xác nhận của bạn là : " + generateVerifyCode;
    return message;
}
}
