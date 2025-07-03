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

        g.setColor(new Color(204, 198, 255));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);

        // Dibuja la aleta izquierda
        g.drawLine(150, 200, 50, 100);
        g.drawLine(50, 100, 150, 50);

        // Dibuja la aleta derecha
        g.drawLine(150, 200, 250, 100);
        g.drawLine(250, 100, 150, 50);

        // Dibuja la curva inferior de la aleta derecha
        g.drawArc(120, 150, 100, 100, 0, -180); // Aproximación con un arco

        // Dibuja la línea que conecta la parte inferior de la "V" con la aleta curva
        g.drawLine(150, 200, 170, 220); // Ajusta estas coordenadas para la conexión

        // Dibuja la forma de la aleta curva
        // Esto es una simplificación, para la forma exacta se necesitarían más líneas o Path2D
        g.drawLine(170, 220, 140, 250);
        g.drawLine(140, 250, 100, 230);
    }
}