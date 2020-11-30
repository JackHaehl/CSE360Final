import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class RepoTest{

    public Repository repository;

    @Test
    public void studentInputTest() throws FileNotFoundException
    {
        repository = new Repository();
        Student[] students;
        File roster = new File(".\\Tests\\sampleRoster.csv");
        repository.makeStudentList(roster);
        assertEquals("gjohnson",repository.getStudents().get(1).getASURite());
    }

    @Test
    public void saveTest() throws FileNotFoundException,java.io.IOException{
        repository = new Repository();
        Student[] students;
        File roster = new File(".\\Tests\\sampleRoster.csv");
        repository.makeStudentList(roster);
        repository.getJTable();
        repository.save();
        assertEquals("gjohnson",repository.getStudents().get(1).getASURite());
    }

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
    }

}