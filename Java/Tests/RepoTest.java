import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RepoTest{

    public Repository repository = new Repository();

    @Test
    public void studentInputTest() throws FileNotFoundException
    {

        Student[] students;
        File roster = new File(".\\Tests\\sampleRoster.csv");
        repository.makeStudentList(roster);
        assertEquals("gjohnson",repository.getStudents().get(1).getASURite());
    }

}