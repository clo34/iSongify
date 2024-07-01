import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;
import java.util.Scanner;

public class FrontendDeveloperTests
{
    /**
     * Checks if readFile successfully reads a valid file and prints out a message saying so
     * @throws IOException if the file can't be found or read
     */
    @Test
    public void test1() throws IOException
    {
        TextUITester tester = new TextUITester("kaggle.csv");

        IterableSortedCollection<SongInterface> tree = new ISCPlaceholder<>();
        BackendInterface backend = new BackendPlaceholder(tree);
        Scanner in = new Scanner(System.in);
        FrontendInterface frontend = new Frontend(in,backend);

        frontend.readFile();

        String output = tester.checkOutput();

        Assertions.assertEquals(true, output.contains("Done reading this file."),
                "TEST FAILED");
    }

    /*
    @Test
    public void test2() throws IOException
    {
        TextUITester tester = new TextUITester("incorrect.csv");

        IterableSortedCollection<SongInterface> tree = new ISCPlaceholder<>();
        BackendInterface backend = new BackendPlaceholder(tree);
        Scanner in = new Scanner(System.in);
        FrontendInterface frontend = new Frontend(in,backend);

        frontend.readFile();

        String output = tester.checkOutput();

        Assertions.assertEquals(true, output.contains(*errorMessage*),
                "TEST FAILED");
    }
     */

    /**
     * Checks if getValues() correctly outputs a message indicating the numbers of songs
     * that were found between the user's inputted range of Danceability values
     */
    @Test
    public void test3()
    {
        TextUITester tester = new TextUITester("20 - 30");

        IterableSortedCollection<SongInterface> tree = new ISCPlaceholder<>();
        BackendInterface backend = new BackendPlaceholder(tree);
        Scanner in = new Scanner(System.in);
        FrontendInterface frontend = new Frontend(in,backend);

        frontend.getValues();

        String output = tester.checkOutput();

        Assertions.assertEquals(true, output.contains("5 songs found between 20 - 30:\n" +
                        "    Hey, Soul Sister\n" +
                        "    Love The Way You Lie\n" +
                        "    TiK ToK\n" +
                        "    Bad Romance\n" +
                        "    Just the Way You Are"),
                "TEST FAILED");
    }

    /**
     * Checks that a ArrayIndexOutOfBoundsException is thrown (and caught)
     * when there is no space before and after the hyphen separating min/max
     */
    @Test
    public void test4()
    {
        TextUITester tester = new TextUITester("20-30");

        IterableSortedCollection<SongInterface> tree = new ISCPlaceholder<>();
        BackendInterface backend = new BackendPlaceholder(tree);
        Scanner in = new Scanner(System.in);
        FrontendInterface frontend = new Frontend(in,backend);

        frontend.getValues();

        String output = tester.checkOutput();

        Assertions.assertEquals(true,
                                output.contains("Ensure you include a space before and after the hyphen..."),
                                "TEST FAILED");
    }

    /**
     * Checks that a NumberFormatException is thrown when the user enters values
     * which are not integers for min and max
     */
    @Test
    public void test5()
    {
        TextUITester tester = new TextUITester("abcd - efgh");

        IterableSortedCollection<SongInterface> tree = new ISCPlaceholder<>();
        BackendInterface backend = new BackendPlaceholder(tree);
        Scanner in = new Scanner(System.in);
        FrontendInterface frontend = new Frontend(in,backend);

        frontend.getValues();

        String output = tester.checkOutput();

        Assertions.assertEquals(true,
                                output.contains("Please enter an integer value for both MIN and MAX..."),
                                "TEST FAILED");
    }

    /**
     * Checks that a NumberFormatException is thrown when the user enters a value
     * which is not an integer for minEnergy
     */
    @Test
    public void test6()
    {
        TextUITester tester = new TextUITester("abcde");

        IterableSortedCollection<SongInterface> tree = new ISCPlaceholder<>();
        BackendInterface backend = new BackendPlaceholder(tree);
        Scanner in = new Scanner(System.in);
        FrontendInterface frontend = new Frontend(in,backend);

        frontend.setFilter();

        String output = tester.checkOutput();

        Assertions.assertEquals(true,
                                output.contains("Please enter an integer value for the minimum energy..."),
                                "TEST FAILED");
    }

    /**
     * Checks if fiveFastest() outputs a correctly-formatted message
     * indicating the five fastest songs that were found between
     * the user's desired Danceability range
     */
    @Test
    public void test7()
    {
        TextUITester tester = new TextUITester("20 - 30");

        IterableSortedCollection<SongInterface> tree = new ISCPlaceholder<>();
        BackendInterface backend = new BackendPlaceholder(tree);
        Scanner in = new Scanner(System.in);
        FrontendInterface frontend = new Frontend(in,backend);

        frontend.getValues();
        frontend.topFive();

        String output = tester.checkOutput();

        Assertions.assertEquals(true, output.contains("Top Five fastest songs found between 20 - 30:\n" +
                        "    97: Hey, Soul Sister\n" +
                        "    87: Love The Way You Lie"),
                "TEST FAILED");
    }

    /*
    @Test
    public void test8()
    {
        TextUITester tester = new TextUITester("50");

        IterableSortedCollection<SongInterface> tree = new ISCPlaceholder<>();
        BackendInterface backend = new BackendPlaceholder(tree);
        Scanner in = new Scanner(System.in);
        FrontendInterface frontend = new Frontend(in,backend);

        frontend.setFilter();
        frontend.topFive();

        String output = tester.checkOutput();

        Assertions.assertEquals(true, output.contains(*errorMessage*),
                "TEST FAILED");
    }
     */

}