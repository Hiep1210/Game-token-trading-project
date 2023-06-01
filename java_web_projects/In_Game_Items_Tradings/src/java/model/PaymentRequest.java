/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class PaymentRequest {
    int id;
    int user_id;
    double money;
    String date;
    String img;

    public PaymentRequest(int user_id, double money, String date, String img) {
        this.user_id = user_id;
        this.money = money;
        this.date = date;
        this.img = img;
    }

    public PaymentRequest(int id, int user_id, double money, String date, String img) {
        this.id = id;
        this.user_id = user_id;
        this.money = money;
        this.date = date;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" + "id=" + id + ", user_id=" + user_id + ", money=" + money + ", date=" + date + ", img=" + img + '}';
    }
     
}
