import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
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

    private ArrayList<Student> students = new ArrayList<Student>();

    private ArrayList<Attendance> attendances = new ArrayList<Attendance>();

    private String[] jTableHeaders;
    private String[][] jTableData;

    public Repository(){

    }


    public JTable getJTable(){
        //TODO: Make a 2D array for data, and a 1D Array for the names.
        //ArrayList columnTitles = new ArrayList(Arrays.asList("ID","First Name","Last Name","Program","Level","ASURITE"));
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
            columnTitles[attendanceCounter] = attendance.date;
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

            for(int j = 0; j < attendances.size();i++){
                tableData[i][6+j] = attendances.get(j).timeList.get(i).toString();
            }

        }

        jTableHeaders = columnTitles;
        jTableData = tableData;

        return new JTable(tableData,columnTitles);
    }

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


    private void sortStudentList(int begin, int end){
        if(begin < end){
            int partitionIndex = partitionForStudentSort(begin,end);

            sortStudentList(begin,partitionIndex-1);
            sortStudentList(partitionIndex+1,end);
        }
    }

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

    public void makeStudentList(File studentFile) throws FileNotFoundException{

        Scanner fileIn = new Scanner(studentFile);
        String lineIn;
        String[] lineData;


        while(fileIn.hasNextLine()){
            lineIn = fileIn.nextLine();
            lineData = lineIn.split(",");
            Student newStudent = new Student(Integer.parseInt(lineData[0]),lineData[1],lineData[2],lineData[3],lineData[4],lineData[5]);
            students.add(newStudent);
        }


    }

    public void makeAttendance(Date date, File attendanceFile) throws FileNotFoundException {
        attendances.add(new Attendance(attendanceFile,date.toString()));
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    public ArrayList<Attendance> getAttendances(){
        return attendances;
    }



}
