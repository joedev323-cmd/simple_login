 

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ModernLogin {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 750);
        frame.setLocationRelativeTo(null);

        // 1. MAIN PANEL: Blue to Purple Gradient Background
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Gradient from top-left (Blue) to bottom-right (Purple)
                GradientPaint gp = new GradientPaint(0, 0, new Color(52, 143, 235), getWidth(), getHeight(), new Color(110, 72, 170));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(new GridBagLayout());

        // 2. THE LOGIN CARD: White Rounded Rectangle
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(380, 550));
        card.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 40, 15, 40); // Internal padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // --- TITLE ---
        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 40, 40, 40); // More space below title
        card.add(title, gbc);

        // --- Username FIELD ---
        JTextField emailField = new JTextField("USername");
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        emailField.setForeground(Color.LIGHT_GRAY);
        // Only bottom border like the image
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        emailField.setPreferredSize(new Dimension(280, 40));
        
        emailField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("USername")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                    emailField.setForeground(Color.LIGHT_GRAY);
                    emailField.setText("USername");
                }
            }
        });
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 40, 10, 40);
        card.add(emailField, gbc);

        // --- PASSWORD FIELD ---
        JPasswordField passField = new JPasswordField("Password");
        passField.setEchoChar((char) 0); // Show text initially for placeholder
        passField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passField.setForeground(Color.LIGHT_GRAY);
        passField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        passField.setPreferredSize(new Dimension(280, 40));

        passField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                String pass = new String(passField.getPassword());
                if (pass.equals("Password")) {
                    passField.setText("");
                    passField.setEchoChar('●'); // Start hiding characters
                    passField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                String pass = new String(passField.getPassword());
                if (pass.isEmpty()) {
                    passField.setEchoChar((char) 0); // Show "Password" text
                    passField.setForeground(Color.LIGHT_GRAY);
                    passField.setText("Password");
                }
            }
        });
        gbc.gridy = 2;
        card.add(passField, gbc);

        // --- GRADIENT BUTTON ---
        JButton loginBtn = new JButton("Login") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(52, 143, 235), getWidth(), 0, new Color(110, 72, 170));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        loginBtn.setContentAreaFilled(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setFocusPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        gbc.gridy = 3;
        gbc.ipady = 20; // Height of button
        gbc.insets = new Insets(40, 40, 10, 40);
        card.add(loginBtn, gbc);

        // --- FOOTER TEXT ---
        // JLabel footer = new JLabel("<html><center><font color='gray'>Forgot </font><font color='#348feb'><b>Password?</b></font><br><br><font color='gray'>Don't have an account? </font><font color='#348feb'><b>Sign up</b></font></center></html>", SwingConstants.CENTER);
        // footer.setFont(new Font("SansSerif", Font.PLAIN, 13));
        // gbc.gridy = 4;
        // gbc.ipady = 0;
        // gbc.insets = new Insets(20, 40, 20, 40);
        // card.add(footer, gbc);
        /*am leaving this footer out for now */
        // ASSEMBLE
        background.add(card);
        frame.add(background);
        frame.setVisible(true);
    }
}
