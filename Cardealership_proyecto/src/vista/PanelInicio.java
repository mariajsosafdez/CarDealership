package vista;

import java.awt.*;
import javax.swing.*;

public class PanelInicio extends JPanel {

    public PanelInicio() {

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Sistema de Concesionario", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));

        add(titulo, BorderLayout.CENTER);
    }
}