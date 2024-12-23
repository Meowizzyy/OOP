package Entities;

import java.io.Serializable;
import java.util.Date;

import People.User;
import Enumerators.ITOrderStatus;

public class Order implements Serializable {
    

	private ITOrderStatus status;
    
    private String description;
    
    private Date sentTime;
    
    private User sender;

    
    public Order(String description) {
    	
    	this.status = ITOrderStatus.NEW;;
    	this.description = description;
    	this.sentTime = new Date();

    }
    public Order(String description, User sender) {
    	
    	this.status = ITOrderStatus.NEW;;
    	this.description = description;
    	this.sentTime = new Date();
    	this.sender = sender;
    }
    public Order() {
    	this.status = ITOrderStatus.NEW;
    	this.sentTime = new Date();
    }
    public Order(ITOrderStatus status) {
    	this.status = status;

    }
    
    public String getDescription() { // GETTER ТЕКСТ ЗАПРОСА DESCRIPTION
        return this.description;
    }

    public void setDescription(String description) { // SETTER ТЕКСТА
        this.description = description;
    }

    public ITOrderStatus getStatus() { // GETTER FOR STATUS
    	return this.status;
    }
    
    public void setStatus(ITOrderStatus status) { // Watched status become OLD.
    	this.status = status;
    }
    
    public Date getDate() {   // GETTER FOR sended TIME
        return this.sentTime;
    }
    
    public User getSender() { // GETTER for SENDER
    	return sender;
    }
    public void setSender(User sender) {
    	this.sender = sender;
    }

    public String toString(){
        return "Order [status " + this.status + ", description "+ this.description + ", sendTime " + this.sentTime +
        ", sender " + this.sender + "]";
    }
}
