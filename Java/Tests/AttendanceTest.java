import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryder Roth, Jack Haehl
 * @version 1
 * @since 11/23/2020
 * Junit tests that verify
 * the functionality of the
 * Attendance Class
 */
public class AttendanceTest
{
    /**
     * tests input of a file with no repeating
     * ASURites
     * @throws FileNotFoundException
     */
    @Test
    public void inputTest() throws FileNotFoundException
    {
        File myFile = new File(".\\Tests\\dateTest1.csv");
        Attendance inputTest = new Attendance(myFile, "11/23/2020");

        int time1 = inputTest.getTimeAttended(0);
        int time2 = inputTest.getTimeAttended(1);
        int time3 = inputTest.getTimeAttended(2);
        int time4 = inputTest.getTimeAttended(3);
        int time5 = inputTest.getTimeAttended(4);

        assertEquals(30, time1);
        assertEquals(10, time2);
        assertEquals(5, time3);
        assertEquals(3, time4);
        assertEquals(48, time5);
    }

    /**
     * tests input of a file with repeating
     * ASURites
     * @throws FileNotFoundException
     */
    @Test
    public void duplicateIDInputTest() throws FileNotFoundException
    {
        File myFile = new File(".\\Tests\\dateTest2.csv");
        Attendance inputTest = new Attendance(myFile, "11/23/2020");

        int time1 = inputTest.getTimeAttended(0);
        int time2 = inputTest.getTimeAttended(1);
        int time3 = inputTest.getTimeAttended(2);
        int time4 = inputTest.getTimeAttended(3);
        int time5 = inputTest.getTimeAttended(4);

        assertEquals(30*2, time1);
        assertEquals(10*2, time2);
        assertEquals(5*2, time3);
        assertEquals(3*2, time4);
        assertEquals(48*2, time5);
    }

    /**
     * tests the mapping to a studentList
     * function, given that all the students
     * match to all of the students within dates.
     * @throws FileNotFoundException
     */
    @Test
    public void mapToStudentListNoExtraStudents() throws FileNotFoundException
    {
        File myFile = new File(".\\Tests\\dateTest2.csv");
        Attendance inputTest = new Attendance(myFile, "11/23/2020");
        Student newStudent1 = new Student(3, "B", "B", "E", "G", "bobby");
        Student newStudent2 = new Student(2, "J", "H", "CS", "U", "jhaehl");
        Student newStudent3 = new Student(5, "L", "P", "Y", "N", "lastperson");
        Student newStudent4 = new Student(4, "O", "P", "N", "P", "otherperson");
        Student newStudent5 = new Student(1, "R", "R", "CS", "U", "rrroth1");

        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(newStudent1);
        studentList.add(newStudent2);
        studentList.add(newStudent3);
        studentList.add(newStudent4);
        studentList.add(newStudent5);

        String[] returnString = inputTest.mapToSortedStudentList(studentList);

        assertEquals(null, returnString);

        int time1 = inputTest.getTimeAttended(0);
        int time2 = inputTest.getTimeAttended(1);
        int time3 = inputTest.getTimeAttended(2);
        int time4 = inputTest.getTimeAttended(3);
        int time5 = inputTest.getTimeAttended(4);

        assertEquals(30*2, time1);
        assertEquals(10*2, time2);
        assertEquals(5*2, time3);
        assertEquals(3*2, time4);
        assertEquals(48*2, time5);
    }

    /**
     * tests the return if there is extra students
     * in the attendance file that are not in the
     * student list, specifically is checking
     * if the csv formatted String array return
     * is correct
     * @throws FileNotFoundException
     */
    @Test
    public void mapToStudentListExtraStudents() throws FileNotFoundException
    {
        File myFile = new File(".\\Tests\\dateTest2.csv");
        Attendance inputTest = new Attendance(myFile, "11/23/2020");
        Student newStudent1 = new Student(3, "B", "B", "E", "G", "bobby");
        Student newStudent2 = new Student(2, "J", "H", "CS", "U", "jhaehl");
        Student newStudent3 = new Student(5, "L", "P", "Y", "N", "lastperson");
        Student newStudent4 = new Student(4, "O", "P", "N", "P", "otherperson");
        Student newStudent5 = new Student(1, "R", "R", "CS", "U", "rrroth1");

        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(newStudent1);
        studentList.add(newStudent3);
        studentList.add(newStudent5);

        String[] returnString = inputTest.mapToSortedStudentList(studentList);
        String[] expectedString = {"jhaehl,20","otherperson,6"};

        assertEquals(expectedString, returnString);

        int time1 = inputTest.getTimeAttended(0);
        int time2 = inputTest.getTimeAttended(1);
        int time3 = inputTest.getTimeAttended(2);

        assertEquals(30*2, time1);
        assertEquals(5*2, time2);
        assertEquals(48*2, time3);
    }
}
