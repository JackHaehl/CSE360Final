import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * @author Jack Haehl, Ryder Roth
 * @version 1
 * @since 11/30/2020
 * This RepoTest class, here all functions of the repository are tested
 * including the creation of student and attendance lists
 * then their parsing to JTables and output to CSV files.
 */

public class RepoTest{

    public Repository repository;


    /**
     * Tests if the Repository can properly parse a csv file into a list of students objects with all data assigned
     * to its proper placement.
     * @throws FileNotFoundException
     */
    @Test
    public void studentInputTest() throws FileNotFoundException
    {
        repository = new Repository();
        Student[] students;
        File roster = new File(".\\Tests\\sampleRoster.csv");
        repository.makeStudentList(roster);
        assertEquals("gjohnson",repository.getStudents().get(1).getASURite());
    }


    /**
     * Tests if the Repository can properly save data to a csv file without any IOExceptions.
     * @throws FileNotFoundException
     * @throws java.io.IOException
     */
    @Test
    public void saveTest() throws FileNotFoundException,java.io.IOException{
        repository = new Repository();
        Student[] students;
        File roster = new File(".\\Tests\\sampleRoster.csv");
        repository.makeStudentList(roster);
        repository.getJTable();
        repository.save();
        File output = new File(".\\table.csv");
        Scanner fileCheck = new Scanner(output);
        assertEquals("ID,First Name,Last Name,Program,Level,ASURITE",fileCheck.nextLine());
    }


    /**
     * Tests if the Repository can properly sort a student list and form a JTable with roster and attendance data
     * included.
     * @throws FileNotFoundException
     * @throws java.io.IOException
     */
    @Test
    public void attendanceTest() throws FileNotFoundException,java.io.IOException{
        repository = new Repository();
        Student[] students;
        File roster = new File(".\\Tests\\sampleRoster.csv");
        repository.makeStudentList(roster);
        Date date = new Date();
        File dates = new File(".\\Tests\\dateTest3.csv");
        repository.makeAttendance(date,dates);
        repository.getJTable();
        repository.save();
        ArrayList<Integer> timeList = repository.getAttendances().get(0).getTimeList();
        ArrayList<String> ASURiteList = repository.getAttendances().get(0).getASURiteList();
        assertEquals("[11, 50, 10, 30]",timeList.toString());
        assertEquals("[dmenace, gjohnson, jhaehl, mmario]",ASURiteList.toString());
    }

}