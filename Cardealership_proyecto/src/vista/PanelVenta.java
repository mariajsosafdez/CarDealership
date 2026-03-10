package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Concesionario;
import modelo.persona.Cliente;
import modelo.persona.ValidacionException;
import modelo.persona.Vendedor;
import modelo.vehiculo.Vehiculo;
import modelo.venta.InvalidVentaException;
import modelo.venta.Venta;
import utils.Utils;

public class PanelVenta extends JPanel {

	private Concesionario concesionario;
	private PanelEmpleado panelEmpleado;

	private JTable tablaCuerpo;
	private DefaultTableModel tabla = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private JTextField txtCliente = new JTextField(10);
	private JTextField txtVendedor = new JTextField(10);

	// Labels
	private JLabel lblVenta = new JLabel("Registrar una nueva venta:");
	private JLabel lblCliente = new JLabel("Documento del cliente ");
	private JLabel lblVendedor = new JLabel("Documento del vendedor:");
	private JLabel lblVehiculo = new JLabel("Vehiculo:");
	private JComboBox<String> cbVehiculos;
	private final JPanel panelBtn = new JPanel();
	private final JButton btnEliminar = new JButton("Eliminar");
	private final JButton btnDetalle = new JButton("Detalle");
	private final JButton btnAgregar = new JButton("Agregar \nVehiculo");
	private Venta ventaActual = null;
	private JLabel lblMetodoPago = new JLabel("Método de pago:");
	private JComboBox<String> cbMetodoPago =
	        new JComboBox<>(new String[]{"Efectivo","Tarjeta","Transferencia","Financiado"});

	// TODO CARGAR LAS VENTAS DESDE EL FICHERO
	public void cargarVentas() {
		for (Venta v : concesionario.listarVentas()) {
			tabla.addRow(new Object[] { v.getCodigo(), v.getFecha(), v.getCliente().getId(), v.getVendedor().getId(),
					v.getTotal() });
		}
	}

	// Actualizar vehiculos registrados
	public void actualizarVehiculos() {

		cbVehiculos.removeAllItems();

		for (Vehiculo v : concesionario.listarVehiculos()) {
			cbVehiculos.addItem(v.getPlaca() + " " + v.getMarca() + " " + v.getModelo());
		}
	}

	public PanelVenta(Concesionario concesionario, PanelEmpleado panelEmpleado) {
		this.concesionario = concesionario;
		this.panelEmpleado = panelEmpleado;

		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		cbVehiculos = new JComboBox<>();
		actualizarVehiculos();

		// FORMULARIO
		JPanel Formulario = new JPanel();
		JPanel fila1 = new JPanel();
		JPanel fila2 = new JPanel();
		JPanel fila3 = new JPanel();

		lblVenta.setFont(new Font("Tahoma", Font.BOLD, 15));
		Formulario.add(lblVenta);

		JButton btnRegistrar = new JButton("Registrar");

		fila1.add(lblCliente);
		fila1.add(txtCliente);
		fila1.add(lblVendedor);
		fila1.add(txtVendedor);
		fila2.add(lblVehiculo);
		fila2.add(cbVehiculos);
		fila2.add(lblMetodoPago);
		fila2.add(cbMetodoPago);

		fila3.add(btnRegistrar);
		Formulario.setLayout(new GridLayout(0, 1, 0, 0));

		Formulario.add(fila1);
		Formulario.add(fila2);
		Formulario.add(fila3);

		// TABLA

		tabla.addColumn("Codigo");
		tabla.addColumn("Fecha");
		tabla.addColumn("Cliente");
		tabla.addColumn("Vendedor");
		tabla.addColumn("Total");
		

		tablaCuerpo = new JTable(tabla);
		JScrollPane scroll = new JScrollPane(tablaCuerpo);

		// AÑADIR TABLA Y FORMULARIO

		add(scroll, BorderLayout.CENTER);
		add(Formulario, BorderLayout.SOUTH);

		// PANEL BORRAR TODO
		add(panelBtn, BorderLayout.EAST);
		panelBtn.setLayout(new BorderLayout(0, 0));

		panelBtn.add(btnEliminar, BorderLayout.SOUTH);
		panelBtn.add(btnDetalle, BorderLayout.NORTH);
		panelBtn.add(btnAgregar, BorderLayout.CENTER);

		// Eliminar registro
		btnEliminar.addActionListener(e -> {
			if (tablaCuerpo.getSelectedRow() != -1) {
				int filaEliminar = tablaCuerpo.getSelectedRow();
				String codigo = (String) tabla.getValueAt(filaEliminar, 0);
				boolean estadoEliminar = concesionario.eliminarVenta(codigo);
				// TODO ELIMINAR DEL FICHERO
				if (estadoEliminar) {
					tabla.removeRow(filaEliminar);
					Utils.eliminarObjeto("venta", codigo);
					JOptionPane.showMessageDialog(this, "Venta eliminada correctamente");
				} else {
					JOptionPane.showMessageDialog(this, "Venta no encontrada en el sistema");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Seleccione una fila a eliminar");
			}
			tablaCuerpo.clearSelection();

		});
		// detalle compra
		btnDetalle.addActionListener(e ->

		{
			if (tablaCuerpo.getSelectedRow() != -1) {
				int filadetalle = tablaCuerpo.getSelectedRow();
				String codigo = (String) tabla.getValueAt(filadetalle, 0);
				Venta v = concesionario.buscarVenta(codigo);
				if (v != null) {
					JOptionPane.showMessageDialog(this, v);
				} else {
					JOptionPane.showMessageDialog(this, "Venta no encontrada en el sistema");
				}
				tablaCuerpo.clearSelection();
			} else {
				JOptionPane.showMessageDialog(this, "Seleccione una venta para mostrar los detalles");
			}
			tablaCuerpo.clearSelection();
		});
		//Añadir vehiculo
		btnAgregar.addActionListener(e -> {

		    if (ventaActual == null) {
		        JOptionPane.showMessageDialog(this, "Primero debe registrar una venta.");
		        return;
		    }

		    String tipoVehiculo = (String) cbVehiculos.getSelectedItem();

		    if (tipoVehiculo == null) {
		        JOptionPane.showMessageDialog(this, "Seleccione un vehículo");
		        return;
		    }

		    String placa = tipoVehiculo.split(" ")[0];

		    Vehiculo v = concesionario.buscarVehiculos(placa);

		    try {

		        ventaActual.agregarVehiculo(v);

		        JOptionPane.showMessageDialog(this,
		                "Vehículo agregado a la venta\nTotal actual: $" + ventaActual.getTotal());

		        // actualizar tabla
		        int fila = tablaCuerpo.getRowCount() - 1;

		        tabla.setValueAt(ventaActual.getTotal(), fila, 4);

		    } catch (InvalidVentaException ex) {

		        JOptionPane.showMessageDialog(this, ex.getMessage());

		    }
		});

		// REGISTRO DE VENTAS

		btnRegistrar.addActionListener(e -> {

		    String tipoVehiculo = (String) cbVehiculos.getSelectedItem();
		    String placa = tipoVehiculo.split(" ")[0];
		    String clienteDoc = txtCliente.getText();
		    String vendedorDoc = txtVendedor.getText();
		    String metodoPago = (String) cbMetodoPago.getSelectedItem();

		    if (!placa.isBlank() && !clienteDoc.isBlank() && !vendedorDoc.isBlank()
		            && concesionario.buscarEmpleado(vendedorDoc) instanceof Vendedor) {

		        try {

		            ventaActual = concesionario.venderVehiculo(
		                    concesionario.buscarVehiculos(placa),
		                    concesionario.buscarCliente(clienteDoc),
		                    (Vendedor) concesionario.buscarEmpleado(vendedorDoc)
		            );

		            Utils.guardarObjeto(ventaActual);
		            ventaActual.getVendedor().registrarVenta(ventaActual);

		            panelEmpleado.actualizarTabla();

		            tabla.addRow(new Object[]{
		                    ventaActual.getCodigo(),
		                    ventaActual.getFecha(),
		                    ventaActual.getCliente().getId(),
		                    ventaActual.getVendedor().getId(),
		                    ventaActual.getTotal(),
		                    metodoPago
		            });
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(this, ex.getMessage());
		        }

		    } else {
		        JOptionPane.showMessageDialog(this, "Complete todos los campos correctamente");
		    }
		});

	}

}
