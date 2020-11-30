import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;


/**
 * @author Jack Haehl, Ryder Roth
 * @version 1
 * @since 11/30/2020
 * This is the world class,
 * it handles the visual output of the main window of the program
 * including a menu bar, a table displaying all necessary data and a menu bar displaying
 * action options to the user.
 */
public class World implements Observer
{

    /**
     * This is the main method, it initializes the world.
     * 
     */
    public static void main(String[] args){
        World world = new World();
        world.renderWorld();
    }
        JFrame frame;

        JTable table;

        Repository repository;

        JScrollPane scrollPane;

        Date date;

        UserDateGetter dateGetter;

        File fileToAdd;

        boolean activeRoster = false;
        boolean activeAttendance = false;


        World(){
            repository = new Repository();
        }

    /**
     * Loads the main window for the program including a table that can be scrolled through, and a menu bar.
     */
    public void renderWorld(){
            frame = new JFrame();
            frame.setTitle("CSE360 Final Project");

            table = createDefaultTable();

            JMenuBar menuBar = createJMenuBar();

            frame.setJMenuBar(menuBar);

            scrollPane = new JScrollPane(table);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            frame.add(scrollPane);
            frame.setSize(1080,720);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

    /**
     * Creates a table with default data for the program. Meant for the period before any data has been entered.
     * @return A basic table with some student data.
     */
    private JTable createDefaultTable(){
            String[][] sampleData = {
                    {"1210101010","Javier","Gonzales","Computer Science","Graduate","javiergs"},
                    {"0000000000","John","Doe","Engineering","Undergraduate","jdoe24"}
            };

            String[] titles = {"ID","First Name", "Last Name", "Program", "Level", "ASURITE"};

            JTable table = new JTable(sampleData,titles);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            return table;
        }

        private void renderJTableWithData(){
        JTable table = repository.getJTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            scrollPane.setViewportView(table);
            frame.setVisible(true);
        }

    /**
     * Creates the Menu Bar for the main window of the program.
     * @return A JMenu bar with file controls and an about page.
     */
    private JMenuBar createJMenuBar(){
            JMenuBar mb = new JMenuBar();

            //Initialize buttons on menu
            JMenu fileButton = new JMenu("File");
            JMenu aboutButton = new JMenu("About");

            //Initialize sub-buttons that drop down from menu buttons
            JMenuItem loadRosterSubButton = new JMenuItem("Load a Roster");
            loadRosterSubButton.addActionListener(new ActionListener()
            {
                /**
                 * Calls repository's methods to create the list of students based on the picked file.
                 * @param e
                 */
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    repository = new Repository();
                    try {
                        repository.makeStudentList(new FileFinder().getFile());
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    renderJTableWithData();
                    activeRoster = true;
                }
            });

            JMenuItem addAttendanceSubButton = new JMenuItem("Add Attendance");
            addAttendanceSubButton.addActionListener(new ActionListener() {
                /**
                 * Opens a file picker and calls repository to parse the chosen attendance file.
                 * @param e
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(activeRoster){
                        fileToAdd = new FileFinder().getFile();
                        Date date = new Date();
                        dateGetter = new UserDateGetter();
                        dateGetter.addObserver(World.this);
                        dateGetter.openGUI();
                        activeAttendance = true;
                    }
                }
            });

            JMenuItem saveSubButton = new JMenuItem("Save");
            saveSubButton.addActionListener(new ActionListener() {
                /**
                 * Calls repository to save the current JTable as a csv file places in table.csv.
                 * @param e
                 */
                @Override
                public void actionPerformed(ActionEvent e){
                    if(activeRoster){
                        try {
                            repository.save();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            JMenuItem plotDataSubButton = new JMenuItem("PlotData");
            plotDataSubButton.addActionListener(new ActionListener() {
                /**
                 * Calls the necessary methods to plot all currently visible attendance data.
                 * @param e
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(activeRoster && activeAttendance){
                        Chart chart = new Chart(repository);
                        chart.createAndDisplayChart();
                    }
                }
            });

            JMenuItem aboutSubButton = new JMenuItem("About Us");
            aboutSubButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DialogHandler dialogHandler = new DialogHandler();
                    dialogHandler.createAboutUsDialog(frame);
                }
            });

            fileButton.add(loadRosterSubButton);
            fileButton.add(addAttendanceSubButton);
            fileButton.add(saveSubButton);
            fileButton.add(plotDataSubButton);

            aboutButton.add(aboutSubButton);

            mb.add(fileButton);

            mb.add(aboutButton);

            return mb;
        }

        @Override
        public void update(Observable o, Object arg)
        {
            date = dateGetter.getDate();
            try {
                repository.makeAttendance(date, fileToAdd);
                String[] report = repository.getAttendances().get(repository.getAttendances().size()-1).mapToSortedStudentList(repository.getStudents());
                if(report != null){
                    DialogHandler dialog = new DialogHandler();
                    dialog.createAttendanceDialog(frame,report);
                }

            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            renderJTableWithData();
        }





}
