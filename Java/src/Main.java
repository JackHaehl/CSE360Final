import java.util.Date;

public class Main
{
    public static void main(String[] args) throws InterruptedException {
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
        Date date;
        UserDateGetter myGetter = new UserDateGetter();
        myGetter.openGUI();
        while (true)
        {
            date = myGetter.getDate();
            System.out.println(date);

            Thread.sleep(2000);
        }

    }
}
