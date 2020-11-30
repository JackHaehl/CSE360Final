import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


/**
 * @author Jack Haehl, Ryder Roth
 * @version 1
 * @since 11/30/2020
 * This is the world class,
 * it handles the visual output of the main window of the program
 * including a menu bar, a table displaying all necessary data and a menu bar displaying
 * action options to the user.
 */
public class World {

    /**
     * This is the main method, it initialized the world.
     * 
     */
    public static void main(String[] args){
        World world = new World();
        world.renderWorld();
    }
        JFrame frame;

        JTable table;

        Repository repository;



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

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            frame.add(scrollPane);
            frame.setSize(1080,720);
            frame.setVisible(true);


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
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try {
                        repository.makeStudentList(new FileFinder().getFile());
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
            });

            JMenuItem addAttendanceSubButton = new JMenuItem("Add Attendance");
            JMenuItem saveSubButton = new JMenuItem("Save");
            JMenuItem plotDataSubButton = new JMenuItem("PlotData");

            JMenuItem aboutSubButton = new JMenuItem("About Us");

            fileButton.add(loadRosterSubButton);
            fileButton.add(addAttendanceSubButton);
            fileButton.add(saveSubButton);
            fileButton.add(plotDataSubButton);

            aboutButton.add(aboutSubButton);

            mb.add(fileButton);

            mb.add(aboutButton);

            return mb;
        }



}
