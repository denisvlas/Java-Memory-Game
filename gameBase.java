import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

abstract class gameBase extends JFrame {

    protected static final Random random = new Random();
    protected ArrayList<JButton> pattern = new ArrayList<>();
    protected ArrayList<JButton> buttons = new ArrayList<>();

    protected int counter = 0;
    protected int turn = 1;
    protected int level = 0;
    protected int health = 3;

    protected boolean playerTurn = false;

    protected int initialRows = 2;
    protected int initialCols = 2;
    protected int score = 0;
    protected JLabel scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
    protected JLabel text;
    protected JLabel levelLabel;
    protected JLabel remainingAttemptsLabel = new JLabel("HP: " + health, SwingConstants.RIGHT);
    protected Font FONT = new Font("Arial", Font.BOLD, 16);

    protected JPanel buttonPanel = new JPanel(new GridLayout(initialRows, initialCols, 10, 10));
    protected JPanel contentPane = new JPanel(new BorderLayout());

    protected Color BG = new Color(71, 71, 107);
    protected JPanel topPanel = new JPanel(new BorderLayout(0, 20));

    protected JPanel controlPanel = new JPanel(new BorderLayout(10, 10));

    protected JButton startButton = new JButton("Start Game");
    protected JButton showPattern = new JButton("Show Pattern");
    protected int precedentScore = 0;
    protected int highestScore = 0;
    protected JLabel highestScorelavLabel = new JLabel("BEST: " + highestScore, SwingConstants.CENTER);
    protected JButton backButton = new JButton("< Back");

    protected UserList userList;
    protected User currentUser;
    protected String playerName;
    protected JLabel playerNameLabel;
    JPanel backButtonPanel = new JPanel(new BorderLayout());


    public gameBase() {
        setTitle("Memory Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 670);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        contentPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        contentPane.setBackground(BG);
        buttonPanel.setBackground(BG);

        backButton.addActionListener(this::startGame);
        backButton.setBackground(new Color(255, 170, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(255, 124, 122));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(255, 170, 0));
            }
        });

        backButtonPanel.add(backButton, BorderLayout.WEST);
        
        backButtonPanel.add(highestScorelavLabel, BorderLayout.EAST);

        backButtonPanel.setBackground(BG);

        topPanel.add(backButtonPanel, BorderLayout.NORTH);

        remainingAttemptsLabel.setForeground(Color.WHITE);
        remainingAttemptsLabel.setFont(FONT);
        topPanel.add(remainingAttemptsLabel, BorderLayout.EAST);

        highestScorelavLabel.setForeground(Color.WHITE);
        highestScorelavLabel.setFont(FONT);
        topPanel.add(scoreLabel, BorderLayout.CENTER);

        levelLabel = new JLabel("Level: " + (level + 1));
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(FONT);

        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 22));

        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(FONT);

        text = new JLabel();

        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setFont(FONT);
        topPanel.add(text, BorderLayout.SOUTH);

        topPanel.add(levelLabel, BorderLayout.WEST);

        contentPane.add(topPanel, BorderLayout.NORTH);

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

        controlPanel.add(startButton, BorderLayout.NORTH);

        showPattern.addActionListener(this::showPattern);
        showPattern.setBackground(new Color(0, 128, 0));
        showPattern.setForeground(Color.WHITE);
        showPattern.setFont(new Font("Arial", Font.BOLD, 18));
        showPattern.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        showPattern.setFocusPainted(false);
        showPattern.setCursor(new Cursor(Cursor.HAND_CURSOR));

        showPattern.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showPattern.setBackground(new Color(0, 180, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                showPattern.setBackground(new Color(0, 128, 0));
            }
        });

        controlPanel.add(showPattern, BorderLayout.SOUTH);

        controlPanel.setBackground(new Color(71, 71, 107));
        topPanel.setBackground(new Color(71, 71, 107));

        contentPane.add(controlPanel, BorderLayout.SOUTH);
        initiateGame();
    }

    protected abstract void initiateGame();

    protected abstract void showPattern(ActionEvent e);

    protected abstract void showPattern();

    protected abstract void generatePattern();

    protected abstract void startGame(ActionEvent e);

    protected abstract void buttonClicked(ActionEvent e);

    protected abstract void resetButtonColors();

    protected abstract void nextTurn();

    protected abstract void resetGame();

}
