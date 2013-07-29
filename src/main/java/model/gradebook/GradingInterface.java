package gradebook.model;

/**
* This interface is used for reporting the letter grades of an
* object that implements it.
*
*@author Ryan Pallarino
*/
public interface GradingInterface {


    double A = 0.9;
    double B = 0.8;
    double C = 0.7;
    double D = 0.6;

    String reportLetterGrade();
}
