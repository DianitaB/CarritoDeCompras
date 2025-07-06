package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;

public class MiJDesktopPane extends JDesktopPane {

    public MiJDesktopPane() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fondo suave
        g.setColor(new Color(230, 245, 255));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Centro de la ventana
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // --- Sombra del carrito ---
        g.setColor(new Color(180, 180, 180, 100)); // gris semitransparente
        g.fillOval(centerX - 90, centerY + 70, 180, 30);

        // --- Canasta del carrito (inclinada) ---
        g.setColor(new Color(100, 100, 100));
        Polygon canasta = new Polygon();
        canasta.addPoint(centerX - 80, centerY);
        canasta.addPoint(centerX + 80, centerY);
        canasta.addPoint(centerX + 60, centerY - 60);
        canasta.addPoint(centerX - 100, centerY - 60);
        g.fillPolygon(canasta);

        // --- Rejilla del carrito ---
        g.setColor(Color.LIGHT_GRAY);
        for (int i = -60; i <= 60; i += 20) {
            g.drawLine(centerX + i, centerY, centerX + i - 20, centerY - 60);
        }

        // --- Ruedas ---
        g.setColor(Color.DARK_GRAY);
        g.fillOval(centerX - 70, centerY + 40, 40, 40);
        g.fillOval(centerX + 30, centerY + 40, 40, 40);

        g.setColor(Color.BLACK);
        g.drawOval(centerX - 70, centerY + 40, 40, 40);
        g.drawOval(centerX + 30, centerY + 40, 40, 40);

        // --- Productos en el carrito ---
        g.setColor(new Color(255, 100, 100));
        g.fill3DRect(centerX - 60, centerY - 85, 30, 30, true);

        g.setColor(new Color(100, 255, 100));
        g.fill3DRect(centerX - 20, centerY - 95, 30, 40, true);

        g.setColor(new Color(100, 150, 255));
        g.fill3DRect(centerX + 20, centerY - 90, 30, 35, true);

        // --- Nombre del sistema (centrado arriba) ---
        String titulo = "DIANITA'S STORE";
        g.setFont(new Font("SansSerif", Font.BOLD, 28));
        g.setColor(new Color(0, 102, 204));
        FontMetrics fm = g.getFontMetrics();
        int textX = centerX - fm.stringWidth(titulo) / 2;
        g.drawString(titulo, textX, centerY - 120);
    }
}
