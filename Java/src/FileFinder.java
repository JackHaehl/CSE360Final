import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * @author Ryder Roth, Jack Haehl
 * @version 1
 * @since 11/27/2020
 * This is the FileFinder class, it
 * gets the input from the user for
 * what file to read and where it is.
 * It only accepts csv files.
 */
public class FileFinder
{
    /**
     * constructor for file finder, which
     * has no variables to construct.
     */
    public FileFinder(){};

    /**
     * calls the JFileChooser and returns
     * the file that the user wanted to be chosen.
     * @return userFile
     */
    public File getFile()
    {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Separated Value File", "csv");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(chooser.getParent());
        return chooser.getSelectedFile();
    }
}
