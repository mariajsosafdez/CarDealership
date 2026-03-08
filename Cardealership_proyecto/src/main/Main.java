package main;
import javax.swing.SwingUtilities;

import modelo.Concesionario;
import vista.*;

public class Main {

	public static void main(String[] args) {
		Concesionario concesionario = new Concesionario("CarDelership");
		
		SwingUtilities.invokeLater(() -> {
            new Ventana(concesionario);
        });
	}

}
