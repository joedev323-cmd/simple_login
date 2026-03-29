import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemCard extends JPanel {
    private String itemName;
    private String price;
    private Image productImg;  // image is stored here
    private boolean isHovered = false;
    
    private Color blue = new Color(52, 143, 235);
    private Color purple = new Color(110, 72, 170);

    public ItemCard(String name, String price, String imageName) {
        this.itemName = name;
        this.price = price;
        
        //loading the images from the dir. here  you are not limited to type just change to desired eg png.
        java.net.URL imgURL = getClass().getResource("/images/" + imageName + ".jpg"); 
        if (imgURL != null) {
            this.productImg = new ImageIcon(imgURL).getImage();
        }

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

        // 1. SHADOW / HOVER EFFECT
        if (isHovered) {
            g2d.setColor(new Color(0, 0, 0, 30));
            g2d.fillRoundRect(3, 3, getWidth()-6, getHeight()-6, 25, 25);
        }

        // 2. MAIN CARD BODY
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);

        // 3. PRODUCT IMAGE RENDERING
        g2d.setColor(new Color(245, 245, 245));
        g2d.fillRoundRect(15, 15, getWidth()-30, 120, 15, 15);
        
        if (productImg != null) {
            // Draw image scaled to fit the placeholder
            g2d.drawImage(productImg, 25, 25, getWidth()-50, 100, this);
        } else {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawString("No Image", 60, 80);
        }

        // 4. ITEM NAME
        g2d.setColor(new Color(50, 50, 50));
        g2d.setFont(new Font("SansSerif", Font.BOLD, 15));
        g2d.drawString(itemName, 15, 160);

        // 5. PRICE BADGE
        GradientPaint gp = new GradientPaint(0, 0, blue, getWidth(), 0, purple);    
        g2d.setPaint(gp);     
        g2d.fillRoundRect(15, 185, 90, 30, 10, 10);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 13));
        g2d.drawString("Ksh " + price, 25, 205);

        g2d.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Shop Preview");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(650, 350);
            frame.getContentPane().setBackground(new Color(230, 230, 235));
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));

            // Ensure images like "keyboard.jpeg" exist in your /images/ folder
            frame.add(new ItemCard("Mechanical Keyboard", "900", "keyboard"));
            frame.add(new ItemCard("Gaming Mouse", "1,000", "mouse"));
            frame.add(new ItemCard("Gaming Mat", "1,200", "mat"));
            frame.add(new ItemCard("monitor","3,500","monitor"));

            frame.setVisible(true);
        });
    }
}
