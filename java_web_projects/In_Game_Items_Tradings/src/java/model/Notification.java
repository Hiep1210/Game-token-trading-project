/*
*Programmer: Nguyễn Chí Trung
*Description: This file describes the model of notification that will appear on dashboard
*/
package model;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Notification {
    private int noti_id;
    private int user_id;
    private String date;
    private String noti_content;
    private String content_type;

    public Notification() {
    }
        
    public Notification(int user_id, String date, String noti_content, String content_type) {
        this.user_id = user_id;
        this.date = date;
        this.noti_content = noti_content;
        this.content_type = content_type;
    }    
    
    public Notification(int noti_id, int user_id, String date, String noti_content, String content_type) {
        this.noti_id = noti_id;
        this.user_id = user_id;
        this.date = date;
        this.noti_content = noti_content;
        this.content_type = content_type;
    }

    public int getNoti_id() {
        return noti_id;
    }

    public void setNoti_id(int noti_id) {
        this.noti_id = noti_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoti_content() {
        return noti_content;
    }

    public void setNoti_content(String noti_content) {
        this.noti_content = noti_content;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }
    
}
