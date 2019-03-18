package lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;


public class TVAnimation extends JPanel implements ActionListener {
    private static int maxWidth;
    private static int maxHeight;

    // the antenna coords
    double points1[][] = {
            { -90, -25 }, { 90, -25 }, { 90, 90 }, {-90, 90}
    };
    // TV box coords
    double points2[][] = {
            { -50, -80 }, { 0, -25 }, { 50, -80 }
    };

    Timer timer;

    // for animation
    private double scale = 1;
    private double delta = 0.01;

    private double tx = 1;
    private double ty = 0;
    private double deltaX = 1;
    private int radius = 200;
    private int radiusExtention = 110;

    public TVAnimation() {
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

         // Set rendering params.
         RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
         g2d.setRenderingHints(rh);

        // Set background color.
        g2d.setBackground(new Color(128, 255, 0));
        g2d.clearRect(0, 0, maxWidth, maxHeight);

        // Set (0;0) to the center to draw main Frame.
        g2d.translate(maxWidth/2, maxHeight/2);

        // Frame.
        BasicStroke bs = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2d.setStroke(bs);
        g2d.drawRect(
                -(radius + radiusExtention),
                -(radius + radiusExtention),
                (radius + radiusExtention)*2,
                (radius + radiusExtention)*2
        );

        // Reset center to default value for the main animation.
        g2d.translate(tx, ty);

        // Transparency
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) scale));

        // Draw antenna
        BasicStroke bs2 = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
        g2d.setStroke(bs2);
        GeneralPath antena = new GeneralPath();
        antena.moveTo(points2[0][0], points2[0][1]);
        for (int k = 1; k < points2.length; k++)
            antena.lineTo(points2[k][0], points2[k][1]);
        g2d.draw(antena);

        // Draw TV box
        g2d.setColor(new Color(255, 166, 0));
        GeneralPath tvbox = new GeneralPath();
        tvbox.moveTo(points1[0][0], points1[0][1]);
        for (int k = 1; k < points1.length; k++)
            tvbox.lineTo(points1[k][0], points1[k][1]);
        tvbox.closePath();
        g2d.fill(tvbox);

        // Draw TV screen
        GradientPaint gp = new GradientPaint(
                25, 50,
                new Color(129, 129, 129),
                60, 5,
                new Color(0, 0, 0),
                true
        );
        g2d.setPaint(gp);
        g2d.fillRoundRect(-80, -15,115,95, 25, 25);

        // Draw three dots near the screen.
        g2d.setColor(new Color(0,0,0));
        g2d.fillRoundRect(65, 25, 8,8, 100, 100);
        g2d.fillRoundRect(65, 45, 8,8, 100, 100);
        g2d.fillRoundRect(65, 65, 8,8, 100, 100);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 750);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new TVAnimation());

        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    public void actionPerformed(ActionEvent e) {
        // scaling
        if (scale < 0.01) {
            delta = -delta;
        } else if (scale > 0.99) {
            delta = -delta;
        }

        // movement
        double radiusInSquare = Math.pow(radius, 2);
        if (tx <= 0 && ty < 0){
            tx -= deltaX;
            ty = -Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx > 0 && ty <= 0){
            tx -= deltaX;
            ty = -Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx >= 0 && ty > 0){
            tx += deltaX;
            ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx < 0 && ty >= 0){
            tx += deltaX;
            ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }

        scale += delta;

        repaint();
    }
}
