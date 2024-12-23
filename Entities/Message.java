package Entities;

import java.io.*;
import java.util.Date;

import People.Employee;

//each empolyee can send massages to each other

public class Message implements Serializable{
    
    private Employee recipient;
    private Employee sender;
    private String text;
    private Date messageDate;
   
    
    public Message(Employee recipient, String text, Employee sender) { //Constructors
		this.recipient = recipient;
		this.text = text;
		this.messageDate = new Date();
		this.sender = sender;
	}
    public Message() {  
    	messageDate = new Date();
    }
    
    // Getter FOR TEXT
	public String getText() { 
		return text;
	}
	
	//Getter and SETTER FOR RECIPIENT RECIPIENT
	public Employee getRecipient() { 
		return recipient;
	}
	public void setRecipient(Employee newRecipient) { 
		this.recipient = newRecipient;
	}
	
	// GETTER FOR SENDER
	public Employee getSender() { 
		return sender;
	}
	
	 //GETTER FOR MassageDate
	public Date getDate() {
		return messageDate;
	}
	
	 // Setter for TEXT
	public boolean setText(String text) {
		
		if(text.equals(null) || text.equals("")) { // CHECKS IF IT EMPTY
			return false;
		}else
			this.text= text;
		
		return true;
	}
	@Override
	public String toString() {
		return "Message [recipient=" + recipient + "\n sender=" + sender + "\n text=" + text + ", messageDate="
				+ messageDate.toString() + "]";
	}

	
	
    
    
}
