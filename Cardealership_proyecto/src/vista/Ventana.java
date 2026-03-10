package vista;

import java.awt.*;
import javax.swing.*;

import modelo.*;
import modelo.persona.*;
import modelo.vehiculo.*;
import vistaVehiculo.PanelVehiculo;

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

		JLabel header = new JLabel("Gerente Concesionario", SwingConstants.CENTER);
		header.setFont(new Font("Arial", Font.BOLD, 22));
		header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
		add(header, BorderLayout.NORTH);

		// MENU LATERAL
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

		// PANEL CENTRAL 
		cardLayout = new CardLayout();
		panelCentral = new JPanel(cardLayout);
		
		panelCentral.add(new PanelInicio(), "INICIO");
		panelCentral.add(new PanelCliente(concesionario), "CLIENTES");
		PanelEmpleado panelEmpleado = new PanelEmpleado(concesionario);
		panelCentral.add(panelEmpleado, "EMPLEADOS");
		panelCentral.add(new PanelVehiculo(concesionario), "VEHICULOS");
		PanelVenta panelVenta = new PanelVenta(concesionario, panelEmpleado);
		panelCentral.add(panelVenta, "VENTAS");

		add(panelCentral, BorderLayout.CENTER);
		
		btnClientes.addActionListener(e -> cardLayout.show(panelCentral, "CLIENTES"));
		btnEmpleados.addActionListener(e -> cardLayout.show(panelCentral, "EMPLEADOS"));
		btnVehiculos.addActionListener(e -> cardLayout.show(panelCentral, "VEHICULOS"));
		btnVentas.addActionListener(e -> {
			cardLayout.show(panelCentral, "VENTAS");
			panelVenta.actualizarVehiculos();
		});
		
		setVisible(true);
	}


}
