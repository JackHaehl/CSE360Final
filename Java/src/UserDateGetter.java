import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Observable;

public class UserDateGetter extends Observable
{
    Date date;
    String[] months;
    Integer[] days;
    Integer[] years;
    JComboBox<String> monthChoices;
    JComboBox<Integer> dayChoices;
    JComboBox<Integer> yearChoices;

    public UserDateGetter()
    {
        date = null;

        months = new String[]{"January", "February", "March", "April","May", "June", "July", "August",
                              "September", "October", "November", "December"};
        monthChoices = new JComboBox<String>(months);

        days = new Integer[31];
        for (int i = 0; i < 31; i++)
        {
            days[i] = i + 1;
        }
        dayChoices = new JComboBox<Integer>(days);

        years = new Integer[50];
        Date today = new Date();
        int currentYear = today.getYear() + 1900;
        for (int i = 0; i < 50; i++)
        {
            years[i] = currentYear - i;
        }
        yearChoices = new JComboBox<Integer>(years);
    }

    public void openGUI()
    {
        JFrame dateFrame = new JFrame("Enter Date");
        dateFrame.setVisible(true);
        dateFrame.setSize(200,200);

        JPanel datePanel = new JPanel();

        dateFrame.add(datePanel);

        JLabel label = new JLabel("Select the Date of Attendance");
        label.setVisible(true);

        datePanel.add(monthChoices);
        datePanel.add(dayChoices);
        datePanel.add(yearChoices);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDate();
                dateFrame.dispose();
            }
        });

        datePanel.add(submitButton);

        dateFrame.pack();
    }

    public void setDate()
    {
        date = new Date();
        int selectedMonth = monthChoices.getSelectedIndex();
        int selectedDay = days[dayChoices.getSelectedIndex()];
        int selectedYear = years[yearChoices.getSelectedIndex()] - 1900;

        date.setMonth(selectedMonth);
        date.setDate(selectedDay);
        date.setYear(selectedYear);

        setChanged();
        notifyObservers();
    }

    public Date getDate() {
        return date;
    }
}
