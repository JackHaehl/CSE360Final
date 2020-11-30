import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Observable;

/**
 * @author Ryder Roth, Jack Haehl
 * @version 1
 * @since 11/27/2020
 * This is the userDateGetter class,
 * which is used to get a date input from users.
 * When the user selects submit, it closes and
 * notifies the observers.
 */
public class UserDateGetter extends Observable
{
    /**
     * Date stored in the class
     * updated when the user enters a date.
     */
    Date date;

    /**
     * The names of the months are stored here
     */
    String[] months;

    /**
     * The days in a month are stored here.
     */
    Integer[] days;

    /**
     * The years that can be selected by the user
     * are stored in this array.
     */
    Integer[] years;

    /**
     * The JComboBox which holds the months array
     */
    JComboBox<String> monthChoices;

    /**
     * The JComboBox that holds the days array
     */
    JComboBox<Integer> dayChoices;

    /**
     * The JComboBox that holds the years array.
     */
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

    /**
     * opens the date selecter gui, and when the user
     * hits the submit button it updates the current date
     * and notifies observers.
     */
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

    /**
     * sets the date to the current selected date
     * and notifies observers of the change
     */
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

    /**
     * returns the date stored in the class
     * @return date
     */
    public Date getDate() {
        return date;
    }
}
