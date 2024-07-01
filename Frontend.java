import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Frontend implements FrontendInterface
{
    private String min = "min";
    private String max = "max";
    private String energy = "none";
    private final Scanner scan;
    private final BackendInterface backend;

    public Frontend(Scanner in, BackendInterface backend)
    {
        scan = in;
        this.backend = backend;
    }

    /**
     * Gives the user an opportunity to issue new commands
     * until they select Q to quit.
     */
    public void runCommandLoop()
    {
        displayMainMenu();
        String response = scan.nextLine().toUpperCase();

        while(!response.equals("Q"))
        {
            if(response.equals("R"))
            {
                readFile();
            }
            else if(response.equals("G"))
            {
                getValues();
            }
            else if(response.equals("F"))
            {
                setFilter();
            }
            else if(response.equals("D"))
            {
                topFive();
            }
            else
            {
                System.out.println("Please enter one of the options from the menu!");
            }
            displayMainMenu();
            response = scan.nextLine().toUpperCase();
        }
        scan.close();
    }

    /**
     * Displays the menu of command options to the user.
     */
    public void displayMainMenu()
    {
        String menu = """
            
            ~~~ Command Menu ~~~
                [R]ead Data
                [G]et Songs by Danceability [min - max]
                [F]ilter New Songs (by Min Energy: none)
                [D]isplay Five Fastest
                [Q]uit
            Choose command:""";
        menu=menu.replace("min",min).replace("max",max).replace("none",energy);
        System.out.print(menu + " ");
    }

    /**
     * Provides text-based user interface and error handling
     * for the [R]ead Data command.
     */
    public void readFile()
    {
        System.out.print("Enter path to csv file to load: ");
        String fileName = scan.nextLine();

        try
        {
            backend.readData(fileName);
            System.out.println("Done reading this file.");
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Provides text-based user interface and error handling
     * for the [G]et Songs by Danceability command.
     */
    public void getValues()
    {
        System.out.print("Enter range of values (MIN - MAX): ");
        String range = scan.nextLine();

        try
        {
            String[] rangeArray = range.split(" - ");
            min = rangeArray[0];
            max = rangeArray[1];

            int minVal = Integer.parseInt(min);
            int maxVal = Integer.parseInt(max);

            List<String> result = backend.getRange(minVal, maxVal);

            if(!energy.equals("none"))
            {
                System.out.println(result.size() + " songs found between " + min + " - " + max +
                        " with energy >= " + energy + ":");

                for(String s : result)
                {
                    System.out.println("    " + s);
                }
            }
            else
            {
                System.out.println(result.size() + " songs found between " + min + " - " + max + ":");

                for(String s : result)
                {
                    System.out.println("    " + s);
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException exception)
        {
            System.out.println("Ensure you include a space before and after the hyphen...");
        }
        catch(NumberFormatException exception)
        {
            System.out.println("Please enter an integer value for both MIN and MAX...");
        }
    }

    /**
     * Provides text-based user interface and error handling
     * for the [F]ilter Energetic Songs (by Min Energy) command.
     */
    public void setFilter()
    {
        System.out.print("Enter minimum energy: ");
        energy = scan.nextLine();

        try
        {
            List<String> result = backend.filterEnergeticSongs(Integer.parseInt(energy));

            if(!min.equals("min") && !max.equals("max"))
            {
                System.out.println(result.size() + " songs found between " + min + " - " + max +
                        " with energy >= " + energy + ":");

                for(String s : result)
                {
                    System.out.println("    " + s);
                }
            }
        }
        catch(NumberFormatException exception)
        {
            System.out.println("Please enter an integer value for the minimum energy...");
        }
    }

    /**
     * Provides text-based user interface and error handling
     * for the [D]isplay Five Fastest command.
     */
    public void topFive()
    {
        try
        {
            List<String> result = backend.fiveFastest();

            if(!energy.equals("none") && !min.equals("min") && !max.equals("max"))
            {
                System.out.println("Top Five fastest songs found between " + min + " - " + max
                        + " with energy >= " + energy + ":");

                for(String s : result)
                {
                    System.out.println("    " + s);
                }
            }
            else
            {
                System.out.println("Top Five fastest songs found between " + min + " - " + max + ":");

                for(String s : result)
                {
                    System.out.println("    " + s);
                }
            }
        }
        catch(IllegalStateException exception)
        {
            System.out.println(exception.getMessage());
        }
    }
}