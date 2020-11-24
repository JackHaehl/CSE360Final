import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
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
            ASURite = lineIn.substring( 0, delimiterIndex-1 );
            time = Integer.valueOf( lineIn.substring( delimiterIndex+1, lineIn.length() ) );

            timeList.add(time);
            ASURiteList.add(ASURite);
        }

        sort();
        addDuplicatesTogether();

    }

    private void swapInBothLists(int index1, int index2)
    {
        int timeToSave = timeList.get(index1);
        String ASURiteToSave = ASURiteList.get(index1);

        ASURiteList.set(index1, ASURiteList.get(index2));
        timeList.set(index1, timeList.get(index2));
        ASURiteList.set(index2, ASURiteToSave);
        timeList.set(index2, timeToSave);
    }

    private void quickSortHelper(int lowIndex, int highIndex)
    {
        int pivot = partition(lowIndex, highIndex);
        if (pivot-1 > lowIndex)
        {
            quickSortHelper(lowIndex, pivot-1);
        }
        if (pivot+1 < highIndex)
        {
            quickSortHelper(pivot+1, highIndex);
        }
    }

    private int partition(int lowIndex, int highIndex)
    {
        swapInBothLists(lowIndex, highIndex);

        int pivotPoint = highIndex;
        int storePoint = lowIndex;
        int comparePoint = lowIndex;

        for (int i = lowIndex; i <= highIndex; i++)
        {
            if ( ASURiteList.get(comparePoint).compareTo(ASURiteList.get(pivotPoint)) < 0)
            {
                swapInBothLists(comparePoint, storePoint);
                storePoint++;
            }
            comparePoint++;
        }
        swapInBothLists(storePoint, pivotPoint);
        pivotPoint = storePoint;
        return pivotPoint;
    }

    private void quickSortBothLists()
    {
        quickSortHelper(0, ASURiteList.size()-1);
    }

    private void sort()
    {
        quickSortBothLists();
    }

    private void addDuplicatesTogether()
    {
        for (int i = 0; i < ASURiteList.size() - 1; i++)
        {
            if ( ASURiteList.get(i).compareTo(ASURiteList.get(i+1)) == 0 )
            {
                ASURiteList.remove(i);
                timeList.set( i+1, (timeList.get(i) + timeList.get(i+1)) );
                timeList.remove(i);
                i--;
            }
        }
    }

    private int[] mapToTimeList(ArrayList<Student> sortedStudentList)
    {
        int mappedList[] = new int[sortedStudentList.size()];
        int previousStudentMatchIndex = 0;
        String studentIndexASURite, ASURiteIndexASURite;
        int mappedListIndex = 0;
        boolean matchFound = false;

        for (int i = 0; i < ASURiteList.size(); i++)
        {
            matchFound = false;
            ASURiteIndexASURite = ASURiteList.get(i);


            for (int j = previousStudentMatchIndex; j < sortedStudentList.size(); j++)
            {
                studentIndexASURite = sortedStudentList.get(j).getASURite();

                if (studentIndexASURite.compareTo(ASURiteIndexASURite) == 0)
                {
                    matchFound = true;
                    previousStudentMatchIndex = j;
                    mappedList[mappedListIndex] = j;
                }
            }

            if (!matchFound)
            {
                mappedList[mappedListIndex] = -1;
            }

            mappedListIndex++;
        }

        return mappedList;
    }

    String[] mapToSortedStudentList(ArrayList<Student> sortedStudentList)
    {
        int mapToTimeList[] = mapToTimeList(sortedStudentList);
        ArrayList<String> extraStudents = new ArrayList<String>();
        String extraStudent;
        int indexShift = 0;

        for (int i = 0; i < mapToTimeList.length; i++)
        {
            if (mapToTimeList[i] == -1)
            {
                extraStudent = ASURiteList.get(i) + "," + timeList.get(i);
                extraStudents.add(extraStudent);
                ASURiteList.remove(i - indexShift);
                timeList.remove(i - indexShift);
                indexShift++;
            }
        }

        return (String[]) extraStudents.toArray();
    }

    int getTimeAttended(int index)
    {
        return timeList.get(index);
    }

}
