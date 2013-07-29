package gradebook.model;

import java.util.ArrayList;


/**
* Represents a Class with a semester that the class is offered.
* Classes belong to a course and can have any number of sections.
*
*@author Ryan Pallarino
*/
public class Class implements GradingInterface {

    private ArrayList<Section> sections;
    private String semester;
    private Course parentCourse;
    private double averageScore;

    public Class(Course course) {
        sections = new ArrayList<Section>();
        parentCourse = course;
        course.addClass(this);
    }

    public double getScore() {
        return averageScore;
    }

    public void setScore(double score) {
        averageScore = score;
    }

    public Course getParentCourse() {
        return parentCourse;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void removeSection(Section section) {
        sections.remove(section);
    }

    public void clearSection() {
        sections.clear();
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String newSemester) {
        semester = newSemester;
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
