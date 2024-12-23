package DataBase;

import java.io.*;
import java.util.Vector;

import Entities.*;
import People.*;


public class Data implements Serializable{
    private static Data instance;
    public Data() {}
    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }
    private static Vector<Employee>employees = new Vector<>();
    private static Vector<Student> students = new Vector<>();
    private static Vector<Teacher> teachers = new Vector<>();
    private static Vector<String> logs = new Vector<>();
    private static Vector<News> news = new Vector<>();
    private static Vector<Message> messages = new Vector<>();
    // private static Vector<Researcher> researchers;
    private static Vector<Manager> managers = new Vector<>();
    private static Vector<BachelorStudent> bachelorStudents = new Vector<>();
    private static Vector<GraduateStudent> graduateStudents = new Vector<>();
    private static Vector<User> users = new Vector<>();
    private static Vector<Course> courses = new Vector<>();
    private static Vector<Admin> admins = new Vector<>();
    private static Vector<Order> orders = new Vector<>();
    private static Vector<Journal> journals = new Vector<>();

    public static void saveAndSerialize() {
        serialize(employees, "C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\employees.bin");
        serialize(managers, "C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\managers.bin");
        serialize(bachelorStudents, "C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\bachelorStudents.bin");
        serialize(graduateStudents, "C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\graduateStudents.bin");
        serialize(teachers, "C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\teachers.bin");
        serialize(admins, "C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\admins.bin");
        serialize(students,"C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\students.bin");
        //entities
        serialize(courses, "C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\courses.bin");
        serialize(orders, "C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\orders.bin");
        serialize(news, "C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\news.bin");
        serialize(messages, "C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\messages.bin");
        serialize(journals, "C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\journals.bin");

    }

    @SuppressWarnings("unchecked")
    public static void loadAndDeserialize() throws ClassNotFoundException {

        employees = (Vector<Employee>) deserialize("C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\employees.bin");
        managers = (Vector<Manager>) deserialize("C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\managers.bin");
        bachelorStudents = (Vector<BachelorStudent>) deserialize("C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\bachelorStudents.bin");
        graduateStudents = (Vector<GraduateStudent>) deserialize("C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\graduateStudents.bin");
        students = (Vector<Student>) deserialize("C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\students.bin") ;
        teachers = (Vector<Teacher>) deserialize("C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\teachers.bin");
        courses = (Vector<Course>) deserialize("C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\courses.bin");
        orders = (Vector<Order>) deserialize("C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\orders.bin");
        news = (Vector<News>) deserialize("C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\news.bin");
        messages = (Vector<Message>) deserialize("C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\messages.bin");
        journals = (Vector<Journal>) deserialize("C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\journals.bin");
        admins = (Vector<Admin>) deserialize("C:\\Users\\ruste\\Desktop\\projj\\src\\SerializableObjects\\admins.bin");

    }

    private static Object deserialize(String file) throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            return ois.readObject();
        }catch(IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static void serialize(Object o, String file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
            oos.close();
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Vector<Course> getCourses(){
        return courses;
    }

    public static  Vector<Employee> getEmployees() {
        return employees;
    }


    public static Vector<Student> getStudents() {
        return students;
    }

    public static Vector<Teacher> getTeacher() {
        return teachers;
    }


    public static Vector<Journal> getJournals() {
        return journals;
    }


    public static Vector<Order> getOrders() {
        return orders;
    }

    public static Vector<News> getNews() {
        return news;
    }

    public static Vector<Message> getMessages() {
        return messages;
    }


    public static Vector<Admin> getAdmins() {
        return admins;
    }

    /*
     * public static Vector<Researcher> getResearchers() { return researchers; }
     */


    public static Vector<Manager> getManagers() {
        return managers;
    }


    public static Vector<BachelorStudent> getBachelorStudents() {
        return bachelorStudents;
    }


    public static Vector<GraduateStudent> getGraduateStudents() {
        return graduateStudents;
    }

    public static Vector<User> getUsers() {
        return users;
    }

    public static void addLog(String log) {
        logs.add(log);
    }

    public static Vector<String> getLogs() {
        return logs;
    }


    //
    public static void addOrder(Order order) {
        orders.add(order);
    }


    // ADD USERS
    public static void addUser(User user) {
        users.add(user);
    }

    public static void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public static void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public static void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public static void addBachelorStudent(BachelorStudent student) {
        bachelorStudents.add(student);
    }

    public static void addGraduateStudent(GraduateStudent student) {
        graduateStudents.add(student);
    }

    public static void addManager(Manager manager) {
        managers.add(manager);
    }

    public static void addStudent(Student student){students.add(student);}

    public static void addCourse(Course course){
        courses.add(course);
    }



    //REMOVE USERS
    public static void removeUser(User user) { //USER
        users.remove(user);
    }

    public static void removeTeacher(Teacher techer) {//TEACHER
        teachers.remove(techer);
    }

    public static void removeEmployee(Employee empl) {//EMPLOYEE
        employees.remove(empl);
    }

    public static void removeBachelorStudents(BachelorStudent student) {//BACHELOER STUDENT
        bachelorStudents.remove(student);
    }
    public static void removeGraduateStudents(GraduateStudent gStudent) {//Graduate STUDENT
        graduateStudents.remove(gStudent);
    }

    public static void removeManager(Manager manager) {//MANAGER
        managers.remove(manager);
    }


    public static void removeAdmin(Admin admin) { //ADMIN
        admins.remove(admin);
    }


}
