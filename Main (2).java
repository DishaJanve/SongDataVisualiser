package MiniProGui;

import java.util.*;
import java.sql.*;
public class Main {
	public static void main(String[] args)throws ClassNotFoundException, SQLException {
		Scanner sc=new Scanner(System.in);
		String songName,SongName,username;
		Song foundSong;
		HashMap<String,Song> Songs=SongDatabase.getSongs();
		Playlist playlist=new Playlist();
		PartyMode partyMode = new PartyMode();
		User obj=new User();
		UserDetails user;
		//PartyMode partyMode = new PartyMode();
		Map<String,List<String>> genreToSongs=SongDatabase.getgenretosongs();
		int ch,ch2;
		System.out.println("Menu:");	
		System.out.println("1. Remote Mode");
		System.out.println("2. Party Mode");
		System.out.println("Enter your choice");
		ch=sc.nextInt();
		sc.nextLine();
		switch(ch) {
		case 1:do {
			System.out.println("Enter your Username");
			username=sc.nextLine();
			user=obj.getUserByUsername(username);
			System.out.println("Menu:");
			System.out.println("1. View Playlist");
			System.out.println("2. Search Songs");
			System.out.println("3. Remove a song from Playlist");
			System.out.println("4. To Exit");
			ch2=sc.nextInt();sc.nextLine();
			switch(ch2) {
			case 1:
				obj.displayUserPlaylist(username);
				break;
			case 2:
				System.out.println("Enter the song name to be searched:");
	            songName = sc.nextLine();
	            SongName=songName.toUpperCase();
	            if (Songs.containsKey(SongName)) {
	            	foundSong = Songs.get(SongName);
	                System.out.println("Song found: " + foundSong);
	                System.out.println("What would you like to do?");
	                System.out.println("1. Play the song");
	                System.out.println("2. Add the song to the playlist");
	                System.out.println("3. Play Playlist in shuffle mode");
	                System.out.println("4. View Listening History");
	                int action = sc.nextInt();
	                sc.nextLine(); // Consume the newline
	                switch(action) {
	                case 1:
	                	System.out.println("Now playing: " + foundSong.name);
	                    user.ListeningHistory.push(foundSong);
	                    break;
	                case 2:
	                	 user.playlist.addSongtoplaylist(foundSong);
		                    playlist.addSongtoplaylist(foundSong);
		                    System.out.println("Song added to your playlist: " + foundSong.name);
		                    System.out.println();
		                    System.out.println("Other Songs you might like:");
		                    // Recommend songs based on genre
		                    playlist.recommendSongsBasedOnLast(Songs, genreToSongs);
		                    break;
	                case 3:
	                	user.playlist.shuffleplaylist();
	                	break;
	                case 4:
	                	user.displayListeningHistory();
	                } 
	        }
			break;
			case 3:
				System.out.println("Enter the song to be removed");
            	String songname=sc.nextLine();
            	user.playlist.deleteSongfromplaylist(songname);
            	break;
			}	
		}while(ch2!=3);
			break;
		case 2:
				int choice;
				do{
				System.out.println("1. Search and Add a Song to Queue.");
				System.out.println("2. Play Next Song from Queue.");
				System.out.println("3. To View Queue.");
				System.out.println("4. To Exit.");
				System.out.println("Enter Choice");
				choice=sc.nextInt();
				sc.nextLine();
				switch(choice) {
				case 1:System.out.println("Enter the song name to be searched: ");
	            songName = sc.nextLine();
	            SongName=songName.toUpperCase();
	            if (Songs.containsKey(SongName)) {
	            	foundSong = Songs.get(SongName);
	            	System.out.println("Enter your username: ");
	                username = sc.nextLine();
	                user=obj.getUserByUsername(username);
	                partyMode.addSongToQueue(foundSong, user);
	            }
	            else
	                System.out.println("Sorry! We couldn't find the song that you were lookig for.");
				break;
				case 2:
					partyMode.playNextSong();
					break;
				case 3:
					partyMode.displayQueue();
					break;
				}
			}while(choice!=4);

			break;
		}}
}