import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class game extends gameBase {

    public game(String playerName, UserList userList) {
        super();
        super.backButton.addActionListener(this::goBack);
        this.userList = userList;
        this.playerName = playerName;

        currentUser = userList.getUser(playerName);
        if (currentUser != null) {
            highestScore = currentUser.getHighestScore();
            highestScorelavLabel.setText("BEST: " + highestScore);
        } else {
            currentUser = new User(playerName, 0);
        }
        System.out.println(playerName);
        playerNameLabel = new JLabel(playerName, SwingConstants.CENTER);
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        playerNameLabel.setForeground(Color.WHITE);

        backButtonPanel.add(playerNameLabel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void goBack(ActionEvent e) {

        currentUser.setHighestScore(Math.max(currentUser.getHighestScore(), highestScore));
        userList.updateUser(currentUser);

        dispose();
        new StartPage(userList).setVisible(true);
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
        if (level > 1) {

            initialRows = initialRows + 1;
            initialCols = initialCols + 1;
        }

        buttonPanel.setLayout(new GridLayout(initialRows, initialCols));
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        turn = 1;
        counter = 0;
        health = 3;
        remainingAttemptsLabel.setText("HP: " + health);
        remainingAttemptsLabel.setForeground(Color.WHITE);

        for (int i = 0; i < initialRows * initialCols; i++) {
            JButton button = new JButton(" ");
            button.setName("button" + (i + 1));
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
        counter = 0;
        showPattern();
    }

    @Override
    protected void generatePattern() {

        while (pattern.size() < turn) {
            JButton randomButton = buttons.get(random.nextInt((initialCols) * (initialRows)));
            if (!pattern.contains(randomButton)) {
                pattern.add(randomButton);
            }
        }
    }

    @Override
    protected void startGame(ActionEvent e) {

        if (startButton.getText().equals("Restart level")) {
            score = precedentScore;
            scoreLabel.setText("Score: " + score);
            resetButtonColors();
        }

        startButton.setText("Restart level");
        pattern.clear();
        counter = 0;
        turn = 1;
        health = 3;
        remainingAttemptsLabel.setForeground(Color.WHITE);
        remainingAttemptsLabel.setText("HP: " + health);

        levelLabel.setText("Level: " + level);
        playerTurn = false;
        generatePattern();
        showPattern();
    }

    @Override
    protected void buttonClicked(ActionEvent e) {
        if (!playerTurn)
            return;

        JButton button = (JButton) e.getSource();
        String buttonName = button.getName();
        if (buttonName.equals(pattern.get(counter).getName())) {
            button.setBackground(new Color(255 - level * 30, 255, 0));
            counter++;

            button.setText("✔️");

            text.setText((counter) + "/" + turn);
            text.setForeground(new Color(0, 128, 0));
            if (counter == turn) {
                score = score + (level * 10 + (counter - 1) * 5);
                scoreLabel.setText("Score: " + score);
                highestScore = Math.max(highestScore, score);
                highestScorelavLabel.setText("BEST: " + highestScore);
                button.setText("+" + (level * 10 + (counter - 1) * 5));
                if (health < 3) {
                    health++;
                    remainingAttemptsLabel.setText("HP: " + health);
                    if (health == 3)
                        remainingAttemptsLabel.setForeground(Color.WHITE);

                }
                resetButtonColors();

                nextTurn();
            }
        } else {
            button.setBackground(Color.RED);
            button.setText("x");
            button.setFont(new Font("Arial", Font.BOLD, 22));

            text.setText("Wrong");
            text.setForeground(Color.RED);
            resetButtonColors();
            counter = 0;
            health--;
            remainingAttemptsLabel.setText("HP: " + health);
            remainingAttemptsLabel.setForeground(new Color(166, 23, 0));
            generatePattern();
            showPattern();
            if (health == 0) {
                resetGame();
            }
        }
    }

    @Override
    protected void resetButtonColors() {
        playerTurn = false;
        Timer timer = new Timer(speed, e -> {
            for (JButton button : buttons) {
                button.setBackground(Color.gray);
                button.setText("");

            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    protected void showPattern() {
        System.out.println("showPattern");
        System.out.println(speed);

        playerTurn = false;
        Timer timer = new Timer(600, e -> {
            for (int i = 0; i < pattern.size(); i++) {
                JButton button = pattern.get(i);
                if (button != null) {
                    button.setBackground(new Color(255, 255 - level * 30, 0));
                    resetButtonColors();
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

        if (turn > initialCols * initialRows) {

            precedentScore = score;
            initiateGame();
            generatePattern();
            showPattern();

        } else {

            generatePattern();
            showPattern();

        }
        if (speed > 100)
            speed -= 50;
    }

    @Override
    protected void resetGame() {
        speed = 500;
        level = 0;
        initialCols = 2;
        initialRows = 2;
        score = 0;
        scoreLabel.setText("Score: " + score);
        initiateGame();

        generatePattern();
        showPattern();
        remainingAttemptsLabel.setText("HP: " + health);
        remainingAttemptsLabel.setForeground(Color.WHITE);
    }

}
