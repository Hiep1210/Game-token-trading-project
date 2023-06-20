/*
*Programmer: Trần Thế Hùng 
*Description: This file describes the model of processItem 
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author Asus
 */
public class ProcessItem {

    private int id;
    private int senderId;
    private int receiverId;
    private int transactionId;
    private int transactionTypeIdId;
    private String gameAccountName;
    private LocalDateTime processTime;
    private Object object;

    public ProcessItem() {
    }

    public ProcessItem(int senderId, int receiverId, int transactionId, int transactionTypeIdId, String gameAccountName, LocalDateTime processTime) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.transactionId = transactionId;
        this.transactionTypeIdId = transactionTypeIdId;
        this.gameAccountName = gameAccountName;
        this.processTime = processTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionTypeIdId() {
        return transactionTypeIdId;
    }

    public void setTransactionTypeIdId(int transactionTypeIdId) {
        this.transactionTypeIdId = transactionTypeIdId;
    }

    public String getGameAccountName() {
        return gameAccountName;
    }

    public void setGameAccountName(String gameAccountName) {
        this.gameAccountName = gameAccountName;
    }

    public LocalDateTime getProcessTime() {
        return processTime;
    }

    public void setProcessTime(LocalDateTime processTime) {
        this.processTime = processTime;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ProcessItem{" + "id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", transactionId=" + transactionId + ", transactionTypeIdId=" + transactionTypeIdId + ", gameAccountName=" + gameAccountName + ", processTime=" + processTime + ", object=" + object + '}';
    }

}
