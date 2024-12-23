package People;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;
import java.util.Map.Entry;

import Entities.Course;
import Entities.Organization;
import Enumerators.Faculty;
import Entities.Mark;

public class Student extends User implements Serializable {
    private String id;
    private int yearOfStudy;
    private int totalCredits;
    private Vector<Course> courses = new Vector<>();
    private double gpa;
    private int creditLimit;
    private Faculty faculty;
    private String speciality;
    private Organization org;
    private HashMap<Course, Mark> marks = new HashMap<Course, Mark>();
    private String Transcript = "";

    public Student() {
        super();
    }

    public Student(String username, String password, Date birthDate, String phoneNumber, String email, String name,
                   String surname, String id, int yearOfStudy, double gpa, int creditLimit, Faculty faculty, String speciality) {
        super(username, password, birthDate, phoneNumber, email, name, surname);
        this.id = id;
        this.yearOfStudy = yearOfStudy;
        this.gpa = gpa;
        this.creditLimit = creditLimit;
        this.faculty = faculty;
        this.speciality = speciality;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public double getGpa() {
        return gpa;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    public void setOrganization(Organization org) {
        this.org = org;
        org.addMember(this);
    }

    public Organization getOrganization() {
        return org;
    }
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setPoints(Course course, Double points,int typeOfPoints ) {
        Mark mark = marks.get(course);
        if(mark == null && courses.contains(course)) {
            mark = new Mark();
            marks.put(course, mark);
        }
        if(typeOfPoints == 1) {
            mark.setFirstAttMarks(points);
        }
        if(typeOfPoints == 2) {
            mark.setSecondAttMarks(points);
        }
        if(typeOfPoints == 3) {
            mark.setFinalScore(points);
        }
    }

    public String getMarkOfCourse(Course course) {
        Mark mark = marks.get(course);
        if (mark == null) {
            return "Оценки по курсу еще не выставлены.";
        }
        double totalPoints = mark.totalPoints();
        gpa = this.convertMarkToGradePoints(totalPoints);
        return course + "\n first attestation: " + mark.sumOfFirstAtt() +
                "\n second attestation: " + mark.sumOfSecondAtt() +
                "\n final score: " + mark.getFinalScore() +
                "\n total score: " + totalPoints +
                "\n GPA: " + gpa +
                "\n Grade: " + this.gpaConverter(gpa);
    }



    public void rateTeacher(Teacher teacher, int rating) {
        if (rating < 0 || rating > 10) {
            System.out.println("Рейтинг должен быть от 0 до 10.");
            return;
        }
        teacher.addRate(rating); // Используем уже существующий метод addRate в Teacher
        System.out.println("Вы успешно оценили преподавателя " + teacher.getName() + " на " + rating + " баллов.");
    }




    public HashMap<Course, Mark> getMarks() {
        return marks;
    }

    public void setMarks(HashMap<Course, Mark> marks) {
        this.marks = marks;
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) { // Добавляем курс только если его нет
            courses.add(course);
            marks.put(course, new Mark()); // Создаем новый объект Mark для курса
            totalCredits += course.getCredits(); // Обновляем кредиты
        }
    }

    public void dropCourse(Course course) {
        marks.remove(course);
        courses.remove(course);
    }
    public String viewCourses() {
        if (courses.isEmpty()) return "No courses registered.";

        StringBuilder strCourses = new StringBuilder();
        for (Course c : courses) {
            strCourses.append(c.getCourseName()).append(" | Credits: ").append(c.getCredits()).append("\n");
        }
        return strCourses.toString();
    }


    public void registerForCourses(Course course) {
        course.getTeacher().approveStudent(course, this);
    }

    public Teacher viewTeacherOfCourse(Course course) {
        return course.getTeacher();
    }
    public String getTranscript() {
        StringBuilder transcript = new StringBuilder();
        for (Course course : courses) {
            Mark mark = marks.get(course);
            if (mark == null) {
                transcript.append(course).append("\n")
                        .append(" first attestation: N/A\n")
                        .append(" second attestation: N/A\n")
                        .append(" final score: N/A\n")
                        .append(" total score: N/A\n")
                        .append(" GPA: N/A\n")
                        .append(" Grade: N/A\n");
            } else {
                double totalPoints = mark.totalPoints();
                double gpa = this.convertMarkToGradePoints(totalPoints);
                transcript.append(course).append("\n")
                        .append(" first attestation: ").append(mark.sumOfFirstAtt()).append("\n")
                        .append(" second attestation: ").append(mark.sumOfSecondAtt()).append("\n")
                        .append(" final score: ").append(mark.getFinalScore()).append("\n")
                        .append(" total score: ").append(totalPoints).append("\n")
                        .append(" GPA: ").append(gpa).append("\n")
                        .append(" Grade: ").append(this.gpaConverter(gpa)).append("\n");
            }
        }
        return transcript.toString();
    }


    public void viewTranscript() {
        System.out.println(getTranscript());
        System.out.println("Total GPA: " + gpa + " | " + this.gpaConverter(gpa) + "\n");

    }


    public Organization studentOrganization() {
        return this.studentOrganization();
    }

    public void becomeHead() {
        org.setHead(this);
    }

    public void joinOrganization(Organization org) {
        this.org = org;
        org.addMember(this);
    }

    public void leaveOrganization() {
        this.org = null;
        org.removeMember(this);
    }

    public void showInfo() {
        System.out.println("-----------" + this.getId() + "-----------");
        System.out.println("Student: " + this.getName() + " " + this.getSurname());
        System.out.println("Faculty " + this.getFaculty());
        System.out.println("Speciality " + this.getSpeciality());
    }

    public void changeFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void calculateGPA() {
        double totalScore = 0;
        int totalCredits = 0;
        for (Entry<Course, Mark> entry : marks.entrySet()) {
            Course course = entry.getKey();
            Mark mark = entry.getValue();
            Double totalGrade = convertMarkToGradePoints(mark.totalPoints());
            totalScore += totalGrade* course.getCredits();
            totalCredits += course.getCredits();
        }

        if (totalCredits > 0) {
            this.gpa = totalScore / totalCredits;
        } else {
            this.gpa = 0;
        }
    }

    public String gpaConverter(Double gpa) {
        if (gpa >= 4.0) {
            return "A";
        } else if (gpa >= 3.7) {
            return "A-";
        } else if (gpa >= 3.3) {
            return "B+";
        } else if (gpa >= 3.0) {
            return "B";
        } else if (gpa >= 2.7) {
            return "B-";
        } else if (gpa >= 2.3) {
            return "C+";
        } else if (gpa >= 2.0) {
            return "C";
        } else if (gpa >= 1.7) {
            return "C-";
        } else if (gpa >= 1.3) {
            return "D+";
        } else if (gpa >= 1.0) {
            return "D";
        } else {
            return "F";
        }
    }
    public Double convertMarkToGradePoints(Double totalPoints) {
        if (totalPoints >= 95) {
            return 4.0;
        } else if (totalPoints >= 90) {
            return 3.67;
        } else if (totalPoints >= 85) {
            return 3.33;
        } else if (totalPoints >= 80) {
            return 3.0;
        } else if (totalPoints >= 75) {
            return 2.67;
        } else if (totalPoints >= 70) {
            return 2.33;
        } else if (totalPoints >= 65) {
            return 2.0;
        } else if (totalPoints >= 60) {
            return 1.67;
        } else if (totalPoints >= 55) {
            return 1.33;
        } else if (totalPoints >= 50) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    public boolean equals(Object o) {
        if(!super.equals(o))return false;
        Student s = (Student)o;
        return Objects.equals(this.id, s.id) && Objects.equals(this.faculty, s.faculty) && Objects.equals(this.speciality, s.speciality) && this.yearOfStudy == s.yearOfStudy;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id, speciality, faculty);
    }

}
