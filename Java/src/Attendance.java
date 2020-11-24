import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the attendance class,
 * it stores a date, ASURite, and
 * the amount of minutes each ASURite
 * attended for.
 */
public class Attendance
{
    /**
     * the date is that this attendance
     * represents
     */
    String date;

    /**
     * an array list which holds the
     * ASURite of each student,.
     * the indexes of ASURiteList and timeList
     * correspond, so that at index 1, the
     * person at ASURite index 1 attended for
     * the value in timeList at index 1.
     */
    ArrayList<String> ASURiteList;

    /**
     * an array list which holds
     * the time attended by each student,
     * the indexes of ASURiteList and timeList
     * correspond, so that at index 1, the
     * person at ASURite index 1 attended for
     * the value in timeList at index 1.
     */
    ArrayList<Integer> timeList;

    /**
     * constructor for dates, requires a date
     * and a dateFile, given this it will parse
     * the file and establish its values within
     * its class then sort them and add duplicates.
     * @param dateFile
     * @param date
     * @throws FileNotFoundException
     */
    Attendance(File dateFile, String date) throws FileNotFoundException
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
            ASURite = lineIn.substring( 0, delimiterIndex );
            time = Integer.valueOf( lineIn.substring( delimiterIndex+1, lineIn.length() ) );

            timeList.add(time);
            ASURiteList.add(ASURite);
        }

        sort();
        addDuplicatesTogether();
    }

    // Swaps a value between an index in both ASURiteList
    // and time list
    private void swapInBothLists(int index1, int index2)
    {
        int timeToSave = timeList.get(index1);
        String ASURiteToSave = ASURiteList.get(index1);

        ASURiteList.set(index1, ASURiteList.get(index2));
        timeList.set(index1, timeList.get(index2));
        ASURiteList.set(index2, ASURiteToSave);
        timeList.set(index2, timeToSave);
    }

    // The helper function perform quicksort on both arrays
    // and index them the same.
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

    // the partition algorithm for quicksort,
    // swaps elements in both arrays
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

    // calls quicksort to sort both lists and
    // align them in terms of value and index
    private void quickSortBothLists()
    {
        quickSortHelper(0, ASURiteList.size()-1);
    }

    // method to call sort on both lists
    private void sort()
    {
        quickSortBothLists();
    }

    // method to remove duplicates from the
    // arraylist and add the time attended from
    // duplicates together
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

    // converts an arrayList of type String to
    // an array of Strings.
    private String[] arrayListToStringArray(ArrayList<String> stringArrayList)
    {
        String arrayToReturn[] = new String[stringArrayList.size()];

        for (int i = 0; i < stringArrayList.size(); i++)
        {
            arrayToReturn[i] = stringArrayList.get(i);
        }

        return arrayToReturn;
    }

    // maps the sorted studentList to the times
    // that are held in the dates class, returns the index
    // where the time list matches the sorted student list
    // for spots where there is extra time, puts a negative 1
    // at that index.
    private int[] mapToTimeList(ArrayList<Student> sortedStudentList)
    {
        int mappedList[] = new int[ASURiteList.size()];
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

    /**
     * maps the dates list to the sorted student list,
     * then returns a string array in csv format
     * for each of the attendees that are not on the
     * student list and their time attended.
     * @param sortedStudentList
     * @return extraStudents[]
     */
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
                extraStudent = ASURiteList.get(i - indexShift) + "," + timeList.get(i - indexShift);
                extraStudents.add(extraStudent);
                ASURiteList.remove(i - indexShift);
                timeList.remove(i - indexShift);
                indexShift++;
            }
        }
        if ( extraStudents.size() == 0)
        {
            return null;
        }
        else {
            return arrayListToStringArray(extraStudents);
        }

    }

    /**
     * returns the time attended by the student
     * at a given index.
     * @param index
     * @return timeList.get(index)
     */
    int getTimeAttended(int index)
    {
        return timeList.get(index);
    }

}
