package ModernLogin.ItemCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemCard extends JPanel {
    private String itemName;
    private String price;
    private boolean isHovered = false;
    
    // MATCHING COLORS FROM YOUR LOGIN
    private Color blue = new Color(52, 143, 235);
    private Color purple = new Color(110, 72, 170);

    public ItemCard(String name, String price) {
        this.itemName = name;
        this.price = price;
        setPreferredSize(new Dimension(180, 240));
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { isHovered = true; repaint(); }
            public void mouseExited(MouseEvent e) { isHovered = false; repaint(); }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. SHADOW / BORDER (Changes on hover)
        if (isHovered) {
            g2d.setColor(new Color(200, 200, 200, 100));
            g2d.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, 25, 25);
        }

        // 2. MAIN CARD BODY
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);

        // 3. PRODUCT IMAGE PLACEHOLDER (Matches login style)
        g2d.setColor(new Color(245, 245, 245));
        g2d.fillRoundRect(15, 15, getWidth()-30, 120, 15, 15);
        
        // 4. ITEM NAME (Matches Title Font style)
        g2d.setColor(new Color(50, 50, 50));
        g2d.setFont(new Font("SansSerif", Font.BOLD, 15));
        g2d.drawString(itemName, 15, 160);

        // 5. PRICE BADGE (Uses your Login Gradient)
        GradientPaint gp = new GradientPaint(0, 0, blue, getWidth(), 0, purple);
        g2d.setPaint(gp);
        g2d.fillRoundRect(15, 185, 70, 30, 10, 10);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 13));
        g2d.drawString("$" + price, 25, 205);

        g2d.dispose();
    }


    public static void main(String[] args) {
          SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Item Card Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.getContentPane().setBackground(new Color(240, 240, 240));
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

            frame.add(new ItemCard("Mechanical Keyboard", "99.99"));
            frame.add(new ItemCard("Gaming Mouse", "49.99"));

            frame.setVisible(true);
        });
         
    }
}
