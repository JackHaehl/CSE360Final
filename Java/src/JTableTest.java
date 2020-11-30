import javax.swing.*;
import java.awt.*;

public class JTableTest {

    public static void main(String[] args){

        JFrame f;
        JTable t;

        f = new JFrame();

        f.setTitle("Frame Example");

        String[][] data = {
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" }
        };

        String[] columnNames = {"Name","Roll Number", "Department"};

        t = new JTable(data,columnNames);
        t.setBounds(30,40,200,300);

        JScrollPane sp = new JScrollPane(t);

        f.add(sp);

        f.setSize(500,200);

        f.setVisible(true);

    }
}
