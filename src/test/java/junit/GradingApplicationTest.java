package junit;

import org.junit.*;
import static org.junit.Assert.*;
import gradebook.model.*;
import java.util.ArrayList;

public class GradingApplicationTest{

    private GradingScheme grader;
    private Course course;
    private gradebook.model.Class class1;
    private gradebook.model.Class class2;
    private Section section1;
    private Section section2;
    private Student student1;
    private Student student2;
    private Student student3;
    private GradebookItem grade1;
    private GradebookItem grade2;
    private GradebookItem grade3;
    private GradebookItem grade4;
    private GradebookCategory tests;
    private GradebookCategory homework;

    @Before
    public void setUp() throws Exception {
        grader = new GradingScheme();
        course = new Course();
        course.setName("Butt");
        course.setCourseNumber(2340);
        course.setSubject("CS");
        class1 = new gradebook.model.Class(course);
        class2 = new gradebook.model.Class(course);
        section1 = new Section(class1);
        section2 = new Section(class2);
        student1 = new Student("Bob");
        student2 = new Student("Joe");
        student3 = new Student("Sally");
        section1.addStudent(student1);
        section1.addStudent(student2);
        section1.addStudent(student3);
        section2.addStudent(student1);
        section2.addStudent(student2);
        section2.addStudent(student3);
        tests = new GradebookCategory("Tests", .2);
        homework = new GradebookCategory("Homework", .8);
        student1.addGradeCategory(tests);
        student1.addGradeCategory(homework);
        grade1 = new GradebookItem(0.9, tests);
        grade2 = new GradebookItem(0.8, tests);
        grade3 = new GradebookItem(0.95, homework);
        grade4 = new GradebookItem(0.5, homework);
    }

    @Test
    public void testCourse() {
        assertTrue("Name test failed!", course.getName() == "Butt");
        assertTrue("CourseNumber test failed!", course.getCourseNumber() == 2340);
        assertTrue("CourseSubject test failed!", course.getSubject() == "CS");
        assertTrue("Class1 is not in course!", course.getClasses().contains(class1));
        assertTrue("Class2 is not in course!", course.getClasses().contains(class2));
    }

    @Test
    public void testClasses() {
        assertTrue("Section1 is not in Class1", class1.getSections().contains(section1));
        assertTrue("Section2 is not in Class2", class2.getSections().contains(section2));
        class2.removeSection(section2);
        assertFalse("Section2 has not been removed from Class2", class2.getSections().contains(section2));
        class1.addSection(section2);
        section2.setParentClass(class1);
        assertTrue("Section2 is not in Class1", class1.getSections().contains(section2));
    }

    @Test
    public void testSections() {
        assertTrue("Section1 does not have Class1 as parent", section1.getParentClass() == class1);
        assertTrue("Section1 does not have all 3 students", section1.getStudents().size() == 3);
        section1.clearStudents();
        assertTrue("Section1 is not empty", section1.getStudents().isEmpty());
        section2.removeStudent(student3);
        section1.addStudent(student3);
        assertFalse("Student3 not removed from section2", section2.getStudents().contains(student3));
        assertTrue("student3 not in section1", section1.getStudents().contains(student3));
        section1.clearStudents();
        section1.addStudent(student1);
        section1.addStudent(student2);
        section1.addStudent(student3);
    }

    @Test
    public void testStudents() {
        assertTrue("Student1 has tests", student1.getGrades().contains(tests));
        assertTrue("student1 has homework", student1.getGrades().contains(homework));
        student2.addGradeCategory(tests);
        student2.addGradeCategory(homework);
        assertTrue("Student2 has incorrect name", student2.getName() == "Joe");
        student2.setName("Poe");
        assertTrue("Student2 name has not changed", student2.getName() == "Poe");
    }

    @Test
    public void testGradeCategories() {
        assertTrue("Tests doesn't have grade1", tests.getGrades().contains(grade1));
        tests.dropLowest();
        tests.calculateScore();
        assertFalse("tests still has grade2", tests.getGrades().contains(grade2));
        assertTrue("tests doesn't have correct score after drop", tests.getScore() == 0.9);
        tests.addGrade(grade2);
        assertTrue("tests doesn't have grade2", tests.getGrades().contains(grade2));
        tests.calculateScore();
        assertTrue("tests doesn't have correct score", tests.getScore() >= 0.84 && tests.getScore() <= 0.86);
        tests.replaceLowestWithAverage();
        tests.calculateScore();
        assertTrue("tests doesn't have correct score after replace", tests.getScore() >= 0.874 && tests.getScore() <= 0.876);
        tests.clearGrades();
        assertTrue("tests still has grades", tests.getGrades().isEmpty());
        tests.addGrade(grade1);
        tests.addGrade(grade2);
    }

    @Test
    public void testGradingScheme() {
        student2.addGradeCategory(tests);
        student2.addGradeCategory(homework);
        grader.scoreStudent(student1);
        grader.scoreStudent(student2);
        assertTrue("student1 has wrong score", student1.getScore() >= 0.74 && student1.getScore() <= 0.76);
        grader.scoreSection(section1);
        assertTrue("section1 has wrong score", section1.getScore() >= 0.83 && section1.getScore() <= 0.84);
        section2.clearStudents();
        class1.addSection(section2);
        class2.removeSection(section2);
        grader.scoreClass(class1);
        assertTrue("class1 has wrong score", class1.getScore() >= 0.91 && class1.getScore() <= 0.92);
        class2.clearSection();
        grader.scoreCourse(course);
        assertTrue("course has wrong score", course.getScore() >= 0.95 && course.getScore() <= 0.96);
        assertTrue("student1 has wrong letter grade", student1.reportLetterGrade() == "C");
        assertTrue("section1 has wrong letter grade", section1.reportLetterGrade() == "B");
        assertTrue("class1 has wrong letter grade", class1.reportLetterGrade() == "A");
        assertTrue("course has wrong letter grade", course.reportLetterGrade() == "A");
    }
}
