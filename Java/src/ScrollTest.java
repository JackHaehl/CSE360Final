import javax.swing.*;
import java.awt.*;

public class ScrollTest {

    public static void main(String[] args){
        JFrame frame = new JFrame("Scroll Pane");

        frame.setSize(550,550);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame.getContentPane().setLayout(new ScrollPaneLayout());
       frame.getContentPane().setLayout(new FlowLayout());

        JTextArea tA = new JTextArea(20,20);
        tA.append("iojfewjiofjioweijoewfjiweijf");

        JScrollPane scrollableTextArea = new JScrollPane(tA);

        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.getContentPane().add(scrollableTextArea);

        frame.setVisible(true);
    }
}
