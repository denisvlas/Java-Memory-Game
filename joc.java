import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

// Clasa de baza abstracta pentru jocurile de memorie
abstract class jocBase extends JFrame {

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

    public jocBase() {
        setTitle("Memory Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 670);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        contentPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        contentPane.setBackground(BG);
        buttonPanel.setBackground(BG);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(this::goBack);

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

        

        JPanel backButtonPanel = new JPanel(new BorderLayout());
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
        // topPanel.add(scoreLabel, BorderLayout.NORTH);

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
   private void goBack(ActionEvent e) {
    // User currentUser = userList.stream().filter(user -> user.name.equals(playerName)).findFirst().orElse(null);
    // if (currentUser != null) {
    //     currentUser.highestScore = Math.max(currentUser.highestScore, score);
    // }

        dispose(); // Închide fereastra de joc
        new StartPage().setVisible(true); // Deschide pagina de start
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

public class joc extends jocBase {

    public joc(String playerName) {
        super();
        // setTitle("Memory Game");
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setSize(600, 670);
        setLocationRelativeTo(null); // Centrare pe ecran

        // Adăugăm un buton "Back" în partea de sus a paginii de joc
        
    }
 
    @Override
    public void initiateGame() {
        contentPane.remove(buttonPanel);

        setContentPane(contentPane);
        
        buttons.clear(); 
        pattern.clear();

        buttonPanel.removeAll();
        
        level++; 
        levelLabel.setText("Level: " + level);
        if(level>1){

        initialRows = initialRows + 1;
        initialCols = initialCols + 1;}

        buttonPanel.setLayout(new GridLayout(initialRows, initialCols));
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        turn = 1;
        counter = 0;
        health=3;
        remainingAttemptsLabel.setText("HP: " + health);
        remainingAttemptsLabel.setForeground(Color.WHITE);

        for (int i = 0; i < initialRows * initialCols; i++) {
            JButton button = new JButton(" ");
            button.setName("button" + (i+1)); 
            button.setBackground(Color.GRAY);
            button.addActionListener(this::buttonClicked);
            buttons.add(button);
            buttonPanel.add(button);
            button.setFocusPainted(false); 
            button.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
         
            }

        revalidate();
        repaint();
       
    }

    @Override
    protected void showPattern(ActionEvent e) {
        counter=0;
        showPattern();
    }

    @Override
    protected void generatePattern() {
        
        while (pattern.size() < turn) {
            JButton randomButton = buttons.get(random.nextInt((initialCols)*(initialRows)));
            if (!pattern.contains(randomButton)) {
                pattern.add(randomButton);
            }
        }
    }

    @Override
    protected void startGame(ActionEvent e) {
        
        if (startButton.getText().equals("Restart level")){
            score=precedentScore;
            scoreLabel.setText("Score: "+score);
        }

        startButton.setText("Restart level");
        pattern.clear();
        counter = 0;
        turn = 1;
        health = 3;
        remainingAttemptsLabel.setForeground(Color.WHITE);
        remainingAttemptsLabel.setText("HP: "+health);

        levelLabel.setText("Level: " + level);
        playerTurn = false;
        generatePattern();
        showPattern();
    }

    @Override
    protected void buttonClicked(ActionEvent e) {
        if (!playerTurn) return;
    
        JButton button = (JButton) e.getSource();
        String buttonName = button.getName();
        if (buttonName.equals(pattern.get(counter).getName())) {
            button.setBackground(new Color(255-level*30, 255, 0));
            counter++;
            text.setText((counter) + "/" + turn);
            text.setForeground(new Color(0, 128, 0));
            if (counter == turn) {
                score=score+level*10+counter-1;
                scoreLabel.setText("Score: "+score);
                highestScore=Math.max(highestScore,score);
                highestScorelavLabel.setText("BEST: "+highestScore);
                if (health<3){
                health++;
                remainingAttemptsLabel.setText("HP: " + health);
                if(health==3)
                remainingAttemptsLabel.setForeground(Color.WHITE);
                    
            }

                nextTurn();
            }
        } else {
            button.setBackground(Color.RED);
            button.setText("x");
            button.setFont( new Font("Arial", Font.BOLD, 22));
            
            text.setText("Wrong");
            text.setForeground(Color.RED);
            resetButtonColors();
            counter = 0;
            health--;
            remainingAttemptsLabel.setText("HP: " + health);
            remainingAttemptsLabel.setForeground(new Color(166, 23, 0));
    
            if (health ==0) {
                resetGame();
            }
        }
    }

    @Override
    protected void resetButtonColors() {
        playerTurn = false;
        Timer timer = new Timer(500, e -> {
            for (JButton button : buttons) {
                button.setBackground(Color.gray);
                button.setText("");

            }
            showPattern(); 
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    protected void showPattern() {
        playerTurn = false;
        Timer timer = new Timer(800, e -> {
            for (int i = 0; i < pattern.size(); i++) {
                JButton button =  pattern.get(i);
                if (button != null) {
                    button.setBackground(new Color(255, 255-level*30, 0));
                    Timer colorTimer = new Timer(500, e1 -> {
                        button.setBackground(Color.GRAY); 
                        revalidate(); 
                    });
                    colorTimer.setRepeats(false);
                    colorTimer.start();
                }
            }
            text.setText((counter) + "/" + turn);
            text.setForeground(new Color(0, 128, 0));
            text.setFont(FONT);
            playerTurn = true;
    
        });
        timer.setRepeats(false);
        timer.start();
    }


    @Override
    protected void nextTurn() {
        counter = 0; 
        turn++; 
        
        if (turn > initialCols*initialRows) { 
            
            precedentScore=score;
            initiateGame();
            generatePattern();
            showPattern();

        }
        else {

        generatePattern();
        showPattern();
        
        }
    }

    @Override
    protected void resetGame() {
        level=0;
        initialCols=2;
        initialRows=2;
        score=0;
        scoreLabel.setText("Score: "+score);
        initiateGame();

        generatePattern();
        showPattern();
        remainingAttemptsLabel.setText("HP: "+health);
        remainingAttemptsLabel.setForeground(Color.WHITE);
    }
    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> new joc().setVisible(true));
    // }
}
