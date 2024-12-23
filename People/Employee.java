package People;

import java.util.Date;
import java.util.Vector;
import Entities.Message;

public class Employee extends User {

    private double Salary;
    private Date hireDate;
    private Vector<Message> sentMessages = new Vector<>(); // Store sent messages
    private Vector<Message> receivedMessages = new Vector<>(); // Список полученных сообщений

    public Employee() {

    }

    public Employee(String username, String password, Date birthDate, String phoneNumber, String email, String name, String surname,
                    double Salary, Date hireDate) {
        super(username, password, birthDate, phoneNumber, email, name, surname);
        this.Salary = Salary;
        this.hireDate = hireDate;
    }

    //getter and setter for salary
    public double getSalary() {
        return this.Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    //getter and setter for salary
    public Date getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void sendMessage(Employee recipient, String text) {
        Message newMessage = new Message(recipient, text, this); // Создаем сообщение
        sentMessages.addElement(newMessage); // Добавляем в список отправленных
        recipient.receiveMessage(newMessage); // Передаем сообщение получателю
    }

    public void receiveMessage(Message message) {
        receivedMessages.addElement(message); // Добавляем сообщение в список полученных
    }


    //viewing all messages
    public Vector<Message> viewMessages() {
        Vector<Message> allMessages = new Vector<>();
        allMessages.addAll(sentMessages);    // Добавляем отправленные
        allMessages.addAll(receivedMessages); // Добавляем полученные
        return allMessages;
    }

    public Vector<Message> viewReceivedMessages() {
        return receivedMessages; // Возвращаем только полученные сообщения
    }

}
