import org.junit.Test;

import java.io.File;

import static org.junit.Assert.fail;

/**
 * @author Ryder Roth, Jack Haehl
 * @version 1
 * @since 11/27/2020
 * Junit tests that verify
 * the functionality of the
 * FileFinder class
 */
public class FileFinderTest
{
    /**
     * basic test that allows to test
     * if the class returns a file that has been chosen.
     */
    @Test
    public void ChoosingAFileTest()
    {
        FileFinder fileFinder = new FileFinder();
        File file = fileFinder.getFile();
        if (!file.exists())
        {
            fail("file does not exist");
        }
    }
}
