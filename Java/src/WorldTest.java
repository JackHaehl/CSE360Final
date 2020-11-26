import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class WorldTest {
    public static void main(String[] args){

        //Initialize Frame
        JFrame f = new JFrame("Menu Demo");


        JMenuBar mb = new JMenuBar();

        //Initialize buttons on menu
        JMenu x = new JMenu("Menu");
        JMenu y = new JMenu("Students");

        //Initialize sub-buttons that drop down from menu buttons
        JMenuItem m1 = new JMenuItem("MenuItem1");
        JMenuItem m2 = new JMenuItem("MenuItem2");
        JMenuItem m3 = new JMenuItem("MenuItem3");

        JMenuItem m4 = new JMenuItem("Save");

        x.add(m1);
        x.add(m2);
        x.add(m3);

        y.add(m4);

        mb.add(x);

        mb.add(y);

        //Sets the menu bar of the frame to our chosen menu bar.
        f.setJMenuBar(mb);

        f.setSize(500,500);
        f.setVisible(true);

        }




    }

