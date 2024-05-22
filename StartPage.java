import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.TreeSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class StartPage extends JFrame {
    private JTextField nameField;
    private JButton startButton;
    private JPanel contentPane;
    private Color BG = new Color(71, 71, 107);
    private UserList userList = new UserList();
    private DefaultListModel<String> highScoreListModel;
    private JList<String> highScoreList;

    public StartPage(UserList userList) {
        this.userList = userList;
        showUserListDialog();
        initializeUI();

    }

    private void showUserListDialog() {
        highScoreListModel = new DefaultListModel<>();

        TreeSet<Integer> scoresSet = new TreeSet<>(Comparator.reverseOrder());

        for (User user : userList) {
            scoresSet.add(user.getHighestScore());
        }

        for (Integer score : scoresSet) {
            for (User user : userList) {
                if (user.getHighestScore() == score) {
                    highScoreListModel.addElement(user.getName() + " - " + score + "\n");
                }
            }
        }

        highScoreList = new JList<>(highScoreListModel);
        highScoreList.setFont(new Font("Arial", Font.PLAIN, 16));
        highScoreList.setBackground(BG);
        highScoreList.setForeground(Color.WHITE);
    }

    public void initializeUI() {
        setTitle("Welcome to Memory Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 670);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(120, 50, 110, 50));
        contentPane.setBackground(BG);
        setContentPane(contentPane);

        JLabel nameLabel = new JLabel("MEMORY GAME");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(nameLabel);

        contentPane.add(Box.createVerticalGlue());

        nameField = new JTextField(30);
        nameField.setMaximumSize(new Dimension(300, 40));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameField.setText("Enter your name");
        nameField.setEnabled(false);

        nameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameField.setEnabled(true);

                nameField.setText("");
                nameField.setForeground(Color.BLACK);

            }
        });

        contentPane.add(nameField);

        contentPane.add(Box.createVerticalGlue());

        startButton = new JButton("Enter Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(this::startGame);
        startButton.setBackground(new Color(0, 128, 0));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        startButton.setFocusPainted(false);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(0, 180, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(0, 128, 0));
            }
        });

        contentPane.add(startButton);

        JScrollPane scrollPane = new JScrollPane(highScoreList);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        scrollPane.setBorder(null);

        contentPane.add(scrollPane);

        highScoreList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                        String selectedUser = highScoreList.getSelectedValue().split(" - ")[0];
                        startGame(selectedUser);
                }
            }
        });

    }

    StartPage() {
        setTitle("Welcome to Memory Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 670);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(200, 50, 310, 50));
        contentPane.setBackground(BG);
        setContentPane(contentPane);

        JLabel nameLabel = new JLabel("MEMORY GAME");

        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(nameLabel);


        nameField = new JTextField(30);
        nameField.setMaximumSize(new Dimension(300, 40));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lableName = new JLabel("Enter your name");
        lableName.setMaximumSize(new Dimension(300, 40));
        lableName.setAlignmentX(Component.CENTER_ALIGNMENT);
        lableName.setForeground(Color.gray); 

        contentPane.add(lableName);


        nameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameField.setEnabled(true);

                nameField.setText("");
                nameField.setForeground(Color.BLACK);

            }
        });

        contentPane.add(nameField);

        contentPane.add(Box.createVerticalGlue());

        startButton = new JButton("Enter Game");
        contentPane.add(Box.createVerticalGlue());
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(this::startGame);
        startButton.setBackground(new Color(0, 128, 0));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        startButton.setFocusPainted(false);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(0, 180, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                
                startButton.setBackground(new Color(0, 128, 0));
            }
        });

        contentPane.add(startButton);
    }

    private void startGame(ActionEvent e) {
        String playerName = nameField.getText().trim();
        if (!playerName.matches("[A-Z][a-zA-Z]{3,10}")) {
            try {
                throw new IllegalArgumentException(
                        "The name must be between 3 and 10 characters long and start with an uppercase letter.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Name", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            dispose();
            new game(playerName, userList).setVisible(true);
        }
    }

    private void startGame(String name) {

        dispose();
        new game(name, userList).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartPage().setVisible(true));
    }
}
