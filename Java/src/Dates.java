import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dates {
    String date;
    ArrayList<String> ASURiteList;
    ArrayList<Integer> timeList;

    Dates(File dateFile, String date) throws FileNotFoundException
    {
        this.date = date;
        this.ASURiteList = new ArrayList<String>();
        this.timeList = new ArrayList<Integer>();

        Scanner fileIn = new Scanner(dateFile);
        char delimiter = ',';
        String lineIn;
        int delimiterIndex;
        int time;
        String ASURite;

        while (fileIn.hasNextLine())
        {
            lineIn = fileIn.nextLine();
            delimiterIndex = lineIn.indexOf(delimiter);
            ASURite = lineIn.substring(0, delimiterIndex-1 );
            time = Integer.valueOf(lineIn.substring(delimiterIndex+1));
            timeList.add(time);
            ASURiteList.add(ASURite);
        }
    }

    void sort()
    {

    }

    void mapToStudentList(ArrayList<Student> StudentList)
    {

    }

    int getTimeAttended(int index)
    {
        return timeList.get(index);
    }

}
