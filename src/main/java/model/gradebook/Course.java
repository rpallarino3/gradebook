package gradebook.model;

import java.util.ArrayList;

/**
* Represents a course that has a subject, number, prerequisites, and name.
* A course can have any number of classes.
*@author Ryan Pallarino
*
*/
public class Course implements GradingInterface {

    private String subject;
    private int courseNumber;
    private String name;
    private ArrayList<Course> prerequisites;
    private ArrayList<Class> classes;
    private double averageScore;

    public Course() {
        prerequisites = new ArrayList<Course>();
        classes = new ArrayList<Class>();
        averageScore = 1;
    }

    public double getScore() {
        return averageScore;
    }

    public void setScore(double score) {
        averageScore = score;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void addClass(Class newClass) {
        classes.add(newClass);
    }

    public void removeClass(Class oldClass) {
        classes.remove(oldClass);
    }

    public void clearClasses() {
        classes.clear();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String newSubject) {
        subject = newSubject;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int newCourseNumber) {
        courseNumber = newCourseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String reportLetterGrade() {
        if (averageScore >= A) {
            return "A";
        } else if (averageScore >= B) {
            return "B";
        } else if (averageScore >= C) {
            return "C";
        } else if (averageScore >= D) {
            return "D";
        } else {
            return "F";
        }
    }
}
