import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Main
{
    public static void main(String[] args) throws InterruptedException, IOException {
        /*System.out.println("Hello World!");


        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(chooser.getParent());
        System.out.print(returnVal);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }*/

        /*
        Date date;
        UserDateGetter myGetter = new UserDateGetter();
        myGetter.openGUI();
        while (true)
        {
            date = myGetter.getDate();
            System.out.println(date);

            Thread.sleep(2000);
        }
        */

        Repository repository = new Repository();
        File roster = new File(".\\Tests\\sampleRoster.csv");
        repository.makeStudentList(roster);

        repository.makeAttendance(new Date(), new File(".\\Tests\\dateTest1.csv"));
        Date date = new Date();
        date.setDate(1);
        repository.makeAttendance(date, new File(".\\Tests\\dateTest2.csv"));

        Chart chart = new Chart(repository);
        chart.createAndDisplayChart();

    }
}
