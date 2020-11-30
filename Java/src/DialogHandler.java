import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;

public class DialogHandler {

    public void createAttendanceDialog(JFrame frame, String[] dialog){
        JDialog jDialog = new JDialog(frame,"Data loaded!");

        String labelText = "<html>Data loaded for the roster.<br>" +
                dialog.length+" additional attendee(s) found:<br>";

        for(int i = 0; i < dialog.length;i++){
            labelText += dialog[i];
            labelText += "<br>";
        }
        labelText += "</html>";

        JLabel label = new JLabel(labelText);

        jDialog.add(label);

        jDialog.setSize(400,400);
        jDialog.setVisible(true);
    }

    public void createAboutUsDialog(JFrame frame){
        JDialog jDialog = new JDialog(frame,"About Us");

        JLabel label = new JLabel("<html> The team behind this program consists of <br>" +
                "Jack Haehl, ID: 1216722936 <br>" +
                "Ryder Roth, ID: 1216790822");

        jDialog.add(label);

        jDialog.setSize(400,250);

        jDialog.setVisible(true);
    }
}
