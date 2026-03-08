package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.*;
import modelo.persona.*;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class PanelCliente extends JPanel {
	private Concesionario concesionario;

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
	private JTextField txtEmail = new JTextField(10);

	// Labels
	private JLabel lblRegCliente = new JLabel("Registrar un nuevo cliente:");
	private JLabel lblTipoDoc = new JLabel("Tipo de documento: ");
	private String[] opciones = { " ", "CC", "CE", "Pasaporte" };
	private JLabel lblDocumento = new JLabel("Documento:");
	private JLabel lblNombre = new JLabel("Nombre:");
	private JLabel lblApellido = new JLabel("Apellido");
	private JLabel lblTelefono = new JLabel("Teléfono:");
	private JLabel lblEmail = new JLabel("Email:");
	private final JPanel panelBorrar = new JPanel();
	private final JButton btnEliminar = new JButton("Eliminar");

	// Cargar Clientes el arreglo de clientes en concesionario que se cargan desde
	// los ficheros
	public void cargarClientes() {
		for (Cliente c : concesionario.listarClientes()) {
			tabla.addRow(new Object[] { c.getTipoDocumento(), c.getId(), c.getNombre(), c.getApellido(),
					c.getTelefono(), c.getEmail(), c });
		}
	}

	public PanelCliente(Concesionario concesionario) {
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
		fila2.add(lblEmail);
		fila2.add(txtEmail);
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
		tabla.addColumn("Email");

		tablaCuerpo = new JTable(tabla);
		JScrollPane scroll = new JScrollPane(tablaCuerpo);
		add(scroll, BorderLayout.CENTER);
		add(Formulario, BorderLayout.SOUTH);

		// PANEL BORRAR
		add(panelBorrar, BorderLayout.EAST);
		panelBorrar.setLayout(new BorderLayout(0, 0));

		panelBorrar.add(btnEliminar, BorderLayout.SOUTH);
		btnEliminar.addActionListener(e -> {
			if (tablaCuerpo.getSelectedColumn() != -1) {
				int filaEliminar = tablaCuerpo.getSelectedRow();
				String documento = (String) tabla.getValueAt(filaEliminar, 1);
				boolean estadoEliminar = true; // concesionario.eliminarCliente(concesionario.buscarClienteIndex(documento));

				if (estadoEliminar) {
					// Buscar el cliente
					tabla.removeRow(filaEliminar);
					JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
				} else {
					JOptionPane.showMessageDialog(this, "Cliente no encontrado en el sistema");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Seleccione una fila a eliminar");
			}
			tablaCuerpo.clearSelection();

		});

		// REGISTRO DE CLIENTES

		btnRegistrar.addActionListener(e -> {

			String tipoDoc = (String) cbTipoDoc.getSelectedItem();
			String documento = txtDocumento.getText();
			String nombre = txtNombre.getText();
			String apellido = txtApellido.getText();
			String telefono = txtTelefono.getText();
			String email = txtEmail.getText();

			if (!tipoDoc.isBlank() && !documento.isBlank() && !nombre.isBlank() && !apellido.isBlank()
					&& !telefono.isBlank() && !email.isBlank()) {

				Cliente cliente = new Cliente(tipoDoc, documento, nombre, apellido, telefono, email);
				// concesionario.registrarCliente(tipoDoc, documento, nombre, apellido,
				// telefono, email);
				// Cliente c = concesionario.buscarCliente(documento);
				tabla.addRow(new Object[] { tipoDoc, documento, nombre, apellido, telefono, email, cliente });

				cbTipoDoc.setSelectedItem(" ");
				txtDocumento.setText("");
				txtNombre.setText("");
				txtApellido.setText("");
				txtTelefono.setText("");
				txtEmail.setText("");

			} else {
				JOptionPane.showMessageDialog(this, "Complete todos los campos");
			}
		});

	}
}