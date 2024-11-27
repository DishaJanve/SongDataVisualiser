package MiniProGui;

import java.util.*;
import java.sql.*;
public class SongDatabase {
	static HashMap<String,Song> Songs;
    public static HashMap<String,Song> getSongs()throws ClassNotFoundException, SQLException{
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/minipro","root","root");
    	Songs=loadSongsFromDatabase(con);
    	/*System.out.println("Genre to Songs Adjacency List:");
        genreToSongs.forEach((genre, songs) -> 
            System.out.println(genre + " -> " + songs)
        );*/
    	return Songs;
    }
    public static Map<String,List<String>> getgenretosongs() {
    	Map<String, List<String>> genreToSongs = createGenreToSongsAdjList(Songs);
    	return genreToSongs;
    }
    public static HashMap<String, Song> loadSongsFromDatabase(Connection conn) {
        HashMap<String, Song> songMap = new HashMap<>();
        String query = "SELECT t.title, t.genre, t.duration, t.tempo, t.mood, a.name AS artist, al.title AS album "
                     + "FROM track t "
                     + "JOIN album al ON t.albumID = al.albumID "
                     + "JOIN artist a ON al.ArtistID = a.ArtistID";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Retrieve values from the result set
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String duration = rs.getString("duration"); // assuming duration is in seconds
                int tempo = rs.getInt("tempo");
                String mood = rs.getString("mood");
                String artist = rs.getString("artist");
                String album = rs.getString("album");

                // Create a new Song object for each track
                Song song = new Song(title, duration, genre, artist, album, tempo, mood);

                // Convert song title to uppercase and use it as the key in the HashMap
                songMap.put(title.toUpperCase(), song); // Storing in HashMap with uppercase title as key
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songMap; // Return the HashMap of songs
    }
    public static Map<String, List<String>> createGenreToSongsAdjList(HashMap<String, Song> songMap) {
        Map<String, List<String>> genreToSongs = new HashMap<>();

        // Iterate through the songMap
        for (Song song : songMap.values()) {
            String genre = song.getGenre(); // Get the genre of the song
            String title = song.getName(); // Get the title of the song

            // Add the song title to the genre's list
            genreToSongs.putIfAbsent(genre, new ArrayList<>()); // Initialize the list if not present
            genreToSongs.get(genre).add(title); // Add the song title to the list
        }

        return genreToSongs; // Return the adjacency list
    }
}