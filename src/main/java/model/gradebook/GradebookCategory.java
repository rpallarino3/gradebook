package gradebook.model;

import java.util.ArrayList;


/**
* This class represents a GradebookCategory with a name and a weight.
* A GradebookCategory has grades which are any number of GradebookItems
* in this category.
*
*@author Ryan Pallarino
*/
public class GradebookCategory {

    private double weight;
    private String name;
    private ArrayList<GradebookItem> grades;
    private double score;

    public GradebookCategory(String aName, double aWeight) {
        score = 1;
        name = aName;
        weight = aWeight;
        grades = new ArrayList<GradebookItem>();
    }

    public ArrayList<GradebookItem> getGrades() {
        return grades;
    }

    public void addGrade(GradebookItem grade) {
        grades.add(grade);
    }

    public void removeGrade(GradebookItem grade) {
        grades.remove(grade);
    }

    public void clearGrades() {
        grades.clear();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double newWeight) {
        weight = newWeight;
    }

    public void dropLowest() {
        if (grades.size() >= 1) {
            GradebookItem lowest = grades.get(0);
            for (int i = 0; i < grades.size(); i++) {
                if (grades.get(i).getScore() < lowest.getScore()) {
                    lowest = grades.get(i);
                }
            }
            grades.remove(lowest);
        }
    }

    public void replaceLowestWithAverage() {
        calculateScore();
        dropLowest();
        GradebookItem newItem = new GradebookItem(score, this);
    }

    public void calculateScore() {
        if (grades.size() != 0) {
            double totalScore = 0;
            for (int i = 0; i < grades.size(); i++) {
                totalScore += grades.get(i).getScore();
            }
            score = totalScore / grades.size();
        } else {
            score = 1;
        }
    }

    public double getScore() {
        return score;
    }
}
