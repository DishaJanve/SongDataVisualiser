package MiniProGui;

import java.util.*;
public class SongQueueItem implements Comparable<SongQueueItem> {
    Song song;
    UserDetails user;
    public SongQueueItem(Song song, UserDetails user) {
        this.song = song;
        this.user = user;
    }

    // Compare based on user priority
    @Override
    public int compareTo(SongQueueItem other) {
        if (this.user.isPremium() && !other.user.isPremium()) {
            return -1; // Premium users have higher priority
        } else if (!this.user.isPremium() && other.user.isPremium()) {
            return 1;
        } else {
            return 0; // Same priority for both regular and premium
        }
    }
}