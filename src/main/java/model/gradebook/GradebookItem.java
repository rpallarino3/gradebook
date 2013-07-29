package gradebook.model;


/**
* This class represents a GradebookItem which is
* an assignment or test, etc. that has a score.
* Each item belongs to a GradebookCategory.
*
*@author Ryan Pallarino
*/
public class GradebookItem {

    private String name;
    private GradebookCategory category;
    private double score;

    public GradebookItem(double aScore, GradebookCategory aCategory) {
        score = aScore;
        category = aCategory;
        aCategory.addGrade(this);
    }

    public double getScore() {
        return score;
    }

    public void setScore(double newScore) {
        score = newScore;
    }

    public GradebookCategory getCategory() {
        return category;
    }

    public void setCategory(GradebookCategory newCategory) {
        category = newCategory;
    }
}
