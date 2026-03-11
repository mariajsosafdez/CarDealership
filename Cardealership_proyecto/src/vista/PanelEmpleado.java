package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.*;
import modelo.persona.*;
import utils.Utils;

public class PanelEmpleado extends JPanel {
	private Concesionario concesionario;

	// cree esto
	private JTable tablaCuerpo;
	private DefaultTableModel tabla = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private JTextField txtDocumento = new JTextField(10);
	private JTextField txtNombre = new JTextField(10);
	private JTextField txtApellido = new JTextField(10);
	private JTextField txtTelefono = new JTextField(10);
	private JTextField txtSalario = new JTextField(10);
	private final JCheckBox chckbxVendedor = new JCheckBox();

	// Labels
	private JLabel lblRegCliente = new JLabel("Registrar un nuevo empleado:");
	private JLabel lblTipoDoc = new JLabel("Tipo de documento: ");
	private String[] opciones = { " ", "CC", "CE", "Pasaporte" };
	private JLabel lblDocumento = new JLabel("Documento:");
	private JLabel lblNombre = new JLabel("Nombre:");
	private JLabel lblApellido = new JLabel("Apellido");
	private JLabel lblTelefono = new JLabel("Teléfono:");
	private JLabel lblSalario = new JLabel("Salario:");
	private JLabel lblVendedor = new JLabel("Vendedor:");
	private final JPanel panelBorrar = new JPanel();
	private final JButton btnEliminar = new JButton("Eliminar");

	// TODO CARGAR EMPLEADOS DESDE LOS FICHEROS
	public void cargarEmpleados() {
		for (Empleado e : concesionario.listarEmpleados()) {
			if (e instanceof Vendedor) {
				Vendedor v = (Vendedor) e;
				tabla.addRow(new Object[] { v.getTipoDocumento(), v.getId(), v.getNombre(), v.getApellido(),
						v.getTelefono(), v.getSalario(), v.getVehiculosVendidos(), v });
			} else {
				tabla.addRow(new Object[] { e.getTipoDocumento(), e.getId(), e.getNombre(), e.getApellido(),
						e.getTelefono(), e.getSalario(), "NA", e });
			}
		}
	}

	// recargar vendedores
	public void actualizarTabla() {

		tabla.setRowCount(0); // limpia la tabla

		for (Empleado e : concesionario.listarEmpleados()) {

			tabla.addRow(
					new Object[] { e.getTipoDocumento(), e.getId(), e.getNombre(), e.getApellido(), e.getTelefono(),
							e.getSalario(), e instanceof Vendedor ? ((Vendedor) e).getVehiculosVendidos() : "NA" });

		}
	}

	public PanelEmpleado(Concesionario concesionario) {
		this.concesionario = concesionario;

		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// FORMULARIO
		JPanel Formulario = new JPanel();
		JPanel fila1 = new JPanel();
		JPanel fila2 = new JPanel();
		JPanel fila3 = new JPanel();

		lblRegCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		Formulario.add(lblRegCliente);

		JButton btnRegistrar = new JButton("Registrar");

		fila1.add(lblTipoDoc);
		JComboBox cbTipoDoc = new JComboBox(opciones);
		fila1.add(cbTipoDoc);

		fila1.add(lblDocumento);
		fila1.add(txtDocumento);

		fila1.add(lblNombre);
		fila1.add(txtNombre);

		fila2.add(lblApellido);
		fila2.add(txtApellido);

		fila2.add(lblTelefono);
		fila2.add(txtTelefono);

		fila2.add(lblSalario);
		fila2.add(txtSalario);

		fila2.add(lblVendedor);
		fila2.add(chckbxVendedor);

		fila3.add(btnRegistrar);
		Formulario.setLayout(new GridLayout(0, 1, 0, 0));

		Formulario.add(fila1);
		Formulario.add(fila2);
		Formulario.add(fila3);

		// TABLA

		tabla.addColumn("Tipo Documento");
		tabla.addColumn("Documento");
		tabla.addColumn("Nombre");
		tabla.addColumn("Apellido");
		tabla.addColumn("Teléfono");
		tabla.addColumn("Salario");
		tabla.addColumn("Vehiculos Vendidos");

		tablaCuerpo = new JTable(tabla);
		JScrollPane scroll = new JScrollPane(tablaCuerpo);
		add(scroll, BorderLayout.CENTER);
		add(Formulario, BorderLayout.SOUTH);

		// PANEL BORRAR
		add(panelBorrar, BorderLayout.EAST);
		panelBorrar.setLayout(new BorderLayout(0, 0));
		panelBorrar.add(btnEliminar, BorderLayout.SOUTH);

		btnEliminar.addActionListener(e -> {
			if (tablaCuerpo.getSelectedRow() != -1) {
				int filaEliminar = tablaCuerpo.getSelectedRow();
				String documento = (String) tabla.getValueAt(filaEliminar, 1);
				boolean estadoEliminar = concesionario
						.eliminarEmpleado((concesionario.buscarEmpleado(documento)).getId());

				// TODO ELIMINAR DEL FICHERO
				if (estadoEliminar) {
					// Buscar el empleado
					tabla.removeRow(filaEliminar);
					Utils.eliminarObjeto("empleado", documento);
					JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente");
				} else {
					JOptionPane.showMessageDialog(this, "Empleado no encontrado en el sistema");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Seleccione una fila a eliminar");
			}
			tablaCuerpo.clearSelection();

		});

		// REGISTRO DE EMPLEADOS

		btnRegistrar.addActionListener(e -> {

			String tipoDoc = (String) cbTipoDoc.getSelectedItem();
			String documento = txtDocumento.getText();
			String nombre = txtNombre.getText();
			String apellido = txtApellido.getText();
			String telefono = txtTelefono.getText();
			boolean isVendedor = chckbxVendedor.isSelected();
			float salario = -1;
			try {

				salario = Float.parseFloat(txtSalario.getText());

			} catch (NumberFormatException ex) {

				JOptionPane.showMessageDialog(this, ex.getMessage());

			}

			if (!tipoDoc.isBlank() && !documento.isBlank() && !nombre.isBlank() && !apellido.isBlank()
					&& !telefono.isBlank() && salario >= 0) {

				// TODO RESOLVER LO DEL TRYCATCH
				// TODO AÑADIR AL FICHERO

				if (isVendedor) {
					try {
						concesionario.registrarVendedor(tipoDoc, documento, nombre, apellido, telefono, salario);
						Empleado v = concesionario.buscarEmpleado(documento);
						tabla.addRow(new Object[] { tipoDoc, documento, nombre, apellido, telefono, salario, 0, v });
					} catch (ValidacionException ex) {
						JOptionPane.showMessageDialog(this, ex.getMessage());
					}

				} else {

					try {
						concesionario.registrarEmpleado(tipoDoc, documento, nombre, apellido, telefono, salario);
						Empleado em = concesionario.buscarEmpleado(documento);
						tabla.addRow(
								new Object[] { tipoDoc, documento, nombre, apellido, telefono, salario, "NA", em });
					} catch (ValidacionException ex) {
						JOptionPane.showMessageDialog(this, ex.getMessage());
					}

				}

				cbTipoDoc.setSelectedIndex(0);
				txtDocumento.setText("");
				txtNombre.setText("");
				txtApellido.setText("");
				txtTelefono.setText("");
				txtSalario.setText("");
				chckbxVendedor.setSelected(false);

			} else {
				JOptionPane.showMessageDialog(this, "Complete todos los campos correctamente");
			}
		});

		cargarEmpleados();
	}
}