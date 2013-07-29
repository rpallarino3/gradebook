package gradebook.model;

import java.util.ArrayList;


/**
* This class reperesents a student that can be enrolled in a section.
* The student has grades which are any number of gradebook categories.
* When the score of the student is calculated, all gradebook categories
* are normalized by their weight with the total weight of all categories.
* For exampled: if there are two categories with weight 0.2 and 0.4,
* each categories score will be divied by 0.6 to normalize to 1.
*@author Ryan Pallarino
*/
public class Student implements GradingInterface {

    private ArrayList<GradebookCategory> grades;
    private String name;
    private double score;

    public Student(String newName) {
        grades = new ArrayList<GradebookCategory>();
        name = newName;
        score = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double newScore) {
        score = newScore;
    }

    public ArrayList<GradebookCategory> getGrades() {
        return grades;
    }

    public void addGradeCategory(GradebookCategory grade) {
        grades.add(grade);
    }

    public void removeGradeCategory(GradebookCategory grade) {
        grades.remove(grade);
    }

    public void clearGrades() {
        grades.clear();
    }

    public String reportLetterGrade() {
        if (score >= A) {
            return "A";
        } else if (score >= B) {
            return "B";
        } else if (score >= C) {
            return "C";
        } else if (score >= D) {
            return "D";
        } else {
            return "F";
        }
    }
}
