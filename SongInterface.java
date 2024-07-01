public interface SongInterface extends Comparable<SongInterface> {

    String getTitle(); // returns this song's title

    String getArtist(); // returns this song's artist

    String getGenres(); // returns string containing each of this song's genres

    int getYear(); // returns this song's year in the Billboard
    
    int getBPM(); // returns this song's speed/tempo in beats per minute
    
    int getEnergy(); // returns this song's energy rating 
    
    int getDanceability(); // returns this song's danceability rating
    
    int getLoudness(); // returns this song's loudness in dB
    
    int getLiveness(); // returns this song's liveness rating

    int getValence(); //returns this song's valence

    int getDuration(); //returns this song's duration

    int getAcousticness(); //returns this song's acousticness rating

    int getSpeechiness(); // returns this song's speechyness rating

    int getPopularity(); //returns this song's popularity rating
}
