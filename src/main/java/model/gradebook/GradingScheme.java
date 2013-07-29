package gradebook.model;


/**
* This class represents a GradingScheme which is used to
* calculate the scores of other objects.
*
*
*@author Ryan Pallarino
*/
public class GradingScheme {

    public void scoreStudent(Student student) {
        if (student.getGrades().size() != 0) {
            double totalscore = 0;
            double totalweight = 0;
            for (int i = 0; i < student.getGrades().size(); i++) {
                GradebookCategory curCategory = student.getGrades().get(i);
                curCategory.calculateScore();
                totalscore += (curCategory.getScore()
                    * curCategory.getWeight());
                totalweight += curCategory.getWeight();
            }
            student.setScore(totalscore / totalweight);
        } else {
            student.setScore(1);
        }
    }

    public void scoreSection(Section section) {
        if (section.getStudents().size() != 0) {
            double totalscore = 0;
            for (int i = 0; i < section.getStudents().size(); i++) {
                scoreStudent(section.getStudents().get(i));
                totalscore += section.getStudents().get(i).getScore();
            }
            section.setScore(totalscore / section.getStudents().size());
        } else {
            section.setScore(1);
        }
    }

    public void scoreClass(Class aClass) {
        if (aClass.getSections().size() != 0) {
            double totalscore = 0;
            for (int i = 0; i < aClass.getSections().size(); i++) {
                scoreSection(aClass.getSections().get(i));
                totalscore += aClass.getSections().get(i).getScore();
            }
            aClass.setScore(totalscore / aClass.getSections().size());
        } else {
            aClass.setScore(1);
        }
    }

    public void scoreCourse(Course course) {
        if (course.getClasses().size() != 0) {
            double totalscore = 0;
            for (int i = 0; i < course.getClasses().size(); i++) {
                scoreClass(course.getClasses().get(i));
                totalscore += course.getClasses().get(i).getScore();
            }
            course.setScore(totalscore / course.getClasses().size());
        } else {
            course.setScore(1);
        }
    }
}
