package vista;

import java.awt.*;
import javax.swing.*;

import modelo.*;
import modelo.persona.*;
import modelo.vehiculo.*;

public class Ventana extends JFrame {
	private Concesionario concesionario; //Necesita tener acceso a los métodos de concesionario
	private CardLayout cardLayout;
	private JPanel panelCentral;

	public Ventana(Concesionario concesionario) {
		this.concesionario = concesionario;
		setTitle("Sistema Gerente - Concesionario");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// =========================
		// HEADER
		// =========================
		JLabel header = new JLabel("Sistema de Gestión - Gerente Concesionario", SwingConstants.CENTER);
		header.setFont(new Font("Arial", Font.BOLD, 22));
		header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
		add(header, BorderLayout.NORTH);

		// =========================
		// MENÚ LATERAL
		// =========================
		JPanel menuLateral = new JPanel();
		menuLateral.setLayout(new GridLayout(4, 1, 10, 10));
		menuLateral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JButton btnClientes = new JButton("Clientes");
		JButton btnEmpleados = new JButton("Empleados");
		JButton btnVehiculos = new JButton("Vehículos");
		JButton btnVentas = new JButton("Ventas");

		menuLateral.add(btnClientes);
		menuLateral.add(btnEmpleados);
		menuLateral.add(btnVehiculos);
		menuLateral.add(btnVentas);

		add(menuLateral, BorderLayout.WEST);

		// =========================
		// PANEL CENTRAL (CardLayout)
		// =========================
		cardLayout = new CardLayout();
		panelCentral = new JPanel(cardLayout);

		panelCentral.add(new PanelCliente(concesionario), "CLIENTES");
		panelCentral.add(new PanelEmpleado(concesionario), "EMPLEADOS");
		panelCentral.add(crearPanelDummy("Gestión de Vehículos"), "VEHICULOS");
		panelCentral.add(crearPanelDummy("Gestión de Ventas"), "VENTAS");

		add(panelCentral, BorderLayout.CENTER);

		// =========================
		// EVENTOS
		// =========================
		btnClientes.addActionListener(e -> cardLayout.show(panelCentral, "CLIENTES"));
		btnEmpleados.addActionListener(e -> cardLayout.show(panelCentral, "EMPLEADOS"));
		btnVehiculos.addActionListener(e -> cardLayout.show(panelCentral, "VEHICULOS"));
		btnVentas.addActionListener(e -> cardLayout.show(panelCentral, "VENTAS"));
		
		
		setVisible(true);
	}

	// Método para crear paneles temporales
	private JPanel crearPanelDummy(String texto) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(texto, SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		panel.add(label, BorderLayout.CENTER);
		return panel;
	}

}
