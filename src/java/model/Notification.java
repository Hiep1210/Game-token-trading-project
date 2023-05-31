/*
*Programmer: Trần Thế Hùng 
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
    private String img;

    public Notification() {
    }
    
    public Notification(int user_id, String date, String noti_content, String img) {
        this.user_id = user_id;
        this.date = date;
        this.noti_content = noti_content;
        this.img = img;
    }
    
    public Notification(int noti_id, int user_id, String date, String noti_content, String img) {
        this.noti_id = noti_id;
        this.user_id = user_id;
        this.date = date;
        this.noti_content = noti_content;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Notification{" + "noti_id=" + noti_id + ", user_id=" + user_id + ", date=" + date + ", noti_content=" + noti_content + ", img=" + img + '}';
    }
    
}
