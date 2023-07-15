package controller;

import dao.RoleDAO;
import dao.UserDAO;
import java.io.IOException;
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
import java.util.ArrayList;
import model.GameItems;
import model.Role;
import model.User;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

@WebServlet(name = "EditGameItemController", urlPatterns = {"/EditGameItemController"})
public class EditGameItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletFileUpload upload;
        FileItemIterator iterator;
        FileItemStream item;
        String id_raw = null;
        String skinName = null;
        String itemName = null;
        String type = null;
        String rarity = null;
        String gameItemPicFilename = null;
        User loggedUser = (User) request.getSession().getAttribute("user");
        try {
            User user = (User) request.getSession().getAttribute("user");
            String redirect = "ViewGameItem.jsp";
        if (user == null) {
            redirect = "BuyPageController";
        } else if (!isAdmin(user.getRoleid())) {
            redirect = "BuyPageController";
        }else{
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
                        case "skinName":
                            skinName = fieldValue;
                            break;
                        case "itemName":
                            itemName = fieldValue;
                            break;
                        case "dob":
                            type = fieldValue;
                            break;
                        case "rarity":
                            rarity = fieldValue;
                            break;
                        default:
                            break;
                    }
                } else { // Process file upload
                    // Get the original filename of the uploaded profile picture
                    gameItemPicFilename = item.getName();
                    //User not select any picture
                    if ("".equals(gameItemPicFilename)) {
                        gameItemPicFilename = loggedUser.getAvatar();
                    }
                    saveProfilePicture(item); // Save the profile picture
                }
            }
        }

            // Create a User object and perform update profile logic
            int id = Integer.parseInt(id_raw);
            
            GameItems gameItem = new GameItems(id,skinName, itemName,type,rarity,"",gameItemPicFilename);
            // Perform update logic using the user object
            UserDAO dao = new UserDAO();
         //   dao.editUserProfile(user);
            // Redirect the user to the appropriate page after update
            //request.getSession().setAttribute("user", user);
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
            domain = EditUserProfile.class.getProtectionDomain();
            path = domain.getCodeSource().getLocation().getPath();
            jarPath = URLDecoder.decode(path, "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        return jarPath.replace("build/web/WEB-INF/classes/", "").substring(1)
                + "web/UI/image/"; // Update this path to the desired location for profile pictures
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
