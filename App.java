import java.util.Scanner;

/**
 * Main entry point for running the iSongify app.
 */
public class App {
    public static void main(String[] args) {
	IterableSortedCollection<SongInterface> tree = new SortedSongCollection<>();
	//IterableSortedCollection<SongInterface> tree = new IterableRedBlackTree<>();
	BackendInterface backend = new BackendImplementation(tree); //plugged BE

	Scanner in = new Scanner(System.in);
	FrontendInterface frontend = new Frontend(in,backend); //plugged FE

	System.out.println("Welcome to iSongify");
	System.out.println("===================");
	
	frontend.runCommandLoop();

	System.out.println();
	System.out.println("===================");
	System.out.println("Thanks, and Goodbye");
    }
}
