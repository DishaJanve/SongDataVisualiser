package MiniProGui;

import java.util.*;
public class PartyMode {
	    PriorityQueue<SongQueueItem> partyQueue;

	    public PartyMode() {
	        // PriorityQueue with default comparator (using compareTo in SongQueueItem)
	        this.partyQueue = new PriorityQueue<>();
	    }

	    // Method to add a song to the queue
	    public void addSongToQueue(Song song, UserDetails user) {
	        SongQueueItem item = new SongQueueItem(song, user);
	        partyQueue.add(item);
	        System.out.println(user.username + " added song: " + song.name + " to the queue.");
	    }

	    // Method to play the next song (with the highest priority)
	    public void playNextSong() {
	        if (!partyQueue.isEmpty()) {
	            SongQueueItem item = partyQueue.poll(); // Retrieves and removes the first element
	            System.out.println("Now playing: " + item.song.name + " by " + item.user.username);
	        } else {
	            System.out.println("No songs in the queue.");
	        }
	    }
	    public void displayQueue() {
	        if (partyQueue.isEmpty()) {
	            System.out.println("The queue is empty.");
	            return;
	        }

	        // Create a temporary list to sort and display the queue
	        List<SongQueueItem> tempList = new ArrayList<>(partyQueue);
	        tempList.sort(null); // Sort using the natural order (based on compareTo in SongQueueItem)

	        System.out.println("Songs in the queue:");
	        for (SongQueueItem item : tempList) {
	            System.out.println("Song: " + item.song.name + ", Added by: " + item.user.username);
	        }
	    }
	}