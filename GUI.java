package MiniProGui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionEvent;
import MiniProGui.Song;

public class GUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // Declare playlist, queue, and listeningHistory at the class level to avoid "cannot be resolved" errors.
    private List <String> playlist = new ArrayList<>(Arrays.asList("Blinding Lights", "Hallelujah", "Someone Like You"));
    private Queue<String> queue = new LinkedList<>();
    private List<String> listeningHistory = new ArrayList<>();

    private Map<String, String> userDatabase = new HashMap<>();

    public GUI() {
    	userDatabase.put("Alice123", "password");
        userDatabase.put("BobTheDog", "password");
        userDatabase.put("CATCF", "password");
        userDatabase.put("Dianasour", "password");
        userDatabase.put("ChristmasEVE", "password");

        frame = new JFrame("Spotify Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#264653"), 0, getHeight(), Color.decode("#2A9D8F"));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        frame.add(panel);

        JLabel musicSymbol = new JLabel("\u266B \u266C \u266A");
        musicSymbol.setFont(new Font("Serif", Font.BOLD, 30));
        musicSymbol.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(musicSymbol, gbc);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(usernameField, gbc);
     
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        loginButton.setBackground(Color.decode("#2A9D8F"));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                JOptionPane.showMessageDialog(frame, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                openMainApp();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private void openMainApp() {
        frame.dispose();

        JFrame mainAppFrame = new JFrame("Spotify Music Visualizer");
        mainAppFrame.setSize(600, 400);
        mainAppFrame.setLocationRelativeTo(null);
        mainAppFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = createGradientPanel(); // This will apply the gradient background
        mainPanel.setLayout(new GridBagLayout()); // Using GridBagLayout to center the buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Set padding around buttons
        gbc.anchor = GridBagConstraints.CENTER; // Center the buttons

        // Create buttons and add them to the panel
        JButton remoteModeButton = createStyledButton("Remote Mode");
        JButton partyModeButton = createStyledButton("Party Mode");

        remoteModeButton.addActionListener(e -> openRemoteMode(mainAppFrame));
        partyModeButton.addActionListener(e -> openPartyMode(mainAppFrame));

        mainPanel.add(remoteModeButton, gbc);

        gbc.gridy = 1; // Move to next row
        mainPanel.add(partyModeButton, gbc);

        JButton logoutButton = createStyledButton("Log Out");
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainAppFrame, "Logging Out...");
            mainAppFrame.dispose();
            new GUI();
        });

        gbc.gridy = 2; // Move to next row
        mainPanel.add(logoutButton, gbc);

        mainAppFrame.add(mainPanel);
        mainAppFrame.setVisible(true);
    }


    private JPanel createGradientPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#264653"), 0, getHeight(), Color.decode("#2A9D8F"));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }

    private void openRemoteMode(JFrame mainFrame) {
        mainFrame.dispose();

        JFrame remoteFrame = new JFrame("Remote Mode");
        remoteFrame.setSize(600, 400);
        remoteFrame.setLocationRelativeTo(null);
        remoteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = createGradientPanel(); // Use the same gradient background here
        panel.setLayout(new GridBagLayout());  // Using GridBagLayout for centering components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;    // Set the components to be centered
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        
        // Create and add buttons to the panel
        JButton viewButton = createStyledButton("View Playlist");
        JButton searchButton = createStyledButton("Search & Add");
        JButton removeButton = createStyledButton("Remove Song");

        viewButton.addActionListener(e -> {
            // Display the updated playlist with the added songs
            StringBuilder playlistDisplay = new StringBuilder("Playlist:\n");
            if (playlist.isEmpty()) {
                playlistDisplay.append("No songs in your playlist.");
            } else {
                for (String song : playlist) {
                    playlistDisplay.append("- ").append(song).append("\n");
                }
            }
            JOptionPane.showMessageDialog(remoteFrame, playlistDisplay.toString());
        });

        searchButton.addActionListener(e -> openSearchOptions(remoteFrame));
        removeButton.addActionListener(e -> removeSong(remoteFrame));

        JButton exitButton = createStyledButton("Exit Remote Mode");
        exitButton.addActionListener(e -> {
            remoteFrame.dispose();
            openMainApp();
        });

        // Add buttons to the panel and center them vertically and horizontally
        gbc.gridy = 0;
        panel.add(viewButton, gbc);
        gbc.gridy = 1;
        panel.add(searchButton, gbc);
        gbc.gridy = 2;
        panel.add(removeButton, gbc);
        gbc.gridy = 3;
        panel.add(exitButton, gbc);

        remoteFrame.add(panel);
        remoteFrame.setVisible(true);
    }


    private void openPartyMode(JFrame mainFrame) {
        mainFrame.dispose();

        JFrame partyFrame = new JFrame("Party Mode");
        partyFrame.setSize(600, 400);
        partyFrame.setLocationRelativeTo(null);
        partyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = createGradientPanel(); // Use the same gradient background here
        panel.setLayout(new GridBagLayout());  // Using GridBagLayout for centering components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;    // Set the components to be centered
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        
        // Create and add buttons to the panel
        JButton searchButton = createStyledButton("Search & Add to Queue");
        JButton playNextButton = createStyledButton("Play Next Song in Queue");
        JButton viewQueueButton = createStyledButton("View Queue");

        searchButton.addActionListener(e -> {
            String song = JOptionPane.showInputDialog(partyFrame, "Enter song to add to queue:");
            if (song != null && !song.isEmpty()) {
                queue.add(song);
                JOptionPane.showMessageDialog(partyFrame, "Song added to queue: " + song);
            }
        });

        playNextButton.addActionListener(e -> {
            String nextSong = queue.poll();
            if (nextSong != null) {
                JOptionPane.showMessageDialog(partyFrame, "Playing next song: " + nextSong);
            } else {
                JOptionPane.showMessageDialog(partyFrame, "Queue is empty!");
            }
        });

        viewQueueButton.addActionListener(e -> JOptionPane.showMessageDialog(partyFrame, "Queue: " + queue));

        JButton exitButton = createStyledButton("Exit Party Mode");
        exitButton.addActionListener(e -> {
            partyFrame.dispose();
            openMainApp();
        });

        // Add buttons to the panel and center them vertically and horizontally
        gbc.gridy = 0;
        panel.add(searchButton, gbc);
        gbc.gridy = 1;
        panel.add(playNextButton, gbc);
        gbc.gridy = 2;
        panel.add(viewQueueButton, gbc);
        gbc.gridy = 3;
        panel.add(exitButton, gbc);

        partyFrame.add(panel);
        partyFrame.setVisible(true);
    }


    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.decode("#2A9D8F"));
        button.setForeground(Color.WHITE);
        return button;
    }

    private void removeSong(JFrame frame) {
        String songToRemove = JOptionPane.showInputDialog(frame, "Enter the song name to remove:");
        if (songToRemove != null && !songToRemove.isEmpty()) {
            // Case-insensitive removal based only on song name
            boolean songFound = false;
            Iterator<String> iterator = playlist.iterator();
            while (iterator.hasNext()) {
                String song = iterator.next();
                if (song.equalsIgnoreCase(songToRemove)) {
                    iterator.remove();  // Remove the song from the playlist
                    songFound = true;
                    JOptionPane.showMessageDialog(frame, songToRemove + " has been removed from your playlist.");
                    break;
                }
            }

            if (!songFound) {
                JOptionPane.showMessageDialog(frame, "Song not found in playlist.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a valid song name.");
        }
    }

    private void openSearchOptions(JFrame frame) {
        String songToSearch = JOptionPane.showInputDialog(frame, "Enter song name to add to playlist:");
        if (songToSearch != null && !songToSearch.isEmpty()) {
            // Case-insensitive check for song existence
            boolean songExists = false;
            for (String existingSong : playlist) {
                if (existingSong.equalsIgnoreCase(songToSearch)) {
                    songExists = true;
                    break;
                }
            }

            if (!songExists) {
                playlist.add(songToSearch); // Add the song to the playlist
                listeningHistory.add(songToSearch); // Add the song to the listening history
                JOptionPane.showMessageDialog(frame, songToSearch + " has been added to your playlist and history!");
            } else {
                JOptionPane.showMessageDialog(frame, songToSearch + " is already in the playlist.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a valid song name.");
        }

        // Create a new frame for additional options
        JFrame searchFrame = new JFrame("Search Options");
        searchFrame.setSize(400, 300);
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a gradient panel for the background
        JPanel panel = createGradientPanel(); 
        panel.setLayout(new GridBagLayout()); // Center buttons using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Padding around buttons
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment

        // Create buttons for Shuffle Playlist and View Recently Played Songs
        JButton shuffleButton = createStyledButton("Shuffle Playlist");
        JButton historyButton = createStyledButton("View Recently Played Songs");

        // Add shuffle functionality
        shuffleButton.addActionListener(e -> {
            List<String> shuffledPlaylist = new ArrayList<>(playlist);
            Collections.shuffle(shuffledPlaylist);
            JOptionPane.showMessageDialog(searchFrame, "Shuffled Playlist: " + shuffledPlaylist);
        });

        // Add view history functionality
        historyButton.addActionListener(e -> 
            JOptionPane.showMessageDialog(searchFrame, "Recently Played Songs: " + listeningHistory)
        );

        // Add buttons to the panel
        panel.add(shuffleButton, gbc);

        gbc.gridy = 1; // Move to the next row
        panel.add(historyButton, gbc);

        // Add the panel to the frame and make it visible
        searchFrame.add(panel);
        searchFrame.setVisible(true);
    }





    public static void main(String[] args) {
        new GUI();
    }
}
