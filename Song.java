package MiniProGui;

public class Song {
	    String name;
	    String duration;  // duration in seconds
	    String genre;
	    String artist;
	    String album;
	    int tempo;
	    String mood;

	    public Song(String name, String duration, String genre, String artist, String album, int tempo, String mood) {
	        this.name = name;
	        this.duration = duration;
	        this.genre = genre;
	        this.artist = artist;
	        this.album = album;
	        this.tempo=tempo;
	        this.mood=mood;
	    }

	    @Override
	    public String toString() {
	        return name + " by " + artist + " from album " + album + " [" + genre + "]";
	    }
	    String getGenre() {
	    	return genre;
	    }
	    String getName() {
	    	return name;
	    }
	}