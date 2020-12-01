import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Jack Haehl, Ryder Roth
 * @version 1
 * This is the repository class.
 * It parses student and attendance data
 * into a form suitable for use with UI elements.
 */

public class Repository {

    /**
     * The list holding all students currently in the roster.
     */
    private ArrayList<Student> students = new ArrayList<Student>();

    /**
     * The list holding all attendance data currently stored.
     */
    private ArrayList<Attendance> attendances = new ArrayList<Attendance>();

    /**
     * The array of headers for columns in the table.
     */
    private String[] jTableHeaders;

    /**
     * The array of data for the body of the table.
     */
    private String[][] jTableData;

    public Repository(){

    }

    /**
     * Formulates the students and attendances ArrayLists into arrays for use within JTables
     * @return a JTable with necessary student data, attendance data, and headers.
     */
    public JTable getJTable(){
        String[] columnTitles = new String[6+attendances.size()];
        columnTitles[0] = "ID";
        columnTitles[1] = "First Name";
        columnTitles[2] = "Last Name";
        columnTitles[3] = "Program";
        columnTitles[4] = "Level";
        columnTitles[5] = "ASURITE";

        int attendanceCounter = 6;
        for(Attendance attendance: attendances){
            //columnTitles.add(attendance.date);
            columnTitles[attendanceCounter] = dateToString(attendance.date);
            attendanceCounter++;
        }
        int rows = students.size();
        //int columns = columnTitles.size();
        int columns = columnTitles.length;

        String[][] tableData = new String[rows][columns];

        for (int i = 0; i < students.size(); i++) {
            Integer id = new Integer(students.get(i).getID());
            tableData[i][0] = id.toString();
            tableData[i][1] = students.get(i).getFirstName();
            tableData[i][2] = students.get(i).getLastName();
            tableData[i][3] = students.get(i).getProgram();
            tableData[i][4] = students.get(i).getAcademicLevel();
            tableData[i][5] = students.get(i).getASURite();

            for(int j = 0; j < attendances.size();j++){
                tableData[i][6+j] = attendances.get(j).timeList.get(i).toString();
            }

        }

        jTableHeaders = columnTitles;
        jTableData = tableData;

        return new JTable(tableData,columnTitles);
    }

    /**
     * Saves the data from the current JTable into a properly formatted JTable
     * @throws java.io.IOException
     */
    public void save() throws java.io.IOException{
        FileWriter writer = new FileWriter("table.csv");

        for(int i = 0 ; i < jTableHeaders.length;i++) {
            writer.write(jTableHeaders[i]);
            if (i != jTableHeaders.length - 1) {
                writer.write(",");
            }
        }
        writer.write("\n");

        for(int i = 0; i < jTableData.length;i++){
            for(int j = 0 ; j < jTableData[i].length;j++){
                writer.write(jTableData[i][j]);
                if(j != jTableData[i].length-1){
                    writer.write(",");
                }
            }
            writer.write("\n");
        }

        writer.close();
    }


    /**
     * A quicksort implementation for the Student list.
     * @param begin
     * @param end
     */
    private void sortStudentList(int begin, int end){
        if(begin < end){
            int partitionIndex = partitionForStudentSort(begin,end);

            sortStudentList(begin,partitionIndex-1);
            sortStudentList(partitionIndex+1,end);
        }
    }

    /**
     * A quicksort helper function for the Student list.
     * @param begin
     * @param end
     * @return the index that separates the partiitons in a QuickSort.
     */
    private int partitionForStudentSort(int begin, int end){
        String pivot = students.get(end).getASURite();
        int i = (begin-1);

        for (int j = begin; j < end ; j++){
            //Possible fix is comparator >= 0?
            if(students.get(j).getASURite().compareTo(pivot) <= 0){
                i++;

                Student swapTemp = students.get(i);
                students.set(i,students.get(j));
                students.set(j,swapTemp);
            }
        }

        Student swapTemp = students.get(i+1);
        students.set(i+1,students.get(end));
        students.set(end,swapTemp);

        return i+1;
    }

    /**
     * Creates the list of students from an input CSV file
     * Formats the data into an ArrayList of students, with each member containing all identification information
     * for an individual student.
     * @param studentFile the file address for the CSV file containing the student roster data.
     * @throws FileNotFoundException
     */
    public void makeStudentList(File studentFile) throws FileNotFoundException{

        Scanner fileIn = new Scanner(studentFile);
        String lineIn;
        String[] lineData;


        while(fileIn.hasNextLine()){
            lineIn = fileIn.nextLine();
            lineData = lineIn.split(",");
            Student newStudent = new Student(Integer.parseInt(lineData[0]),lineData[1],lineData[2],lineData[3],lineData[4],lineData[5]);
            students.add(newStudent);
            sortStudentList(0,students.size()-1);
        }


    }

    /**
     * Creates the list of attendance for every date of class from an input CSV file
     * Formats the data into an ArrayList of attendance objects, with each member containing
     * the ASURITE of each student and their attendance time for the pertaining day of class.
     * @param date the date of class associated with the attendance information provided by a date picker.
     * @param attendanceFile the file address for the CSV file containing the student attendance data.
     * @throws FileNotFoundException
     */
    public void makeAttendance(Date date, File attendanceFile) throws FileNotFoundException {
        attendances.add(new Attendance(attendanceFile,date));
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    public ArrayList<Attendance> getAttendances(){
        return attendances;
    }

    /**
     * Formats the date provided in the standard Java format into the desired format for a table header.
     * @param date a Java date provided by the date picker
     * @return a string with the necessary month information and day for use as a JTable header.
     */
    private String dateToString(Date date)
    {
        String dateAsString = "";
        int month = date.getMonth();
        int day = date.getDate();
        int year = date.getYear() + 1900;

        switch (month)
        {
            case 0:
                dateAsString += "Jan ";
                break;
            case 1:
                dateAsString += "Feb ";
                break;
            case 2:
                dateAsString += "Mar ";
                break;
            case 3:
                dateAsString += "Apr ";
                break;
            case 4:
                dateAsString += "May ";
                break;
            case 5:
                dateAsString += "Jun ";
                break;
            case 6:
                dateAsString += "Jul ";
                break;
            case 7:
                dateAsString += "Aug ";
                break;
            case 8:
                dateAsString += "Sep ";
                break;
            case 9:
                dateAsString += "Oct ";
                break;
            case 10:
                dateAsString += "Nov ";
                break;
            case 11:
                dateAsString += "Dec ";
                break;
            default:
                break;
        }

        dateAsString += day + " " + year;
        return dateAsString;
    }


}
