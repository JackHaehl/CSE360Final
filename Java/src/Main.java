import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello World!");


        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(chooser.getParent());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }




    }
}
