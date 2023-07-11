package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
public class Comment {
    private int id;
    private String ccontent;
    private int user_id;
    private int tid;

    public Comment() {
    }

    public Comment(int id, String ccontent, int user_id, int tid) {
        this.id = id;
        this.ccontent = ccontent;
        this.user_id = user_id;
        this.tid = tid;
    }

    public String getCcontent() {
        return ccontent;
    }

    public int getId() {
        return id;
    }

    public int getTid() {
        return tid;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
}
