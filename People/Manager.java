package People;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

import Comparators.StudentGPAComparator;
import DataBase.Data;
import Entities.Course;
import Entities.News;
import Entities.Order;
import Enumerators.ManagerType;

public class Manager extends Employee {

    private ManagerType managerType;
    public Vector<News> news = new Vector<News>();
    private Data data;

    //getter and setter for managerType
    private ManagerType getManagerType(){
        return managerType;
    }

    private void setManagerType(ManagerType managerType){
        this.managerType = managerType;
    }

    //                          Operations

    //approving(adding course to student) student regisrtation
    public boolean approveStudentRegistration(Course course, Student student) {
        if (student.getTotalCredits() + course.getCredits() <= student.getCreditLimit()) {
            if (course.addStudent(student)) {
                student.addCourse(course);
                return true;
            }
        }
        return false;
    }




    //statistical reports on academic performance of a student
    public void createStatisticalReports(Student student) {
        student.showInfo();
        System.out.println(student.getGpa());
        student.viewTranscript();
    }


    //assigning(adding) course to teacher
    public void assignCoursesToTeachers(Course course, Teacher teacher) {
        course.setTeacher(teacher);
        teacher.addCourse(course);
    }

    //we divided managing new into 2. posting news and deleting news. Next one is Posting news
    public void postNews(Date newsDate, String title, String text) {
        News newPost = new News(newsDate, title, text, this);
        news.addElement(newPost);
    }

    //deleting news
    public void deleteNews(News newNews){
        news.removeElement(newNews);
    }

    //viewing all news
    public String viewNews() {
        if (Data.getNews().isEmpty()) {
            return "Новостей нет.";
        }
        StringBuilder s = new StringBuilder();
        for (News n : Data.getNews()) {
            s.append(n.toString()).append("\n");
        }
        return s.toString();
    }


    //view info about student
    public void viewInfoAboutStudents(Student student) {
        student.showInfo();
    }

    //view info about student sorted by gpa
    public Vector<Student> viewInfoAboutStudentsByGPA(boolean ascending) {
        Vector<Student> students;
        Comparator<Student> studentGPAcomparator = new StudentGPAComparator();
        students = data.getStudents();
        Collections.sort(students, studentGPAcomparator);
        //sorting them ascending by GPA if the boolean is true and descending if false
        if (ascending)
            return students;
        else {
            Collections.reverse(students);
            return students;
        }
    }

    //view info about student sorted by name
    public Vector<Student> viewInfoAboutStudentsByName(boolean alphabetically) {
        Vector<Student> students;
        students = data.getStudents();
//        Collections.sort(students, UserNameComparator);
        //sorting them alphabetically if the boolean is true and reverse if false
        if (alphabetically){
            return students;
        } else {
            Collections.reverse(students);
            return students;
        }
    }

    //view info about teacher
    public void viewInfoAboutTeachers(Teacher teacher) {
        teacher.showInfo();
    }

    //view info about teacher by name
    public Vector<Teacher> viewInfoAboutTeachersByName(Teacher teacher, boolean alphabetically) {
        Vector<Teacher> teachers;
        teachers = data.getTeacher();
        //sorting them alphabetically if the boolean is true and reverse if false
        if (alphabetically){
            return teachers;
        } else {
            Collections.reverse(teachers);
            return teachers;
        }
    }

    //viewing all requests by employees
    public Vector<Order> viewRequestsByEmployees() {
        return Data.getOrders(); // Используем статический метод из Data
    }

}