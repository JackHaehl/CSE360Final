import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class WorldTest {
    public static void main(String[] args){

        JFrame f = new JFrame("Menu Demo");

        JMenuBar mb = new JMenuBar();

        JMenu x = new JMenu("Menu");
        JMenu y = new JMenu("Students");

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

        f.setJMenuBar(mb);

        f.setSize(500,500);
        f.setVisible(true);

        }




    }

