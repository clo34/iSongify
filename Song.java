public class Song implements SongInterface {
    private String title;
    private String artist;
    private String genres;
    private int year;
    private int bpm;
    private int energy;
    private int danceability;
    private int loudness;
    private int liveness;
    private int valence;
    private int duration;
    private int acousticness;
    private int speechiness;
    private int popularity;

    public Song(String title, String artist, String genres, int year, int bpm, int energy, int danceability, int loudness, int liveness,
                int valence, int duration, int acousticness, int speechiness, int popularity) {
        this.title = title;
        this.artist = artist;
        this.genres = genres;
        this.year = year;
        this.bpm = bpm;
        this.energy = energy;
        this.danceability = danceability;
        this.loudness = loudness;
        this.liveness = liveness;
        this.valence = valence;
        this.duration = duration;
        this.acousticness = acousticness;
        this.speechiness = speechiness;
        this.popularity = popularity;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public String getGenres() {
        return genres;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getBPM() {
        return bpm;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public int getDanceability() {
        return danceability;
    }

    @Override
    public int getLoudness() {
        return loudness;
    }

    @Override
    public int getLiveness() {
        return liveness;
    }

    @Override
    public int getValence() {
        return valence;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getAcousticness() {
        return acousticness;
    }

    @Override
    public int getSpeechiness() {
        return speechiness;
    }

    @Override
    public int getPopularity() {
        return popularity;
    }

    /*
     * Compares the titles of the songs
     */
    @Override
    public int compareTo(SongInterface other) {
        if (!(other instanceof Song)) {
            throw new ClassCastException("A Song object expected.");
        }

        // Cast the other object to a Song
        Song otherSong = (Song) other;

        // Compare based on the year
        return Integer.compare(this.getYear(), otherSong.getYear());

    }
    /*
     * Compares two Song objects based on their year.
     *
     * @param other the other Song object to compare with
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     */

}