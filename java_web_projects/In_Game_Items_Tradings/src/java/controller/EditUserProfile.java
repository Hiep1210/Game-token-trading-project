/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.ProtectionDomain;
import model.User;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

/**
 *
 * @author VICTUS
 */
@WebServlet(name = "EditUserProfile", urlPatterns = {"/EditUserProfile"})
public class EditUserProfile extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletFileUpload upload;
        FileItemIterator iterator;
        FileItemStream item;
        String id_raw = null;
        String username = null;
        String email = null;
        String dob = null;
        String gender = null;
        String profilePicFilename = null;

        try {
            upload = new ServletFileUpload();
            iterator = upload.getItemIterator(request);
            while (iterator.hasNext()) {
                item = iterator.next();
                if (item.isFormField()) { // Process form fields
                    String fieldName = item.getFieldName();
                    String fieldValue = Streams.asString(item.openStream());
                    switch (fieldName) {
                        case "id":
                            id_raw = fieldValue;
                            break;
                        case "username":
                            username = fieldValue;
                            break;
                        case "email":
                            email = fieldValue;
                            break;
                        case "dob":
                            dob = fieldValue;
                            break;
                        case "gender":
                            gender = fieldValue;
                            break;
                        default:
                            break;
                    }
                } else { // Process file upload
                    profilePicFilename = item.getName(); // Get the original filename of the uploaded profile picture
                    if ("".equals(profilePicFilename)) {
                        profilePicFilename = ((User) request.getSession().getAttribute("user")).getAvatar();
                    }
                    request.getSession().setAttribute("profilepic", profilePicFilename);
                    saveProfilePicture(item); // Save the profile picture
                }
            }

            // Create a User object and perform sign-up logic
            int id = Integer.parseInt(id_raw);
            User user = new User(id, profilePicFilename, username, email, dob, gender);
            // Perform update logic using the user object
            UserDAO dao = new UserDAO();
            dao.editUserProfile(user);
            // Redirect the user to the appropriate page after sign-up
            request.getSession().setAttribute("user", user);
            response.sendRedirect("UserProfileController");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    String location = null;

    private void saveProfilePicture(FileItemStream item) {
        InputStream initialStream;
        Path targetDir;
        Path target;
        try {
            if (location == null) {
                location = getLocation();
            }
            initialStream = item.openStream();
            targetDir = Paths.get(location, "profile_pics"); // Create a "profile_pics" directory inside the location
            targetDir.toFile().mkdirs(); // Create the directory if it doesn't exist
            target = targetDir.resolve(item.getName()); // Save the profile picture with its original filename
            Files.copy(initialStream, target, StandardCopyOption.REPLACE_EXISTING);
            IOUtils.closeQuietly(initialStream);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String getLocation() {
        String path;
        String jarPath = null;
        ProtectionDomain domain;
        try {
            domain = SendPaymentRequestController.class.getProtectionDomain();
            path = domain.getCodeSource().getLocation().getPath();
            jarPath = URLDecoder.decode(path, "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        return jarPath.replace("build/web/WEB-INF/classes/", "").substring(1)
                + "web/UI/image/"; // Update this path to the desired location for profile pictures
    }
}