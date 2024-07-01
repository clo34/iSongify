import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BackendImplementation implements BackendInterface{

    private IterableSortedCollection<SongInterface> songCollection;
    private boolean getRangeCalled = false;
    //private BinarySearchTree<Song> songTree;
    //private SortedCollection<SongInterface> songCollection;

    public BackendImplementation(IterableSortedCollection<SongInterface> songCollection) {
      this.songCollection = songCollection;
    }
//public BackendImplementation() {
//    songTree = new BinarySearchTree<>();
//}



    //testing stuff out
//    private SortedCollection<SongInterface> songCollection;
//
//    public BackendImplementation() {
//        songCollection = new SortedCollection<>();
//    }


    @Override
    public void readData(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Skip the header row
            br.readLine();

            String line;
            // Read each line from the CSV file
            while ((line = br.readLine()) != null) {
                // Split the line into an array of values
                String[] values = line.split(",");
                // Assuming CSV format matches SongInterface fields
                String title = values[0];
                String artist = values[1];
                String genre = values[2];
                int year;
                int bpm;
                int energy;
                int danceability;
                int dB;
                int live;
                int val;
                int duration;
                int acousticness;
                int speechiness;
                int popularity;
                try {
                    year = Integer.parseInt(values[3]);
                    bpm = Integer.parseInt(values[4]);
                    energy = Integer.parseInt(values[5]);
                    danceability = Integer.parseInt(values[6]);
                    dB = Integer.parseInt(values[7]);
                    live = Integer.parseInt(values[8]);
                    val = Integer.parseInt(values[9]);
                    duration = Integer.parseInt(values[10]);
                    acousticness = Integer.parseInt(values[11]);
                    speechiness = Integer.parseInt(values[12]);
                    popularity = Integer.parseInt(values[13]);
                } catch (NumberFormatException e) {
                    // Handle non-numeric values here (e.g., provide default values or skip the record)
                    System.out.println("Skipping record due to non-numeric values in one or more fields.");
                    continue; // Skip this record and move to the next one
                }

                // Create a new Song object and add it to the songCollection
                SongInterface song = new Song(title, artist, genre, year, bpm, energy, danceability, dB, live, val, duration, acousticness, speechiness, popularity);
                songCollection.add(song);   //--> fix later (need to implement other interface methods before)
                //System.out.println(title + artist + genre + year + bpm + energy + danceability + dB + live + val + duration + acousticness + speechiness + popularity);

            }
        }
    }

    @Override
    public List<String> getRange(int low, int high) {
        List<String> titlesInRange = new ArrayList<>();
        for (SongInterface song : songCollection) {
            int danceability = song.getDanceability();
            if (danceability >= low && danceability <= high) {
                titlesInRange.add(song.getTitle());
            }
        }
        getRangeCalled = true;
        return titlesInRange;
    }

    @Override
    public List<String> filterEnergeticSongs(int minEnergy) {
        if (!getRangeCalled) {
            System.out.println("Cannot filter energetic songs without calling getRange first.");
        }
        List<String> energeticSongs = new ArrayList<>();
        for (SongInterface song : songCollection) {
            if (song.getEnergy() >= minEnergy) {
                energeticSongs.add(song.getTitle());
            }
        }
        return energeticSongs;
    }

    @Override
    public List<String> fiveFastest() {
        List<String> fastestSongs = new ArrayList<>();

        if(!getRangeCalled){
            throw new IllegalStateException("Cannot output the five fastest songs without a call to getRange() first.");
        }
        // Maintain a priority queue to keep track of the top 5 fastest songs
        PriorityQueue<SongInterface> fastestQueue = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.getBPM(), s2.getBPM()));

        // Iterate through the song collection
        for (SongInterface song : songCollection) {
            // Add the current song to the priority queue
            fastestQueue.offer(song);

            // If the queue size exceeds 5, remove the slowest song
            if (fastestQueue.size() > 5) {
                fastestQueue.poll();
            }
        }

        // Retrieve the top 5 fastest songs from the priority queue
        while (!fastestQueue.isEmpty()) {
            SongInterface song = fastestQueue.poll();
            fastestSongs.add(song.getBPM() + ": " + song.getTitle());
        }

        // Reverse the list to maintain the original order of the fastest songs
        Collections.reverse(fastestSongs);

        return fastestSongs;
    }
}

