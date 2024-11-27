package MiniProGui;

import java.util.*;
class Node {
    Song song;
    Node next;
    Node prev;

    public Node(Song song) {
        this.song = song;
        this.next = this; // Points to itself (Circular)
        this.prev = this; // Points to itself (Circular)
    }
}
public class Playlist {
    Node head;
    int size;
    public Playlist() {
        head = null;
        size = 0;
    }

    // Add a song to the playlist
    public void addSongtoplaylist(Song song) {
        Node newNode = new Node(song);

        if (head == null) {
            head = newNode;
        } else {
            Node lastNode = head.prev;
            lastNode.next = newNode;
            newNode.prev = lastNode;
            newNode.next = head;
            head.prev = newNode;
        }
        size++;
    }

    // Delete a song from the playlist
    public void deleteSongfromplaylist(String songName) {
        if (head == null) {
            System.out.println("The playlist is empty.");
            return;
        }

        Node current = head;
        do {
            if (current.song.name.equals(songName)) {
                // If the node to be deleted is the only node in the list
                if (size == 1) {
                    head = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    if (current == head) {
                        head = current.next; // If deleting the head, move head to the next node
                    }
                }
                size--;
                System.out.println("Song removed from playlist: " + songName);
                return;
            }
            current = current.next;
        } while (current != head);

        System.out.println("Song not found: " + songName);
    }
    
    public void shuffleplaylist() {
        if (size <= 1) return;

        // Step 1: Store the nodes in an array
        Node[] nodes = new Node[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            nodes[i] = current;
            current = current.next;
        }

        // Step 2: Shuffle the array of nodes using Fisher-Yates algorithm
        Random random = new Random();
        for (int i = size - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
        }

        // Step 3: Rebuild the DCLL with shuffled nodes
        head = nodes[0]; // The first node becomes the new head
        nodes[0].prev = nodes[size - 1]; // Link first node's prev to the last node
        nodes[size - 1].next = nodes[0]; // Link last node's next to the first node

        // Now, update the next and prev pointers of all other nodes
        for (int i = 1; i < size; i++) {
            nodes[i].prev = nodes[i - 1]; // Link current node's prev to the previous node
            nodes[i].next = nodes[(i + 1) % size]; // Link current node's next to the next node (circular)
        }

        // Step 4: Display the shuffled playlist
        displayPlaylist();
    }


    // Display all songs in the playlist
    public void displayPlaylist() {
        if (head == null) {
            System.out.println("The playlist is empty.");
            return;
        }

        Node current = head;
        getSize();
        do {
            System.out.println(current.song);
            current = current.next;
        } while (current != head);
    }

    // Get the size of the playlist
   public void getSize() {
       System.out.println(size+" Songs");
    }
   
// Inside the Playlist class
public void recommendSongsBasedOnLast(HashMap<String, Song> allSongs, Map<String, List<String>> genreToSongs) {
    // Step 1: Get the genre of the last inserted song
    String lastInsertedGenre = head.prev.song.genre;

    // Step 2: Fetch songs of the same genre from the adjacency list
    List<String> songsOfSameGenre = genreToSongs.getOrDefault(lastInsertedGenre, new ArrayList<>());
    // Step 3: Filter out songs already in the playlist
    List<Song> recommendations = new ArrayList<>();
    Node current = head;
    Set<String> playlistSongNames = new HashSet<>(); // To track songs in the playlist
    do {
        playlistSongNames.add(current.song.name.toUpperCase());
        current = current.next;
    } while (current != head);

    for (String songName : songsOfSameGenre) {
        if (!playlistSongNames.contains(songName.toUpperCase())) {
            recommendations.add(allSongs.get(songName.toUpperCase()));
        }
    }

    // Step 4: Return recommendations
    if (recommendations.isEmpty()) {
        System.out.println("No new recommendations available for the genre: " + lastInsertedGenre);
    } else {
        for (Song song : recommendations) {
            System.out.println(song);
        }
    }
}
}