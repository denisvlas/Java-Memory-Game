import javax.swing.*;

import java.awt.event.MouseEvent ;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;

public class StartPage extends JFrame {
    private JTextField nameField;
    private JButton startButton;
    private JPanel contentPane;
    private Color BG = new Color(71, 71, 107);

    public StartPage() {
        setTitle("Welcome to Memory Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 670);
        setLocationRelativeTo(null); // Centrare pe ecran

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); // Utilizarea BoxLayout pentru dispunere verticală
        contentPane.setBorder(BorderFactory.createEmptyBorder(220, 50, 310, 50));
        contentPane.setBackground(BG);
        setContentPane(contentPane);

        JLabel nameLabel = new JLabel("MEMORY GAME");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinierea la centru pe axa X
        contentPane.add(nameLabel);

        contentPane.add(Box.createVerticalGlue()); // Adaugă un spațiu vertical automat între etichetă și câmpul de text

        nameField = new JTextField(30);
        nameField.setMaximumSize(new Dimension(300, 40)); 
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT); 
       
nameField.setForeground(Color.GRAY);

nameField = new JTextField(30);
nameField.setMaximumSize(new Dimension(300, 40)); 
nameField.setAlignmentX(Component.CENTER_ALIGNMENT); 

nameField.setForeground(Color.GRAY);
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

        contentPane.add(Box.createVerticalGlue()); // Adaugă un spațiu vertical automat între câmpul de text și buton

        startButton = new JButton("Enter Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinierea la centru pe axa X
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
        System.out.println(playerName);
        if (playerName.length() < 3 || playerName.equals("Enter your name") ){
            JOptionPane.showMessageDialog(this, "Name must be at least 3 characters long.", "Invalid Name", JOptionPane.ERROR_MESSAGE);
        } else {
            dispose(); // Închide fereastra de start
            new joc(playerName).setVisible(true); // Deschide jocul
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartPage().setVisible(true));
    }
}
