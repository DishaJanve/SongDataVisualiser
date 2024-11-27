package MiniProGui;

import java.util.*;
public class User {
	UserDetails[] users;
	User() {
		users = new UserDetails[5];
        // Hardcoding the details of 5 users
        users[0] = new UserDetails("Alice123", "password", true);
        users[1] = new UserDetails("BobTheDog", "password", false);
        users[2] = new UserDetails("CATCF", "password", true);
        users[3] = new UserDetails("Dianasour", "password", false);
        users[4] = new UserDetails("ChristmasEVE", "password", true);
        Song song1 = new Song("Shape of You", "240", "Pop", "Ed Sheeran", "Divide", 96, "Happy");
        Song song2 = new Song("Blinding Lights", "200", "Synthwave", "The Weeknd", "After Hours", 88, "Energetic");
        Song song3 = new Song("Hallelujah", "300", "Classical", "Leonard Cohen", "Various Positions", 60, "Peaceful");
        Song song4 = new Song("Hotel California", "390", "Rock", "Eagles", "Hotel California", 75, "Nostalgic");
        Song song5 = new Song("Someone Like You", "285", "Pop", "Adele", "21", 70, "Sad");
        Song song6 = new Song("Closer", "250", "EDM", "Chainsmokers", "Collage", 120, "Romantic");
        Song song7 = new Song("Perfect", "263", "Pop", "Ed Sheeran", "Divide", 95, "Romantic");
        Song song8 = new Song("Memories", "195", "Pop", "Maroon 5", "Jordi", 80, "Melancholy");
        Playlist playlist1 = new Playlist();
        playlist1.addSongtoplaylist(song1);
        playlist1.addSongtoplaylist(song2);

        Playlist playlist2 = new Playlist();
        playlist2.addSongtoplaylist(song3);
        playlist2.addSongtoplaylist(song4);

        Playlist playlist3 = new Playlist();
        playlist3.addSongtoplaylist(song5);
        playlist3.addSongtoplaylist(song6);

        Playlist playlist4 = new Playlist();
        playlist4.addSongtoplaylist(song7);
        playlist4.addSongtoplaylist(song8);

        Playlist playlist5 = new Playlist();
        playlist5.addSongtoplaylist(song1);
        playlist5.addSongtoplaylist(song5);
        users[0].playlist = playlist1;
        users[1].playlist = playlist2;
        users[2].playlist = playlist3;
        users[3].playlist = playlist4;
        users[4].playlist = playlist5;
        users[0].ListeningHistory.push(song2);
        users[0].ListeningHistory.push(song3);

        users[1].ListeningHistory.push(song4);
        users[1].ListeningHistory.push(song5);

        users[2].ListeningHistory.push(song6);
        users[2].ListeningHistory.push(song7);

        users[3].ListeningHistory.push(song8);
        users[3].ListeningHistory.push(song1);

        users[4].ListeningHistory.push(song5);
        users[4].ListeningHistory.push(song2);
	}
	
	 public UserDetails getUserByUsername(String username) {
	        for (UserDetails user : users) {
	            if (user.username.equals(username)) {
	                return user;
	            }
	        }
	        return null;
	    }
	 public void displayUserPlaylist(String username) {
		 for (UserDetails user : users) {
	            if (user.username.equals(username)) {
	               user.playlist.displayPlaylist();
	            }
	 }
}}
class UserDetails{
	String username;
	String password;
	boolean Premium;
	Playlist playlist;
	Stack<Song> ListeningHistory=new Stack<>();
	public UserDetails() {}
	public UserDetails(String username, String password, boolean isPremium) {
        this.username = username;
        this.password=password;
        this.Premium = isPremium;
    }
    public boolean isPremium() {
        return this.Premium;
    }
    public Stack<Song> getListeningHistory() {
        return this.ListeningHistory;
    }
    public void displayListeningHistory() {
        if (ListeningHistory.isEmpty()) {
            System.out.println("No songs in listening history for user: " + username);
        } else {
            System.out.println("Listening history for user: " + username);
            for (Song song : ListeningHistory) {
                System.out.println("- " + song.name + " by " + song.artist);
            }
        }
    }
}