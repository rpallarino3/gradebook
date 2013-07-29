package gradebook.model;

import java.util.ArrayList;


/**
* This class represents a section of a class.
* The section has a list of students that are currently enrolled in it.
* A section can have any number of students.
*
*@author Ryan Pallarino
*/
public class Section implements GradingInterface {

    private Class parentClass;
    private ArrayList<Student> students;
    private String sectionName;
    private double averageScore;

    public Section(Class pClass) {
        students = new ArrayList<Student>();
        parentClass = pClass;
        pClass.addSection(this);
        averageScore = 1;
    }

    public double getScore() {
        return averageScore;
    }

    public void setScore(double score) {
        averageScore = score;
    }

    public Class getParentClass() {
        return parentClass;
    }

    public void setParentClass(Class newClass) {
        parentClass = newClass;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String newName) {
        sectionName = newName;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void clearStudents() {
        students.clear();
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
