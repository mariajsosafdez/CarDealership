package vistaVehiculo;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.*;
import modelo.vehiculo.*;
import utils.Utils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

public class PanelVehiculo extends JPanel {
	private Concesionario concesionario;

	private JTable tablaCuerpoL;
	private DefaultTableModel tablaL = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable tablaCuerpoR;
	private DefaultTableModel tablaR = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private final JPanel panelBtn = new JPanel();
	private final JButton btnEliminar = new JButton("Eliminar");
	private final JButton btnDetalle = new JButton("Detalles");

	// labels
	private final JLabel lbltipos = new JLabel("Seleccione el vehiculo a registrar");

	public PanelVehiculo(Concesionario concesionario) {
		this.concesionario = concesionario;
		
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// FORMULARIO
		JPanel Formulario = new JPanel();
		JPanel fila1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) fila1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		String[] tipoVehiculo = { "AUTO", "MOTO" };
		JComboBox tipos = new JComboBox(tipoVehiculo);

		CardLayout layoutCampos = new CardLayout();
		JPanel panelCampos = new JPanel(layoutCampos);
		panelCampos.add(new FormAuto(concesionario, tablaL), "AUTO");
		panelCampos.add(new FormMoto(concesionario, tablaR), "MOTO");

		// seleccion para cambio de form
		tipos.addActionListener(e -> {

			String tipo = (String) tipos.getSelectedItem();

			if (tipo.equals("AUTO")) {
				layoutCampos.show(panelCampos, "AUTO");
			} else {
				layoutCampos.show(panelCampos, "MOTO");
			}
		});
		Formulario.setLayout(new BorderLayout(0, 0));
		lbltipos.setFont(new Font("Tahoma", Font.BOLD, 14));
		fila1.add(lbltipos);
		fila1.add(tipos);
		// Formulario.add(fila1);
		Formulario.add(fila1, BorderLayout.NORTH);
		Formulario.add(panelCampos);

		add(Formulario, BorderLayout.SOUTH);

		// TABLA

		tablaL.addColumn("Placa");
		tablaL.addColumn("Marca");
		tablaL.addColumn("Modelo");
		tablaL.addColumn("Año");
		tablaL.addColumn("Precio");

		tablaR.addColumn("Placa");
		tablaR.addColumn("Marca");
		tablaR.addColumn("Modelo");
		tablaR.addColumn("Año");
		tablaR.addColumn("Precio");

		tablaCuerpoL = new JTable(tablaL);
		tablaCuerpoR = new JTable(tablaR);

		JPanel panelInfo = new JPanel();
		add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(new GridLayout(0, 2, 5, 0));
		JScrollPane scrollL = new JScrollPane(tablaCuerpoL);
		panelInfo.add(scrollL);
		JScrollPane scrollR = new JScrollPane(tablaCuerpoR);
		panelInfo.add(scrollR);

		// PANEL LATERAL

		add(panelBtn, BorderLayout.EAST);
		panelBtn.setLayout(new BorderLayout(0, 0));

		panelBtn.add(btnEliminar, BorderLayout.SOUTH);
		panelBtn.add(btnDetalle, BorderLayout.NORTH);

		// ELIMINAR REGISTRO
		btnEliminar.addActionListener(e -> {

			if (tablaCuerpoL.getSelectedRow() == -1 && tablaCuerpoR.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Seleccione una fila a eliminar");
			} else if (tablaCuerpoL.getSelectedRow() != -1 && tablaCuerpoR.getSelectedRow() == -1) {
				eliminarVehiculo(tablaCuerpoL, tablaL, "auto");
			} else if (tablaCuerpoR.getSelectedRow() != -1 && tablaCuerpoL.getSelectedRow() == -1) {
				eliminarVehiculo(tablaCuerpoR, tablaR, "moto");
			} else {
				eliminarVehiculo(tablaCuerpoL, tablaL, "auto");
				eliminarVehiculo(tablaCuerpoR, tablaR, "moto");
			}

			tablaCuerpoL.clearSelection();
			tablaCuerpoR.clearSelection();
		});
		// DETALLE VEHICULO
		btnDetalle.addActionListener(e ->

		{
			if (tablaCuerpoL.getSelectedRow() == -1 && tablaCuerpoR.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Seleccione una vehiculo");
			} else if (tablaCuerpoL.getSelectedRow() != -1 && tablaCuerpoR.getSelectedRow() == -1) {
				mostrarDetalles(tablaCuerpoL, tablaL);
			} else if (tablaCuerpoR.getSelectedRow() != -1 && tablaCuerpoL.getSelectedRow() == -1) {
				mostrarDetalles(tablaCuerpoR, tablaR);
			} else {
				JOptionPane.showMessageDialog(this, "Solo seleccione un vehiculo a la vez");
				tablaCuerpoL.clearSelection();
				tablaCuerpoR.clearSelection();
			}
		});

	}

	public void eliminarVehiculo(JTable tablaCuerpo, DefaultTableModel tabla, String tipo) {
		int filaEliminar = tablaCuerpo.getSelectedRow();
		String placa = (String) tabla.getValueAt(filaEliminar, 0);
		boolean estadoEliminar = concesionario.eliminarVehiculo((concesionario.buscarVehiculos(placa)).getPlaca());
		// TODO ELIMINAR DEL FICHERO
		if (estadoEliminar) {
			tabla.removeRow(filaEliminar);
			Utils.eliminarObjeto(tipo, placa);
			JOptionPane.showMessageDialog(this, "Vehiculo eliminado correctamente");
		} else {
			JOptionPane.showMessageDialog(this, "Vehiculo no encontrado en el sistema");
		}
	}

	public void mostrarDetalles(JTable tablaCuerpo, DefaultTableModel tabla) {
		int filadetalle = tablaCuerpo.getSelectedRow();
		String placa = (String) tabla.getValueAt(filadetalle, 0);
		Vehiculo v = concesionario.buscarVehiculos(placa);
		if (v != null) {
			JOptionPane.showMessageDialog(this, v);
		} else {
			JOptionPane.showMessageDialog(this, "Vehiculo no encontrado en el sistema");
		}
		tablaCuerpo.clearSelection();
	}
}
